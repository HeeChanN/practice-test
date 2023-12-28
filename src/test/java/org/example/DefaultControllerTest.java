package org.example;

import org.example.controller.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

public class DefaultControllerTest {

    /** Controller를 테스트 하기 위한 테스트 클래스 들 */
    private class SampleRequest implements Request{

        private static final String DEFAULT_NAME = "Test";
        private String name;

        public SampleRequest(String name){
            this.name = name;
        }
        public SampleRequest(){
            this(DEFAULT_NAME);
        }
        @Override
        public String getName() {
            return this.name;
        }
    }

    private class SampleHandler implements RequestHandler{
        @Override
        public Response process(Request request) throws Exception {
            return new SampleResponse();
        }
    }

    public class SampleExceptionHandler implements RequestHandler{
        @Override
        public Response process(Request request) throws Exception {
            throw new Exception("error processing request");
        }
    }
    /** 테스트 객체를 위한 클래스 */
    public class SampleResponse implements Response{
        private static final String NAME = "Test";

        public String getName(){
            return NAME;
        }

        public boolean equals(Object object){
            boolean result = false;
            if(object instanceof SampleResponse){
                result = ((SampleResponse) object).getName().equals(getName());
            }

            return result;
        }
        public int hashCode(){
            return NAME.hashCode();
        }
    }


    /**
     *  단위 테스트의 핵심은 한 번에 한 객체씩 테스트
     *  테스트 대상이 되는 모든 객체는 도메인 객체로 간주
     * */
    private DefaultController controller;
    private Request request;
    private RequestHandler handler;

    /** 각 테스트 메서드 사이에서 호출되는 JUnit의 기본 확장
     *  포인트이다.
     *  Before는 테스트 실행 직전 실행,
     *  After는 테스트 실행 직후 실행됨
     *
     * */
    @Before
    public void init()throws Exception{
        controller = new DefaultController();
        handler = new SampleHandler();
        request = new SampleRequest();
        controller.addHandler(request,handler);
    }

    /**
     *  1. addRequest 메서드로 RequestH와 함께 담당 RequestHandler 추가
     *  2. 동일한 Request로 RequestHandler 얻기
     *  3. 얻어낸 RequestHandler가 앞에 추가헀던 것과 같은지 확인하기
     * */
    @Test
    public void testAddHandler(){
//        Request request = new SampleRequest();
//        RequestHandler handler = new SampleHandler();
//        controller.addHandler(request,handler);
        RequestHandler handler2 = controller.getHandler(request);
        assertSame("Same",handler2,handler);
    }

    @Test
    public void testProcessRequest(){
//        Request request = new SampleRequest();
//        RequestHandler handler = new SampleHandler();
//        controller.addHandler(request,handler);
        Response response= controller.processRequest(request);
        assertNotNull("Must not return a null",response);
        assertEquals(new SampleResponse(), response);
    }

    @Test
    public void testProcessRequestErrorResponse(){
        SampleRequest eRequest = new SampleRequest("testError");
        SampleExceptionHandler eHandler = new SampleExceptionHandler();
        controller.addHandler(eRequest,eHandler);
        Response response = controller.processRequest(eRequest);
        assertNotNull("Not null", response);
        assertEquals(ErrorResponse.class, response.getClass());
    }

    @Test(expected = RuntimeException.class)
    public void testGetHandlerNotDefined(){
        SampleRequest r = new SampleRequest("testNotDefined");

        // RuntimeException 발생
        controller.getHandler(r);
    }

    @Test(expected = RuntimeException.class)
    public void testAddRequestDuplicateName(){
        SampleRequest r = new SampleRequest();
        SampleHandler h = new SampleHandler();

        // RuntimeException 발생
        controller.addHandler(r,h);

    }

    /** 130 밀리초 동안 10만번 가능한지 테스트
     *  나중에 timeout 기준을 설정하기 위해 ignore 해두기
     *  타임 아웃 테스트는 아래 코드와 같이 @Test(timeout = 밀리초단위)
     * */
    @Test(timeout = 130)
    @Ignore(value = "Ignore for now until we decide time limit")
    public void testProcessMultipleRequestsTimeout(){
        Request request1;
        Response response1 = new SampleResponse();
        RequestHandler handler1 = new SampleHandler();
        for(int i =0;i<=99999;i++){
            request1 = new SampleRequest(String.valueOf(i));
            controller.addHandler(request1,handler1);
            response1 = controller.processRequest(request1);
            assertNotNull(response1);
            assertNotSame(ErrorResponse.class, response1.getClass());
        }
    }

}

/**
 *  테스트 코드 작성 순서
 *
 *  1. 정해진 상태 (객체 생성, 자원획득)로 테스트 환경 초기화
 *  2. 테스트할 메서드 호출
 *  3. 결과 확인 ( assert 메서드 이용 )
 *
 * */