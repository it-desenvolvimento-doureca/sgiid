package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.AB_MOV_MANUTENCAO_DOSIFICADORES;

public class AB_MOV_MANUTENCAO_DOSIFICADORESDao extends GenericDaoJpaImpl<AB_MOV_MANUTENCAO_DOSIFICADORES,Integer> implements GenericDao<AB_MOV_MANUTENCAO_DOSIFICADORES,Integer> {
	public AB_MOV_MANUTENCAO_DOSIFICADORESDao() {
		super(AB_MOV_MANUTENCAO_DOSIFICADORES.class);
	}

	
	public List<AB_MOV_MANUTENCAO_DOSIFICADORES> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a,(select b.NOME_UTILIZADOR from GER_UTILIZADORES b where b.ID_UTILIZADOR = a.UTZ_EXECUCAO) as NOME_UTZ_EXECUCAO "
				+ ",(select c.COD_TINA from AB_DIC_TINA c where c.ID_TINA = a.ID_TINA) as COD_TINA from AB_MOV_MANUTENCAO_DOSIFICADORES a where a.ID_MANUTENCAO = :id ");
		query.setParameter("id", id);
		List<AB_MOV_MANUTENCAO_DOSIFICADORES> data = query.getResultList();
		return data;

	}
	
	public List<AB_MOV_MANUTENCAO_DOSIFICADORES> getall() {

		Query query = entityManager.createQuery("Select a from AB_MOV_MANUTENCAO_DOSIFICADORES a ");
		List<AB_MOV_MANUTENCAO_DOSIFICADORES> data = query.getResultList();
		return data;

	}

}
