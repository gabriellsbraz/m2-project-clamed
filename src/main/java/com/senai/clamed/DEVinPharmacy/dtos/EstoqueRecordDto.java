package com.senai.clamed.DEVinPharmacy.dtos;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record EstoqueRecordDto
        (@NotNull Long cnpj,
         @NotNull Integer nroRegistro,
         @NotNull Integer quantidade,
         LocalDateTime dataAtualizacao) {

}
