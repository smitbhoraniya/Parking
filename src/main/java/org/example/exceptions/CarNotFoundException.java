package org.example.exceptions;

public class CarNotFoundException extends Exception {
    public CarNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
