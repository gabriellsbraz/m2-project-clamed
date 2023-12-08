package com.senai.clamed.DEVinPharmacy.dtos;

import com.senai.clamed.DEVinPharmacy.models.EstoqueModel;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record EstoqueRecordDto
        (@NotNull Long cnpj,
        @NotNull Integer nRegistro,
        @NotNull Integer quantidade,
        @NotNull LocalDateTime dataAtualizacao) {

}
