package co.edu.icesi.Daos;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.model.TsscStory;
import co.edu.icesi.model.TsscTopic;
@Repository
@Scope("singleton")
public class TopicDAO implements ITopicDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public void save(TsscTopic entity) {
		// TODO Auto-generated method stub
		entityManager.persist(entity);
	}

	@Override
	public void update(TsscTopic entity) {
		// TODO Auto-generated method stub
		entityManager.merge(entity);
	}

	@Override
	public void delete(TsscTopic entity) {
		// TODO Auto-generated method stub
		entityManager.merge(entity);
	}

	@Override
	public List<TsscTopic> findByName(String name) {
		// TODO Auto-generated method stub
		String jpql = "Select a from TsscTopic a WHERE a.placa = '" + name + "'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<TsscTopic> findByDescription(String name) {
		// TODO Auto-generated method stub
		String jpql = "Select a from TsscTopic a WHERE a.description = '" + name + "'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<TsscTopic> findByGroupPrefix(String name) {
		// TODO Auto-generated method stub
		String jpql = "Select a from TsscTopic a WHERE a.GROUP_PREFIX = '" + name + "'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<TsscTopic> findAll() {
		String jpql = "Select a from TsscTopic a";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public TsscTopic findById(long id) {
		// TODO Auto-generated method stub
		return entityManager.find(TsscTopic.class, id);
	}
	
}
