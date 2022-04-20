package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.GER_UTILIZADORES;
import pt.example.entity.MAN_DIC_AMBITO_UTILIZADORES;

public class MAN_DIC_AMBITO_UTILIZADORESDao extends GenericDaoJpaImpl<MAN_DIC_AMBITO_UTILIZADORES, Integer>
		implements GenericDao<MAN_DIC_AMBITO_UTILIZADORES, Integer> {
	public MAN_DIC_AMBITO_UTILIZADORESDao() {
		super(MAN_DIC_AMBITO_UTILIZADORES.class);
	}

	public List<MAN_DIC_AMBITO_UTILIZADORES> getall() {

		Query query = entityManager.createQuery(
				"Select a,b.NOME_UTILIZADOR from MAN_DIC_AMBITO_UTILIZADORES a,GER_UTILIZADORES d WHERE a.ID_RESPONSAVEL = b.ID_UTILIZADOR ");

		List<MAN_DIC_AMBITO_UTILIZADORES> utz = query.getResultList();
		return utz;

	}

	public List<GER_UTILIZADORES> getMAN_DIC_AMBITO_UTILIZADORES_ALLUSERS(Integer id) {
		Query query = entityManager.createQuery("select a from GER_UTILIZADORES a "
				+ "where  a.ID_UTILIZADOR not in (select b.ID_UTILIZADOR from MAN_DIC_AMBITO_UTILIZADORES b where b.ID_EQUIPA = :id) "
				+ "and a.INATIVO != 1 ");
		query.setParameter("id", id);
		List<GER_UTILIZADORES> data = query.getResultList();
		return data;

	}

	public List<GER_UTILIZADORES> getMAN_DIC_AMBITO_UTILIZADORES_EQUIPA(Integer id) {
		Query query = entityManager.createQuery("select a,b.NOME_UTILIZADOR,b.ID_UTILIZADOR from MAN_DIC_AMBITO_UTILIZADORES a ,GER_UTILIZADORES b "
				+ "where  a.ID_UTILIZADOR = b.ID_UTILIZADOR and a.ID_EQUIPA = :id ");
		query.setParameter("id", id);
		List<GER_UTILIZADORES> data = query.getResultList();
		return data;

	}
}
