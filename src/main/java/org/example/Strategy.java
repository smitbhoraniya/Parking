package org.example;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public enum Strategy {
    NEAREST(parkingLots -> parkingLots, slots -> slots.stream().filter(ParkingSlot::isFree).findFirst().orElse(null)),
    FARTHEST(List::reversed, slots -> slots.stream().filter(ParkingSlot::isFree).reduce((first, second) -> second).orElse(null)),
    DISTRIBUTED(parkingLots -> parkingLots.stream().sorted(Comparator.comparingInt(ParkingLot::emptySlotCount).reversed()).toList(),
            slots -> slots.stream().filter(ParkingSlot::isFree).findFirst().orElse(null));

    private final Function<List<ParkingLot>, List<ParkingLot>> getParkingLots;
    private final Function<List<ParkingSlot>, ParkingSlot> getSlot;
    Strategy(Function<List<ParkingLot>, List<ParkingLot>> getParkingLots, Function<List<ParkingSlot>, ParkingSlot> getSlot) {
        this.getParkingLots = getParkingLots;
        this.getSlot = getSlot;
    }

    public ParkingSlot getAvailableSlot(List<ParkingSlot> slots) {
        return this.getSlot.apply(slots);
    }

    public List<ParkingLot> getParkinglot(List<ParkingLot> parkingLots) {
        return this.getParkingLots.apply(parkingLots);
    }
}
