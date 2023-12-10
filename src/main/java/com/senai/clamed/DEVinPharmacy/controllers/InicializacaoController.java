package com.senai.clamed.DEVinPharmacy.controllers;

import com.senai.clamed.DEVinPharmacy.services.InicializacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InicializacaoController {

    @Autowired
    private InicializacaoService inicializacaoService;

    @PostMapping("/inicializacao")
    public ResponseEntity<Void> iniciarDadosIniciais() {
        inicializacaoService.popularDadosIniciais();
        return ResponseEntity.ok().build();
    }
}
