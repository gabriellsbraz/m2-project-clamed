package com.senai.clamed.DEVinPharmacy.dtos;

import com.senai.clamed.DEVinPharmacy.models.EnderecoModel;
import com.senai.clamed.DEVinPharmacy.models.EstoqueModel;
import jakarta.persistence.Embedded;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

public record FarmaciaRecordDto
        (@NotNull Long cnpj,
         @NotBlank String razaoSocial,
         @NotBlank String nomeFantasia,
         @NotBlank String email,
         String telefone,
         @NotBlank String celular,
         EnderecoRecordDto endereco)
{

}
