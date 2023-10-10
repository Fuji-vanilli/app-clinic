package com.cliniquems.porteservice.service;

import com.cliniquems.porteservice.Utils.Response;
import com.cliniquems.porteservice.dto.Patient;
import com.cliniquems.porteservice.dto.PorteMapper;
import com.cliniquems.porteservice.dto.PorteRequest;
import com.cliniquems.porteservice.model.Porte;
import com.cliniquems.porteservice.repository.PorteRepository;
import com.cliniquems.porteservice.validator.PorteValidator;
import com.cliniquems.porteservice.webRest.WebClientGetter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.sound.sampled.Port;
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
public class PorteServiceImpl implements PorteService {
    private final PorteRepository repository;
    private final PorteMapper porteMapper;
    private final WebClientGetter webClient;
    @Override
    public Response add(PorteRequest request) {
        List<String> errors= PorteValidator.validate(request);
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
        if(repository.existsByNumero(request.getNumero())){
            log.error("porte already exist on database");
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "porte already exist on database"
            );
        }

        Porte porte= porteMapper.mapToPorte(request);
        porte.setCreationDate(new Date());
        porte.setLastUpdate(new Date());

        repository.save(porte);

        porte.setMedecin(webClient.getMedecin(porte.getCodeMedecin()));

        log.info("new porte added successfully!");

        URI location= ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("{numero}")
                .buildAndExpand("api/porte/get/"+porte.getNumero())
                .toUri();

        return generateResponse(
                HttpStatus.OK,
                location,
                Map.of(
                        "porte", porteMapper.mapToPorteResponse(porte)
                ),
                "new porte added successfully!"
        );
    }

    @Override
    public Response addPatient(Map<String, String> data) {
        String numero= data.get("numero");
        Optional<Porte> porteOptional= repository.findByNumero(numero);

        if(porteOptional.isEmpty()){
            log.error("porte with the numero: {} doesn't exist", numero);
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "porte with the numero: "+numero+" doesn't exist!"
            );
        }

        Porte porte= porteOptional.get();
        Patient patient= webClient.getPatient(data.get("codePatient"));

        porte.getCodePatients().add(data.get("codePatient"));
        porte.getPatients().add(patient);
        log.info("porte numero {} patched successfully!", numero);
        return generateResponse(
                HttpStatus.OK,
                null,
                Map.of(
                        "porte", porteMapper.mapToPorteResponse(porte)
                ),
                "porte numero: "+numero+" patched successfully!"
        );
    }
    @Override
    public Response get(String numero) {
        return null;
    }

    @Override
    public Response all(int page, int size) {
        return null;
    }

    @Override
    public Response update(PorteRequest request) {
        return null;
    }

    @Override
    public Response delete(String numero) {
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
