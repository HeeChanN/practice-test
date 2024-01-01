package org.example.ch6;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mortbay.jetty.HttpHeaders;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.AbstractHandler;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.util.ByteArrayISO8859Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;


import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

/** @Before 과 @BeforeClass 차이
 *
 * @Before 는 매 테스트마다 실행
 * @BeforeClass 는 클래스의 테스트 실행 전에 한번만 실행 --> static을 붙여줘야함
 *
 *
 *
 * */

public class TestWebClient {

    @BeforeClass
    public static void setUp() throws  Exception{
        Server server = new Server(8080);

        TestWebClient t = new TestWebClient();


        Context root = new Context(server,"/testGetContentOk");
        root.setHandler(t.new TestGetContentOkHandler());

        Context notFound = new Context(server,"/testGetContentNotFound");
        notFound.setHandler(t.new TestGetContentNotFoundHandler());

        server.setStopAtShutdown(true);
        server.start();
    }



    @Test
    public void testGetContent() throws Exception{
        WebClient client = new WebClient();
        String result = client.getContent(new URL(
                "http://localhost:8080/testGetContentOk"
        ));
        assertEquals("It works",result);

    }

    @Test
    public void testGetContentNotFound() throws Exception{
        WebClient client = new WebClient();
        String result = client.getContent(new URL(
                "http://localhost:8080/testGetContentNotFound"
        ));
        assertNull(result);

    }


    @AfterClass
    public static void tearDown(){

    }
    public class TestGetContentOkHandler extends AbstractHandler {
        @Override
        public void handle(String s, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, int i) throws IOException, ServletException {
            OutputStream out = httpServletResponse.getOutputStream();
            ByteArrayISO8859Writer writer = new ByteArrayISO8859Writer();
            writer.write("It works");
            writer.flush();
            httpServletResponse.setIntHeader(HttpHeaders.CONTENT_LENGTH,writer.size());
            writer.writeTo(out);
            out.flush();
        }
    }

    public class TestGetContentNotFoundHandler extends AbstractHandler{
        @Override
        public void handle(String s, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, int i) throws IOException, ServletException {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
