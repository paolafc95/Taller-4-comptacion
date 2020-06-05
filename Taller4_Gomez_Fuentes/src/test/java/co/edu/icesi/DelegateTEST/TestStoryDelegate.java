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

import co.edu.icesi.Daos.StoryDAO;
import co.edu.icesi.delegate.DelegateStory;
import co.edu.icesi.model.TsscStory;

public class TestStoryDelegate {
	
	@Mock
	private StoryDAO storyDao;
	
	@Mock
	private TsscStory tsscstory;
	
	@InjectMocks
	private DelegateStory delegateStory;
	
	
	@BeforeTest
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void findByIdtest() throws Exception  {
		try {
			Mockito.when(storyDao.findById(1)).thenReturn(tsscstory);
			
			Mockito.verify(storyDao.findById(1));
			delegateStory.findById(1);
			
		} catch (NullPointerException e) {
			// TODO: handle exception
			fail();
		}
		
		
//		long gameId = storyDao.findById(1);
		 
//        Assert.assertEquals(tsscstory, gameId);
//        Mockito.verify(storyDao).findById(gameId);
		
	}
	
	@Test
	public void findAlltest() {
		try {
			List<TsscStory> gl;			
			
			//Mockito.when(storyDao.findAll().thenReturn(gl));
			
			assertThat(storyDao.findAll(), is(true));
			verify(storyDao).findAll();
			
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		
	}
	
	@Test
	public void saveTest() throws Exception{
		try {
			
			TsscStory story = new TsscStory();
			delegateStory.save(story);
			
			Mockito.verify(delegateStory, times(1)).save(story);			
			
		} catch (Exception e) {
			// TODO: handle exception
			fail();
		}	
	}

	@Test
	public void saveNullTest() {
		try {
			
			TsscStory story = new TsscStory();
			delegateStory.save(story);
			
			//Mockito.when(delegateStory.save(null)).thenReturn(false);
			Mockito.verify(delegateStory, times(1)).save(story);
			
			
		} catch (NullPointerException e) {
			// TODO: handle exception
			assertEquals(e.getMessage(), "La historia es nula");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		
		
	}


}
