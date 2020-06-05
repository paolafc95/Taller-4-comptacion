package co.edu.icesi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.model.TsscStory;
import co.edu.icesi.model.TsscTopic;
import co.edu.icesi.service.storyServiceimp;

@Controller
public class TsscStoryController {

	private storyServiceimp serviceimp;
	
	@Autowired
	public TsscStoryController(storyServiceimp serviceimp) {
		this.serviceimp = serviceimp;
		
	}
	@GetMapping("/Stories/")
	public String indexApp(Model model) {
		model.addAttribute("Stories", serviceimp.findAll());
		return "Stories/index";
	}

	@GetMapping("/Stories/add")
	public String addstory(
		Model model,  @ModelAttribute TsscStory story) {
		model.addAttribute("story", new TsscStory());
		
		return "Stories/addStory";
	}

	@PostMapping("/Stories/add")
	public String saveApp(@Validated @ModelAttribute TsscStory story, BindingResult bindingResult,
			@RequestParam(value = "action", required = true) String action,Model model) {
		
		if (!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("story",story);
				
				return "Stories/addStory";
			}
			try {
				serviceimp.save(story);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		model.addAttribute("story",story);
		return "redirect:/Stories/";
	}

	@GetMapping("/Stories/edit/{id}")
	public String showUpdateApp(@PathVariable("id") long id, Model model) {
		TsscStory story = serviceimp.getStory(id);
		if (story == null)
			throw new IllegalArgumentException("Invalid appointment Id:" + id);
		model.addAttribute("story", story);
		
		return "Stories/update-story";
	}

	@PostMapping("/Stories/edit/{id}")
	public String updateApp(@Validated @ModelAttribute TsscStory story, BindingResult bindingResult,
			@PathVariable("id") long id, @RequestParam(value = "action", required = true) String action,Model model) {
		if(bindingResult.hasErrors()) {
			return "Stories/update-story";
		}
		if (action != null && !action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				//game.setPassword(serviceimp.getGame(id).get().getPassword());
				model.addAttribute(story);
				//model.addAttribute("genders", userService.getGenders());
				//model.addAttribute("types", userService.getTypes());
				return "Stories/update-story";
			}
			//user.setPassword(userService.findById(id).get().getPassword());
			try {
				serviceimp.update(story);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "redirect:/Stories/";
	}

	@GetMapping("/Stories/del/{id}")
	public String deleteApp(
			@PathVariable("id") long id) {
		TsscStory story = serviceimp.getStory(id);
				//.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		serviceimp.removeStory(story);
		return "redirect:/Stories/";
	}
}
