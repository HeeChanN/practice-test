package org.example.ch6;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebClient {
    public String getContent(URL url){
        StringBuffer content = new StringBuffer();

        try{
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);

            InputStream is = connection.getInputStream();
            byte[] buffer = new byte[2048];
            int count;
            while(-1 != (count = is.read(buffer))){
                content.append(new String(buffer,0,count));
            }
        }
        catch (IOException e){
            return null;
        }
        return content.toString();
    }
}

/**
 * C 코드와 유사하다 다른 점은 HttpURLConnection을 통해 HTTP 연결을 맺는 점
 *
 * 1. HttpURLConnection 으로 HTTP 커넥션을 맺는다.
 * 2. 내용을 읽기 위해 getInputStream을 이용해 스트림을 만들고
 * 3. read를 통해 다 읽을 떄 까지 읽어온다. --> C와 유사하다. read(fd, &buf, size)
 * 4. 테스트 목적으로 null을 반환하게 만들었다.
 *
 * */
