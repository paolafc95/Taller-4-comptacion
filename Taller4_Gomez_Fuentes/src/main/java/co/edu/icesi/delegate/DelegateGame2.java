package co.edu.icesi.delegate;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.model.TsscGame;

@Component
public class DelegateGame2 {
	
	public final static String URI= "http://localhost:8080/";
	public RestTemplate rest = new RestTemplate();
	
		
	public TsscGame findById(long id) throws Exception {
		TsscGame game = rest.getForObject(URI + "api/games/" + id , TsscGame.class);
		if (game == null) {
			throw new Exception("Game is null");			
		}
		return game;
	}
	
	public Iterable<TsscGame> findAll() {

		TsscGame[] games = rest.getForObject(URI + "api/games", TsscGame[].class);
		List<TsscGame> gl;
		try {
			gl = Arrays.asList(games);
			return gl;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public void save(TsscGame tsscGame) throws Exception {

		TsscGame game = rest.postForEntity(URI + "api/games", tsscGame, TsscGame.class).getBody();

		if (game == null ) {
			throw new Exception("Game is null");
		}
	}
	
	public void delete(Integer id) {
		rest.delete(URI + "api/games/" + id);
	}
	


}
