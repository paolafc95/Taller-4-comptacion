package co.icesi.edu.Integration;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import co.edu.icesi.Daos.IGameDAO;
import co.edu.icesi.Daos.IStoryDAO;
import co.edu.icesi.model.TsscGame;
import co.edu.icesi.model.TsscGroup;
import co.edu.icesi.model.TsscSprint;
import co.edu.icesi.model.TsscStory;
import co.edu.icesi.model.TsscTopic;
import co.edu.icesi.repository.gameRepository;
import co.edu.icesi.repository.storyRepository;
import co.edu.icesi.repository.topicRepository;
import co.edu.icesi.service.storyService;
import co.edu.icesi.service.storyServiceimp;



@SpringBootTest
public class storyTest {

	
	@Autowired
	private IGameDAO gameRepository;
	//private gameRepository gameRepository;
	
	@Autowired
	private IStoryDAO storyRepository;
	//private storyRepository storyRepository;

	@Autowired
	private storyServiceimp storyService;
	
	private TsscTopic topic;
	
	private TsscGame game;
	
	@BeforeTest
	public void setUp() {
		
		topic=new TsscTopic();
		topic.setId(14);
		topic.setDefaultGroups(12);
		topic.setDefaultSprints(23);
		
		game = new TsscGame();
		game.setId(13);
		List<TsscGroup> gr= new ArrayList<>();
		gr.add(new TsscGroup());
		gr.add(new TsscGroup());
		game.setTsscGroups(gr);
		List<TsscSprint> sp= new ArrayList<>();
		sp.add(new TsscSprint());
		sp.add(new TsscSprint());
		game.setTsscSprints(sp);
	
		gameRepository.save(game);
		storyService = new storyServiceimp(gameRepository, storyRepository);
	}
	@Test
	public void testSaveStory() {
		try {
			TsscStory story = new TsscStory();
			List<TsscGroup> gr= new ArrayList<>();
			gr.add(new TsscGroup());
			gr.add(new TsscGroup());
			story.setId(76);
			story.setInitialSprint(new BigDecimal(12));
			story.setPriority(new BigDecimal(32));
			story.setBusinessValue(new BigDecimal(32));
			//gameRepository.save(game);
			assertTrue(storyService.addStory(story, game.getId()));
			//assertEquals(story, storyService.getStory(story.getId()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testEditStory() {
		try {
			TsscStory story = new TsscStory();
			List<TsscGroup> gr= new ArrayList<>();
			gr.add(new TsscGroup());
			gr.add(new TsscGroup());
			story.setId(12);
			story.setInitialSprint(new BigDecimal(34));
			story.setPriority(new BigDecimal(33));
			story.setBusinessValue(new BigDecimal(34));
			storyService.setStory(story, game.getId());
			assertEquals(story, storyService.getStory(story.getId()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail();
		}
	}
	
}

