package com.epcafes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.epcafes.dto.RegistroDTO;
import com.epcafes.model.Usuario;
import com.epcafes.service.UsuarioService;



@Controller
public class RegistroController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/register")
    public String registrar(Usuario usuario, @AuthenticationPrincipal Usuario userDetails){
        return "register";
    }
    
    @PostMapping("/register")
    public String register(RegistroDTO data, @AuthenticationPrincipal Usuario userDetails){
        if (usuarioService.createUser(data, userDetails.getTenant())) {
            return "redirect:/register?success";
        }
        else{
            return "redirect:/register?error";
        }
    }
}
