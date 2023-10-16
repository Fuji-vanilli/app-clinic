package com.cliniquems.orderservice.dto;

import com.cliniquems.orderservice.model.Order;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderMapperImpl implements OrderMapper{

    @Override
    public Order mapToOrder(OrderRequest request) {
        return Order.builder()
                .code(request.getCode())
                .codeOrderLines(request.getCodeOrderLines())
                .build();
    }

    @Override
    public OrderResponse mapToOrderResponse(Order order) {
        return OrderResponse.builder()
                .code(order.getCode())
                .codeOrderLines(order.getCodeOrderLines())
                .orderLines(order.getOrderLines())
                .date(order.getDate())
                .totalPrice(order.getTotalPrice())
                .build();
    }
}
