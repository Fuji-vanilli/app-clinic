package com.cliniquems.porteservice.repository;

import com.cliniquems.porteservice.model.Porte;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PorteRepository extends JpaRepository<Porte, Long> {
    Optional<Porte> findByNumero(String numero);
    boolean existsByNumero(String numero);
    void deleteByNumero(String numero);
    Page<Porte> findAll(Pageable pageable);
}
