package co.edu.icesi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.model.TsscTopic;
import co.edu.icesi.service.topicServiceimp;

@Controller
public class TsscTopicController {

	private topicServiceimp topicServiceimp;
	@Autowired
	public TsscTopicController(topicServiceimp serviceimp) {
		this.topicServiceimp=serviceimp; 
		
	}
	
	
	
	@GetMapping("/Topics/")
	public String indexApp(Model model) {
		model.addAttribute("Topics", topicServiceimp.findAll());
		return "Topics/index";
	}

	@GetMapping("/Topics/add")
	public String addtopic(
		Model model,  @ModelAttribute TsscTopic topic) {
		model.addAttribute("topic", new TsscTopic());
		
		return "Topics/addTopic";
	}

	@PostMapping("/Topics/add")
	public String saveApp(@Validated @ModelAttribute TsscTopic topic, BindingResult bindingResult,
			@RequestParam(value = "action", required = true) String action) {
		if(bindingResult.hasErrors()) {
			return "Topics/addTopic";
		}
		if (!action.equals("Cancel"))
			try {
				topicServiceimp.addTopic(topic);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return "redirect:/Topics/";
	}

	@GetMapping("/Topics/edit/{id}")
	public String showUpdateApp(@PathVariable("id") long id, Model model) {
		TsscTopic topic = topicServiceimp.getTopic(id);
		if (topic == null)
			throw new IllegalArgumentException("Invalid appointment Id:" + id);
		model.addAttribute("topic", topic);
		
		return "Topics/update-topic";
	}

	@PostMapping("/Topics/edit/{id}")
	public String updateApp(@Validated @ModelAttribute TsscTopic topic, BindingResult bindingResult,
			@PathVariable("id") long id, @RequestParam(value = "action", required = true) String action,Model model) {
		if(bindingResult.hasErrors()) {
			return "apps/update-topic";
		}
		if (action != null && !action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				//game.setPassword(serviceimp.getGame(id).get().getPassword());
				model.addAttribute(topic);
				//model.addAttribute("genders", userService.getGenders());
				//model.addAttribute("types", userService.getTypes());
				return "Topics/update-topic";
			}
			//user.setPassword(userService.findById(id).get().getPassword());
			try {
				topicServiceimp.addTopic(topic);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "redirect:/Topics/";
	}

	@GetMapping("/Topics/del/{id}")
	public String deleteApp(
			@PathVariable("id") long id) {
		TsscTopic topic = topicServiceimp.getTopic(id);
				//.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		topicServiceimp.removeTopic(topic);
		return "redirect:/Topics/";
	}
}
