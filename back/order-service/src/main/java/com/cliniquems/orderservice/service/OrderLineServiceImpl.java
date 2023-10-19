package com.cliniquems.orderservice.service;

import com.cliniquems.orderservice.Utils.Response;
import com.cliniquems.orderservice.dto.Medicament;
import com.cliniquems.orderservice.dto.OrderLineMapper;
import com.cliniquems.orderservice.dto.OrderLineRequest;
import com.cliniquems.orderservice.model.OrderLine;
import com.cliniquems.orderservice.repository.OrderLineRepository;
import com.cliniquems.orderservice.validator.OrderLineValidator;
import com.cliniquems.orderservice.webClient.WebClientGetter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Configuration
@Transactional
@RequiredArgsConstructor
@Slf4j
public class OrderLineServiceImpl implements OrderLineService{
    private final OrderLineRepository repository;
    private final OrderLineMapper orderLineMapper;
    private final WebClientGetter webClient;

    @Override
    public Response add(OrderLineRequest request) {
        List<String> errors= OrderLineValidator.validate(request);
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
            log.error("OrderLine already exist on database");
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "OrderLine already exist on database"
            );
        }

        OrderLine orderLine= orderLineMapper.mapToOrderLine(request);
        Medicament medicament= webClient.getMedicament(orderLine.getCodeMedicament());

        orderLine.setMedicament(medicament);
        orderLine.setTotalPrice(medicament.getPrice().multiply(orderLine.getQuantity()));

        repository.save(orderLine);

        URI location= ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("{code}")
                .buildAndExpand("api/orderLine/get/"+orderLine.getCode())
                .toUri();

        log.info("new order line added successfully!");
        return generateResponse(
                HttpStatus.OK,
                location,
                Map.of(
                        "orderLine", orderLineMapper.mapToOrderLineResponse(orderLine)
                ),
                "new order line added successfully!"
        );
    }

    @Override
    public Response get(String code) {

        Optional<OrderLine> orderLineOptional= repository.findByCode(code);
        if(orderLineOptional.isEmpty()) {
            log.error("the order line with the code: {} doesn't exist! ", code);
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "the order line with the code: "+code+" doesn't exist on the database"
            );
        }

        OrderLine orderLine= orderLineOptional.get();
        log.info("order line with the code {} getted successfully!", code);

        Medicament medicament= webClient.getMedicament(orderLine.getCodeMedicament());
        orderLine.setMedicament(medicament);

        return generateResponse(
                HttpStatus.OK,
                null,
                Map.of(
                        "orderLine", orderLineMapper.mapToOrderLineResponse(orderLine)
                ),
                "orderLine with the code: "+code+" getted successfully!"
        );

    }

    @Override
    public Response all(int page, int size) {
        Pageable pageable= PageRequest.of(page, size);
        log.info("all order line with page: {} and size: {} getted successfully!", page, size);

        return generateResponse(
                HttpStatus.OK,
                null,
                Map.of(
                        "numberElement", repository.findAll(pageable).getContent().size(),
                        "orderLines", repository.findAll(pageable).getContent().stream()
                                .map(orderLine -> {
                                    Medicament medicament= webClient.getMedicament(orderLine.getCodeMedicament());
                                    orderLine.setMedicament(medicament);

                                    return orderLineMapper.mapToOrderLineResponse(orderLine);
                                })
                ),
                "all order line with the page: "+page+" size: "+size+" getted successfully!"
        );
    }

    @Override
    public Response delete(String code) {

        if(!repository.existsByCode(code)) {
            log.error("the order line with the code: {} doesn't exist! ", code);
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "the order line with the code: "+code+" doesn't exist on the database"
            );
        }

        repository.deleteByCode(code);
        log.info("order line with the code {} deleted successfully!", code);

        return generateResponse(
                HttpStatus.OK,
                null,
                null,
                "orderLine with the code: "+code+" deleted successfully!"
        );
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
