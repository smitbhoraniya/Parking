package org.example;

import java.util.Objects;

public class Car {
    private final String registrationNumber;
    private final CarColor color;

    public Car(String registrationNumber, CarColor color) {
        this.registrationNumber = registrationNumber;
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Car c)) {
            return false;
        }
        return Objects.equals(c.registrationNumber, this.registrationNumber) && c.color == this.color;
    }
}
