package org.example;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;


import java.util.ArrayList;



/** Hamcrest 라이브러리를 사용해서 작성한 테스트 */
@Ignore
public class HamcrestTest {
    private ArrayList<String> values;

    @Before
    public void setUpList(){
        values = new ArrayList<>();
        values.add("x");
        values.add("y");
        values.add("z");
    }


    /** Hamcrest의 장점은 실패했을 때 사람이 읽을 수 있는 형태의 설명을 제공 */
    @Test
    public void testWithHamcrest(){
        assertThat(values,hasItem(anyOf(equalTo("one"),equalTo("two"),equalTo("three"))));
    }
}
