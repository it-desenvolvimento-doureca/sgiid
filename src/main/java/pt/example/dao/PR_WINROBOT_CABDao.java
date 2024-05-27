package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PR_WINROBOT_CAB;

public class PR_WINROBOT_CABDao extends GenericDaoJpaImpl<PR_WINROBOT_CAB, Integer>
		implements GenericDao<PR_WINROBOT_CAB, Integer> {
	public PR_WINROBOT_CABDao() {
		super(PR_WINROBOT_CAB.class);
	}

	public List<PR_WINROBOT_CAB> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from PR_WINROBOT_CAB a where a.ID_AMOSTRA = :id ");
		query.setParameter("id", id);
		List<PR_WINROBOT_CAB> data = query.getResultList();
		return data;

	}

	public List<PR_WINROBOT_CAB> getbyestado(String estado) {

		Query query = entityManager.createNativeQuery("Select a.* ,b.REFERENCIA as referencias from PR_WINROBOT_CAB a "
				+ " left join (select STRING_AGG(CONCAT(x.COD_REF,' - ',x.DESIGN_REF),',') REFERENCIA,x.ID_CAB from PR_WINROBOT_ARTICLES x group by x.ID_CAB) b on a.ID = b.ID_CAB "
				+ " where a.ESTADO_POLL = :estado order by CASE WHEN ORDEM_POLL is null THEN 999999 ELSE ORDEM_POLL END,DATA_HORA_FIM",
				PR_WINROBOT_CAB.class);
		query.setParameter("estado", estado);
		List<PR_WINROBOT_CAB> data = query.getResultList();
		return data;

	}

	public List<PR_WINROBOT_CAB> getTrabalhobyRACK(String rack) {

		Query query = entityManager.createNativeQuery("select top 1 *,''as referencias  from PR_WINROBOT_CAB a "
				+ "where a.ID in (select b.ID_CAB FROM PR_WINROBOT_RACKS b WHERE b.RACK_CODE = :rack) "
				+ "AND a.ESTADO_POLL = 'E' order by CASE WHEN ORDEM_POLL is null THEN 999999 ELSE ORDEM_POLL END ",
				PR_WINROBOT_CAB.class);
		query.setParameter("rack", rack);
		List<PR_WINROBOT_CAB> data = query.getResultList();
		return data;

	}

	public Integer updateestado(String estado, Integer id,String user) {

		Query query = entityManager
				.createNativeQuery("UPDATE PR_WINROBOT_CAB set ESTADO_POLL = :estado  where ID = :id "
						+ "INSERT INTO [dbo].[PR_WINROBOT_HISTORICO] ([DESCRICAO] ,[DATA_CRIA],ID_CAB) "
						+ "select (select top 1 NOME_UTZ from PR_WINROBOT_USERS where ID_UTZ  = :user  and ID_CAB = :id) + ' ALTEROU ESTADO NA LISTA PARA ' + :estado,GETDATE(), :id ");
		query.setParameter("estado", estado);
		query.setParameter("id", id);
		query.setParameter("user", user);

		Integer data = query.executeUpdate();
		return data;

	}

	public Integer updateordem(Integer ordem, Integer id,String user) {

		Query query = entityManager
				.createNativeQuery("UPDATE PR_WINROBOT_CAB set ORDEM_POLL = :ordem  where ID = :id ");
		query.setParameter("ordem", ordem);
		query.setParameter("id", id); 

		Integer data = query.executeUpdate();
		return data;

	}

	public List<PR_WINROBOT_CAB> getall() {

		Query query = entityManager.createQuery("Select a from PR_WINROBOT_CAB a ");
		List<PR_WINROBOT_CAB> data = query.getResultList();
		return data;

	}

}
