package pt.example.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import pt.example.entity.AB_DIC_BANHO_COMPONENTE;
import pt.example.entity.FIN_DIC_OBJETIVOS;

public class FIN_DIC_OBJETIVOSDao extends GenericDaoJpaImpl<FIN_DIC_OBJETIVOS, Integer>
		implements GenericDao<FIN_DIC_OBJETIVOS, Integer> {
	public FIN_DIC_OBJETIVOSDao() {
		super(FIN_DIC_OBJETIVOS.class);
	}

	public List<FIN_DIC_OBJETIVOS> getbyid(Integer id) {
		Query query = entityManager.createQuery("Select a from FIN_DIC_OBJETIVOS a where a.ID_OBJETIVO = :id");
		query.setParameter("id", id);
		List<FIN_DIC_OBJETIVOS> data = query.getResultList();
		return data;
	}

	public List<FIN_DIC_OBJETIVOS> getall() {
		Query query = entityManager.createQuery("Select a from FIN_DIC_OBJETIVOS a order by ano desc,mes desc");
		List<FIN_DIC_OBJETIVOS> data = query.getResultList();
		return data;
	}

	public List<FIN_DIC_OBJETIVOS> getall2() {
		Query query = entityManager.createNativeQuery("DECLARE @DATA date = GETDATE() "
				+ "select ID_OBJETIVO,a.MES,a.ANO,N_DIAS_UTEIS, CAST(VALOR_OBJETIVO/N_DIAS_UTEIS as decimal(38,2)) as OBJETIVO_DIARIO, "
				+ "VALOR_OBJETIVO,ISNULL(b.FATURACAO_TOTAL,0) FATURACAO_TOTAL, "
				+ "CASE WHEN DATEFROMPARTS(YEAR(@DATA),MONTH(@DATA),1) = DATEFROMPARTS(a.ano,a.mes,1) THEN "
				+ "		((SELECT DATEDIFF(day,DATEFROMPARTS(a.ano,a.mes,1) ,@DATA) + CASE WHEN EXISTS (select * from GER_FERIADOS xc where xc.DATA = @DATA) THEN 0 ELSE 1 END ) -"
				+ "		(select count(*) from GER_FERIADOS c where datepart(month,c.DATA) = a.mes and datepart(year,c.DATA) = a.ano and c.DATA < @DATA and c.CONTA_FATURACAO != 1) - 1) "
				+ "	WHEN @DATA > DATEFROMPARTS(a.ano,a.mes,1) THEN N_DIAS_UTEIS "
				+ "	WHEN @DATA < DATEFROMPARTS(a.ano,a.mes,1) THEN  0 "
				+ "END dias_decorridos from  FIN_DIC_OBJETIVOS a left join FIN_FATURACAO_TOTAL b on (CASE WHEN a.MES = 1 THEN a.ANO - 1 ELSE a.ANO END ) = b.ANO "
				+ " and (CASE WHEN a.MES = 1 THEN 12 ELSE (a.MES-1) END ) = b.MES order by a.ano desc,a.mes desc");
		List<FIN_DIC_OBJETIVOS> data = query.getResultList();
		return data;
	}

}
