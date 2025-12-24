package com.sentiment.demo.exception;

import com.sentiment.demo.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ==============================
    // 400: Errores de validación (@Valid)
    // ==============================
    // Manejo de Error 400 (Validaciones) - Necesario para que pase tu test de "Bad Request"
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        // CODIGO COMENTADO PARA REVISION:
        // String msg = Objects.requireNonNull(
        //         ex.getBindingResult().getFieldError()
        // ).getDefaultMessage();
        
        String msg = ex.getBindingResult().getFieldError() != null
                ? ex.getBindingResult().getFieldError().getDefaultMessage()
                : "Datos de entrada inválidos";

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                // CODIGO COMENTADO PARA REVISION:
                // .body(new ErrorResponse(
                //         msg,
                //         "VALIDATION_ERROR"
                // ));
                .body(new ErrorResponse(msg, "VALIDATION_ERROR"));
    }

    // ==============================
    // 503: Modelo no disponible (timeout/conexión)
    // ==============================
    // CODIGO COMENTADO PARA REVISION (Conflicto de merge):
    // @ExceptionHandler({ResourceAccessException.class, ModelUnavailableException.class})
    // public ResponseEntity<ErrorResponse> handleModelUnavailable(Exception ex) {
    
    // Manejo de Error 500 (Genérico)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        // CODIGO COMENTADO PARA REVISION:
        // return ResponseEntity
        //         .status(HttpStatus.SERVICE_UNAVAILABLE)
        //         .body(new ErrorResponse(
        //                 "Modelo no disponible",
        //                 "MODEL_UNAVAILABLE"
        //         ));
        
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("Ocurrió un error interno inesperado.", "INTERNAL_SERVER_ERROR"));
    }

    // ==============================
    // 502: Error del servicio DS (FastAPI responde 4xx/5xx)
    // ==============================
    // CODIGO COMENTADO PARA REVISION:
    // @ExceptionHandler(HttpStatusCodeException.class)
    // public ResponseEntity<ErrorResponse> handleDsHttpError(HttpStatusCodeException ex) {
    
    // Manejo de Error 503 (Conexión)
    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<ErrorResponse> handleConnectionError(ResourceAccessException ex) {
        // CODIGO COMENTADO PARA REVISION:
        // return ResponseEntity
        //         .status(HttpStatus.BAD_GATEWAY)
        //         .body(new ErrorResponse(
        //                 "Error del servicio de predicción (DS)",
        //                 "DS_SERVICE_ERROR"
        //         ));
        
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(new ErrorResponse("El modelo de IA no está disponible en este momento.", "SERVICE_UNAVAILABLE"));
    }

    // ==============================
    // 500: Error genérico no controlado
    // ==============================
    // CODIGO COMENTADO PARA REVISION (Duplicado):
    // @ExceptionHandler(Exception.class)
    // public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {
    //     return ResponseEntity
    //             .status(HttpStatus.INTERNAL_SERVER_ERROR)
    //             .body(new ErrorResponse(
    //                     "Error interno del servidor",
    //                     "INTERNAL_ERROR"
    //             ));
    // }
}
