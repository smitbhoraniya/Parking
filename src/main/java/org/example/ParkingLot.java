package org.example;

import org.example.exceptions.CarNotFoundException;
import org.example.exceptions.SlotIsOccupiedException;
import org.example.exceptions.SlotNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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

    private ParkingSlot getAvailableSlot(Strategy... strategys) {
        Strategy strategy = strategys.length > 0 ? strategys[0]: Strategy.NEAREST;

        return strategy.getAvailableSlot(slots);
    }

    public String park(Car car, Strategy... strategy) throws SlotIsOccupiedException, SlotNotFoundException {
        ParkingSlot slot = this.getAvailableSlot(strategy);
        if (slot == null) {
            throw new SlotNotFoundException("Slots are full.");
        }

        String id = slot.park(car);
        if (this.isFull()) {
            NotificationBus.getInstance().publish(this, ParkingLotEvent.FULL);
        }
        return id;
    }

    private ParkingSlot getParkedCarSlot(String id) throws CarNotFoundException {
        Optional<ParkingSlot> slot = slots.stream().filter(p -> p.isValidId(id)).findFirst();
        if (slot.isEmpty()) {
            throw new CarNotFoundException("Car is not parked in slot.");
        }
        return slot.get();
    }

    public Car unPark(String id) throws CarNotFoundException, SlotNotFoundException {
        ParkingSlot slot = this.getParkedCarSlot(id);
        if (slot == null) {
            throw new SlotNotFoundException("Slot not found.");
        }

        Car car = slot.unPark(id);
        if (this.isEmpty()) {
            NotificationBus.getInstance().publish(this, ParkingLotEvent.EMPTY);
        }
        return car;
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
