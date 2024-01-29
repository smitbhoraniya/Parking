import org.example.Car;
import org.example.CarColor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CarTest {
    @Test
    public void validCar() {
        assertDoesNotThrow(() -> new Car("123Abc", CarColor.BLACK));
    }

    @Test
    public void inValidCar() {
        assertThrows(Exception.class, () -> new Car("123Abc", CarColor.valueOf("Yellow")));
    }
}
