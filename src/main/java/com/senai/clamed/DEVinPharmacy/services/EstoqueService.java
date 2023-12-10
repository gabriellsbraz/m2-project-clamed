package com.senai.clamed.DEVinPharmacy.services;

import com.senai.clamed.DEVinPharmacy.dtos.EstoqueOutputDto;
import com.senai.clamed.DEVinPharmacy.dtos.EstoqueRecordDto;
import com.senai.clamed.DEVinPharmacy.dtos.EstoqueTrocaMedicamentoDto;
import com.senai.clamed.DEVinPharmacy.models.EstoqueId;
import com.senai.clamed.DEVinPharmacy.models.EstoqueModel;
import com.senai.clamed.DEVinPharmacy.models.FarmaciaModel;
import com.senai.clamed.DEVinPharmacy.models.MedicamentoModel;
import com.senai.clamed.DEVinPharmacy.repositories.EstoqueRepository;
import com.senai.clamed.DEVinPharmacy.repositories.FarmaciaRepository;
import com.senai.clamed.DEVinPharmacy.repositories.MedicamentoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        EstoqueOutputDto estoqueOutputDto = new EstoqueOutputDto(estoqueModel.getCnpj(), estoqueModel.getNroRegistro(), estoqueModel.getQuantidade(), estoqueModel.getDataAtualizacao());
        return ResponseEntity.status(HttpStatus.CREATED).body(estoqueOutputDto);
    }

    public ResponseEntity<List<EstoqueRecordDto>> getAllEstoque() {
        List<EstoqueModel> estoqueList = estoqueRepository.findAll();
        List<EstoqueRecordDto> estoques = estoqueList.stream().map(estoque -> new EstoqueRecordDto(estoque.getCnpj(), estoque.getNroRegistro(), estoque.getQuantidade(), estoque.getDataAtualizacao())).toList();
        return ResponseEntity.status(HttpStatus.OK).body(estoques);
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


    public ResponseEntity<Object> updateEstoque(Long cnpj, Integer nroRegistro, EstoqueRecordDto estoqueRecordDto) {
        Optional<EstoqueModel> optionalEstoque = estoqueRepository.findById(new EstoqueId(cnpj, nroRegistro));
        if (optionalEstoque.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estoque não encontrado.");
        }
        var estoqueModel = optionalEstoque.get();
        BeanUtils.copyProperties(estoqueRecordDto, estoqueModel);
        return ResponseEntity.status(HttpStatus.OK).body(estoqueRepository.save(estoqueModel));
    }


    public void trocarMedicamento(EstoqueTrocaMedicamentoDto record) throws ChangeSetPersister.NotFoundException {
        Optional<EstoqueModel> optEstoqueOrigem = estoqueRepository.findByCnpjAndNroRegistro(record.cnpjOrigem(), record.nroRegistro());
        EstoqueModel estoqueOrigem = optEstoqueOrigem.orElseThrow(() -> new IllegalArgumentException("Registro não encontrado no Estoque (Origem)"));
        Optional<EstoqueModel> optEstoqueDestino = estoqueRepository.findByCnpjAndNroRegistro(record.cnpjDestino(), record.nroRegistro());
        EstoqueModel estoqueDestino = optEstoqueDestino.orElseThrow(() -> new IllegalArgumentException("Registro não encontrado no Estoque (Destino)"));

        if (record.quantidade() <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser um número positivo maior que zero");
        }

        estoqueOrigem.setQuantidade(estoqueOrigem.getQuantidade() - record.quantidade());
        estoqueDestino.setQuantidade(estoqueDestino.getQuantidade() + record.quantidade());

        if (estoqueOrigem.getQuantidade() < 0 || estoqueDestino.getQuantidade() < 0) {
            throw new IllegalArgumentException("Não pode haver estoques com quantidades negativas");
        }

        List<EstoqueModel> estoques = List.of(estoqueOrigem, estoqueDestino);
        estoques.forEach(estoque -> {
            if (estoque.getQuantidade() > 0) {
                estoqueRepository.save(estoque);
            } else {
                estoqueRepository.delete(estoque);
            }
        });

    }

    public ResponseEntity<Object> deleteEstoque(EstoqueRecordDto estoqueRecordDto) {
        Optional<EstoqueModel> optionalEstoque = estoqueRepository.findById(new EstoqueId(estoqueRecordDto.cnpj(), estoqueRecordDto.nroRegistro()));
        if (optionalEstoque.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estoque não encontrado");
        }

        EstoqueModel estoqueModel = optionalEstoque.get();
        Integer quantidade = estoqueModel.getQuantidade();
        Integer quantidadeParaDeletar = estoqueRecordDto.quantidade();
        if (quantidadeParaDeletar > quantidade) {
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

