package com.senai.clamed.DEVinPharmacy.controllers;

import com.senai.clamed.DEVinPharmacy.dtos.EstoqueRecordDto;
import com.senai.clamed.DEVinPharmacy.models.EstoqueId;
import com.senai.clamed.DEVinPharmacy.models.EstoqueModel;
import com.senai.clamed.DEVinPharmacy.repositories.EstoqueRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EstoqueController {
    @Autowired
    private EstoqueRepository estoqueRepository;


    @PostMapping("/estoque")
    public ResponseEntity<EstoqueModel> saveEstoque(@RequestBody @Valid EstoqueRecordDto estoqueRecordDto) {
        var estoqueModel = new EstoqueModel();
        BeanUtils.copyProperties(estoqueRecordDto, estoqueModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(estoqueRepository.save(estoqueModel));
    }

    @GetMapping("/estoque")
    public ResponseEntity<List<EstoqueModel>> getAllEstoque() {
        return ResponseEntity.status(HttpStatus.OK).body(estoqueRepository.findAll());
    }

    @GetMapping("/estoque/{cnpj}/{nRegistro}")
    public ResponseEntity<Object> getOneEstoque(@PathVariable Long cnpj, @PathVariable Integer nRegistro) {
        Optional<EstoqueModel> optionalEstoque = estoqueRepository.findById(new EstoqueId(cnpj, nRegistro));
        if (optionalEstoque.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estoque not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(optionalEstoque.get());
    }


    @PutMapping("/estoque/{cnpj}/{nRegistro}")
    public ResponseEntity<Object> updateEstoque(@PathVariable Long cnpj, @PathVariable Integer nRegistro, @RequestBody EstoqueRecordDto estoqueRecordDto) {
        Optional<EstoqueModel> optionalEstoque = estoqueRepository.findById(new EstoqueId(cnpj, nRegistro));
        if (optionalEstoque.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estoque not found.");
        }
        var estoqueModel = optionalEstoque.get();
        BeanUtils.copyProperties(estoqueRecordDto, estoqueModel);
        return ResponseEntity.status(HttpStatus.OK).body(estoqueRepository.save(estoqueModel));
    }


    @DeleteMapping("/estoque/{cnpj}/{nRegistro}")
    public ResponseEntity<Object> deleteEstoque(@PathVariable Long cnpj, @PathVariable Integer nRegistro) {
        Optional<EstoqueModel> optionalEstoque = estoqueRepository.findById(new EstoqueId(cnpj, nRegistro));
        if (optionalEstoque.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estoque not found");
        }
        estoqueRepository.delete(optionalEstoque.get());
        return ResponseEntity.status(HttpStatus.OK).body("Estoque deleted successfully.");
    }

}
