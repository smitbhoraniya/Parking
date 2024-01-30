import org.example.*;
import org.example.exceptions.CarNotFoundException;
import org.example.exceptions.SlotNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class AttendantTest {
    @Test
    public void shouldParkCarUsingParkingManager() throws Exception {
        Attendant attendant = new Attendant();
        ParkingLot parkingLot = mock(ParkingLot.class);
        ParkingLot parkingLot2 = mock(ParkingLot.class);
        attendant.addParkingLot(parkingLot);
        attendant.addParkingLot(parkingLot2);
        Car car = new Car("Abc", CarColor.BLUE);

        when(parkingLot.park(car)).thenReturn("1");
        attendant.park(car);

        verify(parkingLot).park(car);
        verify(parkingLot2, never()).park(car);
    }

    @Test
    public void shouldNotParkCarInUnAvailableSlot() throws Exception {
        Attendant attendant = new Attendant();
        ParkingLot parkingLot = new ParkingLot(1);
        attendant.addParkingLot(parkingLot);
        Car car = new Car("Abc", CarColor.BLUE);

        attendant.park(car);

        assertThrows(SlotNotFoundException.class, () -> attendant.park(car));
    }

    @Test
    public void shouldUnParkTheCar() throws Exception {
        Attendant attendant = new Attendant();
        ParkingLot parkingLot = new ParkingLot(1);
        attendant.addParkingLot(parkingLot);
        Car car = new Car("Abc", CarColor.BLUE);
        String id = attendant.park(car);

        Car expectedCar = attendant.unPark(id);

        assertEquals(expectedCar, car);
    }

    @Test
    public void shouldNotUnParkTheCarWithInvalidId() throws Exception {
        Attendant attendant = new Attendant();
        ParkingLot parkingLot = new ParkingLot(1);
        attendant.addParkingLot(parkingLot);
        Car car = new Car("Abc", CarColor.BLUE);
        attendant.park(car);

        assertThrows(CarNotFoundException.class, () -> attendant.unPark("1"));
    }
}
