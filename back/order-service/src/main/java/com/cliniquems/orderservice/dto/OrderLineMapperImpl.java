package com.cliniquems.orderservice.dto;

import com.cliniquems.orderservice.model.OrderLine;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderLineMapperImpl implements OrderLineMapper{
    @Override
    public OrderLine mapToOrderLine(OrderLineRequest request) {
        return OrderLine.builder()
                .code(request.getCode())
                .codeMedicaments(request.getCodeMedicaments())
                .build();
    }

    @Override
    public OrderLineResponse mapToOrderLineResponse(OrderLine orderLine) {
        return OrderLineResponse.builder()
                .code(orderLine.getCode())
                .codeMedicaments(orderLine.getCodeMedicaments())
                .medicaments(orderLine.getMedicaments())
                .totalPrice(orderLine.getTotalPrice())
                .build();
    }
}
