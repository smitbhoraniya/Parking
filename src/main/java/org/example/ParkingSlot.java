package org.example;

import java.util.Objects;

public class ParkingSlot {
    private String slotNumber;
    private boolean isSlotAvailable = true;
    private Car car;

    public ParkingSlot(String slotNumber) {
        this.slotNumber = slotNumber;
    }

    public boolean isSlotFree() {
        return this.isSlotAvailable;
    }

    public void park(Car car) throws Exception {
        if (!this.isSlotAvailable) {
            throw new Exception("Car is already parked in this slot.");
        }
        this.isSlotAvailable = false;
        this.car = car;
    }

    public Car unPark() throws Exception {
        if (this.isSlotAvailable) {
            throw new Exception("Slot is empty.");
        }
        this.isSlotAvailable = true;
        return this.car;
    }

    public boolean isValidCarParked(Car car) {
        return Objects.equals(car.getRegistrationNumber(), this.car.getRegistrationNumber());
    }
}
