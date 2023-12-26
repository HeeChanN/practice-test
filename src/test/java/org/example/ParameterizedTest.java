package org.example;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)
public class ParameterizedTest {
    private double expected;
    private double valueOne;
    private double valueTwo;

    @Parameterized.Parameters
    public static Collection<Integer[]> getTestParameters(){
        return Arrays.asList(new Integer[][]{
            {2,1,1},
            {3,2,1},
            {4,3,1},
        });
    }

    public ParameterizedTest (double expected, double valueOne, double valueTwo){
        this.expected =expected;
        this.valueTwo = valueTwo;
        this.valueOne = valueOne;
    }

    @Test
    public void sum(){
        Calculator calc = new Calculator();
        assertEquals(expected,calc.add(valueOne,valueTwo),0);
    }
}

/**
 *  1. JUnit은 먼저 정정 메서드인 getTestParameters를 호출해 컬렉션 객체를 얻는다.
 *  2. 컬렉션에 저장된 배열의 수만큼 순환한다.
 *  3. JUnit은 유일한 public 생성자를 찾는다. 이 때 찾은 생성자가 2개라면 AssertionError를 호출한다.
 *  4. 위 예제에서는 {2,1,1}을 파라미터로 생성자를 호출할 것이다.
 *  5. @Test 메서드를 호출한다.
 *
 *
 *
 *
 * */
