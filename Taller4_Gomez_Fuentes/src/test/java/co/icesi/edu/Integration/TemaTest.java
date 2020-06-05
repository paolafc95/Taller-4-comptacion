package co.icesi.edu.Integration;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import co.edu.icesi.Daos.ITopicDAO;
import co.edu.icesi.model.TsscTopic;
import co.edu.icesi.repository.topicRepository;
import co.edu.icesi.service.topicServiceimp;

@SpringBootTest
public class TemaTest {

	@Autowired
	private topicServiceimp topicServiceimp;
	@Autowired
	//private topicRepository topicRepository;
	private ITopicDAO topicRepository;
	
	@BeforeTest
	public void setUp() {
		
		topicServiceimp = new topicServiceimp(topicRepository);
	}
	@Test
	public void testSaveTema() {
		TsscTopic topic=new TsscTopic();
		topic.setId(12);
		topic.setDefaultGroups(22);
		topic.setDefaultSprints(12);
		try {
			topicServiceimp.addTopic(topic);
			TsscTopic temp = topicServiceimp.getTopic(12);
			assertNotNull(temp);
			assertEquals(topic, temp);
		} catch (NullPointerException e) {
			fail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail();
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
			topic.setId(14);
			topic.setDefaultGroups(24);
			topic.setDefaultSprints(52);
			topic.setName("edited");
			topicServiceimp.setTopic(topic);
			TsscTopic temp = topicServiceimp.getTopic(12);
			assertNotNull(temp);
			assertEquals(topic.getId(), temp.getId());
			assertNotEquals(22, temp.getDefaultGroups());
		} catch (NullPointerException e) {
			fail();		
			} catch (Exception e) {
			// TODO Auto-generated catch block
			fail();
		}
	}
}

