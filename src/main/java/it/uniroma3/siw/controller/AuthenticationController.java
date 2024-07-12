package it.uniroma3.siw.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Host;
import it.uniroma3.siw.model.Immagine;
import it.uniroma3.siw.model.auth.Credential;
import it.uniroma3.siw.model.auth.User;
import it.uniroma3.siw.service.CredentialService;
import it.uniroma3.siw.service.HostService;
import it.uniroma3.siw.service.ImmagineService;
import it.uniroma3.siw.service.UserService;

@Controller
public class AuthenticationController {

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private UserService userService;

    @Autowired
    private HostService hostService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ImmagineService immagineService;

    @GetMapping(value = "/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("credentials", new Credential());
        return "register.html";
    }

    @GetMapping(value = "/login")
    public String showLoginForm(Model model) {
        return "login.html";
    }

    @GetMapping(value = "/")
    public String index(Model model) {
        // Authentication authentication =
        // SecurityContextHolder.getContext().getAuthentication(); //diooooo

        return "index.html";
    }

    @GetMapping(value = "/success")
    public String defaultAfterLogin(Model model) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Credential credentials = credentialService.getCredentialByUsername(userDetails.getUsername());

        return "index.html";
    }

    @PostMapping(value = { "/register" })
    public String registerUser(@ModelAttribute("user") User user,
            BindingResult userBindingResult,

            @ModelAttribute("credential") Credential credentials,
            BindingResult credentialsBindingResult,
            @RequestParam("immagine") MultipartFile immagine,

            Model model) throws IOException{

        if (!userBindingResult.hasErrors() && !credentialsBindingResult.hasErrors()) {
            userService.save(user);
            credentials.setUser(user);

            Host host = new Host(user);

            if (!immagine.isEmpty()) {
                Immagine img = new Immagine();
                img.setFileName(immagine.getOriginalFilename());
                img.setImageData(immagine.getBytes());
                if (host.getImmagini().isEmpty()) {
                    host.getImmagini().add(img);
                }
                else {
                    host.getImmagini().clear();
                    host.getImmagini().add(img);
                }
            immagineService.save(img);
            }

            hostService.save(host);
            // credentials.setPassword(passwordEncoder.encode(credentials.getPassword()));
            credentials.setRuolo(Credential.UTENTE_HOST);
            credentialService.save(credentials);
            model.addAttribute("user", user);

            return "redirect:/";
        }
        return "register.html";
    }
}