import org.example.Car;
import org.example.CarColor;
import org.example.ParkingSlot;
import org.example.exceptions.CarNotFoundException;
import org.example.exceptions.SlotIsOccupiedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingSlotTest {
    @Test
    public void validParkingSlot() {
        assertDoesNotThrow(ParkingSlot::new);
    }

    @Test
    public void isSlotAvailable() {
        ParkingSlot slot = new ParkingSlot();

        boolean actual = slot.isFree();
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void shouldParkCar() throws Exception {
        Car car = new Car("123Abc", CarColor.BLUE);
        ParkingSlot slot = new ParkingSlot();

        slot.park(car);

        boolean actual = slot.isFree();
        boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotParkCarInUnAvailableSlot() throws Exception {
        Car car = new Car("123Abc", CarColor.BLACK);
        ParkingSlot slot = new ParkingSlot();
        slot.park(car);
        Car car2 = new Car("123Efg", CarColor.BLUE);

        assertThrows(SlotIsOccupiedException.class, () -> slot.park(car2));
    }

    @Test
    public void shouldUnParkTheCar() throws Exception {
        ParkingSlot slot = new ParkingSlot();
        Car car = new Car("123Abc", CarColor.BLACK);
        String id = slot.park(car);

        Car unParkedCar = slot.unPark(id);

        assertEquals(car, unParkedCar);
    }

    @Test
    public void shouldNotUnParkCarFromEmptySlot() {
        ParkingSlot slot = new ParkingSlot();

        assertThrows(CarNotFoundException.class, () -> slot.unPark("abc"));
    }

    @Test
    public void shouldNotUnParkCarWithInValidId() throws Exception {
        ParkingSlot slot = new ParkingSlot();
        Car car = new Car("123Abc", CarColor.BLACK);
        slot.park(car);

        assertThrows(UnsupportedOperationException.class, () -> slot.unPark("abc"));
    }

    @Test
    public void unParkTheInvalidCar() throws Exception {
        ParkingSlot slot = new ParkingSlot();
        Car car = new Car("123Abc", CarColor.BLACK);
        Car car2 = new Car("123Efg", CarColor.BLUE);
        String id = slot.park(car);

        Car unParkedCar = slot.unPark(id);

        assertNotEquals(car2, unParkedCar);
    }
}
