package com.cliniquems.patientservice.repository;

import com.cliniquems.patientservice.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends MongoRepository<Patient, String> {
    Optional<Patient> findByCode(String code);
    void deleteByCode(String code);
    boolean existsByCode(String code);
    Page<Patient> findAll(Pageable pageable);

}
