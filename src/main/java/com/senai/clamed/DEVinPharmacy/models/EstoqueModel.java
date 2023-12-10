package com.senai.clamed.DEVinPharmacy.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

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
    private Integer nroRegistro;

    private Integer quantidade;

    @UpdateTimestamp
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime dataAtualizacao;

}
