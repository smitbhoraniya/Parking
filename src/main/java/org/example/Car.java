package org.example;

public class Car {
    private String registrationNumber;
    private CarColor color;

    public Car(String registrationNumber, CarColor color) {
        this.registrationNumber = registrationNumber;
        this.color = color;
    }

    public String getRegistrationNumber() {
        return this.registrationNumber;
    }
}
