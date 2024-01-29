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
        Car car = this.car;
        this.car = null;
        return car;
    }

    public boolean isValidCarParked(Car car) {
        if (!this.isSlotAvailable) {
            return car == this.car;
        }
        return false;
    }
}
