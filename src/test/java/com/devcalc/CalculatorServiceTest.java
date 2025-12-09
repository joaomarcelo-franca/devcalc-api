package com.devcalc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorServiceTest {

    private final CalculatorService service = new CalculatorService();

    @Test
    public void testAdd() {
        assertEquals(15, service.add(10, 5));
    }

    @Test
    public void testSubtract() {
        assertEquals(5, service.subtract(10, 5));
    }

    @Test
    public void testMultiply() {
        assertEquals(50, service.multiply(10, 5));
    }

    @Test
    public void testDivide() {
        assertEquals(2, service.divide(10, 5));
    }
}