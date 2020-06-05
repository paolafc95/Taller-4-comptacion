package co.edu.icesi;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import co.edu.icesi.model.TsscGame;
import co.edu.icesi.model.TsscGroup;
import co.edu.icesi.model.TsscSprint;
import co.edu.icesi.model.TsscStory;
import co.edu.icesi.model.TsscTimecontrol;
import co.edu.icesi.model.TsscTopic;
import co.edu.icesi.repository.gameRepository;
import co.edu.icesi.repository.sprintRepository;
import co.edu.icesi.repository.storyRepository;
import co.edu.icesi.repository.timeCRepository;
import co.edu.icesi.repository.topicRepository;
import co.edu.icesi.service.gameServiceimp;
import co.edu.icesi.service.topicServiceimp;


public class JuegosTest {

	@Mock
	private gameRepository gameRepository;
	
	@Mock
	private topicRepository repository;
	
	@Mock
	private storyRepository storyRepository;
	@Mock
	private timeCRepository timeCRepository;
	@InjectMocks
	private gameServiceimp serviceimp;
	
	private TsscGame game;
	private TsscTopic topic;
	@BeforeTest
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	@BeforeTest
	public void setUpGame2() {
		
		topic=new TsscTopic();
		topic.setId(14);
		topic.setDefaultGroups(12);
		topic.setDefaultSprints(23);
		
		
		TsscStory sp=new TsscStory();
		sp.setId(121);
		TsscStory sp2=new TsscStory();
		sp2.setId(123);
		
		TsscTimecontrol tc=new TsscTimecontrol();
		tc.setId(43);
		TsscTimecontrol tc2=new TsscTimecontrol();
		tc2.setId(23);
		
		List<TsscTimecontrol> ltc = new ArrayList<>();
		ltc.add(tc);
		ltc.add(tc2);
		List<TsscStory> lsp = new ArrayList<>();
		lsp.add(sp);
		lsp.add(sp2);
		
		topic.setTsscSTimecontrols(ltc);
		topic.setTsscStories(lsp);
		storyRepository.save(sp);
		storyRepository.save(sp2);
		
		timeCRepository.save(tc);
		timeCRepository.save(tc2);
		
		repository.save(topic);
		game = new TsscGame();
		game.setId(45);
		
	//	serviceimp = new gameServiceimp(gameRepository, repository);
		
	}
	
	@Test
	public void testSaveGameNull() {
		try {
			TsscGame game = null;
			serviceimp.addGame(game, -1);
			fail();
		} catch (NullPointerException e) {
			// TODO: handle exception
			assertEquals(e.getMessage(), "El juego no puede ser nulo");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail();
		}
	}
	@Test
	public void testSaveGameExist() {
		try {
			TsscGame game = new TsscGame();
			List<TsscGroup> gr= new ArrayList<>();
			gr.add(new TsscGroup());
			gr.add(new TsscGroup());
			game.setTsscGroups(gr);
			List<TsscSprint> sp= new ArrayList<>();
			sp.add(new TsscSprint());
			sp.add(new TsscSprint());
			game.setTsscSprints(sp);
			serviceimp.addGame(game, -1);
			serviceimp.addGame(game, -1);
			fail();
		} catch (NullPointerException e) {
			// TODO: handle exception
			fail();
		} catch (Exception e) {
			assertEquals(e.getMessage(), "El juego ya existe");
		}
	}
	@Test
	public void testSaveGameGroupsZero() {
		try {
			TsscGame game = new TsscGame();
			List<TsscGroup> gr= new ArrayList<>();
			game.setTsscGroups(gr);
			List<TsscSprint> sp= new ArrayList<>();
			game.setTsscSprints(sp);
			serviceimp.addGame(game, -1);
			fail();
		} catch (NullPointerException e) {
			// TODO: handle exception
			fail();
		} catch (Exception e) {
			assertEquals(e.getMessage(), "La cantidad grupos no puede ser 0");
		}
	}
	@Test
	public void testSaveGameSpringZero() {
		try {
			TsscGame game = new TsscGame();
			List<TsscGroup> gr= new ArrayList<>();
			game.setTsscGroups(gr);
			gr.add(new TsscGroup());
			gr.add(new TsscGroup());
			List<TsscSprint> sp= new ArrayList<>();
			game.setTsscSprints(sp);
			serviceimp.addGame(game, -1);
			fail();
		} catch (NullPointerException e) {
			fail();
			// TODO: handle exception
		} catch (Exception e) {
			assertEquals(e.getMessage(), "La cantidad Sprints no puede ser 0");

			// TODO Auto-generated catch block
			
		}
	}
	@Test
	public void testEditGameNull() {
		try {
			TsscGame game = null;
			serviceimp.setGame(game, -1);
			fail();
		} catch (NullPointerException e) {
			// TODO: handle exception
			assertEquals(e.getMessage(), "El juego no puede ser nulo");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail();
		}
	}
	@Test
	public void testEditGameGroupsZero() {
		try {
			TsscGame game = new TsscGame();
			game.setId(23);
			List<TsscGroup> gr= new ArrayList<>();
			game.setTsscGroups(gr);
			List<TsscSprint> sp= new ArrayList<>();
			game.setTsscSprints(sp);
			serviceimp.addGame(game, -1);
			serviceimp.setGame(game, -1);
			fail();
		} catch (NullPointerException e) {
			// TODO: handle exception
			
			fail();
		} catch (Exception e) {
			
			assertEquals(e.getMessage(), "La cantidad grupos no puede ser 0");
		}
	}
	@Test
	public void testEditGameSpringZero() {
		try {
			TsscGame game = new TsscGame();
			//game.setId();
			List<TsscGroup> gr= new ArrayList<>();
			gr.add(new TsscGroup());
			gr.add(new TsscGroup());
			game.setTsscGroups(gr);
			List<TsscSprint> sp= new ArrayList<>();
			game.setTsscSprints(sp);
			serviceimp.setGame(game, -1);
			fail();
		} catch (NullPointerException e) {
			// TODO: handle exception
			fail();
			// TODO: handle exception
		} catch (Exception e) {
			assertEquals(e.getMessage(), "La cantidad Sprints no puede ser 0");

			// TODO Auto-generated catch block
			
		}
	}
	@Test
	public void testSaveGameTemaNotNull() {
		TsscGame game = new TsscGame();
		List<TsscGroup> gr= new ArrayList<>();
		gr.add(new TsscGroup());
		gr.add(new TsscGroup());
		game.setTsscGroups(gr);
		game.setId(23);
		List<TsscSprint> sp= new ArrayList<>();
		sp.add(new TsscSprint());
		sp.add(new TsscSprint());
		game.setTsscSprints(sp);
		
		try {
			assertTrue(serviceimp.addGame(game, 14));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		
	}
	//
	
	@Test
	public void testSaveGame2TopicNull() {
		
		try {
			serviceimp.addGame2(null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(e.getMessage(),"El tema no puede ser nulo");
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail();
		}
	}
	@Test
	public void testSaveGame2TopicExist() {
		try {
			assertNotNull(serviceimp.addGame2(topic));
		} catch (NullPointerException e) {
			e.printStackTrace();
			fail();
			
				
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			fail();
		}
	}
	@Test
	public void testSaveGame2SameStories() {
		try {
			assertEquals(serviceimp.addGame2(topic).getTsscStories(), topic.getTsscStories());
		} catch (NullPointerException e) {
			e.printStackTrace();
			fail();
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail();
		}
	}
	@Test
	public void testSaveGame2SameTimeControl() {
		try {
			assertEquals(serviceimp.addGame2(topic).getTsscTimecontrols(), topic.getTsscSTimecontrols());
		} catch (NullPointerException e) {
			fail();
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail();
		}
	}
}
