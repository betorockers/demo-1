package com.sentiment.demo.dto;

public class SentimentResponse {
    private Prevision prevision;
    private double probabilidad;

    public SentimentResponse(Prevision prevision, double probabilidad) {
        this.prevision = prevision;
        this.probabilidad = probabilidad;
    }

    public Prevision getPrevision() {
        return prevision;
    }

    public void setPrevision(Prevision prevision) {
        this.prevision = prevision;
    }

    public double getProbabilidad() {
        return probabilidad;
    }

    public void setProbabilidad(double probabilidad) {
        this.probabilidad = probabilidad;
    }
}