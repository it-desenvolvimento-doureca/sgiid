package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PIN_MOV_RECEITAS; 

public class PIN_MOV_RECEITASDao extends GenericDaoJpaImpl<PIN_MOV_RECEITAS, Integer>
		implements GenericDao<PIN_MOV_RECEITAS, Integer> {
	public PIN_MOV_RECEITASDao() {
		super(PIN_MOV_RECEITAS.class);
	}

	public List<PIN_MOV_RECEITAS> getbyid(Integer id, Integer linha,Integer versao) {

		Query query = entityManager.createQuery(
				"Select a,b,(select max(c.VERSAO) from PIN_MOV_RECEITAS c where c.ID = a.ID ) as MAX_VERSAO "
				+ ",(select b.NOME_UTILIZADOR from GER_UTILIZADORES b where b.ID_UTILIZADOR = a.UTZ_CRIA) as NOME_UTILIZADOR "
				+ "from PIN_MOV_RECEITAS a, AB_DIC_LINHA b where a.ID = :id and a.LINHA = b.ID_LINHA and a.VERSAO =:versao  and ((not :linha != 0) or (a.LINHA = :linha))");
		query.setParameter("id", id);
		query.setParameter("linha", linha);
		query.setParameter("versao", versao);
		List<PIN_MOV_RECEITAS> data = query.getResultList();
		return data;

	}

	public List<PIN_MOV_RECEITAS> getall(Integer linha) {
		Query query = entityManager.createQuery(
				"Select a,b from PIN_MOV_RECEITAS a,AB_DIC_LINHA b where  a.LINHA = b.ID_LINHA and a.INATIVO != 1 and a.VERSAO_ATIVA = 1 and ((not :linha != 0) or (a.LINHA = :linha))");
		query.setParameter("linha", linha);
		List<PIN_MOV_RECEITAS> data = query.getResultList();
		return data;

	}


	public List<PIN_MOV_RECEITAS> chechExistRef(String referencia,Integer id) {
		Query query = entityManager.createQuery(
				"Select a from PIN_MOV_RECEITAS a where  a.INATIVO != 1 and a.VERSAO_ATIVA = 1 and RECEITA_INATIVA != 1 and  a.REFERENCIA_PINTURA = :referencia AND a.ID != :id ");
		query.setParameter("referencia", referencia);
		query.setParameter("id", id);
		List<PIN_MOV_RECEITAS> data = query.getResultList();
		return data;

	}

	
	public List<PIN_MOV_RECEITAS> getversoes(Integer id, Integer versao) {

		Query query = entityManager.createNativeQuery(
				"Select a.ID,a.VERSAO from PIN_MOV_RECEITAS a where a.ID = :id  order by a.VERSAO ");
		query.setParameter("id", id); 
		List<PIN_MOV_RECEITAS> data = query.getResultList();
		return data;

	}


}
