package co.edu.icesi.DelegateTEST;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeTest;

import co.edu.icesi.Daos.TopicDAO;
import co.edu.icesi.delegate.DelegateTopic;
import co.edu.icesi.model.TsscTopic;

public class TestTopicDelegate {
	
	@Mock
	private TopicDAO topicDao;
	
	@Mock
	private TsscTopic tssctopic;
	
	@InjectMocks
	private DelegateTopic delegateTopic;
	
	
	@BeforeTest
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void findByIdtest() throws Exception  {
		try {
			Mockito.when(topicDao.findById(1)).thenReturn(tssctopic);
			
			Mockito.verify(topicDao.findById(1));
			delegateTopic.findById(1);
			
		} catch (NullPointerException e) {
			// TODO: handle exception
			fail();
		}
		
		
//		long topicId = topicDao.findById(1);
		 
//        Assert.assertEquals(tssctopic, topicId);
//        Mockito.verify(topicDao).findById(topicId);
		
	}
	
	@Test
	public void findAlltest() {
		try {
			List<TsscTopic> gl;			
			
			//Mockito.when(topicDao.findAll().thenReturn(gl));
			
			assertThat(topicDao.findAll(), is(true));
			verify(topicDao).findAll();
			
		} catch (Exception e) {
			// TODO: handle exception
			fail();
			
		}	
		
	}
	
	@Test
	public void saveTest() {
		try {
			
			TsscTopic topic = new TsscTopic();
			delegateTopic.save(topic);
			
			Mockito.verify(delegateTopic, times(1)).save(topic);			
			
		} catch (Exception e) {
			// TODO: handle exception
			fail();
		}		
	}

	@Test
	public void saveNullTest() {
		try {
			
			TsscTopic topic = new TsscTopic();
			delegateTopic.save(topic);
			
			//Mockito.when(delegateTopic.save(null)).thenReturn(false);
			Mockito.verify(delegateTopic, times(1)).save(topic);
			
			
		} catch (NullPointerException e) {
			// TODO: handle exception
			assertEquals(e.getMessage(), "El tema no puede ser nulo");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		
	}

}
