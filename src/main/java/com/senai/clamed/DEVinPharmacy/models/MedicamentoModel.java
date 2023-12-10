package com.senai.clamed.DEVinPharmacy.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_MEDICAMENTO")
public class MedicamentoModel {

    @Id
    private Integer nroRegistro;
    private String nome;
    private String laboratorio;
    private String dosagem;
    private String descricao;
    private Float preco;
    private TipoMedicamento tipoMedicamento;

}
