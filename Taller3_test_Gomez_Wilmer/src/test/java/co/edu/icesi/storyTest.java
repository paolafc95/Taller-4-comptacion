package co.edu.icesi;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.math.BigDecimal;
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
import co.edu.icesi.model.TsscTopic;
import co.edu.icesi.repository.gameRepository;
import co.edu.icesi.repository.storyRepository;
import co.edu.icesi.repository.topicRepository;
import co.edu.icesi.service.storyService;
import co.edu.icesi.service.storyServiceimp;




public class storyTest {

	@Mock
	private storyRepository storyRepository;
	
	@Mock
	private gameRepository gameRepository;

	@InjectMocks
	private storyServiceimp storyService;
	
	private TsscTopic topic;
	
	private TsscGame game;
	
	
	@BeforeTest
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
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
	public void testSaveStoryNull() {
		
		try {
			TsscStory story=null;
			storyService.addStory(story,-1);
			fail();
		} catch (NullPointerException e) {
			// TODO: handle exception
			assertEquals(e.getMessage(), "la historia no puede ser nula");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail();
		}
		
	}
	@Test
	public void testSaveStoryExist() {
		try {
			TsscStory story = new TsscStory();
			List<TsscGroup> gr= new ArrayList<>();
			gr.add(new TsscGroup());
			gr.add(new TsscGroup());
			story.setId(12);
			story.setInitialSprint(new BigDecimal(12));
			story.setPriority(new BigDecimal(32));
			story.setBusinessValue(new BigDecimal(32));
			
			storyService.addStory(story, game.getId());
			storyService.addStory(story, game.getId());
			fail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertEquals(e.getMessage(), "La historia ya existe");
		}
	}
	@Test
	public void testSaveStorySprintInitZero() {
		try {
			TsscStory story = new TsscStory();
			List<TsscGroup> gr= new ArrayList<>();
			gr.add(new TsscGroup());
			gr.add(new TsscGroup());
			story.setId(12);
			story.setInitialSprint(new BigDecimal(0));
			story.setPriority(new BigDecimal(32));
			story.setBusinessValue(new BigDecimal(32));
			storyService.addStory(story, game.getId());
			fail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertEquals(e.getMessage(), "La cantidad Sprints inicial no puede ser 0");
		}
	}
	@Test
	public void testSaveStoryGameNotExist() {
		try {
			TsscStory story = new TsscStory();
			List<TsscGroup> gr= new ArrayList<>();
			gr.add(new TsscGroup());
			gr.add(new TsscGroup());
			story.setId(12);
			story.setInitialSprint(new BigDecimal(23));
			story.setPriority(new BigDecimal(32));
			story.setBusinessValue(new BigDecimal(32));
			storyService.addStory(story, 1212);
			fail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertEquals(e.getMessage(), "El juego debe existir");
		}
	}
	@Test
	public void testSaveStoryValueBusinessZero() {
		try {
			TsscStory story = new TsscStory();
			List<TsscGroup> gr= new ArrayList<>();
			gr.add(new TsscGroup());
			gr.add(new TsscGroup());
			story.setId(12);
			story.setInitialSprint(new BigDecimal(2));
			story.setPriority(new BigDecimal(32));
			story.setBusinessValue(new BigDecimal(0));
			storyService.addStory(story, game.getId());
			fail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertEquals(e.getMessage(), "el valor de negocio no puede ser 0");
		}
	}
	@Test
	public void testsaveStoryPriorityZero() {
		try {
			TsscStory story = new TsscStory();
			List<TsscGroup> gr= new ArrayList<>();
			gr.add(new TsscGroup());
			gr.add(new TsscGroup());
			story.setId(12);
			story.setInitialSprint(new BigDecimal(3));
			story.setPriority(new BigDecimal(0));
			story.setBusinessValue(new BigDecimal(32));
			storyService.addStory(story, game.getId());
			fail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertEquals(e.getMessage(), "La prioridad no puede ser 0");
		}
	}
	@Test
	public void testEditStorySprintInitZero() {
		try {
			TsscStory story = new TsscStory();
			List<TsscGroup> gr= new ArrayList<>();
			gr.add(new TsscGroup());
			gr.add(new TsscGroup());
			story.setId(12);
			story.setInitialSprint(new BigDecimal(0));
			story.setPriority(new BigDecimal(32));
			story.setBusinessValue(new BigDecimal(32));
			storyService.setStory(story, game.getId());
			fail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertEquals(e.getMessage(), "La cantidad Sprints inicial no puede ser 0");
		}
	}
	@Test
	public void testEditStoryGameNotExist() {
		try {
			TsscStory story = new TsscStory();
			List<TsscGroup> gr= new ArrayList<>();
			gr.add(new TsscGroup());
			gr.add(new TsscGroup());
			story.setId(12);
			story.setInitialSprint(new BigDecimal(23));
			story.setPriority(new BigDecimal(32));
			story.setBusinessValue(new BigDecimal(32));
			storyService.setStory(story, 1212);
			fail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertEquals(e.getMessage(), "El juego debe existir");
		}
	}
	@Test
	public void testEditStoryValueBusinessZero() {
		try {
			TsscStory story = new TsscStory();
			List<TsscGroup> gr= new ArrayList<>();
			gr.add(new TsscGroup());
			gr.add(new TsscGroup());
			story.setId(12);
			story.setInitialSprint(new BigDecimal(2));
			story.setPriority(new BigDecimal(32));
			story.setBusinessValue(new BigDecimal(0));
			storyService.setStory(story, game.getId());
			fail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertEquals(e.getMessage(), "el valor de negocio no puede ser 0");
		}
	}
	@Test
	public void testEditStoryPriorityZero() {
		try {
			TsscStory story = new TsscStory();
			List<TsscGroup> gr= new ArrayList<>();
			gr.add(new TsscGroup());
			gr.add(new TsscGroup());
			story.setId(12);
			story.setInitialSprint(new BigDecimal(3));
			story.setPriority(new BigDecimal(0));
			story.setBusinessValue(new BigDecimal(32));
			storyService.setStory(story, game.getId());
			fail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertEquals(e.getMessage(), "La prioridad no puede ser 0");
		}
	}
	@Test
	public void testSaveStory() {
		try {
			TsscStory story = new TsscStory();
			List<TsscGroup> gr= new ArrayList<>();
			gr.add(new TsscGroup());
			gr.add(new TsscGroup());
			story.setId(15);
			story.setInitialSprint(new BigDecimal(12));
			story.setPriority(new BigDecimal(32));
			story.setBusinessValue(new BigDecimal(32));
			//gameRepository.save(game);
			assertTrue(storyService.addStory(story, game.getId()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
			assertEquals(story.getId(), storyService.getStory(story.getId()).getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail();
		}
	}
	
}

