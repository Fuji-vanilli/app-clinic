package com.cliniquems.orderservice.controller;

import com.cliniquems.orderservice.Utils.Response;
import com.cliniquems.orderservice.dto.OrderRequest;
import com.cliniquems.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import static com.cliniquems.orderservice.Utils.Root.APP_ROOT_ORDER;

@RestController
@RequestMapping(APP_ROOT_ORDER)
@RequiredArgsConstructor
public class OrderApi implements OrderController{
    private final OrderService orderService;
    @Override
    public ResponseEntity<Response> add(OrderRequest request)  {
        return ResponseEntity.ok(orderService.add(request));
    }

    @Override
    public ResponseEntity<Response> get(String code) {
        return ResponseEntity.ok(orderService.get(code));
    }

    @Override
    public ResponseEntity<Response> all(int page, int size) {
        return ResponseEntity.ok(orderService.all(page, size));
    }

    @Override
    public ResponseEntity<Response> delete(String code) {
        return ResponseEntity.ok(orderService.delete(code));
    }
}
