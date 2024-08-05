package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PIN_DIC_PROGRAMAS;

public class PIN_DIC_PROGRAMASDao extends GenericDaoJpaImpl<PIN_DIC_PROGRAMAS, Integer>
		implements GenericDao<PIN_DIC_PROGRAMAS, Integer> {
	public PIN_DIC_PROGRAMASDao() {
		super(PIN_DIC_PROGRAMAS.class);
	}

	public List<PIN_DIC_PROGRAMAS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from PIN_DIC_PROGRAMAS a where a.ID = :id AND ISNULL(ANULADO,0) = 0");
		query.setParameter("id", id);
		List<PIN_DIC_PROGRAMAS> data = query.getResultList();
		return data;

	}
	
	public List<PIN_DIC_PROGRAMAS> getbyid2(Integer id) {

		Query query = entityManager.createQuery("Select a,(select b.NOME_PROJETO from PIN_MOV_RECEITAS b where b.INATIVO != 1 and b.VERSAO_ATIVA = 1 AND b.ID = a.ID_RECEITA) as NOME_PROJETO from PIN_DIC_PROGRAMAS a where a.ID = :id AND ISNULL(ANULADO,0) = 0");
		query.setParameter("id", id);
		List<PIN_DIC_PROGRAMAS> data = query.getResultList();
		return data;

	}
	
	public List<PIN_DIC_PROGRAMAS> getbyid3(Integer id) {

		Query query = entityManager.createQuery("Select a,(select b.NOME_PROJETO from PIN_MOV_RECEITAS b where b.INATIVO != 1 and b.VERSAO_ATIVA = 1 AND b.ID = a.ID_RECEITA) as NOME_PROJETO from PIN_DIC_PROGRAMAS a where a.ID = :id AND ISNULL(ATIVO,0) = 1 AND ISNULL(ANULADO,0) = 0");
		query.setParameter("id", id);
		List<PIN_DIC_PROGRAMAS> data = query.getResultList();
		return data;

	}

	public List<PIN_DIC_PROGRAMAS> getbyTempNumber() {

		Query query = entityManager.createNativeQuery("select MAX(CODIGO) + 1, '' as text from PIN_DIC_PROGRAMAS where ISNULL(ANULADO,0) = 0 /*ATIVO = 1*/"); 
		List<PIN_DIC_PROGRAMAS> data = query.getResultList();
		return data;

	}
	
	public List<PIN_DIC_PROGRAMAS> getAll() {
/*where a.ATIVO = 1*/ 
		Query query = entityManager.createQuery(
				"Select a,(select b.NOME_PROJETO from PIN_MOV_RECEITAS b where b.INATIVO != 1 and b.VERSAO_ATIVA = 1 AND b.ID = a.ID_RECEITA) as RECEITA from PIN_DIC_PROGRAMAS a WHERE ISNULL(ANULADO,0) = 0 ");

		List<PIN_DIC_PROGRAMAS> data = query.getResultList();
		return data;

	}
	 

	

	 
}
