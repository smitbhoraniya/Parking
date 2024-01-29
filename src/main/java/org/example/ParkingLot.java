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
            slots.add(new ParkingSlot("A" + i));
        }
    }

    public ParkingSlot getAvailableSlot() {
        Optional<ParkingSlot> slot = slots.stream().filter(ParkingSlot::isSlotFree).findFirst();
        return slot.orElse(null);
    }
}
