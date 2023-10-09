package com.cliniquems.patientservice.service;

import com.cliniquems.patientservice.Utils.Response;
import com.cliniquems.patientservice.dto.PatientMapper;
import com.cliniquems.patientservice.dto.PatientRequest;
import com.cliniquems.patientservice.model.Patient;
import com.cliniquems.patientservice.model.Status;
import com.cliniquems.patientservice.repository.PatientRepository;
import com.cliniquems.patientservice.validator.PatientValidator;
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
                    null,
                    "patient already exist on database"
            );

        }
        Patient patient= patientMapper.mapToPatient(request);
        patient.setCreationDate(new Date());
        patient.setLastUpdateDate(new Date());
        patient.setStatus(Status.WAITING);

        repository.save(patient);
        log.info("new patient added successfully!");

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
        Optional<Patient> patientOptional= repository.findByCode(code);
        if(patientOptional.isEmpty()) {
            log.error("the patient with the code: {} doesn't exist! ", code);
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "the patient with the code: "+code+" doesn't exist on the database"
            );
        }

        Patient patient= patientOptional.get();
        log.info("patient with the code {} getted successfully!", code);
        return generateResponse(
                HttpStatus.OK,
                null,
                Map.of(
                        "patient", patientMapper.mapToPatientResponse(patient)
                ),
                "patient with the code: "+code+" getted successfully!"
        );
    }

    @Override
    public Response all(int page, int size) {
        Pageable pageable= PageRequest.of(page, size);
        log.info("All patient withe {} page and {} size, getted successfully!", page, size);
        return generateResponse(
                HttpStatus.OK,
                null,
                Map.of(
                        "contents number", repository.findAll(pageable).getContent().size(),
                        "patients", repository.findAll(pageable).getContent().stream()
                                .map(patientMapper::mapToPatientResponse)
                                .toList()
                ),
                "all patient with the page:"+page+" and size: "+size+" getted successfully"
        );
    }

    @Override
    public Response delete(String code) {
        Optional<Patient> patientOptional= repository.findByCode(code);
        if(patientOptional.isEmpty()) {
            log.error("the patient with the code: {} doesn't exist! ", code);
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "the patient with the code: "+code+" doesn't exist on the database"
            );
        }

        repository.deleteByCode(code);
        log.info("patient with the code {} deleted successfully!", code);
        return generateResponse(
                HttpStatus.OK,
                null,
                null,
                "patient with the code: "+code+" deleted successfully!"
        );
    }

    @Override
    public Response updateStatus(Map<String, String> updateStatus) {
        final String code= updateStatus.get("code");
        Optional<Patient> patientOptional= repository.findByCode(code);
        if(patientOptional.isEmpty()){
            log.error("the patient with the code: {} doesn't exist!", code);
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "the patient with the code: "+code+" doesn't exist!"
            );
        }
        Patient patient= patientOptional.get();
        log.info("patient with the code {} patched: {} to {} successfully!", code, patient.getStatus(), Status.valueOf(updateStatus.get("status").toUpperCase()));
        patient.setStatus(Status.valueOf(updateStatus.get("status").toUpperCase()));

        repository.save(patient);

        return generateResponse(
                HttpStatus.OK,
                null,
                Map.of(
                        "patient", patientMapper.mapToPatientResponse(patient)
                ),
                "status of the patient with the code: "+code+" updated successfully!"
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
