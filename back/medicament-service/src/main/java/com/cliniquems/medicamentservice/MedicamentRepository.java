package com.cliniquems.medicamentservice;

import com.cliniquems.medicamentservice.model.Medicament;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicamentRepository extends JpaRepository<Medicament, Long> {
    Optional<Medicament> findByCode(String code);
    void deleteByCode(String code);
    boolean existsByCode(String code);
    Page<Medicament> findAll(Pageable pageable);
}
