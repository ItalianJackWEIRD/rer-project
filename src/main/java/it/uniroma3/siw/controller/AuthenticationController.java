package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import it.uniroma3.siw.service.CredentialService;

@Controller
public class AuthenticationController {

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private UserService userService;
}