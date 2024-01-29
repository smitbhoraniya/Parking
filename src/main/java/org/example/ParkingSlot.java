package org.example;

import java.util.Objects;
import java.util.UUID;

public class ParkingSlot {
    private Car car;
    private String id;

    public boolean isFree() {
        return this.car == null;
    }

    public String park(Car car) throws Exception {
        if (!this.isFree()) {
            throw new Exception("Car is already parked in this slot.");
        }
        this.car = car;

        this.id = UUID.randomUUID().toString();
        return this.id;
    }

    public Car unPark(String id) throws Exception {
        if (this.isFree()) {
            throw new Exception("Slot is empty.");
        }
        if (!this.isValidId(id)) {
            throw new Exception("Don't have an authorization to unpark.");
        }
        Car car = this.car;
        this.car = null;
        this.id = null;
        return car;
    }

    public boolean isValidCarParked(Car car) {
        if (!this.isFree()) {
            return car == this.car;
        }
        return false;
    }

    public boolean isValidId(String id) {
        return Objects.equals(this.id, id);
    }
}
