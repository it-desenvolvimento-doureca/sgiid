package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.GER_UTILIZADORES;
import pt.example.entity.MAN_DIC_AMBITO_UTILIZADORES_EQUIPAMENTOS;

public class MAN_DIC_AMBITO_UTILIZADORES_EQUIPAMENTOSDao extends GenericDaoJpaImpl<MAN_DIC_AMBITO_UTILIZADORES_EQUIPAMENTOS, Integer>
		implements GenericDao<MAN_DIC_AMBITO_UTILIZADORES_EQUIPAMENTOS, Integer> {
	public MAN_DIC_AMBITO_UTILIZADORES_EQUIPAMENTOSDao() {
		super(MAN_DIC_AMBITO_UTILIZADORES_EQUIPAMENTOS.class);
	}

	public List<MAN_DIC_AMBITO_UTILIZADORES_EQUIPAMENTOS> getall() {

		Query query = entityManager.createQuery(
				"Select a,b.NOME_UTILIZADOR from MAN_DIC_AMBITO_UTILIZADORES_EQUIPAMENTOS a,GER_UTILIZADORES d WHERE a.ID_RESPONSAVEL = b.ID_UTILIZADOR ");

		List<MAN_DIC_AMBITO_UTILIZADORES_EQUIPAMENTOS> utz = query.getResultList();
		return utz;

	}

	public List<GER_UTILIZADORES> getMAN_DIC_AMBITO_UTILIZADORES_EQUIPAMENTOS_ALLUSERS(Integer id) {
		Query query = entityManager.createQuery("select a from GER_UTILIZADORES a "
				+ "where  a.ID_UTILIZADOR not in (select b.ID_UTILIZADOR from MAN_DIC_AMBITO_UTILIZADORES_EQUIPAMENTOS b where b.ID_EQUIPA = :id) "
				+ "and a.INATIVO != 1 ");
		query.setParameter("id", id);
		List<GER_UTILIZADORES> data = query.getResultList();
		return data;

	}

	public List<GER_UTILIZADORES> getMAN_DIC_AMBITO_UTILIZADORES_EQUIPAMENTOS_EQUIPA(Integer id) {
		Query query = entityManager.createQuery("select a,b.NOME_UTILIZADOR,b.ID_UTILIZADOR from MAN_DIC_AMBITO_UTILIZADORES_EQUIPAMENTOS a ,GER_UTILIZADORES b "
				+ "where  a.ID_UTILIZADOR = b.ID_UTILIZADOR and a.ID_EQUIPA = :id ");
		query.setParameter("id", id);
		List<GER_UTILIZADORES> data = query.getResultList();
		return data;

	}
}
