package com.cliniquems.orderservice.repository;

import com.cliniquems.orderservice.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OrderRepository extends MongoRepository<Order, String> {
    Optional<Order> findByCode(String code);
    boolean existsByCode(String code);
    void deleteByCode(String code);
    Page<Order> findAll(Pageable pageable);
}
