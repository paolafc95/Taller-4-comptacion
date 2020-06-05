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

import co.edu.icesi.Daos.GameDAO;
import co.edu.icesi.delegate.DelegateGame2;
import co.edu.icesi.model.TsscGame;

public class TestGameDelegate {
	
	@Mock
	private GameDAO gameDao;
	
	@Mock
	private TsscGame tsscgame;
	
	@InjectMocks
	private DelegateGame2 delegateGame;
	
	
	@BeforeTest
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void findByIdtest() throws Exception  {
		try {
			Mockito.when(gameDao.findById(1)).thenReturn(tsscgame);
			
			Mockito.verify(gameDao.findById(1));
			delegateGame.findById(1);
			
		} catch (NullPointerException e) {
			// TODO: handle exception
			fail();
		}
		
		
//		long gameId = gameDao.findById(1);
		 
//        Assert.assertEquals(tsscgame, gameId);
//        Mockito.verify(gameDao).findById(gameId);
		
	}
	
	@Test
	public void findAlltest() {
		try {
			List<TsscGame> gl;			
			
			//Mockito.when(gameDao.findAll().thenReturn(gl));
			
			assertThat(gameDao.findAll(), is(true));
			verify(gameDao).findAll();
			
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		
		
	}
	
	@Test
	public void saveTest() {
		try {
			
			TsscGame game = new TsscGame();
			delegateGame.save(game);
			
			Mockito.verify(delegateGame, times(1)).save(game);			
			
		} catch (Exception e) {
			// TODO: handle exception
			fail();
		}
		
		
	}

	@Test
	public void saveNullTest() {
		try {
			
			TsscGame game = new TsscGame();
			delegateGame.save(game);
			
			//Mockito.when(delegateGame.save(null)).thenReturn(false);
			Mockito.verify(delegateGame, times(1)).save(game);
			
			
		} catch (NullPointerException e) {
			// TODO: handle exception
			assertEquals(e.getMessage(), "El juego no puede ser nulo");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		
	}
	
	
}
