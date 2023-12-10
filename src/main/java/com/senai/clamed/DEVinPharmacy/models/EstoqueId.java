package com.senai.clamed.DEVinPharmacy.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class EstoqueId implements Serializable {
    private Long cnpj;
    private Integer nroRegistro;
}
