package co.edu.icesi.DAOTest;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.Daos.IGameDAO;
import co.edu.icesi.Daos.ITopicDAO;
import co.edu.icesi.model.TsscGame;
import co.edu.icesi.model.TsscStory;
import co.edu.icesi.model.TsscTimecontrol;
import co.edu.icesi.model.TsscTopic;


@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
public class TestGameDAO {

	@Autowired
	private IGameDAO gameDAO;
	@Autowired
	private ITopicDAO topicDAO;
	
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void aTest() {

		assertNotNull(gameDAO);
		
		TsscGame game = new TsscGame();
		game.setName("juego1");
		game.setNGroups(3);
		game.setNSprints(4);
		game.setAdminPassword("123");
		game.setGuestPassword("123");
		game.setUserPassword("123");	
		gameDAO.save(game);
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void bTest() {
		assertNotNull(gameDAO);
		
		TsscGame game = gameDAO.findById(2);
		assertNotNull("Code not found", game);
		game.setName("juego principal");
		gameDAO.update(game);
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void cTest() {

		assertNotNull(gameDAO);
		
		TsscGame game = gameDAO.findById(1);
		assertNotNull("El juego NO existe", game);
		gameDAO.delete(game);
		
	}
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void dTest() {
		assertNotNull(gameDAO);
		TsscTopic topic = new TsscTopic();
		topic.setName("new topic");
		topic.setDefaultGroups(4);
		topic.setDefaultSprints(4);
		topic.setDescription("new topic description");
		
		
		TsscGame game = gameDAO.findById(1);
		assertNotNull("Code not found", game);
		game.setTsscTopic(topic);
		gameDAO.update(game);
		List<TsscGame> d = new ArrayList<TsscGame>();
		d.add(game);
		topic.setTsscGames(d);
		topicDAO.save(topic);
		
		List<TsscGame> game2 = gameDAO.findByNameTopic("new topic");
		assertNotNull(game2);
		
	}
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void eTest() {
		assertNotNull(gameDAO);
		TsscTopic topic = new TsscTopic();
		topic.setName("new topic");
		topic.setDefaultGroups(4);
		topic.setDefaultSprints(4);
		topic.setDescription("new topic description");
		
		TsscGame game = gameDAO.findById(1);
		assertNotNull("Code not found", game);
		game.setTsscTopic(topic);
		List<TsscGame> d = new ArrayList<TsscGame>();
		d.add(game);
		topic.setTsscGames(d);
		topicDAO.save(topic);
		gameDAO.update(game);
		
		
		List<TsscGame> game2 = gameDAO.findByDescriptionTopic("new topic description");
		assertNotNull(game2);
		
	}
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void fTest() {
		assertNotNull(gameDAO);
		TsscTopic topic = new TsscTopic();
		topic.setName("new topic");
		topic.setDefaultGroups(4);
		topic.setDefaultSprints(4);
		topic.setDescription("new topic description");
	
		
		TsscGame game = gameDAO.findById(1);
		assertNotNull("Code not found", game);
		game.setTsscTopic(topic);
		List<TsscGame> d = new ArrayList<TsscGame>();
		d.add(game);
		topic.setTsscGames(d);
		topicDAO.save(topic);
		gameDAO.update(game);
		
		List<TsscGame> game2 = gameDAO.findByIdTopic(1);
		assertNotNull(game2);
		
	}
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void gTest() {
		assertNotNull(gameDAO);
		TsscGame game = new TsscGame();
		game.setName("juego12");
		game.setNGroups(3);
		game.setNSprints(4);
		game.setAdminPassword("123");
		game.setGuestPassword("123");
		game.setUserPassword("123");
		LocalDate gamedate = LocalDate.of(2020, 3, 15);
		game.setScheduledDate(gamedate);
		gameDAO.save(game);
		LocalDate inicial = LocalDate.of(2020, 3, 3);
		LocalDate fin = LocalDate.of(2020, 4, 3);
		List<TsscGame> game2 = gameDAO.findByDateRange(inicial, fin);
		assertNotNull(game2);
	}
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void hTest() {
		assertNotNull(gameDAO);
		TsscGame game = new TsscGame();
		game.setName("juego12");
		game.setNGroups(3);
		game.setNSprints(4);
		game.setAdminPassword("123");
		game.setGuestPassword("123");
		game.setUserPassword("123");
		LocalDate gamedate = LocalDate.of(2020, 3, 15);
		LocalTime gameTime = LocalTime.of(4, 22);
		game.setScheduledDate(gamedate);
		game.setScheduledTime(gameTime);
		gameDAO.save(game);
		LocalTime inicio = LocalTime.of(1, 22);
		LocalTime fin = LocalTime.of(5, 22);
		List<TsscGame> game2 = gameDAO.findByDateTime(gamedate, inicio,fin);
		assertNotNull(game2);
		
		
	}
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void iTest() {
		assertNotNull(gameDAO);
		TsscGame game = new TsscGame();
		game.setName("juego12");
		game.setNGroups(3);
		game.setNSprints(4);
		game.setAdminPassword("123");
		game.setGuestPassword("123");
		game.setUserPassword("123");
		LocalDate gamedate = LocalDate.of(2020, 3, 15);
		game.setScheduledDate(gamedate);
		game.setTsscTimecontrol(new ArrayList<TsscTimecontrol>());
		game.setTsscStories(new ArrayList<TsscStory>());
		gameDAO.save(game);
		List<TsscGame> game2 = gameDAO.findByDate(gamedate);
		assertNotNull(game2);
	}
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void jTest() {
		assertNotNull(gameDAO);
		TsscTopic topic = new TsscTopic();
		topic.setName("new topic");
		topic.setDefaultGroups(4);
		topic.setDefaultSprints(4);
		topic.setDescription("new topic description");
		TsscGame game = new TsscGame();
		game.setName("juego12");
		game.setNGroups(3);
		game.setNSprints(4);
		game.setAdminPassword("123");
		game.setGuestPassword("123");
		game.setUserPassword("123");
		LocalDate gamedate = LocalDate.of(2020, 3, 15);
		game.setScheduledDate(gamedate);
		game.setTsscTimecontrol(new ArrayList<TsscTimecontrol>());
		game.setTsscStories(new ArrayList<TsscStory>());
		
		game.setTsscTopic(topic);
		List<TsscGame> d = new ArrayList<TsscGame>();
		d.add(game);
		topic.setTsscGames(d);
		topicDAO.save(topic);
		gameDAO.save(game);
		
		List<TsscTopic> game2 = gameDAO.topicsByDateGame(gamedate);
		assertNotNull(game2);
		
	}
}
