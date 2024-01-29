import org.example.Car;
import org.example.CarColor;
import org.example.ParkingSlot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingSlotTest {
    @Test
    public void validParkingSlot() {
        assertDoesNotThrow(() -> new ParkingSlot());
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

        Exception exception = assertThrows(Exception.class, () -> slot.park(car2));
        String actual = exception.getMessage();
        String expected = "Car is already parked in this slot.";
        assertEquals(expected, actual);
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
    public void shouldNotUnParkCarFromEmptySlot() throws Exception {
        ParkingSlot slot = new ParkingSlot();
        Car car = new Car("123Abc", CarColor.BLACK);

        Exception exception = assertThrows(Exception.class, () -> slot.unPark("abc"));

        String actual = exception.getMessage();
        String expected = "Slot is empty.";
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotUnParkCarWithInValidId() throws Exception {
        ParkingSlot slot = new ParkingSlot();
        Car car = new Car("123Abc", CarColor.BLACK);
        slot.park(car);

        assertThrows(Exception.class, () -> slot.unPark("abc"));
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

    @Test
    public void isValidCarParkedInSlot() throws Exception {
        ParkingSlot slot = new ParkingSlot();
        Car car1 = new Car("123Abc", CarColor.BLUE);
        Car car2 = new Car("123Efg", CarColor.BLUE);

        slot.park(car1);

        boolean actual = slot.isValidCarParked(car2);
        boolean expected = false;
        assertEquals(expected, actual);
    }
}
