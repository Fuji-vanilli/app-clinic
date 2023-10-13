package com.cliniquems.orderservice.repository;

import com.cliniquems.orderservice.model.OrderLine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface OrderLineRepository extends MongoRepository<OrderLine, String> {
    Optional<OrderLine> findByCode(String code);
    Page<OrderLine> findAll(Pageable pageable);
    boolean existsByCode(String code);
    void deleteByCode(String code);
}
