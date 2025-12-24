package com.sentiment.demo.service;

import com.sentiment.demo.client.SentimentDsClient;
import com.sentiment.demo.dto.Prevision;
import com.sentiment.demo.dto.SentimentResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SentimentService {

    private final SentimentDsClient dsClient;

    @Value("${sentiment.mode}")
    private String mode;

    public SentimentService(SentimentDsClient dsClient) {
        this.dsClient = dsClient;
    }

    /**
     *  determina si el sistema esta en modo mock o debe llamar al servicio DS. regresa un DTO segun corresponda.
     * @param text
     * @return SentimentResponse
     */
    public SentimentResponse predict(String text) {
        if ("mock".equalsIgnoreCase(mode)) {
            return new SentimentResponse(Prevision.POSITIVO, 0.95);
        }
        return dsClient.predict(text);
    }
}
