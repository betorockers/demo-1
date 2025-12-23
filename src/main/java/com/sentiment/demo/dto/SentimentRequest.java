package com.sentiment.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SentimentRequest(
        @NotBlank(message = "El texto no puede estar vacío")
        @Size(min = 5, message = "El texto debe tener al menos 5 caracteres")
        String text
                               )
{
    
}
