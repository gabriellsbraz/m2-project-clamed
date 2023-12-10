package com.senai.clamed.DEVinPharmacy.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.senai.clamed.DEVinPharmacy.models.EstoqueModel;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record EstoqueRecordDto
        (@NotNull Long cnpj,
        @NotNull Integer nroRegistro,
        @NotNull Integer quantidade,
         LocalDateTime dataAtualizacao) {

}
