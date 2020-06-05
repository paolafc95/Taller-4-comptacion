package co.edu.icesi.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.model.TsscTopic;
import co.edu.icesi.service.topicServiceimp;

@RestController
public class TopicRestController {
	
	@Autowired
	private topicServiceimp service;
	
	
	@GetMapping("api/topics/{id}")
	public TsscTopic findById(@PathVariable("id") String id) {
		try {
			Integer topicId = Integer.parseInt(id);
			return  service.getTopic(topicId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping("api/topics")
	public Iterable<TsscTopic> findAll(){
		return service.findAll();
	}
	
	@RequestMapping(value = "api/topics", method = RequestMethod.POST)
	public TsscTopic save (@RequestBody TsscTopic tsscTopic) {
		try {
			return service.addTopic(tsscTopic);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
