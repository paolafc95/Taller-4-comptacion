package co.edu.icesi.Daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.model.TsscStory;
import co.edu.icesi.model.TsscTimecontrol;
@Repository
@Scope("singleton")
public class TimeDAO implements ITimeDAO{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(TsscTimecontrol entity) {
		// TODO Auto-generated method stub
		entityManager.persist(entity);
	}

	@Override
	public void update(TsscTimecontrol entity) {
		// TODO Auto-generated method stub
		entityManager.merge(entity);
	}

	@Override
	public void delete(TsscTimecontrol entity) {
		// TODO Auto-generated method stub
		entityManager.remove(entity);
	}

	@Override
	public List<TsscTimecontrol> findByName(String name) {
		// TODO Auto-generated method stub
		String jpql = "Select a from TsscTimecontrol a WHERE a.name = '" + name + "'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<TsscTimecontrol> findAll() {
		// TODO Auto-generated method stub
		String jpql = "Select a from TsscTimecontrol a";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public TsscTimecontrol findById(long id) {
		// TODO Auto-generated method stub
		return entityManager.find(TsscTimecontrol.class, id);
	}
}
