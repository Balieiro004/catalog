package com.balieiro.Catalog.service.exceptions;

public class ControllerNotFoundException extends RuntimeException {

    public ControllerNotFoundException(String message) {
        super(message);
    }
}
