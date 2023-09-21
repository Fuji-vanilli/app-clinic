package com.cliniquems.patientservice.service;

import com.cliniquems.patientservice.Utils.Response;
import com.cliniquems.patientservice.dto.PatientMapper;
import com.cliniquems.patientservice.dto.PatientRequest;
import com.cliniquems.patientservice.model.Patient;
import com.cliniquems.patientservice.repository.PatientRepository;
import com.cliniquems.patientservice.validator.PatientValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PatientServiceImpl implements PatientService{
    private final PatientRepository repository;
    private final PatientMapper patientMapper;
    @Override
    public Response add(PatientRequest request) {
        List<String> errors= PatientValidator.validate(request);
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
            log.error("patient already exist on database");
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    Map.of(
                            "errors", errors
                    ),
                    "patient already exist on database"
            );

        }
        Patient patient= patientMapper.mapToPatient(request);
        patient.setCreationDate(new Date());
        patient.setLastUpdateDate(new Date());

        URI location= ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("{code}")
                .buildAndExpand("api/patient/"+patient.getCode())
                .toUri();

        return generateResponse(
                HttpStatus.OK,
                location,
                Map.of(
                        "patient", patientMapper.mapToPatientResponse(patient)
                ),
                "new patient added successfully!"
        );
    }

    @Override
    public Response get(String code) {
        return null;
    }

    @Override
    public Response all() {
        return null;
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
