import org.example.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParkingLotManagerTest {
    @Test
    public void shouldParkCarInFirstEmptySlot() throws Exception {
        ParkingLotManager manager = new ParkingLotManager(3);
        Car car = new Car("123Abc", CarColor.BLUE);
        ParkingSlot slot = manager.getAvailableParkingSlot();

        slot.park(car);

        boolean expected = slot.isSlotFree();
        boolean actual = false;
        assertEquals(expected, actual);
    }
}
