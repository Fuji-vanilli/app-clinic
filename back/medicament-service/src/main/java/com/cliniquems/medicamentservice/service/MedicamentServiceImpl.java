package com.cliniquems.medicamentservice.service;

import com.cliniquems.medicamentservice.Utils.Response;
import com.cliniquems.medicamentservice.dto.MedicamentMapper;
import com.cliniquems.medicamentservice.dto.MedicamentRequest;
import com.cliniquems.medicamentservice.model.Medicament;
import com.cliniquems.medicamentservice.repository.MedicamentRepository;
import com.cliniquems.medicamentservice.validator.MedicamentValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
public class MedicamentServiceImpl implements MedicamentService{
    private final MedicamentRepository repository;
    private final MedicamentMapper medicamentMapper;
    @Override
    public Response add(MedicamentRequest request) {
        List<String> errors= MedicamentValidator.validate(request);
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
            log.error("medicament already exist on database");
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "medicament already exist on database"
            );
        }
        Medicament medicament= medicamentMapper.mapToMedicament(request);
        medicament.setCreationDate(new Date());
        medicament.setLastUpdate(new Date());

        repository.save(medicament);
        log.info("new medicament added successfully!");

        URI location= ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/{code}")
                .buildAndExpand("api/medicament/get/"+medicament.getCode())
                .toUri();

        return generateResponse(
                HttpStatus.OK,
                location,
                Map.of(
                        "medicament", medicamentMapper.mapToMedicamentResponse(medicament)
                ),
                "new medicament added successfully!"
        );
    }

    @Override
    public Response get(String code) {
        Optional<Medicament> medicamentOptional= repository.findByCode(code);
        if(medicamentOptional.isEmpty()) {
            log.error("the medicament with the code: {} doesn't exist! ", code);
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "the medicament with the code: "+code+" doesn't exist on the database"
            );
        }

        Medicament medicament= medicamentOptional.get();
        log.info("medicament with the code: {} getted successfully!", code);

        return generateResponse(
                HttpStatus.OK,
                null,
                Map.of(
                        "medicament", medicamentMapper.mapToMedicamentResponse(medicament)
                ),
                "medicament with the code: "+code+" getted successfully!"
        );
    }

    @Override
    public Response update(MedicamentRequest request) {
        Optional<Medicament> medicamentOptional= repository.findByCode(request.getCode());
        if(medicamentOptional.isEmpty()) {
            log.error("the medicament with the code: {} doesn't exist on the database", request.getCode());
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "the medicament with the code: "+request.getCode()+" doesn't exist on the database"
            );
        }

        Medicament medicament= medicamentOptional.get();
        medicament.setPrice(request.getPrice());
        medicament.setLastUpdate(new Date());

        repository.save(medicament);
        log.info("the medicament with the code: {} patched successfully!", medicament.getCode());

        return generateResponse(
                HttpStatus.OK,
                null,
                Map.of(
                        "medicament", medicamentMapper.mapToMedicamentResponse(medicament)
                ),
                "the medicament with the code: "+medicament.getCode()+" patched successfully!"
        );
    }

    @Override
    public Response all(int page, int size) {
        Pageable pageable= PageRequest.of(page, size);
        log.info("all medicament getted with the page: {} and size: {} getted successfully!", page, size);

        return generateResponse(
                HttpStatus.OK,
                null,
                Map.of(
                        "numberElement", repository.findAll(pageable).getContent().size(),
                        "medicaments", repository.findAll(pageable).getContent().stream()
                                .map(medicamentMapper::mapToMedicamentResponse)
                ),
                "all medicament with the page: "+page+" and size: "+size+" getted successfully"
        );
    }

    @Override
    public Response delete(String code) {
        if(!repository.existsByCode(code)) {
            log.error("the medicament with the code: {} doesn't exist! ", code);
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "the medicament with the code: "+code+" doesn't exist on the database"
            );
        }

        repository.deleteByCode(code);
        log.info("medicament with the code: {} deleted successfully!", code);

        return generateResponse(
                HttpStatus.OK,
                null,
                null,
                "medicament with the code: "+code+" deleted successfully!"
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
