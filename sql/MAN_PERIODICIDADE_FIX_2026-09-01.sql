/* ============================================================================
   CORRECAO DA PERIODICIDADE DAS MANUTENCOES PREVENTIVAS
   Data: 2026-09-01
   ----------------------------------------------------------------------------
   PROBLEMAS CORRIGIDOS

   1) Proxima data com o dia da semana ERRADO (ex.: plano com "Ter" preferencial
      a devolver 2026-09-14 = Segunda-feira).
      Causa: dbo.GET_PROXIMA_DATA usa DATEPART(WEEKDAY/dw) e @@DATEFIRST, que
      dependem da sessao. A sessao do job/WildFly corre em us_english
      (@@DATEFIRST = 7); o ecra e a simulacao correm com SET DATEFIRST 1.
      Prova: com DATEFIRST 7 -> 2026-09-14 (Segunda); com DATEFIRST 1 -> 2026-09-15.
      Correcao: GET_PROXIMA_DATA passa a calcular o dia da semana de forma
      independente de DATEFIRST (1=Seg ... 7=Dom, ancorado em 1900-01-01).

   2) Datas diferentes em ecras diferentes (14/09 na tabela vs 22/09 na simulacao).
      Causa: existiam varios algoritmos:
        - MAN_SIMULAR_PERIODICIDADE  -> ancora PURA em DATA_INICIO (correcto)
        - MAN_ATUALIZA_PROXIMA_DATA  -> ancora PURA em DATA_INICIO (correcto)
        - MAN_CRIAR_MANUTENCOES_PREVENTIVAS -> GET_PROXIMA_DATA(DATA_ULTIMA_REALIZADA)
          com DATA_ULTIMA_REALIZADA = GETDATE(), ou seja re-ancora na data em que o
          JOB correu -> a serie desliza e perde o dia de referencia (dia 22).
        - MAN_GET_MANUTENCOES_SEMANAS (mapa/gantt) -> re-ancorava na data ja
          ajustada ao dia da semana -> deslize no mensal/anual.
      Correcao: todos passam a usar a ancora DATA_INICIO. O job passa a chamar
      MAN_ATUALIZA_PROXIMA_DATA e a gravar DATA_ULTIMA_REALIZADA = data PLANEADA.

   3) Manutencoes marcadas "em atraso" no 1o dia da semana corrente.
      Causa: MAN_GET_ANALISE_PREDITIVAS classificava ESTADO_ATRASO = 'A' para
      qualquer registo da semana ACTUAL (previstas: ISO_WEEK <= actual) e para
      qualquer manutencao criada e ainda sem DATA_FIM.
      Correcao: atraso apenas a partir da semana SEGUINTE a semana planeada.

   ORDEM DE EXECUCAO: 1 -> 2 -> 3 -> 4 (o passo 5 e opcional: recalculo em massa).
   ============================================================================ */

USE SGIID;
GO

/* ---------------------------------------------------------------- 1 de 4
   dbo.GET_PROXIMA_DATA  -  independente de DATEFIRST
   ---------------------------------------------------------------------- */
ALTER FUNCTION [dbo].[GET_PROXIMA_DATA] (
    @data_inicio      datetime,
    @intervalo        int,
    @tipo_repeticao   varchar(1),
    @dias_semana      varchar(max)
)
RETURNS date
AS
BEGIN
    DECLARE @data_proxima datetime;
    DECLARE @data_ultima  datetime = @data_inicio;
    DECLARE @data         datetime = @data_inicio;
    DECLARE @NextDay      int;
    DECLARE @dow          int;   -- 1=Seg ... 7=Dom, independente de DATEFIRST
    DECLARE @mon          date;  -- Segunda-feira da semana de @data

    DECLARE @t table (
        ID      int     not null,
        Weekday char(3) not null,
        XFlag   bit     not null
    );

    -- 1900-01-01 foi uma Segunda-feira: ancora fixa para o dia da semana
    SET @dow = (DATEDIFF(day, '19000101', CAST(@data AS date)) % 7) + 1;

    IF @tipo_repeticao = 1
    BEGIN
        SET @data_proxima = DATEADD(DAY, @intervalo, @data_ultima);
    END

    ELSE IF @tipo_repeticao = 2
    BEGIN
        IF @dias_semana IS NULL OR @dias_semana = ''
        BEGIN
            SET @data_proxima = DATEADD(WEEK, @intervalo, @data_ultima);
        END
        ELSE
        BEGIN
            INSERT INTO @t(ID, WeekDay, XFlag)
            VALUES (1,'Seg',0),(2,'Ter',0),(3,'Qua',0),
                   (4,'Qui',0),(5,'Sex',0),(6,'Sab',0),(7,'Dom',0);

            UPDATE t SET XFlag = 1
            FROM @t t
            INNER JOIN (SELECT value FROM STRING_SPLIT(@dias_semana, ',')) a
                ON t.ID = TRY_CAST(a.value AS int);

            SET @mon = DATEADD(day, 1 - @dow, CAST(@data AS date));

            -- proximo dia configurado DEPOIS do dia actual (nao igual)
            SET @NextDay = (SELECT MIN(ID) FROM @t WHERE XFlag = 1 AND ID > @dow);

            IF @NextDay IS NOT NULL
                SET @data_proxima = DATEADD(day, @NextDay - 1, @mon);
            ELSE
            BEGIN
                -- voltou ao inicio da semana -> avanca @intervalo semanas
                SET @NextDay = (SELECT MIN(ID) FROM @t WHERE XFlag = 1);
                SET @data_proxima = DATEADD(day, @NextDay - 1,
                                        DATEADD(week, @intervalo, @mon));
            END
        END
    END

    ELSE IF @tipo_repeticao = 3
    BEGIN
        SET @data_proxima = DATEADD(MONTH, @intervalo, @data_ultima);

        IF @dias_semana IS NOT NULL AND @dias_semana <> ''
           AND TRY_CAST(@dias_semana AS int) BETWEEN 1 AND 7
        BEGIN
            -- ajusta para o dia da semana preferido DENTRO da mesma semana Seg-Dom
            SET @data_proxima = DATEADD(DAY,
                TRY_CAST(@dias_semana AS int)
                  - ((DATEDIFF(day,'19000101',CAST(@data_proxima AS date)) % 7) + 1),
                @data_proxima);
        END
    END

    ELSE IF @tipo_repeticao = 4
    BEGIN
        SET @data_proxima = DATEADD(YEAR, @intervalo, @data_ultima);

        IF @dias_semana IS NOT NULL AND @dias_semana <> ''
           AND TRY_CAST(@dias_semana AS int) BETWEEN 1 AND 7
        BEGIN
            SET @data_proxima = DATEADD(DAY,
                TRY_CAST(@dias_semana AS int)
                  - ((DATEDIFF(day,'19000101',CAST(@data_proxima AS date)) % 7) + 1),
                @data_proxima);
        END
    END

    RETURN CAST(@data_proxima AS date);
END;
GO

/* ---------------------------------------------------------------- 2 de 4
   dbo.MAN_CRIAR_MANUTENCOES_PREVENTIVAS
   - SET DATEFIRST 1
   - DATA_ULTIMA_REALIZADA = data PLANEADA (nao GETDATE())
   - DATA_PROXIMA_REALIZADA via MAN_ATUALIZA_PROXIMA_DATA (ancora DATA_INICIO)
   ---------------------------------------------------------------------- */
-- =============================================
-- MAN_CRIAR_MANUTENCOES_PREVENTIVAS (TIPO = 'P')
-- =============================================
ALTER PROCEDURE [dbo].[MAN_CRIAR_MANUTENCOES_PREVENTIVAS]
AS
BEGIN
    SET NOCOUNT ON;
    SET DATEFIRST 1;
    DECLARE @TPLANOS table (ID int);

    DECLARE @TIDS table (ID_MANUTENCAO_CAB int)
    DECLARE @TDATA table (
        rownumber int, 
        DATA_PROXIMA_REALIZADA datetime,
        TIPO_RESPONSAVEL varchar(2),
        ID_EQUIPA int,
        ID_MANUTENCAO int,
        NIVEL int
    )
    DECLARE @id_novo int
    DECLARE @PractitionerId int

    -- Planos a processar
    INSERT INTO @TDATA
    SELECT 
        ROW_NUMBER() OVER(ORDER BY DATA_PROXIMA_REALIZADA ASC),
        DATA_PROXIMA_REALIZADA,
        TIPO_RESPONSAVEL,
        CASE WHEN TIPO_RESPONSAVEL = 'U' THEN a.UTILIZADOR ELSE ID_EQUIPA END,
        a.ID_MANUTENCAO,
        NIVEL
    FROM MAN_MOV_MANUTENCAO_PLANOS a
    LEFT JOIN MAN_MOV_MANUTENCAO_EQUIPAMENTOS b ON a.ID_MANUTENCAO = b.ID_MANUTENCAO
    WHERE DATA_PROXIMA_REALIZADA <= GETDATE() 
      AND DATA_PROXIMA_REALIZADA != ISNULL(DATA_ULTIMA_REALIZADA, 0) 
      AND DATA_PROXIMA_REALIZADA IS NOT NULL
      AND b.ATIVO = 1 
      AND ISNULL(b.OBSOLETO, 0) = 0
      AND ISNULL(a.TIPO_MANUTENCAO, 'P') = 'P'
    GROUP BY DATA_PROXIMA_REALIZADA, TIPO_RESPONSAVEL,
             CASE WHEN TIPO_RESPONSAVEL = 'U' THEN a.UTILIZADOR ELSE ID_EQUIPA END,
             a.ID_MANUTENCAO, a.NIVEL

    -- CURSOR1 mantém-se (necessário para @@IDENTITY por INSERT)
    DECLARE MY_CURSOR CURSOR LOCAL STATIC READ_ONLY FORWARD_ONLY
    FOR SELECT DISTINCT rownumber FROM @TDATA

    OPEN MY_CURSOR
    FETCH NEXT FROM MY_CURSOR INTO @PractitionerId
    WHILE @@FETCH_STATUS = 0
    BEGIN
        INSERT INTO [dbo].[MAN_MOV_MANUTENCAO_CAB]
            ([LOCALIZACAO],[TIPO_LOCALIZACAO],[EQUIPAMENTO],[ESTADO],[TIPO_MANUTENCAO],
             [UTZ_CRIA],[DATA_CRIA],TIPO_RESPONSAVEL,UTILIZADOR,DATA_REALIZACAO,
             RESPONSAVEL_PEDIDO,AMBITO_MANUTENCAO,COD_FORNECEDOR,NOME_FORNECEDOR,
             EMAIL_FORNECEDOR,NIVEL)
        SELECT 
            LOCALIZACAO,TIPO_LOCALIZACAO,b.ID_MANUTENCAO,
            CASE WHEN TIPO_RESPONSAVEL = 'EX' THEN 'PE' ELSE 'P' END,
            'P',1,GETDATE(),TIPO_RESPONSAVEL,ID_EQUIPA,
            a.DATA_PROXIMA_REALIZADA,1,AMBITO_MANUTENCAO,
            COD_FORNECEDOR,NOME_FORNECEDOR,EMAIL_FORNECEDOR,a.NIVEL
        FROM @TDATA a
        INNER JOIN MAN_MOV_MANUTENCAO_EQUIPAMENTOS b ON a.ID_MANUTENCAO = b.ID_MANUTENCAO
        WHERE rownumber = @PractitionerId

        SET @id_novo = SCOPE_IDENTITY()
        INSERT INTO @TIDS SELECT @id_novo

        INSERT INTO MAN_MOV_MANUTENCAO_ACCOES
            (ID_MANUTENCAO_CAB,ID_ACAO,[UTZ_CRIA],[DATA_CRIA],REALIZADA,
             TEMPO_ESTIMADO,REPETIR,TIPO_REPETICAO,DATA_INICIO)
        SELECT 
            @id_novo,b.ID_ACAO,1,GETDATE(),0,
            TEMPO_ESTIMADO,REPETIR,TIPO_REPETICAO,DATA_INICIO
        FROM @TDATA a
        INNER JOIN MAN_MOV_MANUTENCAO_PLANOS b ON 
            ISNULL(a.DATA_PROXIMA_REALIZADA,'1900-01-01') = ISNULL(b.DATA_PROXIMA_REALIZADA,'1900-01-01')
            AND ISNULL(a.TIPO_RESPONSAVEL,'null') = ISNULL(b.TIPO_RESPONSAVEL,'null')
            AND ISNULL(a.ID_EQUIPA,0) = CASE WHEN b.TIPO_RESPONSAVEL = 'U' THEN ISNULL(b.UTILIZADOR,0) ELSE ISNULL(b.ID_EQUIPA,0) END
            AND a.ID_MANUTENCAO = b.ID_MANUTENCAO
        WHERE rownumber = @PractitionerId

        FETCH NEXT FROM MY_CURSOR INTO @PractitionerId
    END
    CLOSE MY_CURSOR
    DEALLOCATE MY_CURSOR

    -- UPDATE DATA_ULTIMA_REALIZADA em massa (fora do cursor)
    -- Guarda os planos processados ANTES de mexer nas datas
    INSERT INTO @TPLANOS (ID)
    SELECT DISTINCT p.ID
    FROM MAN_MOV_MANUTENCAO_PLANOS p
    INNER JOIN @TDATA a ON
        ISNULL(a.DATA_PROXIMA_REALIZADA,'1900-01-01') = ISNULL(p.DATA_PROXIMA_REALIZADA,'1900-01-01')
        AND ISNULL(a.TIPO_RESPONSAVEL,'null') = ISNULL(p.TIPO_RESPONSAVEL,'null')
        AND ISNULL(a.ID_EQUIPA,0) = CASE WHEN p.TIPO_RESPONSAVEL = 'U' THEN ISNULL(p.UTILIZADOR,0) ELSE ISNULL(p.ID_EQUIPA,0) END
        AND a.ID_MANUTENCAO = p.ID_MANUTENCAO
    WHERE ISNULL(p.TIPO_MANUTENCAO,'P') = 'P'

    -- CORRECAO: a ultima realizada e a data PLANEADA, nao a data em que o job correu
    UPDATE p SET DATA_ULTIMA_REALIZADA = CAST(a.DATA_PROXIMA_REALIZADA AS date)
    FROM @TDATA a
    INNER JOIN MAN_MOV_MANUTENCAO_PLANOS p ON 
        ISNULL(a.DATA_PROXIMA_REALIZADA,'1900-01-01') = ISNULL(p.DATA_PROXIMA_REALIZADA,'1900-01-01')
        AND ISNULL(a.TIPO_RESPONSAVEL,'null') = ISNULL(p.TIPO_RESPONSAVEL,'null')
        AND ISNULL(a.ID_EQUIPA,0) = CASE WHEN p.TIPO_RESPONSAVEL = 'U' THEN ISNULL(p.UTILIZADOR,0) ELSE ISNULL(p.ID_EQUIPA,0) END
        AND a.ID_MANUTENCAO = p.ID_MANUTENCAO

    -- CORRECAO: recalcular DATA_PROXIMA_REALIZADA com a MESMA logica do ecra e da
    -- simulacao (ancorada em DATA_INICIO), em vez de GET_PROXIMA_DATA(DATA_ULTIMA).
    UPDATE p SET TOTAL_OCORRENCIAS = ISNULL(p.TOTAL_OCORRENCIAS,0) + 1
    FROM MAN_MOV_MANUTENCAO_PLANOS p
    INNER JOIN @TPLANOS x ON x.ID = p.ID

    DECLARE @pid int
    WHILE EXISTS (SELECT 1 FROM @TPLANOS)
    BEGIN
        SELECT TOP 1 @pid = ID FROM @TPLANOS
        EXEC dbo.MAN_ATUALIZA_PROXIMA_DATA @pid
        DELETE FROM @TPLANOS WHERE ID = @pid
    END











    -- Marca como NE os registos duplicados de dias anteriores
    UPDATE [MAN_MOV_MANUTENCAO_CAB] SET ESTADO = 'NE', DATA_ULT_MODIF = GETDATE(), UTZ_ULT_MODIF = 1
    WHERE ID_MANUTENCAO_CAB IN (
        SELECT a.ID_MANUTENCAO_CAB FROM (
            SELECT a.ID_MANUTENCAO_CAB,
                (SELECT STRING_AGG(xb.ID_ACAO,',') WITHIN GROUP (ORDER BY ID_ACAO ASC)
                 FROM MAN_MOV_MANUTENCAO_ACCOES xb WHERE xb.ID_MANUTENCAO_CAB = a.ID_MANUTENCAO_CAB) AS ACCOES
            FROM [MAN_MOV_MANUTENCAO_CAB] a
            WHERE ISNULL(TIPO_MANUTENCAO,'P') = 'P'
              AND ESTADO = CASE WHEN a.TIPO_RESPONSAVEL = 'EX' THEN 'PE' ELSE 'P' END
              AND CAST(a.DATA_CRIA AS date) < CAST(GETDATE() AS date)
              AND a.EQUIPAMENTO IN (
                  SELECT xa.EQUIPAMENTO FROM [MAN_MOV_MANUTENCAO_CAB] xa
                  WHERE CAST(xa.DATA_CRIA AS date) = CAST(GETDATE() AS date)
                  GROUP BY xa.EQUIPAMENTO)
        ) a
        INNER JOIN (
            SELECT a.ID_MANUTENCAO_CAB,
                (SELECT STRING_AGG(xb.ID_ACAO,',') WITHIN GROUP (ORDER BY ID_ACAO ASC)
                 FROM MAN_MOV_MANUTENCAO_ACCOES xb WHERE xb.ID_MANUTENCAO_CAB = a.ID_MANUTENCAO_CAB) AS ACCOES
            FROM [MAN_MOV_MANUTENCAO_CAB] a
            WHERE ISNULL(TIPO_MANUTENCAO,'P') = 'P'
              AND ESTADO = CASE WHEN a.TIPO_RESPONSAVEL = 'EX' THEN 'PE' ELSE 'P' END
              AND CAST(a.DATA_CRIA AS date) = CAST(GETDATE() AS date)
        ) b ON a.ACCOES = b.ACCOES
        GROUP BY a.ID_MANUTENCAO_CAB
    )

    -- Chamada HTTP para alertas de stock
    DECLARE @Object int
    DECLARE @ResponseText varchar(8000)
    DECLARE @IDS varchar(MAX) = (SELECT STRING_AGG(ID_MANUTENCAO_CAB, ',') FROM @TIDS)

    IF @IDS IS NOT NULL BEGIN
        DECLARE @Body varchar(MAX) = '[{"IDS": "' + @IDS + '"}]'
        EXEC sp_OACreate 'MSXML2.XMLHTTP', @Object OUT
        EXEC sp_OAMethod @Object, 'open', NULL, 'post',
            'http://192.168.40.101:8080/sgiid/rest/sirb/getAlertasStockManutencao', 'false'
        EXEC sp_OAMethod @Object, 'setRequestHeader', null, 'Content-Type', 'application/json'
        EXEC sp_OAMethod @Object, 'send', null, @Body
        EXEC sp_OAMethod @Object, 'responseText', @ResponseText OUTPUT
        SELECT @ResponseText
        EXEC sp_OADestroy @Object
    END


	 IF @IDS IS NOT NULL BEGIN
        DECLARE @ObjectP int
        DECLARE @ResponseTextP varchar(8000)
        DECLARE @BodyP varchar(MAX) = '[{"IDS": "' + @IDS + '"}]'
        EXEC sp_OACreate 'MSXML2.XMLHTTP', @ObjectP OUT
        EXEC sp_OAMethod @ObjectP, 'open', NULL, 'post',
            'http://192.168.40.101:8080/sgiid/rest/sirb/getAlertasManutencoesPreventivas', 'false'
        EXEC sp_OAMethod @ObjectP, 'setRequestHeader', null, 'Content-Type', 'application/json'
        EXEC sp_OAMethod @ObjectP, 'send', null, @BodyP
        EXEC sp_OAMethod @ObjectP, 'responseText', @ResponseTextP OUTPUT
        EXEC sp_OADestroy @ObjectP
    END
END

GO

/* ---------------------------------------------------------------- 3 de 4
   dbo.MAN_GET_MANUTENCOES_SEMANAS (mapa/gantt e popup "Acoes Futuras Previstas")
   - SET DATEFIRST 1 no topo (estava DEPOIS da 1a chamada a GET_PROXIMA_DATA)
   - mensal/anual: ancora PURA em DATA_INICIO (nao na data ja ajustada)
   ---------------------------------------------------------------------- */


-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
ALTER PROCEDURE [dbo].[MAN_GET_MANUTENCOES_SEMANAS]
	@ANO int,
	@LOCALIZACAO varchar(10),
	@AMBITO int,
	@EQUIPAMENTO int,
	@DEPARTAMENTO int,
	@NIVEL int,
	@TIPO varchar(25) = 'P',
	@NIVEL_EQUIPAMENTO varchar(MAX) = null,
	@EQUIPA varchar(MAX) = null
AS
BEGIN
	SET DATEFIRST 1;
	DECLARE @TDATA table (rownumber int, DATA_PROXIMA_REALIZADA datetime,TIPO_RESPONSAVEL varchar (2),ID_EQUIPA int,ID_MANUTENCAO int,ID_MANUTENCAO_LINHA int,OBSOLETO bit,DATA_OBSOLETO datetime,TEMPO_ESTIMADO decimal(10,2),id_planos varchar(max))
	DECLARE @TDATA2 table ( id int,DATA date, tipo varchar(25),OBSOLETO bit,DATA_OBSOLETO datetime,TEMPO_ESTIMADO decimal(10,2),id_planos varchar(max));
	DECLARE @data_proxima datetime,
		@data_inicio datetime,
		@data_final datetime ,
		@tipo_fim varchar(1),
		@repetir int,
		@ocorrencias int,
		@total_ocorrencias int,
		@id_manutencao int,
		@tipo_repeticao varchar(1),
		@dias_semana varchar(max),
		@OBSOLETO bit,
		@DATA_OBSOLETO datetime,@TEMPO_ESTIMADO decimal(10,2),@id_planos varchar(max)

 
 	DECLARE @PractitionerId int 

	insert into @TDATA
	select ROW_NUMBER()  OVER(ORDER BY DATA_PROXIMA_REALIZADA ASC) ,DATA_PROXIMA_REALIZADA,TIPO_RESPONSAVEL,CASE WHEN TIPO_RESPONSAVEL = 'U' THEN a.UTILIZADOR ELSE ID_EQUIPA END,a.ID_MANUTENCAO 
	,MAX(a.ID),ISNULL(b.OBSOLETO,0),DATA_OBSOLETO,SUM(ISNULL(DATEDIFF(MINUTE,'00:00:00',a.TEMPO_ESTIMADO),0)) / 60.0  as TEMPO_ESTIMADO
	,STRING_AGG(a.ID,',') ID_PLANOS
	from  MAN_MOV_MANUTENCAO_PLANOS a
	left join MAN_MOV_MANUTENCAO_EQUIPAMENTOS b on a.ID_MANUTENCAO = b.ID_MANUTENCAO
	left join (select CONCAT('E',ID)  as ID,DESCRICAO,UNIDADE 
		from MAN_DIC_EDIFICIOS 	
			union all 
		select CONCAT('P',a.ID) as ID,b.DESCRICAO + '/' + a.DESCRICAO  ,UNIDADE
		from MAN_DIC_PISOS a 
		inner join MAN_DIC_EDIFICIOS b on a.ID_EDIFICIO = b.ID 
		union all 	
		select CONCAT('D' , a.ID)  as ID,c.DESCRICAO + '/' + b.DESCRICAO+ '/' + a.DESCRICAO ,UNIDADE from MAN_DIC_DIVISOES a 
		inner join MAN_DIC_PISOS b on a.ID_PISO = b.ID inner join MAN_DIC_EDIFICIOS c on b.ID_EDIFICIO = c.ID ) e on e.ID = CONCAT(ISNULL(b.TIPO_LOCALIZACAO,'E'), b.LOCALIZACAO) 
	left join MAN_MOV_MANUTENCAO_GRAUS_IMPORTANCIA c on c.ID_MANUTENCAO = a.ID_MANUTENCAO
	where DATA_INICIO <= CONCAT(@ANO,'-12-31')
	and (b.ATIVO = 1 or (ISNULL(b.OBSOLETO,0) = 1 AND b.ATIVO = 0)) and ((@LOCALIZACAO is null) or (e.ID = @LOCALIZACAO))
	and ((@AMBITO is null) or (b.AMBITO_MANUTENCAO = @AMBITO))
	and ((@EQUIPAMENTO is null) or (b.ID_MANUTENCAO = @EQUIPAMENTO))
	and ((@DEPARTAMENTO is null) or (c.ID_DEPARTAMENTO = @DEPARTAMENTO))
	and ((@NIVEL is null) or (c.NIVEL = @NIVEL))
	and ((@NIVEL_EQUIPAMENTO is null) or (a.NIVEL = @NIVEL_EQUIPAMENTO))
	and ((@EQUIPA is null) or (a.ID_EQUIPA in (SELECT value FROM STRING_SPLIT(@EQUIPA, ',')) and a.TIPO_RESPONSAVEL = 'E'))

	and a.TIPO_MANUTENCAO = @TIPO
	group by DATA_PROXIMA_REALIZADA,TIPO_RESPONSAVEL,CASE WHEN TIPO_RESPONSAVEL = 'U' THEN a.UTILIZADOR ELSE ID_EQUIPA END,a.ID_MANUTENCAO,ISNULL(b.OBSOLETO,0),DATA_OBSOLETO

	
	DECLARE MY_CURSOR CURSOR 
		LOCAL STATIC READ_ONLY FORWARD_ONLY
	FOR 

	select  DISTINCT rownumber from @TDATA a  

	OPEN MY_CURSOR
	FETCH NEXT FROM MY_CURSOR INTO @PractitionerId
	WHILE @@FETCH_STATUS = 0
	BEGIN 


		select @data_proxima = b.DATA_PROXIMA_REALIZADA,@data_inicio = CAST(b.DATA_INICIO AS DATETIME) 
		,@data_final= b.DATA_FINAL,@tipo_fim = b.TIPO_FIM,@total_ocorrencias =  ISNULL(b.TOTAL_OCORRENCIAS,0)
		,@repetir = b.REPETIR,@ocorrencias = ISNULL(b.OCORRENCIAS,0),
		@tipo_repeticao = b.TIPO_REPETICAO,@dias_semana = b.DIAS_SEMANA,
		@id_manutencao = b.ID_MANUTENCAO
		from MAN_MOV_MANUTENCAO_PLANOS  b  
		where b.id = (select ID_MANUTENCAO_LINHA from @TDATA where rownumber = @PractitionerId ) and b.TIPO_MANUTENCAO = @TIPO

		 (select @OBSOLETO = OBSOLETO,@DATA_OBSOLETO= DATA_OBSOLETO,@TEMPO_ESTIMADO=TEMPO_ESTIMADO,@id_planos=id_planos from @TDATA where rownumber = @PractitionerId )

		INSERT INTO @TDATA2
		select @id_manutencao,@data_inicio,CASE WHEN @tipo_repeticao= 2 THEN 'W' 
			WHEN @tipo_repeticao= 3 THEN 
			CASE WHEN @repetir = 1 THEN 'M' WHEN @repetir = 2 THEN 'B' WHEN @repetir = 3 THEN 'T' WHEN @repetir = 4 THEN 'Q' WHEN @repetir = 6 THEN 'S' ELSE
			'M' END  WHEN @tipo_repeticao = 4 THEN 'A' WHEN @tipo_repeticao= 1 THEN 'D' ELSE '' END ,@OBSOLETO,@DATA_OBSOLETO,@TEMPO_ESTIMADO,@id_planos

		IF @data_inicio < CAST( GETDATE() as date)  BEGIN
			SET @data_proxima = dbo.GET_PROXIMA_DATA (@data_inicio,@repetir,@tipo_repeticao,@dias_semana) 
		END ELSE BEGIN
			SET @data_proxima = @data_inicio;
		END


		-- (DATEFIRST movido para o topo do procedimento)
		WHILE CAST(@data_proxima AS DATE) <=  CONCAT(@ANO,'-12-31') BEGIN 
	
			SET @data_proxima = dbo.GET_PROXIMA_DATA (@data_inicio,@repetir,@tipo_repeticao,@dias_semana) 
			INSERT INTO @TDATA2
			select @id_manutencao,@data_proxima,CASE WHEN @tipo_repeticao= 2 THEN 'W' 
			WHEN @tipo_repeticao= 3 THEN 
			CASE WHEN @repetir = 1 THEN 'M' WHEN @repetir = 2 THEN 'B' WHEN @repetir = 3 THEN 'T' WHEN @repetir = 4 THEN 'Q' WHEN @repetir = 6 THEN 'S' ELSE
			'M' END  WHEN @tipo_repeticao = 4 THEN 'A' WHEN @tipo_repeticao= 1 THEN 'D' ELSE '' END ,@OBSOLETO,@DATA_OBSOLETO ,@TEMPO_ESTIMADO,@id_planos
			SET @data_inicio = CASE @tipo_repeticao WHEN '3' THEN DATEADD(MONTH,@repetir,@data_inicio) WHEN '4' THEN DATEADD(YEAR,@repetir,@data_inicio) ELSE @data_proxima END
		
		END;
		

		FETCH NEXT FROM MY_CURSOR INTO @PractitionerId
	END
	CLOSE MY_CURSOR
	DEALLOCATE MY_CURSOR

	SELECT ID,DATA,SEMANA,ANO,MES,tipo,SUM(TEMPO_ESTIMADO) TEMPO_ESTIMADO,STRING_AGG(id_planos,',') id_planos FROM (
		select ID,DATA
		,DATEPART(ISO_WEEK,DATA) as SEMANA
		,DATEPART(YEAR,DATA) as ANO
		,DATEPART(MONTH,DATA) as MES	,tipo,TEMPO_ESTIMADO ,id_planos
		from @TDATA2 where DATA <= CONCAT(@ANO,'-12-31') AND DATA >= CONCAT(@ANO,'-01-01')
		AND CASE WHEN OBSOLETO = 1 THEN DATA ELSE CAST(GETDATE() as date) END <=  CASE WHEN OBSOLETO = 1 THEN DATA_OBSOLETO ELSE CAST(GETDATE() as date) END
	)taba
	where NOT EXISTS(select x.ID_EQUIPAMENTO from MAN_MOV_MANUTENCAO_EQUIPAMENTOS_INATIVIDADE x where taba.DATA between  CAST(x.DATA_INATIVO as date) and CAST(x.DATA_ATIVO as date) and x.ID_EQUIPAMENTO = taba.id )
	GROUP by ID,SEMANA,ANO,MES,DATA,tipo
	ORDER BY DATA
END




GO

/* ---------------------------------------------------------------- 4 de 4
   dbo.MAN_GET_ANALISE_PREDITIVAS
   - atraso apenas a partir da semana SEGUINTE (a semana corrente e "no prazo")
   ---------------------------------------------------------------------- */
ALTER PROCEDURE [dbo].[MAN_GET_ANALISE_PREDITIVAS]
    @ANO int,
    @LOCALIZACAO varchar(10),
    @AMBITO int,
    @EQUIPAMENTO int,
    @DEPARTAMENTO int,
    @NIVEL int,
    @SEMANAS_FILTRO varchar(MAX),
    @DATA_INICIO varchar(MAX),
    @DATA_FIM varchar(MAX),
    @NIVEL_EQUIPAMENTO varchar(MAX),
    @EQUIPA varchar(MAX)
AS
BEGIN

    IF OBJECT_ID('tempdb.dbo.#TEMP_DATA') IS NOT NULL DROP TABLE #TEMP_DATA
    CREATE TABLE #TEMP_DATA (id int, DATA date, SEMANA int, ANO int, MES int, tipo varchar(25), TEMPO_PLANEADO decimal(10,2), id_planos varchar(max));
    INSERT INTO #TEMP_DATA
    EXEC MAN_GET_MANUTENCOES_SEMANAS @ANO,@LOCALIZACAO,@AMBITO,@EQUIPAMENTO,@DEPARTAMENTO,@NIVEL,'PR',@NIVEL_EQUIPAMENTO,@EQUIPA

    IF OBJECT_ID('tempdb.dbo.#TEMP_2') IS NOT NULL DROP TABLE #TEMP_2
    CREATE TABLE #TEMP_2
    (
        SEMANA varchar(max),
        SEMANA_NUM int,
        EQUIPAMENTO varchar(max),
        ID_EQUIPAMENTO int,
        LOCALIZACAO varchar(max),
        DESCRICAO varchar(max),
        PREVISTA int,
        REALIZADA int,
        FORA_PRAZO int,
        TEMPO_PLANEADO decimal(10,2),
        TEMPO_REAL decimal(10,2)
    )

    DECLARE @SEMANAS TABLE (SEMANA int, ANO int, MES int)

    IF OBJECT_ID('tempdb.dbo.#SEMANAS_SHOW') IS NOT NULL DROP TABLE #SEMANAS_SHOW
    CREATE TABLE #SEMANAS_SHOW (SEMANA varchar(25), ANO int, MES int)

    SET DATEFIRST 1;
    INSERT INTO @SEMANAS
    SELECT SEMANA,ANO,MES FROM (
        SELECT
        DATEPART(ISO_WEEK,DATEADD(wk,t2.number,CAST(@ANO as varchar(15)))) as SEMANA
        ,DATEPART(YEAR,DATEADD(wk,t2.number,CAST(@ANO as varchar(15)))) as ANO
        ,DATEPART(MONTH,DATEADD(wk,t2.number,CAST(@ANO as varchar(15)))) as MES
        FROM master..spt_values t2
        WHERE t2.type = 'P' AND t2.number <= 255
        AND YEAR(DATEADD(wk,t2.number,CAST(@ANO as varchar(15))))=@ANO
    ) tabg
    WHERE (SEMANA < 50 AND MES = 1) OR (MES != 1)
    INSERT INTO @SEMANAS SELECT 53,@ANO,12

    IF @DATA_INICIO is not null AND @DATA_FIM is not null BEGIN
        WITH mycte AS (
            SELECT DATEADD(ww, DATEDIFF(ww,0,CAST(@DATA_INICIO AS DATETIME)), 0) DateValue
            UNION ALL
            SELECT DateValue + 7 FROM mycte WHERE DateValue + 7 < @DATA_FIM
        )
        INSERT INTO #SEMANAS_SHOW
        SELECT CONCAT('WEEK ',DATEPART(iso_week, DateValue)), DATEPART(year, DateValue), DATEPART(MONTH, DateValue)
        FROM mycte OPTION (MAXRECURSION 0);
    END ELSE IF @SEMANAS_FILTRO is not null BEGIN
        INSERT INTO #SEMANAS_SHOW
        SELECT CONCAT('WEEK ',value),@ANO,DATEPART(mm,DATEADD(wk, DATEDIFF(wk, 7, '1/1/' + CONVERT(varchar(4),@ANO)) + (value), 7))
        FROM STRING_SPLIT(@SEMANAS_FILTRO, ',') ORDER BY CAST(value as int)
    END ELSE BEGIN
        INSERT INTO #SEMANAS_SHOW
        SELECT CONCAT('WEEK ',SEMANA),@ANO,MES FROM @SEMANAS
    END

    DELETE FROM #TEMP_DATA WHERE DATA <= CAST(GETDATE() as date)

    IF OBJECT_ID('tempdb.dbo.#TEMP_MAN_MOV_MANUTENCAO_CAB') IS NOT NULL DROP TABLE #TEMP_MAN_MOV_MANUTENCAO_CAB
    CREATE TABLE #TEMP_MAN_MOV_MANUTENCAO_CAB
    (ID_MANUTENCAO_CAB int, EQUIPAMENTO int, DATA_CRIA datetime, DATA_FIM datetime, ESTADO varchar(3), tipo_manutencao varchar(3), TEMPO_ESTIMADO decimal(10,2), TEMPO_REAL decimal(10,2))

    ;WITH LOC AS (
        SELECT CONCAT('E',ID) AS ID, DESCRICAO, UNIDADE FROM MAN_DIC_EDIFICIOS
        UNION ALL
        SELECT CONCAT('P',a.ID), b.DESCRICAO + '/' + a.DESCRICAO, UNIDADE
        FROM MAN_DIC_PISOS a JOIN MAN_DIC_EDIFICIOS b ON a.ID_EDIFICIO = b.ID
        UNION ALL
        SELECT CONCAT('D',a.ID), c.DESCRICAO + '/' + b.DESCRICAO + '/' + a.DESCRICAO, UNIDADE
        FROM MAN_DIC_DIVISOES a
        JOIN MAN_DIC_PISOS b ON a.ID_PISO = b.ID
        JOIN MAN_DIC_EDIFICIOS c ON b.ID_EDIFICIO = c.ID
    )

    INSERT INTO #TEMP_MAN_MOV_MANUTENCAO_CAB
    SELECT
        x.ID_MANUTENCAO_CAB,
        x.EQUIPAMENTO,
        x.DATA_CRIA,
        x.DATA_FIM,
        x.ESTADO,
        CASE mp.TIPO_REPETICAO
            WHEN 2 THEN 'W'
            WHEN 4 THEN 'A'
            WHEN 1 THEN 'D'
            WHEN 3 THEN
                CASE mp.REPETIR
                    WHEN 1 THEN 'M'
                    WHEN 2 THEN 'B'
                    WHEN 3 THEN 'T'
                    WHEN 4 THEN 'Q'
                    WHEN 6 THEN 'S'
                    ELSE 'M'
                END
            ELSE ''
        END AS tipo_manutencao,
        ISNULL(DATEDIFF(MINUTE, '00:00:00', mp.TEMPO_ESTIMADO), 0) / 60.0 AS TEMPO_ESTIMADO,
        ISNULL(DATEDIFF(MINUTE, '00:00:00', mp.TEMPO), 0) / 60.0 AS TEMPO_REAL
    FROM MAN_MOV_MANUTENCAO_CAB x
    LEFT JOIN MAN_MOV_MANUTENCAO_EQUIPAMENTOS b ON x.EQUIPAMENTO = b.ID_MANUTENCAO
    LEFT JOIN MAN_MOV_MANUTENCAO_ACCOES mp ON mp.ID_MANUTENCAO_CAB = x.ID_MANUTENCAO_CAB
    LEFT JOIN LOC e ON e.ID = CONCAT(ISNULL(b.TIPO_LOCALIZACAO,'E'), b.LOCALIZACAO)
    LEFT JOIN MAN_MOV_MANUTENCAO_GRAUS_IMPORTANCIA c ON c.ID_MANUTENCAO = x.EQUIPAMENTO
    WHERE TIPO_MANUTENCAO = 'PR'
    AND YEAR(x.DATA_CRIA) = @ANO
    AND ((@DATA_INICIO is null) OR (CAST(x.DATA_CRIA as date) >= @DATA_INICIO))
    AND ((@DATA_FIM is null) OR (CAST(x.DATA_CRIA as date) <= @DATA_FIM))
    AND (b.ATIVO=1 OR (ISNULL(b.OBSOLETO,0)=1 AND b.ATIVO=0))
    AND ((@LOCALIZACAO is null) OR (e.ID=@LOCALIZACAO))
    AND ((@AMBITO is null) OR (b.AMBITO_MANUTENCAO=@AMBITO))
    AND ((@EQUIPAMENTO is null) OR (b.ID_MANUTENCAO=@EQUIPAMENTO))
    AND ((@DEPARTAMENTO is null) OR (c.ID_DEPARTAMENTO=@DEPARTAMENTO))
    AND ((@NIVEL is null) OR (c.NIVEL=@NIVEL))
    AND ((@NIVEL_EQUIPAMENTO is null) OR (x.NIVEL=@NIVEL_EQUIPAMENTO))
    AND ((@EQUIPA is null) OR (x.UTILIZADOR in (SELECT value FROM STRING_SPLIT(@EQUIPA, ',')) AND x.TIPO_RESPONSAVEL='E'))

    ;WITH TT AS (
        SELECT
            ID_MANUTENCAO_CAB,
            SUM(DATEDIFF(SECOND, '00:00:00', TRY_CONVERT(TIME, TEMP_EXEC)) / 60.0 ) / 60.0 AS TEMPO_EXEC
        FROM MAN_MOV_MANUTENCAO_OPERARIOS
        WHERE ESTADO = 'C'
        GROUP BY ID_MANUTENCAO_CAB
    )
    INSERT INTO #TEMP_2
    SELECT DISTINCT
        CONCAT('WEEK ',tabi.SEMANA),
        tabi.SEMANA,
        c.NOME,
        c.ID_MANUTENCAO,
        e.DESCRICAO,
        tabh.ids,
        tabh.PREVISTA,
        tabh.REALIZADA,
        tabh.FORA_PRAZO,
        ISNULL(tempo_tab.TEMPO_PLANEADO, tabh.TEMPO_PLANEADO),
        ISNULL(tempo_tab.TEMPO_REAL, 0)
    FROM @SEMANAS tabi
    LEFT JOIN (
        SELECT id, SEMANA, ANO, MES,
        STRING_AGG(
            CAST(CONCAT(
                CASE WHEN ESTADO_ATRASO='A' THEN 'red' ELSE 'green' END,'::',tipo,'::',
                ISNULL(CAST(ID_MANUTENCAO_CAB as varchar(250)),'P'),'::',
                CAST(SEMANA_CONCLUSAO as varchar(250)),'::',
                CAST(SEMANA_PREVISTA as varchar(250)),'::',id_planos,'|'
            ) AS NVARCHAR(MAX)), N''
        ) AS ids,
        SUM(1) as PREVISTA,
        SUM(CASE WHEN SEMANA_CONCLUSAO!='' AND SEMANA_CONCLUSAO!=0 AND ESTADO_ATRASO in ('C','A') THEN 1 ELSE 0 END) as REALIZADA,
        SUM(CASE WHEN SEMANA_CONCLUSAO!='' AND SEMANA_CONCLUSAO!=0 AND ESTADO_ATRASO='A' THEN 1 ELSE 0 END) as FORA_PRAZO,
        SUM(TEMPO_PLANEADO) TEMPO_PLANEADO,
        STRING_AGG(id_planos,',') id_planos
        FROM (
            SELECT id,
            CASE WHEN SEMANA <> SEMANA_PREVISTA AND SEMANA_CONCLUSAO=0 THEN ISNULL(SEMANA_PREVISTA,SEMANA) ELSE SEMANA END as SEMANA,
            ANO, MES, ID_MANUTENCAO_CAB, ESTADO_ATRASO, tipo,
            ISNULL(SEMANA_CONCLUSAO,'') SEMANA_CONCLUSAO,
            ISNULL(SEMANA_PREVISTA,'') SEMANA_PREVISTA,
            SUM(ISNULL(TEMPO_PLANEADO,0)) TEMPO_PLANEADO,
            STRING_AGG(id_planos,',') id_planos
            FROM (
                -- previstas futuras (sem execução correspondente)
                SELECT a.DATA,null DATA_CRIA,null DATA_FIM,a.id,SEMANA,ANO,MES,null ID_MANUTENCAO_CAB,null ESTADO,
                CASE WHEN (DATEPART(YEAR,DATA)>DATEPART(YEAR,GETDATE())) OR (DATEPART(YEAR,DATA)=DATEPART(YEAR,GETDATE()) AND DATEPART(ISO_WEEK,DATA)>=DATEPART(ISO_WEEK,GETDATE())) THEN 'P'
                ELSE 'A'
                END ESTADO_ATRASO, a.tipo, null SEMANA_PREVISTA, null SEMANA_CONCLUSAO, a.TEMPO_PLANEADO, id_planos
                FROM #TEMP_DATA a
                LEFT JOIN #TEMP_MAN_MOV_MANUTENCAO_CAB b ON a.id=b.EQUIPAMENTO AND a.tipo=b.tipo_manutencao
                AND (CAST(a.DATA as date) BETWEEN CAST(DATEADD(DAY,CASE WHEN tipo='D' THEN -1 ELSE -5 END,b.DATA_CRIA) as date) AND CAST(DATEADD(DAY,1,b.DATA_CRIA) as date))
                WHERE ((@DATA_INICIO is null) OR (cast(a.DATA as date)>=@DATA_INICIO)) AND ((@DATA_FIM is null) OR (a.DATA<=@DATA_FIM))
                AND b.EQUIPAMENTO is null
                AND YEAR(DATEADD(DAY, 4-DATEPART(WEEKDAY,a.DATA), a.DATA))=@ANO

                UNION

                -- manutenções em curso
                SELECT DATA_CRIA,DATA_CRIA,DATA_FIM,EQUIPAMENTO,
                DATEPART(ISO_WEEK,DATA_CRIA),DATEPART(YEAR,DATA_CRIA),DATEPART(MONTH,DATA_CRIA),
                ID_MANUTENCAO_CAB,ESTADO,'C',tipo_manutencao,
                DATEPART(ISO_WEEK,DATA_CRIA),DATEPART(ISO_WEEK,DATA_FIM),TEMPO_ESTIMADO,null id_planos
                FROM #TEMP_MAN_MOV_MANUTENCAO_CAB
                WHERE DATA_FIM IS NOT NULL
                AND DATEPART(ISO_WEEK,DATA_CRIA)=DATEPART(ISO_WEEK,DATA_FIM)
                AND DATEPART(YEAR,DATA_CRIA)=DATEPART(YEAR,DATA_FIM)

                UNION

                -- manutenções concluídas / em atraso
                SELECT DATA_CRIA,DATA_CRIA,DATA_FIM,EQUIPAMENTO,
                DATEPART(ISO_WEEK,DATA_CRIA),DATEPART(YEAR,DATA_CRIA),DATEPART(MONTH,DATA_CRIA),
                ID_MANUTENCAO_CAB,ESTADO,
                CASE WHEN DATA_FIM IS NULL THEN CASE WHEN (DATEPART(YEAR,DATA_CRIA)>DATEPART(YEAR,GETDATE())) OR (DATEPART(YEAR,DATA_CRIA)=DATEPART(YEAR,GETDATE()) AND DATEPART(ISO_WEEK,DATA_CRIA)>=DATEPART(ISO_WEEK,GETDATE())) THEN 'P' ELSE 'A' END
                     WHEN DATEPART(ISO_WEEK,DATA_FIM)!=DATEPART(ISO_WEEK,DATA_CRIA) OR DATEPART(YEAR,DATA_FIM)!=DATEPART(YEAR,DATA_CRIA) THEN 'A'
                     ELSE 'C' END ESTADO_ATRASO,
                tipo_manutencao,
                DATEPART(ISO_WEEK,DATA_CRIA),
                ISNULL(DATEPART(ISO_WEEK,DATA_FIM),0),TEMPO_ESTIMADO,null id_planos
                FROM #TEMP_MAN_MOV_MANUTENCAO_CAB
            ) tabx
            WHERE ((@DATA_INICIO is null) OR (tabx.DATA_CRIA>=@DATA_INICIO)) AND ((@DATA_FIM is null) OR (tabx.DATA_CRIA<=@DATA_FIM))
            GROUP BY id,SEMANA,ANO,MES,ID_MANUTENCAO_CAB,ESTADO_ATRASO,tipo,SEMANA_CONCLUSAO,SEMANA_PREVISTA
        ) taba
        GROUP BY id,SEMANA,ANO,MES
    ) tabh ON tabi.SEMANA=tabh.SEMANA AND tabi.ANO=tabh.ANO
    LEFT JOIN MAN_MOV_MANUTENCAO_EQUIPAMENTOS c ON tabh.id=c.ID_MANUTENCAO
    LEFT JOIN (
        SELECT CONCAT('E',ID) as ID,DESCRICAO,UNIDADE FROM MAN_DIC_EDIFICIOS
        UNION ALL
        SELECT CONCAT('P',a.ID),b.DESCRICAO+'/'+a.DESCRICAO,UNIDADE FROM MAN_DIC_PISOS a INNER JOIN MAN_DIC_EDIFICIOS b ON a.ID_EDIFICIO=b.ID
        UNION ALL
        SELECT CONCAT('D',a.ID),c.DESCRICAO+'/'+b.DESCRICAO+'/'+a.DESCRICAO,UNIDADE FROM MAN_DIC_DIVISOES a INNER JOIN MAN_DIC_PISOS b ON a.ID_PISO=b.ID INNER JOIN MAN_DIC_EDIFICIOS c ON b.ID_EDIFICIO=c.ID
    ) e ON e.ID=CONCAT(ISNULL(c.TIPO_LOCALIZACAO,'E'),c.LOCALIZACAO)
    LEFT JOIN (
        SELECT EQUIPAMENTO, SEMANA, ANO, SUM(y.TEMPO_PLANEADO) TEMPO_PLANEADO, SUM(TEMPO_EXEC) TEMPO_REAL
        FROM (
            SELECT
                t.ID_MANUTENCAO_CAB,
                t.EQUIPAMENTO,
                DATEPART(ISO_WEEK, t.DATA_CRIA) as SEMANA,
                DATEPART(YEAR, t.DATA_CRIA) as ANO,
                SUM(t.TEMPO_ESTIMADO) as TEMPO_PLANEADO,
                SUM(t.TEMPO_REAL) as TEMPO_REAL
            FROM #TEMP_MAN_MOV_MANUTENCAO_CAB t
            GROUP BY t.ID_MANUTENCAO_CAB, t.EQUIPAMENTO, DATEPART(ISO_WEEK, t.DATA_CRIA), DATEPART(YEAR, t.DATA_CRIA)
        ) y
        LEFT JOIN TT tt ON tt.ID_MANUTENCAO_CAB = y.ID_MANUTENCAO_CAB
        GROUP BY EQUIPAMENTO, SEMANA, ANO
    ) tempo_tab ON tempo_tab.EQUIPAMENTO=tabh.id AND tempo_tab.SEMANA=tabi.SEMANA AND tempo_tab.ANO=tabi.ANO

    WHERE tabh.id is not null
    AND ((@SEMANAS_FILTRO is null) OR (tabh.id in (
        SELECT td.id FROM #TEMP_DATA td WHERE td.SEMANA in (SELECT value FROM STRING_SPLIT(@SEMANAS_FILTRO, ','))
        UNION
        SELECT EQUIPAMENTO FROM #TEMP_MAN_MOV_MANUTENCAO_CAB
        WHERE DATEPART(ISO_WEEK, DATA_CRIA) in (SELECT value FROM STRING_SPLIT(@SEMANAS_FILTRO, ','))
        AND DATEPART(YEAR, DATA_CRIA) = @ANO
    )))

    -- PIVOT TABLE
    DECLARE @cols AS NVARCHAR(MAX), @query AS NVARCHAR(MAX);

    SET @cols = STUFF((SELECT ',' + QUOTENAME(CONCAT('WEEK ',c.SEMANA))
        FROM (SELECT DISTINCT SEMANA FROM @SEMANAS) c
        ORDER BY CAST(SEMANA as decimal(6,2))
        FOR XML PATH(''), TYPE).value('.', 'NVARCHAR(MAX)'),1,1,'')

    SET @query = '
    SELECT EQUIPAMENTO, ' + @cols + ',ID_EQUIPAMENTO,LOCALIZACAO,ORDEM FROM (

        SELECT EQUIPAMENTO, ' + @cols + ',ID_EQUIPAMENTO,LOCALIZACAO,0 as ORDEM FROM (
            SELECT null EQUIPAMENTO,null ID_EQUIPAMENTO,null LOCALIZACAO,''1'' DESCRICAO,SEMANA FROM #SEMANAS_SHOW
        ) x PIVOT (max(DESCRICAO) FOR SEMANA IN (' + @cols + ')) p

        UNION ALL

        SELECT EQUIPAMENTO, ' + @cols + ',ID_EQUIPAMENTO,LOCALIZACAO,1 as ORDEM FROM (
            SELECT ''0'' EQUIPAMENTO,null ID_EQUIPAMENTO,null LOCALIZACAO,
                CONCAT(
                    sum(PREVISTA),''/'',
                    sum(REALIZADA),''/'',
                    sum(FORA_PRAZO),''/'',
                    CAST(ISNULL(sum(TEMPO_PLANEADO),0) as varchar(20)),''/'',
                    CAST(ISNULL(sum(TEMPO_REAL),0) as varchar(20))
                ) TOTAIS,SEMANA
            FROM #TEMP_2 GROUP BY SEMANA
        ) x PIVOT (MAX(TOTAIS) FOR SEMANA IN (' + @cols + ')) p

        UNION ALL

        SELECT EQUIPAMENTO, ' + @cols + ',ID_EQUIPAMENTO,LOCALIZACAO,2 as ORDEM FROM (
            SELECT EQUIPAMENTO,ID_EQUIPAMENTO,LOCALIZACAO,DESCRICAO,SEMANA FROM #TEMP_2
        ) x PIVOT (max(DESCRICAO) FOR SEMANA IN (' + @cols + ')) p

    ) final ORDER BY ORDEM, EQUIPAMENTO'

    EXECUTE(@query)

END
GO

/* ---------------------------------------------------------------- 5 (opcional)
   Recalculo em massa de DATA_PROXIMA_REALIZADA de todos os planos activos,
   para corrigir as datas ja gravadas com o dia da semana errado.
   Correr DEPOIS de validar os passos 1-4.
   ---------------------------------------------------------------------- */
/*
SET DATEFIRST 1;
DECLARE @ids table (ID int);
INSERT INTO @ids
SELECT DISTINCT a.ID
FROM MAN_MOV_MANUTENCAO_PLANOS a
INNER JOIN MAN_MOV_MANUTENCAO_EQUIPAMENTOS b ON a.ID_MANUTENCAO = b.ID_MANUTENCAO
WHERE b.ATIVO = 1 AND ISNULL(b.OBSOLETO,0) = 0
  AND ISNULL(a.TIPO_MANUTENCAO,'P') = 'P'
  AND a.TIPO_REPETICAO IS NOT NULL AND a.REPETIR IS NOT NULL;

DECLARE @pid int;
WHILE EXISTS (SELECT 1 FROM @ids)
BEGIN
    SELECT TOP 1 @pid = ID FROM @ids;
    EXEC dbo.MAN_ATUALIZA_PROXIMA_DATA @pid;
    DELETE FROM @ids WHERE ID = @pid;
END
*/
