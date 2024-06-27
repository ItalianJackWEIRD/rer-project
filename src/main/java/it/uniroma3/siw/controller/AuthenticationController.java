package it.uniroma3.siw.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.auth.Credential;
import it.uniroma3.siw.model.auth.User;
import it.uniroma3.siw.service.CredentialService;
import it.uniroma3.siw.service.UserService;

@Controller
public class AuthenticationController {

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/register")
    public String showRegisterForm (Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("credentials", new Credential());
        return "formRegisterUser";
    }

    @GetMapping(value = "/login")
    public String showLoginForm (Model model) {
        return "login.html";
    }

    @GetMapping(value = "/")
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return "index.html";
    }

    @GetMapping(value = "/success")
    public String defaultAfterLogin(Model model) {

        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Credential> credentials = credentialService.getCredentialByUsername(userDetails.getUsername());

        return "index.html";
    }

    @PostMapping(value = { "/register" })
    public String registerUser(@ModelAttribute("user") User user,
                               BindingResult userBindingResult,
                               
                               @ModelAttribute("credentials") Credential credentials,
                               BindingResult credentialsBindingResult,
                               Model model) {

         if(!userBindingResult.hasErrors() && ! credentialsBindingResult.hasErrors()) {
            userService.save(user);
            credentials.setUser(user);

            credentials.setRuolo(Credential.UTENTE_GENERICO);
            credentialService.save(credentials);
            model.addAttribute("user", user);

            return "redirect:/";
        }
        return "formRegisterUser";
    }
}