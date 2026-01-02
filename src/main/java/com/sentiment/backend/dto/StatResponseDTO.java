package com.sentiment.demo.dto;

public record StatResponseDTO(
        int limit,
        long total,
        long positivos,
        long neutros,
        long negativos,
        double pctPositivos,
        double pctNeutros,
        double pctNegativos
) {
}
