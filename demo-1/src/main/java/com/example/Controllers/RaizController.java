package com.example.Controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class RaizController {
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String raiz() {
        return "Olá, Mundo!";
    }

}
