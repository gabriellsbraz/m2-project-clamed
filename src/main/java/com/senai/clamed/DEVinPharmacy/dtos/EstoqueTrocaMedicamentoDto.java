package com.senai.clamed.DEVinPharmacy.dtos;

import jakarta.validation.constraints.NotNull;

public record EstoqueTrocaMedicamentoDto(
        @NotNull Long cnpjOrigem,
        @NotNull Long cnpjDestino,
        @NotNull Integer nroRegistro,
        @NotNull Integer quantidade
) {
}
