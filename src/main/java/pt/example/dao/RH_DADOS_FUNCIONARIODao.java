package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RH_DADOS_FUNCIONARIO;

public class RH_DADOS_FUNCIONARIODao extends GenericDaoJpaImpl<RH_DADOS_FUNCIONARIO, Integer>
		implements GenericDao<RH_DADOS_FUNCIONARIO, Integer> {
	public RH_DADOS_FUNCIONARIODao() {
		super(RH_DADOS_FUNCIONARIO.class);
	}

	public List<RH_DADOS_FUNCIONARIO> getbyid(String id) {

		Query query = entityManager.createQuery("Select a from RH_DADOS_FUNCIONARIO a where a.ID_FUNCIONARIO = :id ");
		query.setParameter("id", id);
		List<RH_DADOS_FUNCIONARIO> data = query.getResultList();
		return data;

	}


	public List<RH_DADOS_FUNCIONARIO> getProximasConsultas(Integer id) {

		Query query = entityManager.createNativeQuery("Select "
				+ "(select MIN(b.DATA) as DATA from RH_MEDICINA_TRABALHO b where b.ID_FUNCIONARIO = :id and b.TIPO_CONSULTA = 1 and b.ATIVO = 1 and b.DATA >= CAST(GETDATE() as date)) as DATA_PROXIMA "
				+ ",(select MAX(c.DATA) as DATA from RH_MEDICINA_TRABALHO c where c.ID_FUNCIONARIO = :id and c.TIPO_CONSULTA = 1 and c.ATIVO = 1 and c.DATA < CAST(GETDATE() as date)) as DATA_ULTIMA ");
		query.setParameter("id", id);
		List<RH_DADOS_FUNCIONARIO> data = query.getResultList();
		return data;

	}

	
	public List<RH_DADOS_FUNCIONARIO> getall() {

		Query query = entityManager.createQuery("Select a from RH_DADOS_FUNCIONARIO a ");
		List<RH_DADOS_FUNCIONARIO> data = query.getResultList();
		return data;

	}

}
