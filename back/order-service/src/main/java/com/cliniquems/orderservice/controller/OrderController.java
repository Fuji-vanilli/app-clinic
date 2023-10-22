package com.cliniquems.orderservice.controller;

import com.cliniquems.orderservice.Utils.Response;
import com.cliniquems.orderservice.dto.OrderRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface OrderController {
    @PostMapping("add")
    ResponseEntity<Response> add(@RequestBody OrderRequest request) ;
    @GetMapping("get/{code}")
    ResponseEntity<Response> get(@PathVariable String code);
    @GetMapping("all")
    ResponseEntity<Response> all(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    );
    @DeleteMapping("delete/{code}")
    ResponseEntity<Response> delete(@PathVariable String code);
}
