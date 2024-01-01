package org.example.ch6;


import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.ResourceHandler;
import org.mortbay.jetty.servlet.Context;

public class JettySample {
    public static void main(String [] args)throws Exception{
        Server server = new Server(8080);

        Context root = new Context(server,"/");
        root.setResourceBase("./pom.xml");
        root.setHandler(new ResourceHandler());

        server.start();
    }
}

/** setResourceBase 메서드는 리소스를 서비스할 문서 루트의 위치를 설정
 *
 *
 *
 * */
