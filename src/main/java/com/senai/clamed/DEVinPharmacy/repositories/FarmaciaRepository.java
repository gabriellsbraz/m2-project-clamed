package com.senai.clamed.DEVinPharmacy.repositories;

import com.senai.clamed.DEVinPharmacy.models.FarmaciaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmaciaRepository extends JpaRepository<FarmaciaModel, Long> {

}
