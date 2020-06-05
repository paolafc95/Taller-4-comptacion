package co.edu.icesi;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import co.edu.icesi.model.TsscTopic;
import co.edu.icesi.repository.topicRepository;
import co.edu.icesi.service.topicServiceimp;

public class TemaTest {

	@Mock
	private topicRepository topicRepository;
	
	@InjectMocks
	private topicServiceimp topicServiceimp;
	
	@BeforeTest
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
		
	}
	@BeforeTest
	public void setUp() {
		
		topicServiceimp = new topicServiceimp(topicRepository);
	}
	@Test
	public void testSaveTemaNull() {
		TsscTopic topic=null;
		try {
			topicServiceimp.addTopic(topic);
		} catch (NullPointerException e) {
			assertEquals(e.getMessage(), "El tema no puede ser nulo");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail();
		}
	}
	@Test
	public void testSaveTemaGroupsZero() {
		TsscTopic topic=new TsscTopic();
		topic.setDefaultGroups(0);
		topic.setDefaultSprints(21);
		try {
			topicServiceimp.addTopic(topic);
			fail();
		} catch (NullPointerException e) {
			fail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertEquals(e.getMessage(), "La cantidad grupos no puede ser 0");
		}
	}
	@Test
	public void testSaveTemaSpringZero() {
		TsscTopic topic=new TsscTopic();
		topic.setDefaultGroups(22);
		topic.setDefaultSprints(0);
		try {
			topicServiceimp.addTopic(topic);
			fail();
		} catch (NullPointerException e) {
			fail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertEquals(e.getMessage(), "La cantidad Sprints no puede ser 0");
		}
	}
	@Test
	public void testSaveTema() {
		TsscTopic topic=new TsscTopic();
		topic.setId(12);
		topic.setDefaultGroups(22);
		topic.setDefaultSprints(12);
		try {
			topicServiceimp.addTopic(topic);
			//verify(topicRepository,times(1)).save(topic);
			TsscTopic temp = topicServiceimp.getTopic(12);
			assertEquals(topic.getId(), temp.getId());
		} catch (NullPointerException e) {
			fail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail();
		}
	}
	@Test
	public void testEditTemaNull() {
		TsscTopic topic=null;
		try {
			topicServiceimp.setTopic(topic);
		} catch (NullPointerException e) {
			assertEquals(e.getMessage(), "El tema no puede ser nulo");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail();
		}
	}
	@Test
	public void testEditTemaGroupsZero() {
		TsscTopic topic=new TsscTopic();
		topic.setDefaultGroups(0);
		topic.setDefaultSprints(21);
		try {
			topicServiceimp.setTopic(topic);
			fail();
		} catch (NullPointerException e) {
			fail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertEquals(e.getMessage(), "La cantidad grupos no puede ser 0");
		}
	}
	@Test
	public void testEditTemaSpringZero() {
		TsscTopic topic=new TsscTopic();
		topic.setDefaultGroups(22);
		topic.setDefaultSprints(0);
		try {
			topicServiceimp.setTopic(topic);
			fail();
		} catch (NullPointerException e) {
			fail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertEquals(e.getMessage(), "La cantidad Sprints no puede ser 0");
		}
	}
	@Test
	public void testEditTema() {
		TsscTopic topic=new TsscTopic();
		topic.setId(12);
		topic.setDefaultGroups(22);
		topic.setDefaultSprints(12);
		try {
			topicServiceimp.addTopic(topic);
			topic = topicServiceimp.getTopic(12);
			topic.setDefaultGroups(24);
			topic.setDefaultSprints(52);
			topic.setName("edited");
			topicServiceimp.setTopic(topic);
			//verify(topicRepository,times(1)).update(topic);
			TsscTopic temp = topicServiceimp.getTopic(12);
			assertNotNull(temp);
			assertEquals(topic.getId(), temp.getId());
			assertNotEquals(22, temp.getDefaultGroups());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail();
		}
	}
}

