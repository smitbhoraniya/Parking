package org.example;

import org.example.exceptions.CarNotFoundException;
import org.example.exceptions.SlotIsOccupiedException;
import org.example.exceptions.SlotNotFoundException;

import java.util.ArrayList;
import java.util.Optional;

public class ParkingLot {
    private final ArrayList<ParkingSlot> slots;

    public ParkingLot(int numberOfSlots) {
        if (numberOfSlots <= 0) {
            throw new IllegalArgumentException("Number of slots should be greater than zero.");
        }
        this.slots = new ArrayList<>(numberOfSlots);
        for (int i = 0; i < numberOfSlots; i++) {
            slots.add(new ParkingSlot());
        }
    }

    private ParkingSlot getAvailableSlot() {
        Optional<ParkingSlot> slot = slots.stream().filter(ParkingSlot::isFree).findFirst();

        return slot.orElse(null);
    }

    public String park(Car car) throws SlotNotFoundException, SlotIsOccupiedException {
        ParkingSlot slot = this.getAvailableSlot();
        if (slot == null) {
            throw new SlotNotFoundException("Parking lot is full.");
        }
        return slot.park(car);
    }

    private ParkingSlot getParkedCarSlot(String id) {
        Optional<ParkingSlot> slot = slots.stream().filter(p -> p.isValidId(id)).findFirst();
        return slot.orElse(null);
    }

    public Car unPark(String id) throws CarNotFoundException, SlotNotFoundException {
        ParkingSlot slot = this.getParkedCarSlot(id);
        if (slot == null) {
            throw new CarNotFoundException("Car is not parked in slot.");
        }

        return slot.unPark(id);
    }

    public boolean isFull() {
        long slotCount = slots.stream().filter(p -> !p.isFree()).count();
        return slotCount == (long) slots.size();
    }
}
