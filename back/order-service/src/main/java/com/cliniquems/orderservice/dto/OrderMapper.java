package com.cliniquems.orderservice.dto;

import com.cliniquems.orderservice.model.Order;

public interface OrderMapper {
    Order mapToOrder(OrderRequest request);
    OrderResponse mapToOrderResponse(Order order);
}
