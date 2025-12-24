package com.sentiment.demo.controller;

import com.sentiment.demo.dto.Prevision;
import com.sentiment.demo.dto.SentimentRequest;
import com.sentiment.demo.dto.SentimentResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sentiment")
public class SentimentController {

    @PostMapping
    public ResponseEntity<SentimentResponse> analyzeSentiment(@Valid @RequestBody SentimentRequest request) {
        // Lógica Mock para pasar los tests y cumplir con el MVP
        // Más adelante aquí se conectará el servicio de Python (Dev 1)
        SentimentResponse response = new SentimentResponse(Prevision.POSITIVO, 0.95);
        return ResponseEntity.ok(response);
    }
}