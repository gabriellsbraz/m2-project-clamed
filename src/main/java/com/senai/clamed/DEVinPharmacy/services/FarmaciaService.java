package com.senai.clamed.DEVinPharmacy.services;

import com.senai.clamed.DEVinPharmacy.dtos.FarmaciaRecordDto;
import com.senai.clamed.DEVinPharmacy.models.EnderecoModel;
import com.senai.clamed.DEVinPharmacy.models.ErrorResponse;
import com.senai.clamed.DEVinPharmacy.models.FarmaciaModel;
import com.senai.clamed.DEVinPharmacy.repositories.FarmaciaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FarmaciaService {
    @Autowired
    private FarmaciaRepository farmaciaRepository;

    public ResponseEntity<?> saveFarmacia(FarmaciaRecordDto farmaciaRecordDto) {
        ResponseEntity<?> validationResult = validateSaveFarmacia(farmaciaRecordDto);
        if (validationResult != null) {
            return validationResult;
        }

        Optional<FarmaciaModel> existeFarmacia = farmaciaRepository.findById(farmaciaRecordDto.cnpj());
        if (existeFarmacia.isPresent()) {
            ErrorResponse errorResponse = new ErrorResponse("CNPJ ja registrado.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        var farmaciaModel = new FarmaciaModel();
        BeanUtils.copyProperties(farmaciaRecordDto, farmaciaModel);

        EnderecoModel enderecoModel = new EnderecoModel();
        BeanUtils.copyProperties(farmaciaRecordDto.endereco(), enderecoModel);

        farmaciaModel.setEndereco(enderecoModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(farmaciaRepository.save(farmaciaModel));
    }

    public ResponseEntity<List<FarmaciaModel>> getAllFarmacia() {
        List<FarmaciaModel> farmacias = farmaciaRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(farmacias);
    }

    public ResponseEntity<Object> getOneFarmacia(Long cnpj) {
        Optional<FarmaciaModel> optionalFarmacia = farmaciaRepository.findById(cnpj);

        return optionalFarmacia.map(farmaciaModel ->
                        ResponseEntity.status(HttpStatus.OK).body((Object) farmaciaModel))
                .orElseGet(() -> {
                    ErrorResponse errorResponse = new ErrorResponse("Farmacia não encontrada.");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body((Object) errorResponse);
                });
    }

    public ResponseEntity<?> updateFarmacia(Long cnpj, FarmaciaRecordDto farmaciaRecordDto) {
        ResponseEntity<?> validationResult = validateUpdateFarmacia(farmaciaRecordDto);
        if (validationResult != null) {
            return validationResult;
        }

        Optional<FarmaciaModel> optionalFarmacia = farmaciaRepository.findById(cnpj);
        if (optionalFarmacia.isPresent()) {
            var farmaciaModel = optionalFarmacia.get();
            BeanUtils.copyProperties(farmaciaRecordDto, farmaciaModel);
            return ResponseEntity.status(HttpStatus.OK).body(farmaciaRepository.save(farmaciaModel));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Farmacia não encontrada."));
        }
    }

    public ResponseEntity<Object> deleteFarmacia(Long cnpj) {
        Optional<FarmaciaModel> optionalFarmacia = farmaciaRepository.findById(cnpj);
        if (optionalFarmacia.isPresent()) {
            farmaciaRepository.delete(optionalFarmacia.get());
            return ResponseEntity.status(HttpStatus.OK).body("Farmacia deletada com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Farmacia não encontrada."));
        }
    }

    private ResponseEntity<?> validateSaveFarmacia(FarmaciaRecordDto farmaciaRecordDto) {
        return null;
    }

    private ResponseEntity<?> validateUpdateFarmacia(FarmaciaRecordDto farmaciaRecordDto) {
        return null;
    }
}