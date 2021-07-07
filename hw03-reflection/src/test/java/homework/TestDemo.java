package homework;

import org.junit.jupiter.api.Assertions;
import ru.gostohov.annotations.After;
import ru.gostohov.annotations.Before;
import ru.gostohov.annotations.Test;
import ru.gostohov.util.Calculator;

public class TestDemo {
    private static Calculator calculator = null;
    private int multiplicationFactor = 0;

    @Before
    public void init() {
        calculator = new Calculator();
        System.out.println("Created new Calculator");
        calculator.setMultiplicationFactor(++multiplicationFactor);
    }

    @Test
    public void addition() {
        Assertions.assertEquals(2, calculator.sum(1, 1));
        Assertions.assertEquals(3, calculator.sum(1, 1));
        Assertions.assertEquals(1, calculator.getMultiplicationFactor());
    }

    @Test
    public void subtraction() {
        Assertions.assertEquals(0, calculator.subtract(1, 1));
        Assertions.assertEquals(1, calculator.subtract(2, 1));
    }

    @After
    public void teardown() {
        System.out.println("Teardown");
    }

}
