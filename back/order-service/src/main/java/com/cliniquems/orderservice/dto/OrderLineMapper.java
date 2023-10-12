package com.cliniquems.orderservice.dto;

import com.cliniquems.orderservice.model.OrderLine;

public interface OrderLineMapper {
    OrderLine mapToOrderLine(OrderLineRequest request);
    OrderLineResponse mapToOrderLineResponse(OrderLine orderLine);
}
