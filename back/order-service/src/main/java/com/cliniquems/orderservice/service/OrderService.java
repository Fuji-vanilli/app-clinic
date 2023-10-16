package com.cliniquems.orderservice.service;

import com.cliniquems.orderservice.Utils.Response;
import com.cliniquems.orderservice.dto.OrderRequest;

public interface OrderService {
    Response add(OrderRequest request);
    Response get(String code);
    Response all(int page, int size);
    Response delete(String code);
}
