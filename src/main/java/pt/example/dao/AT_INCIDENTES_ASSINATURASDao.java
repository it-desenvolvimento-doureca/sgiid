package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.AT_INCIDENTES_ASSINATURAS;

/**
 * Melhorias 2026-08-24 - Assinaturas dos trabalhadores em postos/tarefas
 * similares, no fecho do relatorio de incidentes.
 */
public class AT_INCIDENTES_ASSINATURASDao
		extends GenericDaoJpaImpl<AT_INCIDENTES_ASSINATURAS, Integer>
		implements GenericDao<AT_INCIDENTES_ASSINATURAS, Integer> {

	public AT_INCIDENTES_ASSINATURASDao() {
		super(AT_INCIDENTES_ASSINATURAS.class);
	}

	public List<AT_INCIDENTES_ASSINATURAS> getbyid(Integer id) {
		Query query = entityManager.createQuery(
				"Select a from AT_INCIDENTES_ASSINATURAS a where a.ID_INCIDENTE = :id order by a.ID ");
		query.setParameter("id", id);
		return query.getResultList();
	}

	public List<AT_INCIDENTES_ASSINATURAS> getall() {
		Query query = entityManager.createQuery("Select a from AT_INCIDENTES_ASSINATURAS a ");
		return query.getResultList();
	}
}
