package co.edu.icesi.DAOTest;

import static org.junit.Assert.*;
//import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

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

import co.edu.icesi.Daos.IStoryDAO;
import co.edu.icesi.model.TsscStory;

@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
public class TestStoryDAO {

	@Autowired
	private IStoryDAO storyDAO;
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void aTest() {

		assertNotNull(storyDAO);
		
		TsscStory story = new TsscStory();
		story.setAltDescripton("new description");
		story.setAltDescShown("new");
		story.setBusinessValue(new BigDecimal(12));
		story.setDescription("new description story");
		story.setInitialSprint(new BigDecimal(3));
		story.setNumber(new BigDecimal(3));
		story.setPriority(new BigDecimal(3));
		
		storyDAO.save(story);
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void bTest() {
		assertNotNull(storyDAO);
		
		TsscStory story = storyDAO.findById(2);
		assertNotNull("Code not found", story);
		story.setDescription("story update");
		storyDAO.update(story);
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void cTest() {

		assertNotNull(storyDAO);
		
		TsscStory story = storyDAO.findById(2);
		assertNotNull("La historia NO existe", story);
		storyDAO.delete(story);
		
	}
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void dTest() {

		assertNotNull(storyDAO);
		TsscStory story = new TsscStory();
		story.setAltDescripton("new description");
		story.setAltDescShown("new");
		story.setBusinessValue(new BigDecimal(12));
		story.setDescription("new description story");
		story.setInitialSprint(new BigDecimal(3));
		story.setNumber(new BigDecimal(3));
		story.setPriority(new BigDecimal(3));
		
		TsscStory story2 = storyDAO.findByDescription("new description story").get(0);
		assertNotNull("La historia NO existe", story2);
		
	}

}
