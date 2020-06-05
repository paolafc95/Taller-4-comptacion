package co.edu.icesi.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.model.TsscGame;
import co.edu.icesi.service.gameServiceimp;

@RestController
public class GameRestController2 {
	
	@Autowired
	private gameServiceimp service;
	
	
	@GetMapping("api/games/{id}")
	public TsscGame findById(@PathVariable("id") String id) {
		try {
			Integer gameId = Integer.parseInt(id);
			//findById
			return service.getGame(gameId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping("api/games")
	public Iterable<TsscGame> findAll(){
		return service.findAll();
	}
	
	@RequestMapping(value = "api/games", method = RequestMethod.POST)
	public TsscGame save (@RequestBody TsscGame tsscGame) {
		try {
			return service.save(tsscGame);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
