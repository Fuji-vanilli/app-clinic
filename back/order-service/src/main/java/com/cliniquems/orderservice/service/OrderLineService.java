package com.cliniquems.orderservice.service;

import com.cliniquems.orderservice.Utils.Response;
import com.cliniquems.orderservice.dto.OrderLineRequest;

public interface OrderLineService {
    Response add(OrderLineRequest request);
    Response get(String code);
    Response all(int page, int size);
    Response delete(String code);
}
