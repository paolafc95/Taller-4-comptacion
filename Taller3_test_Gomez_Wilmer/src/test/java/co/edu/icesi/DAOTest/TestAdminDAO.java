package co.edu.icesi.DAOTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.Daos.IAdminDAO;
import co.edu.icesi.model.TsscAdmin;

//@ContextConfiguration("/applicationContext.xml")
//@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
@SpringBootTest
public class TestAdminDAO {

	@Autowired
	private IAdminDAO adminDAO;
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void aTest() {

		Assertions.assertNotNull(adminDAO);
		
		TsscAdmin admin = new TsscAdmin();
		admin.setUsername("admin");
		admin.setUser("The Admin");
		
		adminDAO.save(admin);
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void bTest() {
		Assertions.assertNotNull(adminDAO);
		
		TsscAdmin admin = adminDAO.findById(2);
		Assertions.assertNotNull(admin);
		admin.setUsername("new admin");
		adminDAO.update(admin);
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void cTest() {

		Assertions.assertNotNull(adminDAO);
		
		TsscAdmin admin = adminDAO.findById(2);
		Assertions.assertNotNull(admin);
		adminDAO.delete(admin);
		
	}
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void dTest() {

		Assertions.assertNotNull(adminDAO);
		TsscAdmin admin = new TsscAdmin();
		admin.setUsername("admin2");
		admin.setUser("The Admin2");
		adminDAO.save(admin);
		TsscAdmin admin2 = adminDAO.findById(1);
		Assertions.assertNotNull(admin2);
		adminDAO.delete(admin2);
		
	}
}
