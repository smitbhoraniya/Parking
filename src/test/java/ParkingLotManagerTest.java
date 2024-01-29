import org.example.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotManagerTest {
    @Test
    public void shouldParkCarUsingParkingManager2() throws Exception {
        ParkingLotManager manager = new ParkingLotManager(3);
        Car car = new Car("123Abc", CarColor.BLUE);

        manager.park(car);
    }
}
