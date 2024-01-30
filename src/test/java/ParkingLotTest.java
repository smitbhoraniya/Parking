import org.example.*;
import org.example.exceptions.CarNotFoundException;
import org.example.exceptions.SlotIsOccupiedException;
import org.example.exceptions.SlotNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ParkingLotTest {
    @Test
    public void validParkingLot() {
        assertDoesNotThrow(() -> new ParkingLot(1));
    }

    @Test
    public void inValidParkingLot() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new ParkingLot(0));
        String actual = exception.getMessage();
        String expected = "Number of slots should be greater than zero.";
        assertEquals(expected, actual);
    }

    @Test
    public void shouldParkCarInAvailableParkingSlot() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car("123Abc", CarColor.BLUE);

        parkingLot.park(car);

        assertTrue(parkingLot.isFull());
    }

    @Test
    public void shouldNotParkCarWhenParkingLotIsFull() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car1 = new Car("123Abc", CarColor.WHITE);
        Car car2 = new Car("123Efg", CarColor.BLACK);
        parkingLot.park(car1);

        assertThrows(SlotNotFoundException.class, () -> parkingLot.park(car2));
    }

    @Test
    public void shouldUnParkTheCar() throws Exception {
        ParkingLot parkingLot = new ParkingLot(2);
        Car car1 = new Car("123Abc", CarColor.WHITE);
        String id = parkingLot.park(car1);

        Car car = parkingLot.unPark(id);

        assertEquals(car1, car);
    }

    @Test
    public void shouldNotUnParkTheCarWithInvalidId() throws Exception {
        ParkingLot parkingLot = new ParkingLot(2);
        Car car1 = new Car("123Abc", CarColor.WHITE);
        String id = parkingLot.park(car1);

        assertThrows(CarNotFoundException.class, () -> parkingLot.unPark("123"));
    }

    @Test
    public void shouldAddMultipleAttendantToParkingLot() {
        ParkingLot parkingLot = new ParkingLot(2);
        Attendant attendant = mock(Attendant.class);
        Attendant attendant1 = mock(Attendant.class);
        Attendant attendant2 = mock(Attendant.class);

        parkingLot.addAttendant(attendant);
        parkingLot.addAttendant(attendant1);

        verify(attendant).addParkingLot(parkingLot);
        verify(attendant1).addParkingLot(parkingLot);
        verify(attendant2, never()).addParkingLot(parkingLot);
    }

    @Test
    public void shouldAddMultipleAttendantToParkingLotAndParkCarUsingAttendant() throws SlotNotFoundException, SlotIsOccupiedException {
        ParkingLot parkingLot = new ParkingLot(1);
        Attendant attendant = new Attendant();
        Attendant attendant1 = new Attendant();
        parkingLot.addAttendant(attendant);
        parkingLot.addAttendant(attendant1);
        Car car = new Car("Abc123", CarColor.BLUE);

        attendant.park(car);

        assertTrue(parkingLot.isFull());
    }
}
