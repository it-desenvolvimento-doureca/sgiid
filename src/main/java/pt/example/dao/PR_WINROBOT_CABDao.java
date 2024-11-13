package pt.example.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import pt.example.entity.PR_WINROBOT_CAB;

public class PR_WINROBOT_CABDao extends GenericDaoJpaImpl<PR_WINROBOT_CAB, Integer>
		implements GenericDao<PR_WINROBOT_CAB, Integer> {
	public PR_WINROBOT_CABDao() {
		super(PR_WINROBOT_CAB.class);
	}

	public List<PR_WINROBOT_CAB> getbyid(Integer id) {

		Query query = entityManager.createNativeQuery(
				"Select a.*,''as referencias,'' as racks  from PR_WINROBOT_CAB a where a.ID = :id ",
				PR_WINROBOT_CAB.class);
		query.setParameter("id", id);
		List<PR_WINROBOT_CAB> data = query.getResultList();
		return data;

	}

	public List<PR_WINROBOT_CAB> getbyestado(String estado) {

		Query query = entityManager.createNativeQuery(
				"Select a.* ,b.REFERENCIA as referencias ,C.RACKS as racks from PR_WINROBOT_CAB a "
						+ " left join (select STRING_AGG(CONCAT(x.COD_REF,' - ',x.DESIGN_REF),',') REFERENCIA,x.ID_CAB from PR_WINROBOT_ARTICLES x group by x.ID_CAB) b on a.ID = b.ID_CAB "
						+ " left join (select STRING_AGG(CONCAT(x.RACK_CODE,''),',') RACKS,x.ID_CAB from PR_WINROBOT_RACKS x group by x.ID_CAB) c on a.ID = c.ID_CAB    "
						+ " where a.ESTADO_POLL = :estado and a.ESTADO in ('C') order by CASE WHEN ORDEM_POLL is null THEN 999999 ELSE ORDEM_POLL END,DATA_HORA_FIM",
				PR_WINROBOT_CAB.class);
		query.setParameter("estado", estado);
		List<PR_WINROBOT_CAB> data = query.getResultList();
		return data;

	}

	public List<PR_WINROBOT_CAB> getTrabalhobyRACK(String rack) {

		Query query = entityManager.createNativeQuery(
				"select top 1 *,''as referencias,'' as racks  from PR_WINROBOT_CAB a "
						+ "where a.ID in (select b.ID_CAB FROM PR_WINROBOT_RACKS b WHERE b.RACK_CODE = :rack) "
						+ "AND a.ESTADO_POLL = 'E' order by CASE WHEN ORDEM_POLL is null THEN 999999 ELSE ORDEM_POLL END ",
				PR_WINROBOT_CAB.class);
		query.setParameter("rack", rack);
		List<PR_WINROBOT_CAB> data = query.getResultList();
		return data;

	}

	public List<PR_WINROBOT_CAB> getTrabalhobyRACKPendente(String rack) {

		Query query = entityManager.createNativeQuery(
				"select top 1 *,''as referencias,'' as racks  from PR_WINROBOT_CAB a "
						+ "where a.ID in (select b.ID_CAB FROM PR_WINROBOT_RACKS b WHERE b.RACK_CODE = :rack) "
						+ "AND a.ESTADO_POLL = 'P'AND ESTADO NOT IN('CANC') order by CASE WHEN ORDEM_POLL is null THEN 999999 ELSE ORDEM_POLL END ",
				PR_WINROBOT_CAB.class);
		query.setParameter("rack", rack);
		List<PR_WINROBOT_CAB> data = query.getResultList();
		return data;

	}

	public Integer updateestado(String estado, Integer id, String user) {

		Query query = entityManager.createNativeQuery(
				"UPDATE PR_WINROBOT_CAB set ESTADO_POLL = :estado, NUM_CARRO = CASE WHEN :estado = 'P' THEN null  ELSE NUM_CARRO END "
						+ " , ORDEM_POLL = null " + " where ID = :id "
						+ "INSERT INTO [dbo].[PR_WINROBOT_HISTORICO] ([DESCRICAO] ,[DATA_CRIA],ID_CAB) "
						+ "select (select top 1 NOME from RH_FUNCIONARIOS where COD_FUNC_ORIGEM  = :user) + ' ALTEROU ESTADO NA LISTA PARA ' + :estado,GETDATE(), :id ");
		query.setParameter("estado", estado);
		query.setParameter("id", id);
		query.setParameter("user", user);

		Integer data = query.executeUpdate();
		return data;

	}

	public Integer updateestado2(String estado, Integer id, String user) {

		Query query = entityManager.createNativeQuery(
				"UPDATE PR_WINROBOT_CAB set ESTADO_POLL = :estado, NUM_CARRO = CASE WHEN :estado = 'P' THEN null  ELSE NUM_CARRO END "
						+ " , ORDEM_POLL = null " + " where ID = :id "
						+ "INSERT INTO [dbo].[PR_WINROBOT_HISTORICO] ([DESCRICAO] ,[DATA_CRIA],ID_CAB) "
						+ "select ISNULL((select top 1 NOME from RH_FUNCIONARIOS where COD_FUNC_ORIGEM  = :user),'') + ' ALTEROU ESTADO NA LISTA PARA ' + :estado,GETDATE(), :id ");
		query.setParameter("estado", estado);
		query.setParameter("id", id);
		query.setParameter("user", user);

		if (estado.equals("P")) {
			Query query2_ = entityManager.createNativeQuery("INSERT INTO [PR_WINROBOT_CARROS_TEMP] (NUM_CARRO,ID_CAB,UTZ_CRIA,DATA_CRIA) "
					+ "select NUM_CARRO,ID_CAB,:user UTZ_CRIA,GETDATE() DATA_CRIA from [PR_WINROBOT_CARROS] where ID_CAB = :id ");
			query2_.setParameter("id", id).setParameter("user", user).executeUpdate();
			
			Query query2 = entityManager.createNativeQuery("DELETE [PR_WINROBOT_CARROS] where ID_CAB = :id ");
			query2.setParameter("id", id).executeUpdate();
		}

		Integer data = query.executeUpdate();
		return data;

	}

	public Integer updateordem(Integer ordem, Integer id, String user) {

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

	public PR_WINROBOT_CAB update_(PR_WINROBOT_CAB PR_WINROBOT_CAB) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		CriteriaUpdate<PR_WINROBOT_CAB> update = cb.createCriteriaUpdate(PR_WINROBOT_CAB.class);
		Root<PR_WINROBOT_CAB> e = update.from(PR_WINROBOT_CAB.class);
 
		// set update and where clause
		update.set("DATA_HORA_INICIO", PR_WINROBOT_CAB.getDATA_HORA_INICIO());
		update.set("DATA_HORA_FIM", PR_WINROBOT_CAB.getDATA_HORA_FIM());
		update.set("DATA_MODIF", PR_WINROBOT_CAB.getDATA_MODIF());
		update.set("UTZ_MODIF", PR_WINROBOT_CAB.getUTZ_MODIF());
		update.where(cb.equal(e.get("ID"), PR_WINROBOT_CAB.getID()));

		// perform update
		entityManager.createQuery(update).executeUpdate();
		return PR_WINROBOT_CAB;
	}

}
