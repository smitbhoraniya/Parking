package org.example.exceptions;

public class SlotNotFoundException extends Exception {
    public SlotNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
