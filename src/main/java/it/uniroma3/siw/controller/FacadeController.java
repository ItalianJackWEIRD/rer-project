package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FacadeController {

    @GetMapping("")
    public String getIndexPage() {
        return "index.html";
    }
}
