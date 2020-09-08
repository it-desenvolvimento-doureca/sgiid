package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.GT_DIC_TAREFAS;

public class GT_DIC_TAREFASDao extends GenericDaoJpaImpl<GT_DIC_TAREFAS, Integer>
		implements GenericDao<GT_DIC_TAREFAS, Integer> {
	public GT_DIC_TAREFASDao() {
		super(GT_DIC_TAREFAS.class);
	}

	public List<GT_DIC_TAREFAS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from GT_DIC_TAREFAS a where a.ID = :id  order by a.DESCRICAO_PT");
		query.setParameter("id", id);
		List<GT_DIC_TAREFAS> data = query.getResultList();
		return data;

	}
	
	public List<GT_DIC_TAREFAS> verificaseExiste(String descricao,String tipo) {

		Query query = entityManager.createNativeQuery("Select DESCRICAO_PT,ID from GT_DIC_TAREFAS a where LOWER(a.DESCRICAO_PT) = LOWER('" + descricao +"') and a.TIPO_TAREFA = '" + tipo + "'");
	
		List<GT_DIC_TAREFAS> data = query.getResultList();
		return data;

	}

	public List<GT_DIC_TAREFAS> getall() {

		Query query = entityManager.createQuery("Select a from GT_DIC_TAREFAS a ");
		List<GT_DIC_TAREFAS> data = query.getResultList();
		return data;

	}

	public List<GT_DIC_TAREFAS> getalltipo(String tipo) {

		Query query = entityManager.createQuery("Select a from GT_DIC_TAREFAS a where a.TIPO_TAREFA = '" + tipo + "' order by a.DESCRICAO_PT");
		List<GT_DIC_TAREFAS> data = query.getResultList();
		return data;

	}

}
