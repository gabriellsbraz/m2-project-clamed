package com.senai.clamed.DEVinPharmacy.repositories;

import com.senai.clamed.DEVinPharmacy.models.EstoqueId;
import com.senai.clamed.DEVinPharmacy.models.EstoqueModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstoqueRepository extends JpaRepository<EstoqueModel, EstoqueId> {
    List<EstoqueModel> findByCnpj(Long cnpj);

    Optional<EstoqueModel> findByCnpjAndNroRegistro(Long cnpj, Integer nroRegistro);

}
