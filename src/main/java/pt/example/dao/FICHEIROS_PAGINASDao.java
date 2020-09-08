package pt.example.dao;

import java.util.List;

import javax.persistence.Query;

import pt.example.entity.FICHEIROS_PAGINAS;

public class FICHEIROS_PAGINASDao extends GenericDaoJpaImpl<FICHEIROS_PAGINAS, Integer>
		implements GenericDao<FICHEIROS_PAGINAS, Integer> {
	public FICHEIROS_PAGINASDao() {
		super(FICHEIROS_PAGINAS.class);
	}

	public List<FICHEIROS_PAGINAS> getbyid(Integer id) {

		Query query = entityManager.createQuery("Select a from FICHEIROS_PAGINAS a where a.ID_FICHEIRO = :id");
		query.setParameter("id", id);
		List<FICHEIROS_PAGINAS> data = query.getResultList();
		return data;

	}

	public List<FICHEIROS_PAGINAS> getbyidPagina(Integer id,String pagina) {

		Query query = entityManager.createQuery("Select a,(select b.NOME_UTILIZADOR from GER_UTILIZADORES b where b.ID_UTILIZADOR = a.ID_UTZ_CRIA) from FICHEIROS_PAGINAS a where a.ID_PAGINA = :id and a.PAGINA = :pagina ");
		query.setParameter("id", id);
		query.setParameter("pagina", pagina);
		List<FICHEIROS_PAGINAS> data = query.getResultList();
		return data;

	}
	
	public List<FICHEIROS_PAGINAS> getall() {

		Query query = entityManager.createQuery("Select a from FICHEIROS_PAGINAS a ");
		List<FICHEIROS_PAGINAS> data = query.getResultList();
		return data;

	}
	
}
