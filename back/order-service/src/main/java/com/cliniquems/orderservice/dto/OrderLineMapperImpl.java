package com.cliniquems.orderservice.dto;

import com.cliniquems.orderservice.model.OrderLine;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderLineMapperImpl implements OrderLineMapper{
    @Override
    public OrderLine mapToOrderLine(OrderLineRequest request) {
        return OrderLine.builder()
                .code(request.getCode())
                .codeMedicament(request.getCodeMedicament())
                .quantity(request.getQuantity())
                .build();
    }
    @Override
    public OrderLineResponse mapToOrderLineResponse(OrderLine orderLine) {
        return OrderLineResponse.builder()
                .code(orderLine.getCode())
                .codeMedicament(orderLine.getCodeMedicament())
                .medicament(orderLine.getMedicament())
                .totalPrice(orderLine.getTotalPrice())
                .quantity(orderLine.getQuantity())
                .build();
    }
}
