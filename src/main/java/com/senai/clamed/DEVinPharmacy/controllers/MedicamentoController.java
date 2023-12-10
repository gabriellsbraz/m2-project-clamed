package com.senai.clamed.DEVinPharmacy.controllers;

import com.senai.clamed.DEVinPharmacy.dtos.EstoqueRecordDto;
import com.senai.clamed.DEVinPharmacy.dtos.FarmaciaRecordDto;
import com.senai.clamed.DEVinPharmacy.dtos.MedicamentoRecordDto;
import com.senai.clamed.DEVinPharmacy.models.ApiError;
import com.senai.clamed.DEVinPharmacy.models.EnderecoModel;
import com.senai.clamed.DEVinPharmacy.models.FarmaciaModel;
import com.senai.clamed.DEVinPharmacy.models.MedicamentoModel;
import com.senai.clamed.DEVinPharmacy.repositories.MedicamentoRepository;
import com.senai.clamed.DEVinPharmacy.services.MedicamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MedicamentoController {

    @Autowired
    private MedicamentoService medicamentoService;

    @PostMapping("/medicamentos")
    public ResponseEntity<?> saveMedicamento(@RequestBody @Valid MedicamentoRecordDto medicamentoRecordDto) {
        return medicamentoService.saveMedicamento(medicamentoRecordDto);
    }

    @GetMapping("/medicamentos")
    public ResponseEntity<List<MedicamentoModel>> getAllMedicamento() {
        return medicamentoService.getAllMedicamento();
    }

    @GetMapping("medicamentos/{nroRegistro}")
    public ResponseEntity<Object> getOneMedicamento(@PathVariable Integer nroRegistro) {
        return medicamentoService.getOneMedicamento(nroRegistro);
    }

    @PutMapping("medicamentos/{nroRegistro}")
    public ResponseEntity<Object> updateMedicamento(@PathVariable Integer nroRegistro, @RequestBody @Valid MedicamentoRecordDto medicamentoRecordDto) {
        return medicamentoService.updateMedicamento(nroRegistro, medicamentoRecordDto);
    }

    @DeleteMapping("medicamentos/{nroRegistro}")
    public ResponseEntity<Object> deleteMedicamento(@PathVariable Integer nroRegistro) {
        return medicamentoService.deleteMedicamento(nroRegistro);
    }
}
