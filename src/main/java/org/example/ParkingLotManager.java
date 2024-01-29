package org.example;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotManager {
    private final List<ParkingLot> parkingLots;

    public ParkingLotManager(int numberOfParkingLots) {
        this.parkingLots = new ArrayList<>(numberOfParkingLots);
        for (int i = 0; i < numberOfParkingLots; i++) {
            parkingLots.add(new ParkingLot(1));
        }
    }

    public String park(Car car) throws Exception {
        for (ParkingLot parkingLot: parkingLots) {
            String id = parkingLot.park(car);
            if (id != null) {
                return id;
            }
        }

        throw new Exception("All slots are occupied.");
    }

    public Car unPark(String id) throws Exception {
        for (ParkingLot parkingLot: parkingLots) {
            Car car = parkingLot.unPark(id);
            if (car != null) {
                return car;
            }
        }

        throw new Exception("No car parked with this id.");
    }
}
