package com.cliniquems.medecinservice.repository;

import com.cliniquems.medecinservice.model.Medecin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedecinRepository extends MongoRepository<Medecin, String> {
    Optional<Medecin> findByCode(String code);
    boolean existsByCode(String code);
    void deleteByCode(String code);
    Page<Medecin> findAll(Pageable pageable);
}
