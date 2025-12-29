package com.sentiment.demo.controller;

import com.sentiment.demo.dto.SentimentRequest;
import com.sentiment.demo.dto.SentimentResponse;
import com.sentiment.demo.service.SentimentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class SentimentController {

    private final SentimentService sentimentService;
    private final AtomicInteger requestCounter = new AtomicInteger(0);

    public SentimentController(SentimentService sentimentService) {
        this.sentimentService = sentimentService;
    }

    @PostMapping("/sentiment")
    public ResponseEntity<SentimentResponse> analizar(@Valid @RequestBody SentimentRequest request) {
        requestCounter.incrementAndGet();
        SentimentResponse response = sentimentService.predict(request.text().trim());
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint de estadísticas (en memoria).
     * Retorna el total de peticiones procesadas.
     */
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getStats() {
        return ResponseEntity.ok(Map.of(
                "total_requests", requestCounter.get(),
                "status", "active"
        ));
    }
}