package com.sentiment.demo.dto;

import jakarta.validation.constraints.NotBlank;

public class SentimentRequest {

    @NotBlank(message = "El texto no puede estar vacío")
    private String text;

    public SentimentRequest() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}