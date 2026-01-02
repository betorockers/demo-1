<<<<<<< HEAD:src/main/java/com/sentiment/demo/service/SentimentService.java
package com.sentiment.demo.service;

import com.sentiment.demo.client.SentimentDsClient;
import com.sentiment.demo.dto.Prevision;
import com.sentiment.demo.dto.SentimentResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SentimentService {

    private final SentimentDsClient dsClient;
    private final SentimentStatService sentimentStatService;

    @Value("${sentiment.mode}")
    private String mode;

    public SentimentService(SentimentDsClient dsClient, SentimentStatService sentimentStatService) {
        this.dsClient = dsClient;
        this.sentimentStatService = sentimentStatService;
    }

    /**
     * Orquesta el modo de ejecución:
     * - mock: devuelve una respuesta fija para pruebas/demos
     * - python: delega la predicción al cliente de DS (FastAPI)
     * - persiste la consulta solo si es del modelo para no ensuciar la DB.
     */
    public SentimentResponse predict(String text) {
        if ("mock".equalsIgnoreCase(mode)) {
            return new SentimentResponse(Prevision.POSITIVO, 0.95);
        }
        SentimentResponse  respuesta = dsClient.predict(text);
        sentimentStatService.guardar(text,respuesta.prevision(), respuesta.probabilidad());
        return respuesta;
    }

    /**
     * Verifica la conexión con el modelo si no estamos en modo mock.
     */
    public void checkHealth() {
        if (!"mock".equalsIgnoreCase(mode)) {
            dsClient.healthCheck();
        }
    }
}
=======
package com.sentiment.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sentiment.backend.client.SentimentDsClient;
import com.sentiment.backend.dto.Prevision;
import com.sentiment.backend.dto.SentimentResponse;

@Service
public class SentimentService {

    private final SentimentDsClient dsClient;

    @Value("${sentiment.mode}")
    private String mode;

    public SentimentService(SentimentDsClient dsClient) {
        this.dsClient = dsClient;
    }

    /**
     * Orquesta el modo de ejecución:
     * - mock: devuelve una respuesta fija para pruebas/demos
     * - python: delega la predicción al cliente de DS (FastAPI)
     */
    public SentimentResponse predict(String text) {
        if ("mock".equalsIgnoreCase(mode)) {
            return new SentimentResponse(Prevision.POSITIVO, 0.95);
        }
        return dsClient.predict(text);
    }

    /**
     * Verifica la conexión con el modelo si no estamos en modo mock.
     */
    public void checkHealth() {
        if (!"mock".equalsIgnoreCase(mode)) {
            dsClient.healthCheck();
        }
    }
}
>>>>>>> 4b46a35 (limpieza, profesionalizacion del codigo, reemplazo de nombre del proyecto, cosistencias y contrato):src/main/java/com/sentiment/backend/service/SentimentService.java
