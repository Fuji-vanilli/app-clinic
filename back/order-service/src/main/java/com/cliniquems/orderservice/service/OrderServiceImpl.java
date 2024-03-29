package com.cliniquems.orderservice.service;

import com.cliniquems.orderservice.Utils.Response;
import com.cliniquems.orderservice.dto.OrderLineMapper;
import com.cliniquems.orderservice.dto.OrderMapper;
import com.cliniquems.orderservice.dto.OrderRequest;
import com.cliniquems.orderservice.model.Order;
import com.cliniquems.orderservice.model.OrderLine;
import com.cliniquems.orderservice.repository.OrderLineRepository;
import com.cliniquems.orderservice.repository.OrderRepository;
import com.cliniquems.orderservice.validator.OrderLineValidator;
import com.cliniquems.orderservice.validator.OrderValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService{
    private final OrderRepository repository;
    private final OrderLineRepository orderLineRepository;
    private final OrderMapper orderMapper;
    private final GenerateReport generateReport;
    @Override
    public Response add(OrderRequest request) {
        List<String> errors= OrderValidator.validate(request);
        if(!errors.isEmpty()){
            log.error("some request not found");
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    Map.of(
                            "errors", errors
                    ),
                    "some request not found"
            );
        }
        if(repository.existsByCode(request.getCode())){
            log.error("Order already exist on database");
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "Order already exist on database"
            );
        }

        Order order= orderMapper.mapToOrder(request);
        order.setDate(new Date());

        final List<OrderLine> orderLines = order.getCodeOrderLines().stream()
                .map(c-> orderLineRepository.findByCode(c).orElse(null))
                .toList();

        final BigDecimal totalPrice = orderLines.stream()
                .map(OrderLine::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setOrderLines(orderLines);
        order.setTotalPrice(totalPrice);

        repository.save(order);
        try {
            generateReport.generate(order);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("error to generate report");
        }

        URI location= ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/{code}")
                .buildAndExpand("api/order/get/"+order.getCode())
                .toUri();

        log.info("new order added successfully!");
        return generateResponse(
                HttpStatus.OK,
                location,
                Map.of(
                        "order", orderMapper.mapToOrderResponse(order)
                ),
                "new order added successfully!"
        );
    }

    @Override
    public Response get(String code) {
        Optional<Order> orderOptional= repository.findByCode(code);
        if(orderOptional.isEmpty()) {
            log.error("the order with the code: {} doesn't exist! ", code);
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "the order with the code: "+code+" doesn't exist on the database"
            );
        }
        Order order= orderOptional.get();
        final List<OrderLine> orderLines = order.getCodeOrderLines().stream()
                .map(c -> orderLineRepository.findByCode(c).orElse(null))
                .toList();

        order.setOrderLines(orderLines);
        log.info("order with the code: {} getted successfully", code);
        return generateResponse(
                HttpStatus.OK,
                null,
                Map.of(
                        "order", orderMapper.mapToOrderResponse(order)
                ),
                "order with the code: "+code+" getted successfully!"
        );
    }

    @Override
    public Response all(int page, int size) {
        Pageable pageable= PageRequest.of(page, size);

        log.info("all order with the page: {} and size: {} getted successfully", page, size);
        return generateResponse(
                HttpStatus.OK,
                null,
                Map.of(
                        "orders", repository.findAll(pageable).getContent().stream()
                                .map(orderMapper::mapToOrderResponse)
                ),
                "all order with the page: "+page+" and size: "+size+" getted successfully!"
        );
    }

    @Override
    public Response delete(String code) {
        return null;
    }
    private Response generateResponse(HttpStatus status, URI location, Map<?, ?> data, String message){
        return Response.builder()
                .timeStamp(LocalDateTime.now())
                .status(status)
                .statusCode(status.value())
                .location(location)
                .data(data)
                .message(message)
                .build();
    }
}
