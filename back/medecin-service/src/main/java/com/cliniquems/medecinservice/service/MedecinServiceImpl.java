package com.cliniquems.medecinservice.service;

import com.cliniquems.medecinservice.Utils.Response;
import com.cliniquems.medecinservice.dto.MedecinMapper;
import com.cliniquems.medecinservice.dto.MedecinRequest;
import com.cliniquems.medecinservice.model.Medecin;
import com.cliniquems.medecinservice.repository.MedecinRepository;
import com.cliniquems.medecinservice.validator.MedecinValidator;
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
@Slf4j
@RequiredArgsConstructor
public class MedecinServiceImpl implements MedecinService {
    private final MedecinRepository repository;
    private final MedecinMapper medecinMapper;
    @Override
    public Response add(MedecinRequest request) {
        List<String> errors= MedecinValidator.validate(request);
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
            log.error("medecin already exist on database");
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "medecin already exist on database"
            );

        }

        Medecin medecin= medecinMapper.mapToMedecin(request);
        medecin.setCreationDate(new Date());
        medecin.setLastUpdate(new Date());

        repository.save(medecin);
        log.info("new medecin added successfully!");

        URI location= ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("{code}")
                .buildAndExpand("api/medecin/get/"+medecin.getCode())
                .toUri();

        return generateResponse(
                HttpStatus.OK,
                location,
                Map.of(
                        "medecin", medecinMapper.mapToMedecinResponse(medecin)
                ),
                "new medecin added successfully!"
        );
    }
    @Override
    public Response get(String code) {
        Optional<Medecin> medecinOptional= repository.findByCode(code);
        if(medecinOptional.isEmpty()) {
            log.error("the medecin with the code: {} doesn't exist! ", code);
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "the medecin with the code: "+code+" doesn't exist on the database"
            );
        }

        Medecin medecin= medecinOptional.get();
        log.info("medecin with the code {} getted successfully!", code);
        return generateResponse(
                HttpStatus.OK,
                null,
                Map.of(
                        "medecin", medecinMapper.mapToMedecinResponse(medecin)
                ),
                "medecin with the code: "+code+" getted successfully!"
        );
    }

    @Override
    public Response update(MedecinRequest request) {
        final String code= request.getCode();
        Optional<Medecin> medecinOptional= repository.findByCode(code);
        if(medecinOptional.isEmpty()){
            log.error("the medecin with the code {} doesn't exist!",code);
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "the medecin with the code: "+code+" doesn't exist on the database"
            );
        }

        Medecin medecin= medecinOptional.get();
        medecin.setFirstname(request.getFirstname());
        medecin.setLastname(request.getLastname());
        medecin.setPhoneNumber(request.getPhoneNumber());
        medecin.setSalary(request.getSalary());
        medecin.setLastUpdate(new Date());

        repository.save(medecin);
        log.info("medecin with the code: {} updated successfully!", code);

        return generateResponse(
                HttpStatus.OK,
                null,
                Map.of(
                        "medecin", medecinMapper.mapToMedecinResponse(medecin)
                ),
                "medecin with the code: "+code+" updated successfully!"
        );
    }

    @Override
    public Response all(int page, int size) {
        Pageable pageable= PageRequest.of(page, size);
        log.info("all medecin with the page: {} and size: {} is getted successfully!", page, size);
        return generateResponse(
                HttpStatus.OK,
                null,
                Map.of(
                        "contents number", repository.findAll(pageable).getContent().size(),
                        "medecins", repository.findAll(pageable).getContent().stream()
                                .map(medecinMapper::mapToMedecinResponse)
                                .toList()
                ),
                "all medecin with the page: "+page+" and size: "+size+" is getted successfully!"
        );
    }

    @Override
    public Response delete(String code) {
        Optional<Medecin> medecinOptional= repository.findByCode(code);
        if(medecinOptional.isEmpty()) {
            log.error("the medecin with the code: {} doesn't exist! ", code);
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "the medecin with the code: "+code+" doesn't exist on the database"
            );
        }

        repository.deleteByCode(code);
        log.info("medecin with the code {} deleted successfully!", code);
        return generateResponse(
                HttpStatus.OK,
                null,
                null,
                "medecin with the code: "+code+" deleted successfully!"
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
