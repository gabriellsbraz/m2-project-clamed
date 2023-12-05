package com.senai.clamed.DEVinPharmacy.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FarmaciaRecordDto
        (@NotNull Long cnpj,
         @NotBlank String razaoSocial,
         @NotBlank String nomeFantasia,
         @NotBlank String email,
         String telefone,
         @NotBlank String celular,
         EnderecoRecordDto endereco) {
}
