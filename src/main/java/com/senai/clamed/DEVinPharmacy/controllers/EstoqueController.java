package com.senai.clamed.DEVinPharmacy.controllers;

import com.senai.clamed.DEVinPharmacy.dtos.EstoqueOutputDto;
import com.senai.clamed.DEVinPharmacy.dtos.EstoqueRecordDto;
import com.senai.clamed.DEVinPharmacy.dtos.EstoqueTrocaMedicamentoDto;
import com.senai.clamed.DEVinPharmacy.models.EstoqueModel;
import com.senai.clamed.DEVinPharmacy.services.EstoqueService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EstoqueController {

    @Autowired
    private EstoqueService estoqueService;

    @PostMapping("/estoque")
    public ResponseEntity<Object> saveEstoque(@RequestBody @Valid EstoqueRecordDto estoqueRecordDto) {
        return estoqueService.saveEstoque(estoqueRecordDto);
    }

//    @GetMapping("/estoque")
//    public ResponseEntity<List<EstoqueOutputDto>> getAllEstoque() {
//        return estoqueService.getAllEstoque();
//    }
    @GetMapping("/estoque")
    public ResponseEntity<List<EstoqueRecordDto>> getAllEstoque() {
        return estoqueService.getAllEstoque();
    }


    @GetMapping("/estoque/{cnpj}")
    public ResponseEntity<Object> getEstoqueByFarmacia(@PathVariable Long cnpj) {
        return estoqueService.getEstoqueByFarmacia(cnpj);
    }


    // OCULTAR CNPJ
//    @PutMapping("/estoque")
//    public ResponseEntity<Object> updateEstoque(@PathVariable Long cnpj, @PathVariable Integer nroRegistro, @RequestBody EstoqueRecordDto estoqueRecordDto) {
//        return estoqueService.updateEstoque(cnpj, nroRegistro, estoqueRecordDto);
//    }
    @PutMapping("/estoque")
    public ResponseEntity<String> trocarMedicamento(@RequestBody EstoqueTrocaMedicamentoDto record) {
        try {
            estoqueService.trocarMedicamento(record);
            return ResponseEntity.ok("Troca de medicamento realizada com sucesso");
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/estoque")
    public ResponseEntity<Object> deleteEstoque(@RequestBody @Valid EstoqueRecordDto estoqueRecordDto) {
        return estoqueService.deleteEstoque(estoqueRecordDto);
    }
}

