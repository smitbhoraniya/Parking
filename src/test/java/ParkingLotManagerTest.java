import org.example.*;
import org.example.exceptions.CarNotFoundException;
import org.example.exceptions.SlotNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ParkingLotManagerTest {
    @Test
    public void shouldParkCarUsingParkingManager() throws Exception {
        ParkingLotManager manager = mock(ParkingLotManager.class);
        Car car = new Car("Abc", CarColor.BLUE);

        when(manager.park(car)).thenReturn("1");
        assertEquals("1", manager.park(car));
    }

    @Test
    public void shouldNotParkCarInUnAvailableSlot() throws Exception {
        ParkingLotManager manager = new ParkingLotManager(1);
        Car car = new Car("Abc", CarColor.BLUE);

        manager.park(car);

        assertThrows(SlotNotFoundException.class, () -> manager.park(car));
    }

    @Test
    public void shouldUnParkTheCar() throws Exception {
        ParkingLotManager manager = new ParkingLotManager(3);
        Car car = new Car("Abc", CarColor.BLUE);
        String id = manager.park(car);

        Car expectedCar = manager.unPark(id);

        assertEquals(expectedCar, car);
    }

    @Test
    public void shouldNotUnParkTheCarWithInvalidId() throws Exception {
        ParkingLotManager manager = new ParkingLotManager(1);
        Car car = new Car("Abc", CarColor.BLUE);
        manager.park(car);

        assertThrows(CarNotFoundException.class, () -> manager.unPark("1"));
    }
}
