package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.PR_AMOSTRAS_CAB;

public class PR_AMOSTRAS_CABDao extends GenericDaoJpaImpl<PR_AMOSTRAS_CAB, Integer> implements GenericDao<PR_AMOSTRAS_CAB, Integer> {
	public PR_AMOSTRAS_CABDao() {
		super(PR_AMOSTRAS_CAB.class);
	}

	public List<PR_AMOSTRAS_CAB> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a,(select NOME_UTILIZADOR from GER_UTILIZADORES b where b.ID_UTILIZADOR = a.UTZ_CRIA) from PR_AMOSTRAS_CAB a where a.ID_AMOSTRA = :id ");
		query.setParameter("id", id);
		List<PR_AMOSTRAS_CAB> data = query.getResultList();
		return data;

	}


	public List<PR_AMOSTRAS_CAB> getall() {

		Query query = entityManager.createQuery("Select a,(select COR from AB_DIC_LINHA WHERE ID_LINHA  = a.ID_LINHA),(select x.DESCRICAO from PR_DIC_TIPOLOGIA_ENSAIO x where x.ID_TIPOLOGIA_ENSAIO = a.ID_TIPOLOGIA_ENSAIO) as TIPOLOGIA_ENSAIO_DESC,(select NOME_LINHA from AB_DIC_LINHA WHERE ID_LINHA  = a.ID_LINHA) as NOME_LINHA "
				+ " from PR_AMOSTRAS_CAB a where a.ATIVO  = 1 order by DATA_LANCAMENTO desc,ID_AMOSTRA desc");
		List<PR_AMOSTRAS_CAB> data = query.getResultList();
		return data;

	}

}
