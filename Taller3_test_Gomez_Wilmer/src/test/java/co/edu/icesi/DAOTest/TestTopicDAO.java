package co.edu.icesi.DAOTest;

import static org.junit.Assert.*;
//import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.Daos.ITopicDAO;
import co.edu.icesi.Daos.TopicDAO;
import co.edu.icesi.model.TsscTopic;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@Rollback(false)
public class TestTopicDAO {

	@Autowired
	private ITopicDAO topicDAO;
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void aTest() {
		assertNotNull(topicDAO);
		
		TsscTopic topic = new TsscTopic();
		topic.setName("new topic");
		topic.setDefaultGroups(4);
		topic.setDefaultSprints(4);
		topic.setDescription("new topic description");
		topicDAO.save(topic);
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void bTest() {
		assertNotNull(topicDAO);
		
		TsscTopic topic = topicDAO.findById(1);
		assertNotNull("Code not found", topic);
		topic.setName("topic update");
		topicDAO.update(topic);
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void cTest() {

		assertNotNull(topicDAO);
		
		TsscTopic topic = topicDAO.findById(1);
		assertNotNull("El tema NO existe", topic);
		topicDAO.delete(topic);
		
	}
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void dTest() {

		assertNotNull(topicDAO);
		TsscTopic topic = new TsscTopic();
		topic.setName("new topic2");
		topic.setDefaultGroups(4);
		topic.setDefaultSprints(4);
		topic.setDescription("new topic description2");
		topic.setGroupPrefix("group t");
		TsscTopic topic2 = topicDAO.findByName("new topic2").get(0);
		assertNotNull("El tema NO existe", topic2);
		
	}
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void eTest() {

		assertNotNull(topicDAO);
		TsscTopic topic = new TsscTopic();
		topic.setName("new topic2");
		topic.setDefaultGroups(4);
		topic.setDefaultSprints(4);
		topic.setDescription("new topic description2");
		topic.setGroupPrefix("group t");
		TsscTopic topic2 = topicDAO.findByDescription("new topic description2").get(0);
		assertNotNull("El tema NO existe", topic2);
		
	}
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void fTest() {

		assertNotNull(topicDAO);
		TsscTopic topic = new TsscTopic();
		topic.setName("new topic2");
		topic.setDefaultGroups(4);
		topic.setDefaultSprints(4);
		topic.setDescription("new topic description2");
		topic.setGroupPrefix("group t");
		TsscTopic topic2 = topicDAO.findByGroupPrefix("group t").get(0);
		assertNotNull("El tema NO existe", topic2);
		
	}
}
