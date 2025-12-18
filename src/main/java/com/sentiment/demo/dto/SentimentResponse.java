package com.sentiment.demo.dto;

/**
 * Esta clase es un contrato
 * Define exactamentelo que vamos a responder al usuario 
 */

public class SentimentResponse {
    private String prevision; // positivo, negativo, neutral
    private double probabilidad; // Ejemplo: 0.85
    private String error; // si algo llegaq a salir mal
    

    // Constructor
    public SentimentResponse(String prevision, double probabilidad){
        this.prevision = prevision;
        this.probabilidad = probabilidad;
        this.error = null;    
    }

    // Constructor para el caso de error
    public String getPrevision() {return prevision; }
    public double getProbabilidad() { return probabilidad; }
    public String getError() { return error;}
}
