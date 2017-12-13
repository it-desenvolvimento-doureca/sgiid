package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.AB_DIC_BANHO_ADITIVO;

public class AB_DIC_BANHO_ADITIVODao extends GenericDaoJpaImpl<AB_DIC_BANHO_ADITIVO,Integer> implements GenericDao<AB_DIC_BANHO_ADITIVO,Integer> {
	public AB_DIC_BANHO_ADITIVODao() {
		super(AB_DIC_BANHO_ADITIVO.class);
	}
	
	
	public List<AB_DIC_BANHO_ADITIVO> getbyid(Integer id) {

		Query query = entityManager.createQuery("select a,d, CASE WHEN a.ID_UNIDADE1 IS NULL THEN '' ELSE (select b.MEDIDA from AB_DIC_UNIDADE_MEDIDA b where b.ID_MEDIDA = a.ID_UNIDADE1)END as medida1,CASE WHEN a.ID_UNIDADE2 IS NULL THEN '' ELSE (select c.MEDIDA from AB_DIC_UNIDADE_MEDIDA c where c.ID_MEDIDA = a.ID_UNIDADE2)END as medida2 "
				+ "from AB_DIC_BANHO_ADITIVO a, AB_DIC_COMPONENTE d where a.ID_ADITIVO = d.ID_COMPONENTE and a.ID_BANHO = :id order by a.ID_ADITIVO");
		query.setParameter("id", id);
		List<AB_DIC_BANHO_ADITIVO> data = query.getResultList();
		return data;

	}
	
	public List<AB_DIC_BANHO_ADITIVO> getbyid_aditivo(Integer id) {

		Query query = entityManager.createQuery("Select a from AB_DIC_BANHO_ADITIVO a where a.ID_BANHO_ADITIVO = :id");
		query.setParameter("id", id);
		List<AB_DIC_BANHO_ADITIVO> data = query.getResultList();
		return data;

	}
}
