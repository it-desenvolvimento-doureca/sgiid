package pt.example.dao;

import java.util.List;
import javax.persistence.Query;
import pt.example.entity.RH_SECTORES_EPI_FAMILIA;

public class RH_SECTORES_EPI_FAMILIADao extends GenericDaoJpaImpl<RH_SECTORES_EPI_FAMILIA, Integer>
		implements GenericDao<RH_SECTORES_EPI_FAMILIA, Integer> {
	public RH_SECTORES_EPI_FAMILIADao() {
		super(RH_SECTORES_EPI_FAMILIA.class);
	}

	public List<RH_SECTORES_EPI_FAMILIA> getall() {
		Query query = entityManager.createQuery(
			"Select a from RH_SECTORES_EPI_FAMILIA a where a.ATIVO = 1");
		return query.getResultList();
	}

	public List<RH_SECTORES_EPI_FAMILIA> getbyid(Integer id) {
		Query query = entityManager.createQuery(
			"Select a from RH_SECTORES_EPI_FAMILIA a where a.ID = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}

	// Famílias configuradas num sector, com a descrição e duração resolvidas.
	// Alimenta as linhas do separador EPI's da ficha do funcionário.
	// C0 = id linha, C1 = id família, C2 = descrição, C3 = duração de uso (dias)
	public List<Object[]> getbysector(Integer codSector) {
		String sql =
			"SELECT a.ID AS C0, a.ID_FAMILIA AS C1, f.DESCRICAO AS C2, f.DURACAO_USO_DIAS AS C3 " +
			"FROM RH_SECTORES_EPI_FAMILIA a " +
			"INNER JOIN QUA_EPI_DIC_FAMILIA f ON f.ID_FAMILIA = a.ID_FAMILIA AND f.ATIVO = 1 " +
			"WHERE a.ATIVO = 1 AND a.COD_SECTOR = :id ORDER BY f.DESCRICAO";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("id", codSector);
		return query.getResultList();
	}
}
