package com.senai.clamed.DEVinPharmacy.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_ESTOQUE")
@IdClass(EstoqueId.class)
public class EstoqueModel {
    @Id
    private Long cnpj;
    @Id
    private Integer nRegistro;
    private Integer quantidade;
    private LocalDateTime dataAtualizacao;
}
