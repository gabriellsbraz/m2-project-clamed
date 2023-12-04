package com.senai.clamed.DEVinPharmacy.controllers;

import com.senai.clamed.DEVinPharmacy.dtos.FarmaciaRecordDto;
import com.senai.clamed.DEVinPharmacy.models.FarmaciaModel;
import com.senai.clamed.DEVinPharmacy.repositories.FarmaciaRepository;
import jakarta.validation.Valid;
import org.hibernate.engine.spi.Resolution;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RestController
public class FarmaciaController {

    @Autowired
    private FarmaciaRepository farmaciaRepository;

    @PostMapping("/farmacia")
    public ResponseEntity<FarmaciaModel> saveFarmacia(@RequestBody @Valid FarmaciaRecordDto farmaciaRecordDto) {
        var farmaciaModel = new FarmaciaModel();
        BeanUtils.copyProperties(farmaciaRecordDto, farmaciaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(farmaciaRepository.save(farmaciaModel));
    }

    @GetMapping("/farmacia")
    public ResponseEntity<List<FarmaciaModel>> getAllFarmacia(){
        return ResponseEntity.status(HttpStatus.OK).body(farmaciaRepository.findAll());
    }

    @GetMapping("/farmacia/{cnpj}")
    public ResponseEntity<Object> getOneFarmacia(@PathVariable Long cnpj) {
        Optional<FarmaciaModel> optionalFarmacia = farmaciaRepository.findById(cnpj);
        if(optionalFarmacia.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Farmacia not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(optionalFarmacia.get());
    }

    @DeleteMapping("/farmacia/{cnpj}")
    public ResponseEntity<Object> deleteFarmacia(@PathVariable Long cnpj) {
        Optional<FarmaciaModel> optionalFarmacia = farmaciaRepository.findById(cnpj);
        if(optionalFarmacia.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        farmaciaRepository.delete(optionalFarmacia.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted succesfully.");
    }


}
