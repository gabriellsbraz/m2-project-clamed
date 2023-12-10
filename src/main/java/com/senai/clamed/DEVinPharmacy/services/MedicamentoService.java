package com.senai.clamed.DEVinPharmacy.services;

import com.senai.clamed.DEVinPharmacy.dtos.MedicamentoRecordDto;
import com.senai.clamed.DEVinPharmacy.models.ErrorResponse;
import com.senai.clamed.DEVinPharmacy.models.MedicamentoModel;
import com.senai.clamed.DEVinPharmacy.repositories.MedicamentoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicamentoService {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    public ResponseEntity<?> saveMedicamento(MedicamentoRecordDto medicamentoRecordDto) {
        Optional<MedicamentoModel> existeMedicamento = medicamentoRepository.findById(medicamentoRecordDto.nroRegistro());
        if (existeMedicamento.isPresent()) {
            ErrorResponse errorResponse = new ErrorResponse("nroRegistro ja registrado.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        var medicamentoModel = new MedicamentoModel();
        BeanUtils.copyProperties(medicamentoRecordDto, medicamentoModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(medicamentoRepository.save(medicamentoModel));
    }

    public ResponseEntity<List<MedicamentoModel>> getAllMedicamento() {
        List<MedicamentoModel> medicamentos = medicamentoRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(medicamentos);
    }

    public ResponseEntity<Object> getOneMedicamento(Integer nroRegistro) {
        Optional<MedicamentoModel> optionalMedicamento = medicamentoRepository.findById(nroRegistro);
        return optionalMedicamento.map(medicamentoModel ->
                        ResponseEntity.status(HttpStatus.OK).body((Object) medicamentoModel))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medicamento não encontrado."));
    }

    public ResponseEntity<Object> updateMedicamento(Integer nroRegistro, MedicamentoRecordDto medicamentoRecordDto) {
        Optional<MedicamentoModel> optionalMedicamento = medicamentoRepository.findById(nroRegistro);
        if (optionalMedicamento.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medicamento não encontrado.");
        }

        var medicamentoModel = optionalMedicamento.get();
        BeanUtils.copyProperties(medicamentoRecordDto, medicamentoModel);

        return ResponseEntity.status(HttpStatus.OK).body(medicamentoRepository.save(medicamentoModel));
    }

    public ResponseEntity<Object> deleteMedicamento(Integer nroRegistro) {
        Optional<MedicamentoModel> optionalMedicamento = medicamentoRepository.findById(nroRegistro);
        if (optionalMedicamento.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medicamento não encontrado.");
        }

        medicamentoRepository.delete(optionalMedicamento.get());
        return ResponseEntity.status(HttpStatus.OK).body("Medicamento deletado com sucesso.");
    }
}

