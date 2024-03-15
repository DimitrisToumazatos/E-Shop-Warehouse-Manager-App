package com.team12.warehousemanager;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the functions of the Money class.
 */
public class TestMoney {
    Money money1;
    Money money2;

    /**
     * Sets up the needed objects before each the test.
     */
    @Before
    public void setUp() {
        money1 = new Money(10.0f);
        money2 = new Money(5.0f);
    }

    /**
     * Tests the subtraction method.
     */
    @Test
    public void subtractionTest() {
        money1.subtraction(money2);
        assertEquals(5.0f, money1.getAmount(), 0.0);
    }

    /**
     * Tests the addition method.
     */
    @Test
    public void additionTest() {
        money1.addition(money2);
        assertEquals(15.0f, money1.getAmount(), 0.0);
    }

    /**
     * Tests the multiplication method.
     */
    @Test
    public void multiplicationTest() {
        money1.multiplication(money2);
        assertEquals(50.0f, money1.getAmount(), 0.0);
    }

    /**
     * Tests the division method.
     */
    @Test
    public void divisionTest() {
        money1.division(money2);
        assertEquals(2.0f, money1.getAmount(), 0.0);
    }

    /**
     * Tests the setAmount method.
     */
    @Test
    public void setAmount() {
        money1.setAmount(2.0f);
        assertEquals(2.0f, money1.getAmount(), 0.0);
    }


    /**
     * Test the toString method.
     */
    @Test
    public void toStringTest() {
        assertEquals("€ 10.00", money1.toString());
    }
}
