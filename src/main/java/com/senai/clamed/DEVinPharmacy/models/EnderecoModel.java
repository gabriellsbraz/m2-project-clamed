package com.senai.clamed.DEVinPharmacy.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoModel {
    private Long cep;
    private String logradouro;
    private Integer numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String complemento;
    private Double latitude;
    private Double longitude;
}
