package com.senai.clamed.DEVinPharmacy.dtos;

import com.senai.clamed.DEVinPharmacy.models.TipoMedicamento;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MedicamentoRecordDto
        (@NotNull Integer nRegistro,
        @NotBlank String nome,
        @NotBlank String lab,
        @NotBlank String dosagem,
        @NotBlank String descricao,
        @NotNull Float preco,
         @NotNull
         @Enumerated(EnumType.STRING)
         TipoMedicamento tipoMedicamento){

}
