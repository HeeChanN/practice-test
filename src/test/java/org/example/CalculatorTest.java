package org.example;

import org.junit.Test;
import static org.junit.Assert.*;


public class CalculatorTest {
    @Test
    public void testAdd(){
        Calculator calculator = new Calculator();
        double result = calculator.add(10,50);
        assertEquals(59,result,0);
    }
}
