package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PA_MOV_LINHA;

public class PA_MOV_LINHADao extends GenericDaoJpaImpl<PA_MOV_LINHA, Integer> implements GenericDao<PA_MOV_LINHA, Integer> {
	public PA_MOV_LINHADao() {
		super(PA_MOV_LINHA.class);
	}

	public List<PA_MOV_LINHA> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a, "
				+ "(select (select c.NOME_UTILIZADOR from GER_UTILIZADORES c where c.ID_UTILIZADOR =  b.UTZ_ENCAMINHADO) from GT_MOV_TAREFAS b where b.ID_MODULO = 13 and b.SUB_MODULO = 'PA' and b.ID_CAMPO = a.ID_PLANO_LINHA), "
				+ "(select b.ID_TAREFA from GT_MOV_TAREFAS b where b.ID_MODULO = 13 and b.SUB_MODULO = 'PA' and b.ID_CAMPO = a.ID_PLANO_LINHA) as ID_TAREFA "
				+ " from PA_MOV_LINHA a where a.ID_PLANO_CAB = :id ");
		query.setParameter("id", id);
		List<PA_MOV_LINHA> data = query.getResultList();
		return data;

	}


	public List<PA_MOV_LINHA> getall() {

		Query query = entityManager.createQuery("Select a from PA_MOV_LINHA a");
		List<PA_MOV_LINHA> data = query.getResultList();
		return data;

	}

}
