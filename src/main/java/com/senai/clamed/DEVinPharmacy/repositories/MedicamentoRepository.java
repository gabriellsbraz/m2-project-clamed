package com.senai.clamed.DEVinPharmacy.repositories;

import com.senai.clamed.DEVinPharmacy.models.MedicamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicamentoRepository extends JpaRepository<MedicamentoModel, Integer> {
}
