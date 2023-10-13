package com.cliniquems.orderservice.webClient;

import com.cliniquems.orderservice.dto.Medicament;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class WebClientGetter {
    private final WebClient.Builder webClient;
    public Medicament getMedicament(String code) {
        final CompletableFuture<String> dataFuture = webClient.build().get()
                .uri("http://localhost:3180/api/medicament/get/"+code)
                .retrieve()
                .bodyToMono(String.class)
                .toFuture();

        String dataBrute= "";
        Medicament medicament= null;

        try {
            dataBrute= dataFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("error to fetch medicament future!!!");
        }

        try {
            JSONObject dataObject= new JSONObject(dataBrute);
            JSONObject data= dataObject.getJSONObject("data").getJSONObject("medicament");

            medicament= Medicament.builder()
                    .designation(data.getString("designation"))
                    .price(BigDecimal.valueOf(data.getDouble("price")))
                    .build();
        } catch (JSONException e) {
            throw new RuntimeException("error to deserialize JSON object!!!!");
        }
        return medicament;
    }
}
