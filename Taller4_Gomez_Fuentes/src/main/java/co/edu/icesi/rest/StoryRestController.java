package co.edu.icesi.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.model.TsscStory;
import co.edu.icesi.service.storyServiceimp;

@RestController
public class StoryRestController {
	
	@Autowired
	private storyServiceimp service;
	
	
	@GetMapping("api/stories/{id}")
	public TsscStory findById(@PathVariable("id") String id) {
		try {
			Integer topicId = Integer.parseInt(id);
			//findById
			return  service.getStory(topicId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping("api/stories")
	public Iterable<TsscStory> findAll(){
		return service.findAll();
	}
	
	@RequestMapping(value = "api/stories", method = RequestMethod.POST)
	public TsscStory save (@RequestBody TsscStory tsscStory) {
		try {
			return service.save(tsscStory);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
