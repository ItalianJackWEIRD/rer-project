package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Struttura;
import it.uniroma3.siw.repository.StrutturaRepository;
import it.uniroma3.siw.service.StrutturaService;

@Controller
public class StrutturaController {

	@Autowired
	private StrutturaService strutturaService;

	@Autowired 
	private StrutturaRepository strutturaRepository;

	@GetMapping("/struttura/{id}")
	public String getStruttura(@PathVariable("id") Long id, Model model) {
		model.addAttribute("struttura", this.strutturaService.findById(id));
		return "struttura.html";
	}

	@GetMapping("/struttura")		//SENZA S
	public String getStrutture(Model model) {
		model.addAttribute("strutture", this.strutturaService.findAll());
		return "strutture.html";
	}

	@GetMapping("/formNewStruttura")
	public String formNewstruttura(Model model) {
		model.addAttribute("struttura", new Struttura());
		return "formNewStruttura.html";
	}

	@PostMapping("/struttura")
	public String newStruttura(@ModelAttribute("struttura") Struttura struttura, Model model) {
		if(!strutturaRepository.existsByNameAndCity(struttura.getName(), struttura.getCity())) {
			this.strutturaService.save(struttura);
			model.addAttribute("struttura", struttura);
			return "redirect:/struttura/" + struttura.getId();
		}
		else {
			model.addAttribute("messaggioErrore", "Questo film esiste già");
			return "formNewstruttura.html";
		}			
	}

	@GetMapping("/formSearchStrutture")
	public String formSearchStrutture() {
		return "formSearchStrutture.html";
	}

	@PostMapping("/searchStrutture")
	public String searchStrutture(Model model, @RequestParam String city) {
		model.addAttribute("strutture", this.strutturaService.findByCity(city));
		return "foundStrutture.html";
	}

}
