package org.example;

import org.example.exceptions.CarNotFoundException;
import org.example.exceptions.SlotIsOccupiedException;
import org.example.exceptions.SlotNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Attendant implements Observer {
    private final List<ParkingLot> parkingLots;
    private final Strategy strategy;

    public Attendant(Strategy... strategy) {
        this.parkingLots = new ArrayList<>();
        this.strategy = strategy.length > 0 ? strategy[0]: Strategy.NEAREST;
    }

    public void assign(ParkingLot parkingLot) {
        parkingLots.add(parkingLot);
    }

    public String park(Car car) throws SlotNotFoundException {
        List<ParkingLot> parkingLotsToIterate = strategy.getParkinglot(parkingLots);

        for (ParkingLot parkingLot: parkingLotsToIterate) {
            try {
                String id = parkingLot.park(car, strategy);
                if (id != null) {
                    return id;
                }
            }
            catch (SlotNotFoundException | SlotIsOccupiedException ex) {
                System.out.println(ex.getMessage() + " Trying other parking lot.");
            }
        }

        throw new SlotNotFoundException("All slots are occupied.");
    }

    public Car unPark(String id) throws CarNotFoundException {
        for (ParkingLot parkingLot: parkingLots) {
            try {
                Car car = parkingLot.unPark(id);
                if (car != null) {
                    return car;
                }
            }
            catch (CarNotFoundException | SlotNotFoundException ex) {
                System.out.println(ex.getMessage() + " Trying other parking lot.");
            }
        }

        throw new CarNotFoundException("No car parked with this id.");
    }
}
