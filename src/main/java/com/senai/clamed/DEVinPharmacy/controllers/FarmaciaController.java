package com.senai.clamed.DEVinPharmacy.controllers;

import com.senai.clamed.DEVinPharmacy.dtos.FarmaciaRecordDto;
import com.senai.clamed.DEVinPharmacy.models.ApiError;
import com.senai.clamed.DEVinPharmacy.models.EnderecoModel;
import com.senai.clamed.DEVinPharmacy.models.ErrorResponse;
import com.senai.clamed.DEVinPharmacy.models.FarmaciaModel;
import com.senai.clamed.DEVinPharmacy.repositories.FarmaciaRepository;
import com.senai.clamed.DEVinPharmacy.services.FarmaciaService;
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
    private FarmaciaService farmaciaService;

    @PostMapping("/farmacia")
    public ResponseEntity<?> saveFarmacia(@RequestBody @Valid FarmaciaRecordDto farmaciaRecordDto) {
        return farmaciaService.saveFarmacia(farmaciaRecordDto);
    }

    @GetMapping("/farmacias")
    public ResponseEntity<List<FarmaciaModel>> getAllFarmacia() {
        return farmaciaService.getAllFarmacia();
    }

    @GetMapping("/farmacias/{cnpj}")
    public ResponseEntity<Object> getOneFarmacia(@PathVariable Long cnpj) {
        return farmaciaService.getOneFarmacia(cnpj);
    }

    @PutMapping("/farmacias/{cnpj}")
    public ResponseEntity<?> updateFarmacia(@PathVariable Long cnpj, @RequestBody @Valid FarmaciaRecordDto farmaciaRecordDto) {
        return farmaciaService.updateFarmacia(cnpj, farmaciaRecordDto);
    }

    @DeleteMapping("/farmacias/{cnpj}")
    public ResponseEntity<Object> deleteFarmacia(@PathVariable Long cnpj) {
        return farmaciaService.deleteFarmacia(cnpj);
    }
}
