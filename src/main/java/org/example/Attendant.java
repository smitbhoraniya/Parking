package org.example;

import org.example.exceptions.CarNotFoundException;
import org.example.exceptions.SlotIsOccupiedException;
import org.example.exceptions.SlotNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class Attendant {
    private final List<ParkingLot> parkingLots;
    private final Strategy strategy;

    public Attendant(Strategy... strategy) {
        this.parkingLots = new ArrayList<>();
        this.strategy = strategy.length > 0 ? strategy[0]: Strategy.NEAREST;
    }

    public void assign(ParkingLot parkingLot) {
        parkingLots.add(parkingLot);
    }

    public String park(Car car) throws SlotNotFoundException, SlotIsOccupiedException {
        for (ParkingLot parkingLot: parkingLots) {
            String id = parkingLot.park(car, strategy);
            if (id != null) {
                return id;
            }
        }

        throw new SlotNotFoundException("All slots are occupied.");
    }

    public Car unPark(String id) throws CarNotFoundException {
        for (ParkingLot parkingLot: parkingLots) {
            Car car = parkingLot.unPark(id);
            if (car != null) {
                return car;
            }
        }

        throw new CarNotFoundException("No car parked with this id.");
    }
}
