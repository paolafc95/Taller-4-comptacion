package co.edu.icesi.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.model.TsscGame;
import co.edu.icesi.model.TsscStory;
import co.edu.icesi.model.TsscTimecontrol;
import co.edu.icesi.model.TsscTopic;
import co.edu.icesi.service.gameServiceimp;

@RestController
public class GameRestController2 {
	
	@Autowired
	private gameServiceimp service;
	
	@RequestMapping("api/games")
	public Iterable<TsscGame> getGames() {
		try {
			//findById
			return service.findAll();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("api/games/{id}")
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
	
//	@RequestMapping(value = "api/games", method = RequestMethod.POST)
//	public TsscGame save (@RequestBody TsscGame tsscGame) {
//		try {
//			return service.save(tsscGame);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	@RequestMapping(value = "/api/games/{id}/stories", method = RequestMethod.GET)
	 public Iterable<TsscStory> getGameStories(@PathVariable long id) {
		Iterable<TsscStory> result = service.findStories(id);
		
		return result;
	 }
	@RequestMapping(value = "/api/games/{id}/stories/{ids}", method = RequestMethod.GET)
	 public TsscStory getGameStory(@PathVariable long id,@PathVariable long ids) {
		Iterable<TsscStory> result = service.findStories(id);
		for (TsscStory s : result) {
			if (s.getId() == ids)
				return s;	
		}
		return null;
	 }
	@RequestMapping(value = "/api/games/{id}/times", method = RequestMethod.GET)
	 public Iterable<TsscTimecontrol> getGameTimes(@PathVariable long id) {
		Iterable<TsscTimecontrol> result = service.findCrono(id);
		
		return result;
	 }
	@RequestMapping(value = "/api/games/{id}/times/{idt}", method = RequestMethod.GET)
	 public TsscStory getGameTime(@PathVariable long id,@PathVariable long idt) {
		Iterable<TsscStory> result = service.findStories(id);
		for (TsscStory s : result) {
			if (s.getId() == idt)
				return s;	
		}
		return null;
	 }
	@RequestMapping(value = "/api/games/{id}/topic", method = RequestMethod.GET)
	 public TsscTopic getGameTopic(@PathVariable long id) {
		TsscTopic result = service.getGame(id).getTsscTopic();
		
		return result;
	 }
}
