import org.example.Car;
import org.example.CarColor;
import org.example.ParkingLot;
import org.example.ParkingSlot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    @Test
    public void validParkingLot() {
        assertDoesNotThrow(() -> new ParkingLot(0));
    }

    @Test
    public void getAvailableParkingSlot() throws Exception {
        ParkingLot parkingLot = new ParkingLot(5);
        Car car = new Car("123Abc", CarColor.BLUE);

        ParkingSlot slot = parkingLot.getAvailableSlot();

        boolean actual = slot.isSlotFree();
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void shouldParkCarInAvailableParkingSlot() throws Exception {
        ParkingLot parkingLot = new ParkingLot(5);
        Car car = new Car("123Abc", CarColor.BLUE);
        ParkingSlot slot = parkingLot.getAvailableSlot();

        slot.park(car);

        boolean actual = slot.isSlotFree();
        boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotParkCarWhenParkingLotIsFull() throws Exception {
        ParkingLot parkingLot = new ParkingLot(2);
        Car car1 = new Car("123Abc", CarColor.WHITE);
        Car car2 = new Car("123Efg", CarColor.BLACK);
        ParkingSlot slot1 = parkingLot.getAvailableSlot();
        slot1.park(car1);
        ParkingSlot slot2 = parkingLot.getAvailableSlot();
        slot2.park(car2);

        Exception exception = assertThrows(Exception.class, parkingLot::getAvailableSlot);

        String actual = exception.getMessage();
        String expected = "No slots are Available.";
        assertEquals(expected, actual);
    }
}
