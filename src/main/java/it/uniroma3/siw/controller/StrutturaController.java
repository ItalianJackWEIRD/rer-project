package it.uniroma3.siw.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Host;
import it.uniroma3.siw.model.Immagine;
import it.uniroma3.siw.model.Prenotazione;
import it.uniroma3.siw.model.Struttura;
import it.uniroma3.siw.model.auth.User;
import it.uniroma3.siw.repository.StrutturaRepository;
import it.uniroma3.siw.service.HostService;
import it.uniroma3.siw.service.ImmagineService;
import it.uniroma3.siw.service.PrenotazioneService;
import it.uniroma3.siw.service.StrutturaService;

@Controller
public class StrutturaController extends GlobalController {

	@Autowired
	private StrutturaService strutturaService;

	@Autowired
	private HostService hostService;

	@Autowired
	private StrutturaRepository strutturaRepository;

	@Autowired
	private PrenotazioneService prenotazioneService;

	@Autowired
	private ImmagineService immagineService;

	@GetMapping("/struttura/{id}")
	public String getStruttura(@PathVariable("id") Long id, Model model) {
		model.addAttribute("struttura", this.strutturaService.findById(id));
		return "struttura.html";
	}

	@GetMapping("/struttura") // SENZA S
	public String getStrutture(Model model) {
		model.addAttribute("strutture", this.strutturaService.findAll());
		return "strutture.html";
	}

	@GetMapping("/formNewStruttura")
	public String formNewstruttura(Model model) {
		User user = getCredential().getUser();
		Host host = hostService.findByNameAndSurname(user.getName(), user.getSurname());
		model.addAttribute("host", host);
		model.addAttribute("struttura", new Struttura());
		return "formNewStruttura.html";
	}

	@PostMapping("/struttura")
	public String newStruttura(@ModelAttribute("struttura") Struttura struttura, @RequestParam("immagine") MultipartFile immagine, Model model) throws IOException {
		if (!strutturaRepository.existsByNameAndCity(struttura.getName(), struttura.getCity())) {
			String name = getCredential().getUser().getName();
			String surname = getCredential().getUser().getSurname();
			Host host = this.hostService.findByNameAndSurname(name, surname);

			struttura.setHost(host);

			if (!immagine.isEmpty()) {
				Immagine img = new Immagine();
				img.setFileName(immagine.getOriginalFilename());
				img.setImageData(immagine.getBytes());
				if (struttura.getImmagini() == null) {
					struttura.setImmagini(new ArrayList<>());
				}
				struttura.getImmagini().add(img);
				immagineService.save(img);
			}

			this.strutturaService.save(struttura);
			host.getCase().add(struttura);
			hostService.save(host);
			model.addAttribute("struttura", struttura);
			return "redirect:/struttura/" + struttura.getId();
		} else {
			model.addAttribute("messaggioErrore", "Questa struttura esiste già");
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

	@GetMapping("/struttura/{id}/prenota")
	public String prenotaStruttura(@PathVariable("id") Long id, Model model) {
		Struttura struttura = this.strutturaService.findById(id);
		model.addAttribute("prenotazione", new Prenotazione());
		model.addAttribute("struttura", struttura);
		return "formPrenotaStruttura.html";
	}

	@PostMapping("/struttura/{id}/prenota")
	public String prenotaStruttura(@PathVariable("id") Long id,
			@ModelAttribute("prenotazione") Prenotazione prenotazione, Model model) {
		Struttura struttura = this.strutturaService.findById(id);
		prenotazione.setStruttura(struttura);
		prenotazione.setNomeCasa(struttura.getName());
		prenotazione.setDataPrenotazione(LocalDate.now());
		prenotazione = prenotazioneService.save(prenotazione);
		model.addAttribute("prenotazione", prenotazione);
		// struttura.addPrenotazione(prenotazione);

		return "redirect:/struttura/" + struttura.getId() + "/prenotazione/" + prenotazione.getId();

	}

	@GetMapping("/struttura/{strutturaId}/prenotazione/{prenotazioneId}")
	public String visualizzaPrenotazione(@PathVariable("strutturaId") Long strutturaId,
			@PathVariable("prenotazioneId") Long prenotazioneId, Model model) {
		Prenotazione prenotazione = this.prenotazioneService.findById(prenotazioneId);
		model.addAttribute("prenotazione", prenotazione);
		return "prenotazioneStruttura.html";
	}

	@GetMapping("/editStruttura/{struttura_id}")
public String getFormEditStruttura(@PathVariable("struttura_id") Long id, Model model) {
    Struttura struttura = this.strutturaService.findById(id);
    User user = getCredential().getUser();
    if (hostService.findByNameAndSurname(user.getName(), user.getSurname()).getId() == struttura.getHost().getId()) {
        model.addAttribute("struttura", struttura);
        return "formModificaStruttura.html";
    } else {
        return "redirect:/error";
    }
}

@PostMapping("/editStruttura/{struttura_id}")
public String updateStruttura(@PathVariable("struttura_id") Long id, @ModelAttribute Struttura struttura, 
                             @RequestParam("immagine") MultipartFile immagine) throws IOException {
    struttura.setId(id);
	User user = getCredential().getUser();
	struttura.setHost(hostService.findByNameAndSurname(user.getName(), user.getSurname()));

    if (!immagine.isEmpty()) {
        Immagine img = new Immagine();
        img.setFileName(immagine.getOriginalFilename());
        img.setImageData(immagine.getBytes());
        if (struttura.getImmagini().isEmpty()) {
            struttura.getImmagini().add(img);
        } else {
            struttura.getImmagini().clear();
            struttura.getImmagini().add(img);
        }
        immagineService.save(img);
    }

    this.strutturaService.save(struttura);
    return "redirect:/struttura/" + id;
}

}
