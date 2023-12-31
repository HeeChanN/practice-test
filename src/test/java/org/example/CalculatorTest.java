package org.example;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import static org.junit.Assert.*;


@Ignore
public class CalculatorTest {


    /**
     *  테스트 메서드의 조건
     *  1. @Test
     *  2. 접근자는 public, return은 void
     *  3. 파라미터는 없음
     *
     * */
    @Test
    public void testAdd(){
        Calculator calculator = new Calculator();
        double result = calculator.add(10,50);
        assertEquals(59,result,0);
    }
}
