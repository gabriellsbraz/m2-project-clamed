package com.senai.clamed.DEVinPharmacy.services;

import com.senai.clamed.DEVinPharmacy.dtos.EstoqueOutputDto;
import com.senai.clamed.DEVinPharmacy.dtos.EstoqueRecordDto;
import com.senai.clamed.DEVinPharmacy.models.EstoqueId;
import com.senai.clamed.DEVinPharmacy.models.EstoqueModel;
import com.senai.clamed.DEVinPharmacy.models.FarmaciaModel;
import com.senai.clamed.DEVinPharmacy.models.MedicamentoModel;
import com.senai.clamed.DEVinPharmacy.repositories.EstoqueRepository;
import com.senai.clamed.DEVinPharmacy.repositories.FarmaciaRepository;
import com.senai.clamed.DEVinPharmacy.repositories.MedicamentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private FarmaciaRepository farmaciaRepository;

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    public ResponseEntity<Object> saveEstoque(EstoqueRecordDto estoqueRecordDto) {
        ResponseEntity<Object> validationResult = validateEstoqueSave(estoqueRecordDto);
        if (validationResult != null) {
            return validationResult;
        }

        Optional<FarmaciaModel> existeFarmacia = farmaciaRepository.findById(estoqueRecordDto.cnpj());
        if (existeFarmacia.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Farmácia não encontrada com o CNPJ fornecido");
        }

        Optional<MedicamentoModel> existeMedicamento = medicamentoRepository.findById(estoqueRecordDto.nroRegistro());
        if (existeMedicamento.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Medicamento não encontrado com o número de registro fornecido");
        }

        var estoqueModel = new EstoqueModel();
        BeanUtils.copyProperties(estoqueRecordDto, estoqueModel);
        estoqueModel = estoqueRepository.save(estoqueModel);
        EstoqueOutputDto estoqueOutputDto = new EstoqueOutputDto(estoqueModel.getCnpj(),estoqueModel.getNroRegistro(),estoqueModel.getQuantidade(),estoqueModel.getDataAtualizacao());
        return ResponseEntity.status(HttpStatus.CREATED).body(estoqueOutputDto);
    }

    public ResponseEntity<List<EstoqueOutputDto>> getAllEstoque() {
        List<EstoqueModel> estoqueList = estoqueRepository.findAll();
        List<EstoqueOutputDto>  estoqueOutput = estoqueList.stream().map(estoque -> new EstoqueOutputDto(estoque.getCnpj(), estoque.getNroRegistro(), estoque.getQuantidade(), estoque.getDataAtualizacao())).toList();
        return ResponseEntity.status(HttpStatus.OK).body(estoqueOutput);
    }

    public ResponseEntity<Object> getEstoqueByFarmacia(Long cnpj) {
        Optional<FarmaciaModel> existeFarmacia = farmaciaRepository.findById(cnpj);
        if (existeFarmacia.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Farmácia não encontrada com o CNPJ fornecido");
        }

        List<EstoqueModel> estoqueList = estoqueRepository.findByCnpj(cnpj);
        List<EstoqueOutputDto> estoqueOutputDtos = estoqueList.stream().map(
                estoque -> new EstoqueOutputDto(estoque.getCnpj(), estoque.getNroRegistro(), estoque.getQuantidade(), estoque.getDataAtualizacao())).toList();

        return ResponseEntity.status(HttpStatus.OK).body(estoqueOutputDtos);
    }

    public ResponseEntity<Object> getOneEstoque(Long cnpj, Integer nroRegistro) {
        Optional<EstoqueModel> optionalEstoque = estoqueRepository.findById(new EstoqueId(cnpj, nroRegistro));
        if (optionalEstoque.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estoque não encontrado.");
        }
        EstoqueModel entity = optionalEstoque.get();
        EstoqueOutputDto dto = new EstoqueOutputDto(entity.getCnpj(), entity.getNroRegistro(), entity.getQuantidade(), entity.getDataAtualizacao());
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    public ResponseEntity<Object> updateEstoque(Long cnpj, Integer nroRegistro, EstoqueRecordDto estoqueRecordDto) {
        Optional<EstoqueModel> optionalEstoque = estoqueRepository.findById(new EstoqueId(cnpj, nroRegistro));
        if (optionalEstoque.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estoque não encontrado.");
        }
        var estoqueModel = optionalEstoque.get();
        BeanUtils.copyProperties(estoqueRecordDto, estoqueModel);
        return ResponseEntity.status(HttpStatus.OK).body(estoqueRepository.save(estoqueModel));
    }

    public ResponseEntity<Object> updateEstoque()

    public ResponseEntity<Object> deleteEstoque(EstoqueRecordDto estoqueRecordDto) {
        Optional<EstoqueModel> optionalEstoque = estoqueRepository.findById(new EstoqueId(estoqueRecordDto.cnpj(), estoqueRecordDto.nroRegistro()));
        if (optionalEstoque.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estoque não encontrado");
        }

        EstoqueModel estoqueModel = optionalEstoque.get();
        Integer quantidade = estoqueModel.getQuantidade();
        Integer quantidadeParaDeletar = estoqueRecordDto.quantidade();
        if (quantidadeParaDeletar > quantidade){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Numero a deletar deve ser menor ou igual a quantidade.");
        }

        quantidade -= quantidadeParaDeletar;


        if (quantidade == 0) {
            estoqueRepository.delete((estoqueModel));
        } else {
            estoqueModel.setQuantidade((quantidade));
            estoqueRepository.save(estoqueModel);
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    private ResponseEntity<Object> validateEstoqueSave(EstoqueRecordDto estoqueRecordDto) {
        if (estoqueRecordDto.quantidade() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A quantidade deve ser um número positivo maior que zero");
        }
        return null;
    }
}

