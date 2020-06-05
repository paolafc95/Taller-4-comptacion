package co.edu.icesi.Daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.model.TsscAdmin;
@Repository
@Scope("singleton")
public class AdminDAO implements IAdminDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(TsscAdmin entity) {
		// TODO Auto-generated method stub
		entityManager.persist(entity);
	}

	@Override
	public void update(TsscAdmin entity) {
		// TODO Auto-generated method stub
		entityManager.merge(entity);
	}

	@Override
	public void delete(TsscAdmin entity) {
		// TODO Auto-generated method stub
		entityManager.remove(entity);
	}

	@Override
	public List<TsscAdmin> findByUsername(String username) {
		// TODO Auto-generated method stub
		String jpql = "Select a from TsscAdmin a WHERE a.username = '" + username + "'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<TsscAdmin> findAll() {
		// TODO Auto-generated method stub
		String jpql = "Select a from TsscAdmin a";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public TsscAdmin findById(long id) {
		// TODO Auto-generated method stub
		return entityManager.find(TsscAdmin.class, id);
	}

}
