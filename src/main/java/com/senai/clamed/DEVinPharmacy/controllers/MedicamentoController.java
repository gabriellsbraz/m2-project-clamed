package com.senai.clamed.DEVinPharmacy.controllers;

import com.senai.clamed.DEVinPharmacy.dtos.MedicamentoRecordDto;
import com.senai.clamed.DEVinPharmacy.models.MedicamentoModel;
import com.senai.clamed.DEVinPharmacy.repositories.MedicamentoRepository;
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
    private MedicamentoRepository medicamentoRepository;

    @PostMapping("/medicamento")
    public ResponseEntity<MedicamentoModel> saveFarmacia(@RequestBody @Valid MedicamentoRecordDto medicamentoRecordDto) {
        var medicamentoModel = new MedicamentoModel();
        BeanUtils.copyProperties(medicamentoRecordDto, medicamentoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(medicamentoRepository.save(medicamentoModel));
    }

    @GetMapping("/medicamento")
    public ResponseEntity<List<MedicamentoModel>> getAllMedicamento(){
        return ResponseEntity.status(HttpStatus.OK).body(medicamentoRepository.findAll());
    }

    @GetMapping("/medicamento/{nRegistro}")
    public ResponseEntity<Object> getOneMedicamento(@PathVariable Integer nRegistro) {
        Optional<MedicamentoModel> optionalMedicamento = medicamentoRepository.findById(nRegistro);
        if(optionalMedicamento.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medicamento not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(optionalMedicamento.get());
    }

    @PutMapping("/medicamento/{nRegistro}")
    public ResponseEntity<Object> updateMedicamento(@PathVariable Integer nRegistro, @RequestBody @Valid MedicamentoRecordDto medicamentoRecordDto) {
        Optional<MedicamentoModel> optionalMedicamento = medicamentoRepository.findById(nRegistro);
        if(optionalMedicamento.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medicamento not found.");
        }
        var medicamentoModel = optionalMedicamento.get();
        BeanUtils.copyProperties(medicamentoRecordDto, medicamentoModel);
        return ResponseEntity.status(HttpStatus.OK).body(medicamentoRepository.save(medicamentoModel));
    }

    @DeleteMapping("/medicamento/{nRegistro}")
    public ResponseEntity<Object> deleteMedicamento(@PathVariable Integer nRegistro) {
        Optional<MedicamentoModel> optionalMedicamento = medicamentoRepository.findById(nRegistro);
        if(optionalMedicamento.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medicamento not found.");
        }
        medicamentoRepository.delete(optionalMedicamento.get());
        return ResponseEntity.status(HttpStatus.OK).body("Medicamento deleted succesfully.");
    }

}
