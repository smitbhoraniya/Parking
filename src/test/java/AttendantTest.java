import org.example.*;
import org.example.exceptions.CarNotFoundException;
import org.example.exceptions.SlotIsOccupiedException;
import org.example.exceptions.SlotNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AttendantTest {
    @Test
    public void shouldParkCarUsingParkingManager() throws Exception {
        Attendant attendant = new Attendant();
        ParkingLot parkingLot = mock(ParkingLot.class);
        ParkingLot parkingLot2 = mock(ParkingLot.class);
        attendant.assign(parkingLot);
        attendant.assign(parkingLot2);
        Car car = new Car("Abc", CarColor.BLUE);

        when(parkingLot.park(any(Car.class), any(Strategy.class))).thenReturn("1");
        attendant.park(car);

        verify(parkingLot, times(1)).park(car, Strategy.NEAREST);
        verify(parkingLot2, never()).park(car);
    }

    @Test
    public void shouldNotParkCarInUnAvailableSlot() throws Exception {
        Attendant attendant = new Attendant();
        ParkingLot parkingLot = new ParkingLot(1);
        attendant.assign(parkingLot);
        Car car = new Car("Abc", CarColor.BLUE);

        attendant.park(car);

        assertThrows(SlotNotFoundException.class, () -> attendant.park(car));
    }

    @Test
    public void shouldUnParkTheCar() throws Exception {
        Attendant attendant = new Attendant();
        ParkingLot parkingLot = new ParkingLot(1);
        attendant.assign(parkingLot);
        Car car = new Car("Abc", CarColor.BLUE);
        String id = attendant.park(car);

        Car expectedCar = attendant.unPark(id);

        assertEquals(expectedCar, car);
    }

    @Test
    public void shouldNotUnParkTheCarWithInvalidId() throws Exception {
        Attendant attendant = new Attendant();
        ParkingLot parkingLot = new ParkingLot(1);
        attendant.assign(parkingLot);
        Car car = new Car("Abc", CarColor.BLUE);
        attendant.park(car);

        assertThrows(CarNotFoundException.class, () -> attendant.unPark("1"));
    }

    @Test
    public void shouldParkCarInFirstParkingLotWhenAllParkingLotIsAvailable() throws SlotNotFoundException, SlotIsOccupiedException {
        Attendant attendant = new Attendant();
        ParkingLot parkingLot = new ParkingLot(1);
        attendant.assign(parkingLot);
        ParkingLot parkingLot1 = new ParkingLot(1);
        attendant.assign(parkingLot1);
        Car car = new Car("Abc123", CarColor.BLUE);

        attendant.park(car);

        assertTrue(parkingLot.isFull());
    }

    @Test
    public void multipleAttendantCanBeAssignToSingleParkingLot() throws SlotNotFoundException, SlotIsOccupiedException {
        ParkingLot parkingLot = mock(ParkingLot.class);
        Attendant attendant = new Attendant();
        Attendant attendant1 = new Attendant();
        Car car = new Car("Abc123", CarColor.BLUE);
        Car car1 = new Car("Efg123", CarColor.BLUE);

        when(parkingLot.park(any(Car.class), any(Strategy.class))).thenReturn("1");
        attendant.assign(parkingLot);
        attendant1.assign(parkingLot);
        attendant.park(car);
        attendant1.park(car1);

        verify(parkingLot, times(1)).park(car, Strategy.NEAREST);
        verify(parkingLot, times(1)).park(car1, Strategy.NEAREST);
    }

    @Test
    public void oneAttendantCanParkInMultipleParkingLot() throws SlotNotFoundException, SlotIsOccupiedException {
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        Attendant attendant = new Attendant();
        attendant.assign(parkingLot);
        attendant.assign(parkingLot2);
        Car car = new Car("Abc123", CarColor.BLUE);
        Car car1 = new Car("Efg123", CarColor.BLUE);

        attendant.park(car);
        attendant.park(car1);

        assertTrue(parkingLot.isFull());
        assertTrue(parkingLot2.isFull());
    }


    @Test
    public void attendantCanUnparkCarWhichParkedByOtherAttendant() throws SlotNotFoundException, SlotIsOccupiedException, CarNotFoundException {
        ParkingLot parkingLot = new ParkingLot(2);
        Attendant attendant = new Attendant();
        Attendant attendant1 = new Attendant();
        attendant.assign(parkingLot);
        attendant1.assign(parkingLot);
        Car car = new Car("Abc123", CarColor.BLUE);
        Car car2 = new Car("Efg123", CarColor.BLUE);

        String id = attendant.park(car);
        attendant1.park(car2);
        Car unParkedCar = attendant1.unPark(id);

        assertEquals(car, unParkedCar);
    }

    @Test
    public void makeParkingLotFullAndUnparkCarAndParkTheCar() throws SlotNotFoundException, SlotIsOccupiedException, CarNotFoundException {
        ParkingLot parkingLot = new ParkingLot(2);
        ParkingLot parkingLot1 = new ParkingLot(2);
        Attendant attendant = new Attendant();
        attendant.assign(parkingLot);
        attendant.assign(parkingLot1);
        Car car = new Car("Abc123", CarColor.BLUE);
        Car car1 = new Car("Abc123", CarColor.BLUE);
        Car car2 = new Car("Abc123", CarColor.BLUE);
        Car car3 = new Car("Abc123", CarColor.BLUE);

        String id = attendant.park(car);
        attendant.park(car1);
        attendant.park(car2);
        attendant.unPark(id);
        attendant.park(car3);

        assertTrue(parkingLot.isFull());
    }

    @Test
    public void parkCarUsingFarthestStrategy() throws SlotNotFoundException, SlotIsOccupiedException {
        ParkingLot parkingLot = new ParkingLot(2);
        Attendant attendant = new Attendant(Strategy.FARTHEST);
        attendant.assign(parkingLot);
        Car car = new Car("Abc", CarColor.WHITE);

        attendant.park(car);

        // TODO: How to check car is parked in last empty slot.
    }


}
