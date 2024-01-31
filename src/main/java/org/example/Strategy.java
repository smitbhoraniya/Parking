package org.example;

import java.util.List;
import java.util.function.Function;

public enum Strategy {
    NEAREST(slots -> slots, slots -> slots.stream().filter(ParkingSlot::isFree).findFirst().orElse(null)),
    FARTHEST(List::reversed, slots -> slots.stream().filter(ParkingSlot::isFree).reduce((first, second) -> second).orElse(null));

    private final Function<List<ParkingLot>, List<ParkingLot>> getParkingLot;
    private final Function<List<ParkingSlot>, ParkingSlot> getSlot;
    Strategy(Function<List<ParkingLot>, List<ParkingLot>> getParkingLot, Function<List<ParkingSlot>, ParkingSlot> getSlot) {
        this.getParkingLot = getParkingLot;
        this.getSlot = getSlot;
    }

    public ParkingSlot getAvailableSlot(List<ParkingSlot> slots) {
        return this.getSlot.apply(slots);
    }

    public List<ParkingLot> getParkinglot(List<ParkingLot> parkingLots) {
        return this.getParkingLot.apply(parkingLots);
    }
}
