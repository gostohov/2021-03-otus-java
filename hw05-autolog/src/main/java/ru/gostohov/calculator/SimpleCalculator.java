package ru.gostohov.calculator;

import ru.gostohov.annotations.Log;

public class SimpleCalculator implements Calculator{
    @Log
    @Override
    public void calculation(int number) {
        System.out.println("\tClass Calculator, Method: calculation(int number)");
    }

    @Override
    public void calculation(int numberOne, int numberTwo) {
        System.out.println("\tClass Calculator, Method: calculation(int numberOne, int numberTwo)");
    }

    @Log
    @Override
    public void calculation(int numberOne, int numberTwo, String message) {
        System.out.println("\tClass Calculator, Method: calculation(int numberOne, int numberTwo, String message)");
    }
}
