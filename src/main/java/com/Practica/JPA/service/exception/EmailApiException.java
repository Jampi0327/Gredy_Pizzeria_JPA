package com.Practica.JPA.service.exception;

public class EmailApiException  extends RuntimeException{

    public EmailApiException(){
        super("Error: enviando Email.......");
    }
}
