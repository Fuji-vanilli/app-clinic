package com.cliniquems.orderservice.controller;

import com.cliniquems.orderservice.Utils.Response;
import com.cliniquems.orderservice.dto.OrderLineRequest;
import com.cliniquems.orderservice.service.OrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.cliniquems.orderservice.Utils.Root.APP_ROOT_ORDER_LINE;

@RestController
@RequestMapping(APP_ROOT_ORDER_LINE)
@RequiredArgsConstructor
public class OrderLineApi implements OrderLineController{
    private final OrderLineService orderLineService;
    @Override
    public ResponseEntity<Response> add(OrderLineRequest request) {
        return ResponseEntity.ok(orderLineService.add(request));
    }

    @Override
    public ResponseEntity<Response> get(String code) {
        return ResponseEntity.ok(orderLineService.get(code));
    }

    @Override
    public ResponseEntity<Response> all(int page, int size) {
        return ResponseEntity.ok(orderLineService.all(page, size));
    }

    @Override
    public ResponseEntity<Response> delete(String code) {
        return ResponseEntity.ok(orderLineService.delete(code));
    }
}
