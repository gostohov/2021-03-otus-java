package ru.gostohov;

import ru.gostohov.calculator.Calculator;
import ru.gostohov.calculator.SimpleCalculator;
import ru.gostohov.proxy.ProxyIoC;

public class Main {

    public static void main(String[] args) {
        final Class<Calculator> calculatorClass = Calculator.class;

        final Calculator simpleCalculator = ProxyIoC.createWithLogging(calculatorClass, new SimpleCalculator());
        simpleCalculator.calculation(7);
        simpleCalculator.calculation(92, 100);
        simpleCalculator.calculation(0, 10, "Тестовое сообщение (calculator)");
    }
}
