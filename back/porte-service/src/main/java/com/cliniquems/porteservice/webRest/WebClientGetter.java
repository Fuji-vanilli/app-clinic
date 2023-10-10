package com.cliniquems.porteservice.webRest;

import com.cliniquems.porteservice.dto.Medecin;
import com.cliniquems.porteservice.dto.Patient;
import com.cliniquems.porteservice.dto.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class WebClientGetter {
    private final WebClient.Builder webClient;

    public Medecin getMedecin(String code) {
        final CompletableFuture<String> dataFuture = webClient.build().get()
                .uri("http://localhost:3110/api/medecin/get/" + code)
                .retrieve()
                .bodyToMono(String.class)
                .toFuture();

        String dataBrute= null;
        JSONObject data= null;
        Medecin medecin= new Medecin();

        try {
            dataBrute= dataFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("error to deserialize object future to string!!!");
        }

        try {
            JSONObject dataJson= new JSONObject(dataBrute);
            data= dataJson.getJSONObject("data").getJSONObject("medecin");

            medecin= Medecin.builder()
                    .firstname(data.getString("firstname"))
                    .lastname(data.getString("lastname"))
                    .phoneNumber(data.getString("phoneNumber"))
                    .speciality(data.getString("speciality"))
                    .build();
        } catch (JSONException e) {
            throw new RuntimeException("error to deserialize string to json object!!!!");
        }

        return medecin;
    }

    public Patient getPatient(String code) {
        final CompletableFuture<String> dataFuture = webClient.build().get()
                .uri("http://localhost:3100/api/patient/get/" + code)
                .retrieve()
                .bodyToMono(String.class)
                .toFuture();

        String dataBrute= null;
        JSONObject data= null;
        Patient patient= new Patient();

        try {
            dataBrute= dataFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("error to deserialize object future to string!!!");
        }

        try {
            JSONObject dataJson= new JSONObject(dataBrute);
            data= dataJson.getJSONObject("data").getJSONObject("patient");

            String dateJson= data.getString("date");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date date= dateFormat.parse(dateJson);

            patient= Patient.builder()
                    .code(data.getString("code"))
                    .status(Status.valueOf(data.getString("status").toUpperCase()))
                    .firstname(data.getString("firstname"))
                    .lastname(data.getString("lastname"))
                    .phoneNumber(data.getString("phoneNumber"))
                    .dateOfBirth(date)
                    .build();
        } catch (JSONException e) {
            throw new RuntimeException("error to deserialize string to json object!!!!");
        } catch (ParseException e) {
            throw new RuntimeException("error to convert date type!!!");
        }
        return patient;
    }
}
