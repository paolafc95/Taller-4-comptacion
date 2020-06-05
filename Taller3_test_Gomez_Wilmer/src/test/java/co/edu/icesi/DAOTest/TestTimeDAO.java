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

import co.edu.icesi.Daos.ITimeDAO;
import co.edu.icesi.model.TsscTimecontrol;

@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
public class TestTimeDAO {

	@Autowired
	private ITimeDAO timeDAO;
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void aTest() {

		assertNotNull(timeDAO);
		
		TsscTimecontrol time = new TsscTimecontrol();
		time.setName("new time");
		time.setState("finished");
		timeDAO.save(time);
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void bTest() {
		assertNotNull(timeDAO);
		
		TsscTimecontrol time = timeDAO.findById(2);
		assertNotNull("Code not found", time);
		time.setName("update time");
		timeDAO.update(time);
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void cTest() {

		assertNotNull(timeDAO);
		
		TsscTimecontrol time = timeDAO.findById(2);
		assertNotNull("El cliente NO existe", time);
		timeDAO.delete(time);
		
	}
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void dTest() {

		assertNotNull(timeDAO);
		
		TsscTimecontrol time = new TsscTimecontrol();
		time.setName("new time");
		time.setState("finished");
		timeDAO.save(time);
		
		TsscTimecontrol time2 = timeDAO.findByName("new time").get(0);
		assertNotNull("El cronograma NO existe", time2);
		timeDAO.delete(time2);
		
	}
}
