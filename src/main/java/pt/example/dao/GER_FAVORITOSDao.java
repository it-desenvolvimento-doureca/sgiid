package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.GER_ANALISES;
import pt.example.entity.GER_FAVORITOS;

public class GER_FAVORITOSDao extends GenericDaoJpaImpl<GER_FAVORITOS, Integer>
		implements GenericDao<GER_FAVORITOS, Integer> {
	public GER_FAVORITOSDao() {
		super(GER_FAVORITOS.class);
	}

	public List<GER_FAVORITOS> getbyId(Integer id) {
		Query query = entityManager.createQuery("select a,(select TIPO_LISTA_FAVORITOS from GER_UTILIZADORES b where a.ID_UTILIZADOR = b.ID_UTILIZADOR) from GER_FAVORITOS a where a.ID_UTILIZADOR = :id order by a.ORDEM,a.ID_FAVORITO");
		query.setParameter("id", id);
		List<GER_FAVORITOS> data = query.getResultList();
		return data;

	}

}
