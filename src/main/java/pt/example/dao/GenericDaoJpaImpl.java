package pt.example.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class GenericDaoJpaImpl<T, PK> 
    implements GenericDao<T, PK> {

    protected Class<T> entityClass;

    @PersistenceContext(unitName ="persistenceUnit")
    protected EntityManager entityManager;

    public GenericDaoJpaImpl(Class<T> clazz) {
    	this.entityClass = clazz;
    }

	public T create(T t) {
        this.entityManager.persist(t);
        return t;
	}

	public T read(PK id) {
        return this.entityManager.find(entityClass, id);
	}

	public T update(T t) {
		// TODO Auto-generated method stub
        return this.entityManager.merge(t);
	}

	public void delete(T t) {
        t = this.entityManager.merge(t);
        this.entityManager.remove(t);
		
	}

	 public List<T> allEntries() {
	        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	        CriteriaQuery<T> cq = cb.createQuery(entityClass);
	        Root<T> rootEntry = cq.from(entityClass);
	        CriteriaQuery<T> all = cq.select(rootEntry);
	        TypedQuery<T> allQuery = entityManager.createQuery(all);
	        return allQuery.getResultList();
	 }
	

}