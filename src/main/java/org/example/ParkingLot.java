package org.example;

import java.util.ArrayList;
import java.util.Optional;

public class ParkingLot {
    private ArrayList<ParkingSlot> slots;

    public ParkingLot(int numberOfSlots) {
        if (numberOfSlots <= 0) {
            throw new IllegalArgumentException("Number of slots should be greater than zero.");
        }
        this.slots = new ArrayList<ParkingSlot>(numberOfSlots);
        for (int i = 0; i < numberOfSlots; i++) {
            slots.add(new ParkingSlot());
        }
    }

    private ParkingSlot getAvailableSlot() {
        Optional<ParkingSlot> slot = slots.stream().filter(ParkingSlot::isFree).findFirst();

        return slot.orElse(null);
    }

    public String park(Car car) throws Exception {
        ParkingSlot slot = this.getAvailableSlot();
        if (slot == null) {
            throw new Exception("Parking lot is full.");
        }
        return slot.park(car);
    }

    public Car unPark(String id) throws Exception {
        Optional<ParkingSlot> slot = slots.stream().filter(p -> p.isValidId(id)).findFirst();
        if (slot.isPresent()) {
            return slot.get().unPark(id);
        }

        return null;
    }

    public boolean isFull() {
        long slotCount = slots.stream().filter(p -> !p.isFree()).count();
        return slotCount == (long) slots.size();
    }

    public boolean isEmpty() {
        long slotCount = slots.stream().filter(ParkingSlot::isFree).count();
        return slotCount == (long) slots.size();
    }
}
