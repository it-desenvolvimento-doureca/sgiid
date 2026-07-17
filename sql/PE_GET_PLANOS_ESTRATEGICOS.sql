USE [SGIID]
GO

ALTER PROCEDURE [dbo].[PE_GET_PLANOS_ESTRATEGICOS]
    @tipo         NVARCHAR(10) = 'T',
    @emAtraso     BIT          = 0,
    @user         INT,
    @departamento NVARCHAR(255) = NULL,
    @ano          INT           = NULL
AS
BEGIN
    SELECT
        a.ID_PLANO_CAB,                                                                 -- [0]
        a.DATA_CRIA,                                                                    -- [1]
        a.DATA_OBJETIVO,                                                                -- [2]
        a.AMBITO,                                                                       -- [3]
        a.ORIGEM,                                                                       -- [4]
        (select NOME_UTILIZADOR from GER_UTILIZADORES x where x.ID_UTILIZADOR = a.UTZ_CRIA) as UTZ_CRIA, -- [5]
        a.DESCRICAO,                                                                    -- [6]
        a.ESTADO,                                                                       -- [7]
        b.DATA_ACCAO,                                                                   -- [8]
        (select NOME_UTILIZADOR from GER_UTILIZADORES y where y.ID_UTILIZADOR = b.RESPONSAVEL) as RESPONSAVEL, -- [9]
        c.DESCRICAO_PT,                                                                 -- [10]
        b.DESCRICAO as descricao_acao,                                                  -- [11]
        d.DESCRICAO as DESCRICAO_PRIORIDADE,                                            -- [12]
        b.ESTADO as ESTADO_ACAO,                                                        -- [13]
        b.fastresponse,                                                                 -- [14]
        (select sd.DESCRICAO from PA_DIC_AMBITOS sd where sd.ID_AMBITO = a.AMBITO) as AMBITO_DESC, -- [15]
        f.DESCRICAO as TIPO_ACAO_DESC,                                                  -- [16]
        g.ID_TAREFA,                                                                    -- [17]
        xe.DESCRICAO NOME_DEPARTAMENTO,                                                 -- [18]
        xa.ID,                                                                          -- [19]
        xa.ANO_PLANO,                                                                   -- [20]
        (select NOME_UTILIZADOR from GER_UTILIZADORES y where y.ID_UTILIZADOR = xa.RESPONSAVEL) as RESPONSAVELXA, -- [21]
        xa.DATA_CRIA as DATA_CRIAXA,                                                    -- [22]
        xa.ESTADO ESTADOXA,                                                             -- [23]
        ISNULL(g.PERCENTAGEM_CONCLUSAO,0) conclusaoacao,                                -- [24]
        AVG(CASE WHEN ISNULL(b.INATIVO,0) = 1 THEN NULL
                 WHEN YEAR(b.DATA_CRIA) <= xa.ANO_PLANO AND g.DATA_CONCLUSAO IS NOT NULL AND YEAR(g.DATA_CONCLUSAO) < xa.ANO_PLANO THEN NULL
                 ELSE ISNULL(g.PERCENTAGEM_CONCLUSAO,0) END)
            OVER(PARTITION BY a.ID_PLANO_CAB,xa.ID) AS conclusaoplano,                 -- [25]
        -- [26] conclusaototal — subqueries filtram associações anuladas (INATIVO=0)
        CASE
            WHEN (select COUNT(DISTINCT t1.ID_PLANO_CAB) from PA_MOV_CAB t1
                  where t1.ID_PLANO_CAB in (
                      select vp.ID_PLANO_CAB from PE_PLANOS_ASSOCIADOS vp
                      WHERE vp.ID_PLANO_ESTRATEGICO = xa.ID AND ISNULL(vp.INATIVO,0) = 0
                  )) = 0
            THEN 0
            WHEN (select COUNT(DISTINCT t1.ID_PLANO_CAB) from PA_MOV_CAB t1
                  where t1.ID_PLANO_CAB in (
                      select vp.ID_PLANO_CAB from PE_PLANOS_ASSOCIADOS vp
                      WHERE vp.ID_PLANO_ESTRATEGICO = xa.ID AND ISNULL(vp.INATIVO,0) = 0
                  )) = 1
            THEN AVG(CASE WHEN ISNULL(b.INATIVO,0) = 1 THEN NULL
                         WHEN YEAR(b.DATA_CRIA) <= xa.ANO_PLANO AND g.DATA_CONCLUSAO IS NOT NULL AND YEAR(g.DATA_CONCLUSAO) < xa.ANO_PLANO THEN NULL
                         ELSE ISNULL(g.PERCENTAGEM_CONCLUSAO,0) END)
                 OVER(PARTITION BY xa.ID)
            ELSE ISNULL((
                select AVG(PERCENTAGEM_CONCLUSAO) from (
                    select AVG(PERCENTAGEM_CONCLUSAO) PERCENTAGEM_CONCLUSAO from (
                        select t1.ID_PLANO_CAB,
                            AVG(CASE WHEN YEAR(t2.DATA_CRIA) <= xa.ANO_PLANO AND t3.DATA_CONCLUSAO IS NOT NULL AND YEAR(t3.DATA_CONCLUSAO) < xa.ANO_PLANO THEN NULL ELSE ISNULL(t3.PERCENTAGEM_CONCLUSAO,0) END)
                            OVER(PARTITION BY t1.ID_PLANO_CAB) PERCENTAGEM_CONCLUSAO
                        from PA_MOV_CAB t1
                        left join PA_MOV_LINHA t2 on t1.ID_PLANO_CAB = t2.ID_PLANO_CAB
                            AND ISNULL(t2.INATIVO,0) = 0                               -- excluir linhas anuladas
                        left join GT_MOV_TAREFAS t3 on t3.ID_MODULO=13 and t3.SUB_MODULO='PA'
                            and t2.ID_PLANO_LINHA=t3.ID_CAMPO and t3.ID_TAREFA_PAI is null
                        where t1.ID_PLANO_CAB in (
                            select vp.ID_PLANO_CAB from PE_PLANOS_ASSOCIADOS vp
                            WHERE vp.ID_PLANO_ESTRATEGICO=xa.ID AND ISNULL(vp.INATIVO,0) = 0
                        )
                    ) t4
                    group by ID_PLANO_CAB
                ) t5
            ),0)
        END AS conclusaototal,
        b.OBJETIVO,                                                                     -- [27]
        CASE WHEN (select COUNT(x.ID) from PA_MOV_SEGUIR_LINHA x
                   where x.ID_PLANO_LINHA=b.ID_PLANO_LINHA AND x.UTILIZADOR=@user)>0
             THEN 1 ELSE 0 END SEGUIR_LINHA,                                           -- [28]
        b.ID_PLANO_LINHA,                                                               -- [29]
        b.DATA_CRIA DATA_CRIA_LINHA,                                                    -- [30]
        a.OBJETIVO OBJETIVO_ACAO,                                                       -- [31]
        b.INVESTIMENTOS,                                                                -- [32]
        g.DATA_CONCLUSAO,                                                               -- [33]
        CASE
            WHEN EXISTS(
                select xg.ID_TAREFA from GT_MOV_TAREFAS xg
                where xg.ID_MODULO=13 and xg.SUB_MODULO='PA'
                  and xg.ID_CAMPO in (
                      select xb.ID_PLANO_LINHA from PA_MOV_LINHA xb
                      where xb.ID_PLANO_CAB=a.ID_PLANO_CAB AND ISNULL(xb.INATIVO,0)=0
                  )
                  and xg.ID_TAREFA_PAI is null and xg.ESTADO in ('P','L','E')
            )
            THEN null
            ELSE (
                select MAX(DATA_CONCLUSAO) from GT_MOV_TAREFAS xg
                where xg.ID_MODULO=13 and xg.SUB_MODULO='PA'
                  and xg.ID_CAMPO in (
                      select xb.ID_PLANO_LINHA from PA_MOV_LINHA xb
                      where xb.ID_PLANO_CAB=a.ID_PLANO_CAB AND ISNULL(xb.INATIVO,0)=0
                  )
                  and xg.ID_TAREFA_PAI is null and xg.ESTADO not in ('A','P','L','E')
            )
        END DATA_CONCLUSAO_PLANO,                                                       -- [34]
        (select count(*) from GT_MOV_TAREFAS x where x.ID_TAREFA_PAI=g.ID_TAREFA) subtarefas, -- [35]

        -- NOVAS COLUNAS (R8 — ícone histórico com cor na lista) --------------------
        CASE WHEN EXISTS (
            SELECT 1 FROM PA_MOV_CAB_HISTORICO h
            WHERE h.ID_PLANO_CAB = a.ID_PLANO_CAB
              AND h.TIPO_ALTERACAO = 'DATA_OBJETIVO'
        ) THEN 1 ELSE 0 END AS TEM_ALTERACAO_DATA,                                     -- [36]

        CASE WHEN EXISTS (
            SELECT 1 FROM PA_MOV_LINHA l
            WHERE l.ID_PLANO_CAB = a.ID_PLANO_CAB
              AND ISNULL(l.INATIVO,0) = 1
        ) THEN 1 ELSE 0 END AS TEM_LINHA_ANULADA,                                      -- [37]

        CASE WHEN EXISTS (
            SELECT 1 FROM PA_MOV_LINHA_HISTORICO lh
            WHERE lh.ID_PLANO_LINHA = b.ID_PLANO_LINHA
        ) THEN 1 ELSE 0 END AS TEM_HISTORICO_LINHA                                     -- [38]

    FROM PE_MOV_CAB xa WITH (NOLOCK)
    -- ALTERAÇÃO: excluir associações anuladas
    left join PE_PLANOS_ASSOCIADOS xb WITH (NOLOCK)
        on xa.ID=xb.ID_PLANO_ESTRATEGICO AND ISNULL(xb.INATIVO,0) = 0
    left join PA_MOV_CAB a WITH (NOLOCK) on xb.ID_PLANO_CAB=a.ID_PLANO_CAB
    left join GER_DEPARTAMENTO xe WITH (NOLOCK) on xa.DEPARTAMENTO=xe.ID
    left join PA_MOV_LINHA b WITH (NOLOCK)
        on a.ID_PLANO_CAB=b.ID_PLANO_CAB
    left join GT_DIC_TAREFAS c WITH (NOLOCK) on b.ID_ACCAO=c.ID
    left join RC_DIC_GRAU_IMPORTANCIA d WITH (NOLOCK) on b.PRIORIDADE=d.ID
    left join GER_DEPARTAMENTO e WITH (NOLOCK) on a.DEPARTAMENTO_ORIGEM=e.ID
    left join GT_DIC_TIPO_ACAO f WITH (NOLOCK) on b.TIPO_ACAO=f.ID_TIPO_ACAO
    left join GT_MOV_TAREFAS g WITH (NOLOCK)
        on g.ID_MODULO=13 and g.SUB_MODULO='PA' and b.ID_PLANO_LINHA=g.ID_CAMPO and g.ID_TAREFA_PAI is null
    WHERE
        ((NOT @emAtraso != 0) OR (b.DATA_ACCAO < GETDATE() AND g.ESTADO IN ('P','L','E')))
        AND (@tipo = 'T' OR @tipo IS NULL OR xa.TIPO = @tipo)
        AND (@departamento IS NULL OR ISNULL(xe.DESCRICAO, 'Sem Departamento') = @departamento)
        AND (@ano IS NULL OR xa.ANO_PLANO = @ano)
        AND (@departamento IS NULL OR xa.ESTADO != 'A')
        AND (@departamento IS NULL OR (a.ESTADO IS NULL OR a.ESTADO != 'A'))
        AND (@departamento IS NULL OR g.ESTADO IN ('P','L','E','C') OR g.ID_TAREFA IS NULL)
    ORDER BY xa.DATA_CRIA DESC, a.DATA_OBJETIVO ASC, a.ID_PLANO_CAB ASC, b.DATA_ACCAO ASC
END
GO
