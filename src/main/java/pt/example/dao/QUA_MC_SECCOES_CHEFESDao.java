package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.QUA_MC_SECCOES_CHEFES;

public class QUA_MC_SECCOES_CHEFESDao extends GenericDaoJpaImpl<QUA_MC_SECCOES_CHEFES, Integer>
		implements GenericDao<QUA_MC_SECCOES_CHEFES, Integer> {
	public QUA_MC_SECCOES_CHEFESDao() {
		super(QUA_MC_SECCOES_CHEFES.class);
	}

	public List<Object[]> getChefesSecao(Integer idSeccao) {
		Query query = entityManager.createNativeQuery(
			"SELECT c.ID_SECCAO, c.ID_UTILIZADOR, u.EMAIL, u.NOME_UTILIZADOR " +
			"FROM QUA_MC_SECCOES_CHEFES c " +
			"LEFT JOIN GER_UTILIZADORES u ON u.ID_UTILIZADOR = c.ID_UTILIZADOR " +
			"WHERE c.ID_SECCAO = :idSeccao");
		query.setParameter("idSeccao", idSeccao);
		return query.getResultList();
	}

	public void adicionarChefe(Integer idSeccao, Integer idUtilizador) {
		QUA_MC_SECCOES_CHEFES chefe = new QUA_MC_SECCOES_CHEFES();
		chefe.setID_SECCAO(idSeccao);
		chefe.setID_UTILIZADOR(idUtilizador);
		this.create(chefe);
	}

	public void removerChefe(Integer idSeccao, Integer idUtilizador) {
		Query query = entityManager.createQuery(
			"DELETE FROM QUA_MC_SECCOES_CHEFES c WHERE c.ID_SECCAO = :idSeccao AND c.ID_UTILIZADOR = :idUtilizador");
		query.setParameter("idSeccao", idSeccao);
		query.setParameter("idUtilizador", idUtilizador);
		query.executeUpdate();
	}
}
