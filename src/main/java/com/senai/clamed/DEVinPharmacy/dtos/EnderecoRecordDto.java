package com.senai.clamed.DEVinPharmacy.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnderecoRecordDto
        (@NotNull Long cep,
         @NotBlank String logradouro,
         @NotNull Integer numero,
         @NotBlank String bairro,
         @NotBlank String cidade,
         @NotBlank String estado,
         String complemento,
         @NotNull Double latitude,
         @NotNull Double longitude) {
}
