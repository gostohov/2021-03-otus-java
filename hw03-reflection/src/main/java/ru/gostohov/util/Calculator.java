package ru.gostohov.util;

public class Calculator {
    private int multiplicationFactor;

    public int sum(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public void setMultiplicationFactor(int multiplicationFactor) {
        this.multiplicationFactor = multiplicationFactor;
    }

    public int getMultiplicationFactor() {
        return multiplicationFactor;
    }
}
