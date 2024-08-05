package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.RH_CANDIDATURAS;

public class RH_CANDIDATURASDao extends GenericDaoJpaImpl<RH_CANDIDATURAS, Integer>
		implements GenericDao<RH_CANDIDATURAS, Integer> {
	public RH_CANDIDATURASDao() {
		super(RH_CANDIDATURAS.class);
	}

	public List<RH_CANDIDATURAS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from RH_CANDIDATURAS a where a.ID = :id ");
		query.setParameter("id", id);
		List<RH_CANDIDATURAS> data = query.getResultList();
		return data;

	}

	public List<RH_CANDIDATURAS> getall() {

		Query query = entityManager.createQuery("Select a from RH_CANDIDATURAS a ");
		List<RH_CANDIDATURAS> data = query.getResultList();
		return data;

	}
	
	public List<RH_CANDIDATURAS> getall2() {

		Query query = entityManager.createNativeQuery("Select ID,NOME,EMAIL,DATANASCIMENTO,GENERO,TELEMOVEL,DOMICILIO,FREGUESIA,CONCELHO,CODIGOPOSTAL,DISTRITO,CONTRIBUINTE,CARTACONDUCAO,TRABALHOUDOURECA,CONHECEUDOURECA,FUNCOESINTERESSE,HABILITACOES,AREAFORMACAO,ESTABELECIMENTOENSINO,ANOCONCLUSAO,FORMACOESRELEVANTES,CERTIFICADOEMPILHADORES,ULTIMAEMPRESA,ULTIMAFUNCAO,DATAINICIO,DATAFIM,NOMECV,DISPONIBILIDADE,DATA_SUBMISSAO,DATA_CRIA,DATA_MODIF,UTZ_CRIA,UTZ_MODIF "
				+ " from RH_CANDIDATURAS a ");
		List<RH_CANDIDATURAS> data = query.getResultList();
		return data;

	}

}
