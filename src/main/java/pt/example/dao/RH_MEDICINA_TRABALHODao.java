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
				+ " Select a.ID, a.TIPO_CONSULTA, a.ID_ENTIDADE_MEDICA, a.ID_LOCAL, a.EXAMES, a.DATA, a.ESTADO, a.DATA_CRIA, a.UTZ_CRIA, a.DATA_MODIF, a.UTZ_MODIF, a.UTZ_ANULA, a.DATA_ANULA, a.ATIVO, a.ID_FUNCIONARIO "
				+ ",b.NOME ENTIDADE_MEDICA,c.NOME LOCAL,d.NOME NOME_UTZ,d.COD_FUNC_ORIGEM "
				+ "from RH_MEDICINA_TRABALHO a  "
				+ "left join RH_DIC_ENTIDADE_MEDICA b on a.ID_ENTIDADE_MEDICA = b.ID  "
				+ "left join RH_DIC_ENTIDADE_MEDICA_LOCAIS c on a.ID_LOCAL = c.ID and  a.ID_ENTIDADE_MEDICA = c.ID_ENTIDADE_MEDIA "
				+ "left join RH_FUNCIONARIOS d on a.ID_FUNCIONARIO = d.COD_FUNC_ORIGEM  "
				+ "where a.ATIVO = 1 and ((@id is null) or (a.ID_FUNCIONARIO = @id)) order by a.DATA DESC");
 		List<RH_MEDICINA_TRABALHO> data = query.getResultList();
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
