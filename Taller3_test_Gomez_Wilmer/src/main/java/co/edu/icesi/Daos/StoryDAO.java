package co.edu.icesi.Daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.model.TsscGame;
import co.edu.icesi.model.TsscStory;
@Repository
@Scope("singleton")
public class StoryDAO implements IStoryDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public void save(TsscStory entity) {
		// TODO Auto-generated method stub
		entityManager.persist(entity);
	}

	@Override
	public void update(TsscStory entity) {
		// TODO Auto-generated method stub
		entityManager.merge(entity);
	}

	@Override
	public void delete(TsscStory entity) {
		// TODO Auto-generated method stub
		entityManager.merge(entity);
	}

	@Override
	public List<TsscStory> findByDescription(String descrip) {
		// TODO Auto-generated method stub
		String jpql = "Select a from TsscStory a WHERE a.ALT_DESCRIPTON = '" + descrip + "'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<TsscStory> findAll() {
		// TODO Auto-generated method stub
		String jpql = "Select a from TsscStory a";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public TsscStory findById(long id) {
		// TODO Auto-generated method stub
		return entityManager.find(TsscStory.class, id);
	}
	
	
}
