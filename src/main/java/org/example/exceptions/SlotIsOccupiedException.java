package org.example.exceptions;

public class SlotIsOccupiedException extends Exception {
    public SlotIsOccupiedException(String errorMessage) {
        super(errorMessage);
    }
}
