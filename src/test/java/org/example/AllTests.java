package org.example;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@Ignore
@RunWith(value = Suite.class)
@Suite.SuiteClasses(value = {
        CalculatorTest.class,
        ParameterizedTest.class})
public class AllTests {
}


/**
 *  Suite는 복수의 테스트를 묶을 수 있는 집합
 *  테스트 클래스 내의 모든 @Test를 찾아 실행하는 러너이기도 하다.
 *  이렇게 현재 만들어 놓은 2개의 테스트를 묶어서 한번에 테스트를 진행할 수 있다.
 *
 * */