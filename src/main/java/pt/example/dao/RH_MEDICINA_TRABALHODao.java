package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RH_MEDICINA_TRABALHO;

public class RH_MEDICINA_TRABALHODao extends GenericDaoJpaImpl<RH_MEDICINA_TRABALHO, Integer>
		implements GenericDao<RH_MEDICINA_TRABALHO, Integer> {
	public RH_MEDICINA_TRABALHODao() {
		super(RH_MEDICINA_TRABALHO.class);
	}

	public List<RH_MEDICINA_TRABALHO> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from RH_MEDICINA_TRABALHO a where a.ID = :id ");
		query.setParameter("id", id);
		List<RH_MEDICINA_TRABALHO> data = query.getResultList();
		return data;

	}

	public List<RH_MEDICINA_TRABALHO> getall() {

		Query query = entityManager.createQuery("Select a from RH_MEDICINA_TRABALHO a ");
		List<RH_MEDICINA_TRABALHO> data = query.getResultList();
		return data;

	}

	public List<RH_MEDICINA_TRABALHO> getall2(String id) {

		Query query = entityManager.createNativeQuery("declare @id varchar(25) =  " +id 
				+ " ;WITH DadosCombinados AS ( SELECT ID_FUNCIONARIO,  DT_NASCIMENTO "
				+ "	FROM (select * from OPENQUERY([ESIGEMP], 'select gdf.ID_FUNCIONARIO, gdf.DT_NASCIMENTO  FROM ERPMASTER.GG_DIC_FUNCIONARIOS gdf   ')) a	UNION ALL "
				+ "	SELECT ID_FUNCIONARIO, DT_NASCIMENTO FROM RH_DADOS_FUNCIONARIO ), DadosUnicos AS ( "
				+ "	SELECT *,ROW_NUMBER() OVER (PARTITION BY ID_FUNCIONARIO ORDER BY ID_FUNCIONARIO) AS rn	FROM DadosCombinados ) "
				+ "Select a.ID, a.TIPO_CONSULTA, a.ID_ENTIDADE_MEDICA, a.ID_LOCAL, a.EXAMES, a.DATA, a.ESTADO, a.DATA_CRIA, a.UTZ_CRIA, a.DATA_MODIF, a.UTZ_MODIF, a.UTZ_ANULA, a.DATA_ANULA, a.ATIVO, a.ID_FUNCIONARIO "
				+ ",b.NOME ENTIDADE_MEDICA,c.NOME LOCAL,d.NOME NOME_UTZ,d.COD_FUNC_ORIGEM,d.EXPOSTO_RISCOS,d.PERIOCIDADE "
				+ " ,dbo.calculateproximaConsultaDate(d.EXPOSTO_RISCOS,d.PERIOCIDADE,e.DT_NASCIMENTO,a.DATA) DATA_PROXIMA_CONSULTA "
				+ " ,(select MIN(x.DATA) from RH_MEDICINA_TRABALHO x where x.ID_FUNCIONARIO = a.ID_FUNCIONARIO  and TIPO_CONSULTA = 1 and ATIVO = 1 and x.DATA > a.DATA ) DATA_PROXIMA_CONSULTA_MARCADA "
				+ "from RH_MEDICINA_TRABALHO a  "
				+ "left join RH_DIC_ENTIDADE_MEDICA b on a.ID_ENTIDADE_MEDICA = b.ID  "
				+ "left join RH_DIC_ENTIDADE_MEDICA_LOCAIS c on a.ID_LOCAL = c.ID and  a.ID_ENTIDADE_MEDICA = c.ID_ENTIDADE_MEDICA "
				+ "left join RH_FUNCIONARIOS d on a.ID_FUNCIONARIO = d.COD_FUNC_ORIGEM "
				+ "LEFT JOIN (SELECT ID_FUNCIONARIO, DT_NASCIMENTO FROM DadosUnicos WHERE rn = 1) e on RIGHT(CONCAT('00000',d.COD_FUNC_ORIGEM),5) = e.ID_FUNCIONARIO "
				+ "where a.ATIVO = 1 and ((@id is null) or (a.ID_FUNCIONARIO = @id)) order by a.DATA DESC");
 		List<RH_MEDICINA_TRABALHO> data = query.getResultList();
		return data;

	}
	
	public List<Object> getDataNascimento(String id) {

		Query query = entityManager.createNativeQuery("declare @id varchar(25) =  " +id 
				+ " ;WITH DadosCombinados AS ( SELECT ID_FUNCIONARIO,  DT_NASCIMENTO "
				+ "	FROM (select * from OPENQUERY([ESIGEMP], 'select gdf.ID_FUNCIONARIO, gdf.DT_NASCIMENTO  FROM ERPMASTER.GG_DIC_FUNCIONARIOS gdf where gdf.ID_FUNCIONARIO =  SUBSTR(LPAD(''"+id+"'', 5, ''0''), -5)  ')) a	UNION ALL "
				+ "	SELECT ID_FUNCIONARIO, DT_NASCIMENTO FROM RH_DADOS_FUNCIONARIO ), DadosUnicos AS ( "
				+ "	SELECT *,ROW_NUMBER() OVER (PARTITION BY ID_FUNCIONARIO ORDER BY ID_FUNCIONARIO) AS rn	FROM DadosCombinados ) "
				+ " Select d.EXPOSTO_RISCOS,d.PERIOCIDADE, e.DT_NASCIMENTO from   RH_FUNCIONARIOS d   "
				+ "LEFT JOIN (SELECT ID_FUNCIONARIO, DT_NASCIMENTO FROM DadosUnicos WHERE rn = 1) e on RIGHT(CONCAT('00000',d.COD_FUNC_ORIGEM),5) = e.ID_FUNCIONARIO "
				+ "where RIGHT(CONCAT('00000',d.COD_FUNC_ORIGEM),5) = RIGHT(CONCAT('00000',@id),5)");
 		List<Object> data = query.getResultList();
		return data;

	}
	
	
	public List<Object[]> RH_GET_MEDICINA_TOTAIS(String ano, String utilizador, String DATA_INICIO, String DATA_FIM) {

		Query query = entityManager.createNativeQuery("EXEC RH_GET_MEDICINA_TOTAIS  " + ano + ","+ utilizador + ","+ DATA_INICIO + ","+ DATA_FIM );
		List<Object[]> data = query.getResultList();
		return data;

	}
	
	public List<Object[]> RH_GET_MEDICINA_REALIZADAS(String ano, String utilizador, String DATA_INICIO, String DATA_FIM) {

		Query query = entityManager.createNativeQuery("EXEC RH_GET_MEDICINA_REALIZADAS  " + ano + ","+ utilizador + ","+ DATA_INICIO + ","+ DATA_FIM );
		List<Object[]> data = query.getResultList();
		return data;

	}
	
	public List<Object[]> RH_GET_MEDICINA_PROXIMAS_CONSULTAS(String ano, String utilizador, String DATA_INICIO, String DATA_FIM) {

		Query query = entityManager.createNativeQuery("EXEC RH_GET_MEDICINA_PROXIMAS_CONSULTAS  " + ano + ","+ utilizador + ","+ DATA_INICIO + ","+ DATA_FIM );
		List<Object[]> data = query.getResultList();
		return data;

	}
	
	public List<Object[]> RH_GET_MEDICINA_EXPIRADAS(String ano, String utilizador, String DATA_INICIO, String DATA_FIM) {

		Query query = entityManager.createNativeQuery("EXEC RH_GET_MEDICINA_EXPIRADAS  " + ano + ","+ utilizador + ","+ DATA_INICIO + ","+ DATA_FIM );
		List<Object[]> data = query.getResultList();
		return data;

	}

}
