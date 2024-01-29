package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingLot {
    private ArrayList<ParkingSlot> slots;

    public ParkingLot(int numberOfSlots) {
        this.slots = new ArrayList<ParkingSlot>(numberOfSlots);
        for (int i = 0; i < numberOfSlots; i++) {
            slots.add(new ParkingSlot("A" + i));
        }
    }

    public ParkingSlot getAvailableSlot() throws Exception {
        Optional<ParkingSlot> slot = slots.stream().filter(ParkingSlot::isSlotFree).findFirst();
        if (slot.isPresent()) {
            return slot.get();
        }

        throw new Exception("No slots are Available.");
    }
}
