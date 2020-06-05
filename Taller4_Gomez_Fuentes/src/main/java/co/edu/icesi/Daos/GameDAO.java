package co.edu.icesi.Daos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.model.TsscAdmin;
import co.edu.icesi.model.TsscGame;
import co.edu.icesi.model.TsscTimecontrol;
import co.edu.icesi.model.TsscTopic;
@Repository
@Scope("singleton")
public class GameDAO implements IGameDAO{
	
	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public void save(TsscGame entity) {
		// TODO Auto-generated method stub
		
		entityManager.persist(entity);
	}

	@Override
	public void update(TsscGame entity) {
		// TODO Auto-generated method stub
		entityManager.merge(entity);
	}

	@Override
	public void delete(TsscGame entity) {
		// TODO Auto-generated method stub
		entityManager.remove(entity);
	}

	@Override
	public List<TsscGame> findByName(String name) {
		// TODO Auto-generated method stub
		String jpql = "Select a from TsscGame a WHERE a.name = '" + name + "'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<TsscGame> findByNameTopic(String name) {
		// TODO Auto-generated method stub
		String jpql = "Select a.tsscGames from TsscTopic a WHERE a.name = '" + name + "'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<TsscGame> findByDescriptionTopic(String topic) {
		// TODO Auto-generated method stub
		String jpql = "Select a.tsscGames from TsscTopic a WHERE a.description = '" + topic + "'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<TsscGame> findByIdTopic(long id) {
		// TODO Auto-generated method stub
		String jpql = "Select a.tsscGames from TsscTopic a WHERE a.id = '" + id + "'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<TsscGame> findByDateRange(LocalDate inicial, LocalDate fin) {
		// TODO Auto-generated method stub
		String jpql = "Select a from TsscGame a WHERE a.SCHEDULED_DATE >= '" + inicial + "' AND a.SCHEDULED_DATE <= '" + fin + "'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<TsscGame> findByDateTime(LocalDate inicial, LocalTime inicio, LocalTime fin) {
		// TODO Auto-generated method stub
		String jpql = "Select a from TsscGame a WHERE a.SCHEDULED_DATE = '" + inicial + "' AND a.START_TIME >= '" + inicio + "'"+ "' AND a.START_TIME <= '" + fin + "'";
		return entityManager.createQuery(jpql).getResultList();
	}
	@Override
	public List<TsscGame> findByDate(LocalDate date) {
		// TODO Auto-generated method stub
		String q = "select g from TsscGame g where :date = g.id.SCHEDULED_DATE and (g.tsscStories having count(g) < 10 or g.tsscTimecontrols having count(g) < 1)";
		Query query = entityManager.createQuery(q);
		query.setParameter("date", date);
		return query.getResultList();
	}
	@Override
	public List<TsscTopic> topicsByDateGame(LocalDate date){
		//Game.SCHEDULED_DATE AS date
		String jpql = "SELECT Game.name AS Game_Name,Game.SCHEDULED_TIME AS time, Topic.*"
				+ "FROM TsscTopic AS Topic"+ 
				"JOIN TsscGame AS Game" + 
				"ON (Topic.tsscTopic = Game.TSSC_TOPIC_ID)"+
				"WHERE Game.SCHEDULED_DATE = '"+date+"'"+
				"ORDER BY time";
		return entityManager.createQuery(jpql).getResultList();
	}
	@Override
	public List<TsscGame> findAll() {
		// TODO Auto-generated method stub
		String jpql = "Select a from TsscGame a";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public TsscGame findById(long id) {
		// TODO Auto-generated method stub
		return entityManager.find(TsscGame.class, id);
	}

	@Override
	public void save(TsscTimecontrol entity) {
		// TODO Auto-generated method stub
		entityManager.persist(entity);
		
	}

}
