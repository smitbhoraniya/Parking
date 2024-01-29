package org.example;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotManager {
    private List<ParkingLot> parkingLots;

    public ParkingLotManager(int numberOfParkingLots) {
        this.parkingLots = new ArrayList<ParkingLot>(numberOfParkingLots);
        for (int i = 0; i < numberOfParkingLots; i++) {
            parkingLots.add(new ParkingLot(5));
        }
    }

    public ParkingSlot getAvailableParkingSlot() throws Exception {
        for (ParkingLot parkingLot: parkingLots) {
            ParkingSlot slot = parkingLot.getAvailableSlot();
            if (slot != null) {
                return slot;
            }
        }

        throw new Exception("No slots available.");
    }
}
