package org.example;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotManager {
    private final List<ParkingLot> parkingLots;

    public ParkingLotManager(int numberOfParkingLots) {
        this.parkingLots = new ArrayList<ParkingLot>(numberOfParkingLots);
        for (int i = 0; i < numberOfParkingLots; i++) {
            parkingLots.add(new ParkingLot(5));
        }
    }

    public String park(Car car) throws Exception {
        for (ParkingLot parkingLot: parkingLots) {
            String id = parkingLot.park(car);
            if (id != null) {
                return id;
            }
        }

        return null;
    }
}
