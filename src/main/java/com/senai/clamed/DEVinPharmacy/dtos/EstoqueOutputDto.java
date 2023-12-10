package com.senai.clamed.DEVinPharmacy.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record EstoqueOutputDto(
        @NotNull @JsonIgnore Long cnpj,
        @NotNull Integer nroRegistro,
        @NotNull Integer quantidade,
        LocalDateTime dataAtualizacao) {

}