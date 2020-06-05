package co.edu.icesi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.ci.validations.ValidacionesForm1;
import co.edu.icesi.model.TsscGame;
import co.edu.icesi.model.TsscStory;
import co.edu.icesi.service.gameServiceimp;

@Controller
public class TsscGameController {
	
	
	private gameServiceimp serviceimp;
	
	@Autowired
	public TsscGameController(gameServiceimp gameServiceimp) {
		this.serviceimp = gameServiceimp;
	}
	
	@GetMapping("/Games/")
	public String indexApp(Model model) {
		model.addAttribute("Games", serviceimp.findAll());
		return "Games/index";
	}
//	@GetMapping("/Games/Stories/{id}")
//	public String stories(Model model, @PathVariable("id") long id) {
//		//model.addAttribute("Games", serviceimp.findAll());
//		model.addAttribute("stories", serviceimp.findStories(id));
//		return "Games/Story";
//	}
	@GetMapping("/Games/Topics/{id}")
	public String topics(Model model,@PathVariable("id") long id) {
		//model.addAttribute("Games", serviceimp.findAll());
		
		model.addAttribute("topics", serviceimp.findStories(id));
		return "Games/Topic";
	}
	
	//cronograma
	@GetMapping("/Games/Cronograma/{id}")
	public String timeControl(Model model, @PathVariable("id") long id) {
		//model.addAttribute("Games", serviceimp.findAll());
		model.addAttribute("cronogramas", serviceimp.findCrono(id));
		return "Games/Cronograma";
	}	
		
	@GetMapping("/Games/add")
	public String addGame(
		Model model,  @ModelAttribute TsscGame game) {
		model.addAttribute("game", new TsscGame());
		
		return "Games/addGame";
	}

	@PostMapping("/Games/add")
	public String saveApp(@Validated ({ValidacionesForm1.class})@ModelAttribute TsscGame game, BindingResult bindingResult,
			@RequestParam(value = "action", required = true) String action,Model model) {
		
		if (!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("game",game);
				return "Games/addGame";
			}
			try {
				serviceimp.save(game);
			} catch (Exception e) {
				System.out.println("error");
				// TODO Auto-generated catch block
				ObjectError dd=new ObjectError("name", e.getMessage());
				bindingResult.addError(dd);
				model.addAttribute("game",game);
				return "Games/addGame";
			}
		}
		model.addAttribute("game",game);
		return "redirect:/Games/";
	}

	@GetMapping("/Games/edit/{id}")
	public String showUpdateApp(@PathVariable("id") long id, Model model) {
		TsscGame game = serviceimp.getGame(id);
		if (game == null)
			throw new IllegalArgumentException("Invalid appointment Id:" + id);
		model.addAttribute("game", game);
		
		return "Games/update-game";
	}

	@PostMapping("/Games/edit/{id}")
	public String updateApp(@Validated @ModelAttribute TsscGame game, BindingResult bindingResult,
			@PathVariable("id") long id, @RequestParam(value = "action", required = true) String action,Model model) {
		if(bindingResult.hasErrors()) {
			return "Games/update-game";
		}
		if (action != null && !action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				//game.setPassword(serviceimp.getGame(id).get().getPassword());
				model.addAttribute(game);
				//model.addAttribute("genders", userService.getGenders());
				//model.addAttribute("types", userService.getTypes());
				return "Games/update-game";
			}
			//user.setPassword(userService.findById(id).get().getPassword());
			try {
				serviceimp.save(game);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "redirect:/Games/";
	}

	@GetMapping("/Games/del/{id}")
	public String deleteApp(
			@PathVariable("id") long id) {
		TsscGame game = serviceimp.getGame(id);
				//.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		serviceimp.removeGame(game);
		return "redirect:/Games/";
	}
	@GetMapping("@{/Games/Stories/{id}")
	public String indexStories(Model model, @PathVariable("id") long id) {
		
		model.addAttribute("stories", serviceimp.findStories(id));
		return "Games/Story";
	}
	
	///
	@GetMapping("/Games/Stories/{id}")
	public String stories(Model model, @PathVariable("id") long id) {
		//model.addAttribute("Games", serviceimp.findAll());
		model.addAttribute("stories", serviceimp.findStories(id));
		
		model.addAttribute("game",serviceimp.getGame(id));
		return "Games/Story";
	}
	@GetMapping("/Games/{idG}/Stories/edit/{id}")
	public String showUpdateStory(@PathVariable("id") long idS,@PathVariable("idG") long idG, Model model) {
		List<TsscStory> stories = serviceimp.getGame(idG).getTsscStories();
		int tam=stories.size();
		TsscStory tempSt=null;
		for (int i = 0; i < tam; i++) {
			if(stories.get(i).getId()==idS) {
				tempSt=stories.get(i);
			}
		}
		if (tempSt == null)
			throw new IllegalArgumentException("Invalid appointment Id:" + idG);
		model.addAttribute("story", tempSt);
		model.addAttribute("game",serviceimp.getGame(idG));
		
		return "Games/update-story";
	}

	@PostMapping("/Games/{idg}/Stories/edit/{id}")
	public String updateApp(@Validated @ModelAttribute TsscStory story, BindingResult bindingResult,
			@PathVariable("id") long id,@PathVariable("idg") long idG, @RequestParam(value = "action", required = true) String action,Model model) {
		if(bindingResult.hasErrors()) {
			return "Games/update-story";
		}
		if (action != null && !action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				//game.setPassword(serviceimp.getGame(id).get().getPassword());
				model.addAttribute("story",story);
				model.addAttribute("game",serviceimp.getGame(idG));
				//model.addAttribute("genders", userService.getGenders());
				//model.addAttribute("types", userService.getTypes());
				return "Games/update-story";
			}
			//user.setPassword(userService.findById(id).get().getPassword());
			try {
				TsscGame game = serviceimp.getGame(idG);
				game.addTsscStory(story);
				serviceimp.update(game);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "redirect:/Games/Story/";
	}
	@GetMapping("/Games/Stories/add")
	public String addstory(
		Model model,  @ModelAttribute TsscGame Game) {
		model.addAttribute("story", new TsscStory());
		System.out.println(Game.getId()+"---"+model.getAttribute("game"));
		
		model.addAttribute("game",Game);
		return "Games/addStory";
	}

	@PostMapping("/Games/Stories/add")
	public String saveApp(@Validated @ModelAttribute TsscStory story, BindingResult bindingResult,
			@RequestParam(value = "action", required = true) String action,Model model,@ModelAttribute TsscGame Game) {
		
		if (!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("story",story);
				
				return "Games/addStory";
			}
			try {
				TsscGame game = Game;
				System.out.println(game.getId()+"pp");
				game.addTsscStory(story);
				serviceimp.update(game);
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		model.addAttribute("game",(TsscGame) model.getAttribute("game"));
		model.addAttribute("story",story);
		return "redirect:/Games/Story/";
	}
}
