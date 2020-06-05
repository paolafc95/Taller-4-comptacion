package co.icesi.edu.Integration;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import co.edu.icesi.Daos.IGameDAO;
import co.edu.icesi.Daos.ITopicDAO;
import co.edu.icesi.model.TsscGame;
import co.edu.icesi.model.TsscGroup;
import co.edu.icesi.model.TsscSprint;
import co.edu.icesi.model.TsscStory;
import co.edu.icesi.model.TsscTimecontrol;
import co.edu.icesi.model.TsscTopic;
import co.edu.icesi.repository.storyRepository;
import co.edu.icesi.repository.timeCRepository;
import co.edu.icesi.service.gameServiceimp;

@SpringBootTest
public class JuegosTest {

	@Autowired
	private IGameDAO gameRepository;
	//private gameRepository gameRepository;
	
	@Autowired
	private ITopicDAO repository;
	//private topicRepository repository;
	
	@Autowired
	private storyRepository storyRepository;
	@Autowired
	private timeCRepository timeCRepository;
	@Autowired
	private gameServiceimp serviceimp;
	
	private TsscTopic topic;
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
		
		serviceimp = new gameServiceimp(gameRepository, repository);
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
			fail();
		}
		
	}
	//
	@Test
	public void testSaveGame2TopicExist() {
		try {
			assertNotNull(serviceimp.addGame2(topic));
		} catch (NullPointerException e) {
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
