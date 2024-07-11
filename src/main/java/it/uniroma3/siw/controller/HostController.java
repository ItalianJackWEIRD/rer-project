package it.uniroma3.siw.controller;

import java.io.IOException;
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
import it.uniroma3.siw.service.HostService;
import it.uniroma3.siw.service.ImmagineService;

@Controller
public class HostController extends GlobalController {

	@Autowired
	private HostService hostService;

	@Autowired
	private ImmagineService immagineService;

	@GetMapping("/host/{id}")
	public String getHost(@PathVariable("id") Long id, Model model) {
		model.addAttribute("host", this.hostService.findById(id));
		return "host.html";
	}

	@GetMapping("/host") // SENZA S
	public String getHosts(Model model) {
		model.addAttribute("hosts", this.hostService.findAll());
		return "hosts.html";
	}

	@GetMapping("/formNewHost")
	public String formNewHost(Model model) {
		model.addAttribute("host", new Host());
		return "formNewHost.html";
	}

	@PostMapping("/host")
	public String newHost(@ModelAttribute("host") Host host, @RequestParam("immagine") MultipartFile immagine, Model model) throws IOException{
		
		if (!immagine.isEmpty()) {
            Immagine img = new Immagine();
            img.setFileName(immagine.getOriginalFilename());
            img.setImageData(immagine.getBytes());
            if (host.getImmagini() == null) {
                host.setImmagini(new ArrayList<>());
            }
            host.getImmagini().add(img);
            immagineService.save(img);
        }
		
		this.hostService.save(host);
		model.addAttribute("host", host);
		return "redirect:/host/" + host.getId();
	}
}
