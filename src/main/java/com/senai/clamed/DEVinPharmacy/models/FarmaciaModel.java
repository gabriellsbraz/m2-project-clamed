package com.senai.clamed.DEVinPharmacy.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_FARMACIA")
public class FarmaciaModel{

    @Id
    private Long cnpj;
    private String razaoSocial;
    private String nomeFantasia;
    private String email;
    private String telefone;
    private String celular;
    @Embedded
    private EnderecoModel endereco;

}

