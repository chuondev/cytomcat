package com.chuonye.tomcat.http;

import java.io.IOException;

import com.chuonye.tomcat.http.HttpProcessor;
import com.chuonye.tomcat.net.Handler;
import com.chuonye.tomcat.net.NioEndpoint;
import com.chuonye.tomcat.net.Processor;
// curl 172.31.1.41:10393 -X POST -d "user=abc&passwd=创"
// 
/**
 * 使用 curl 工具进行测试：<br>
 * curl 172.31.1.41:10393/index?a=2 -X POST -d "user=abc&passwd=创" -v <br>
 * curl 172.31.1.41:10393 -X POST -H "Transfer-Encoding:chunked" -d "user=abc&passwd=创" -v <br>
 * curl 172.31.1.41:10393 -F "" -v <br>
 * @author wskwbog
 */
public class TestHttpProcessor {
    public static void main(String[] args) {
        NioEndpoint endpoint = new NioEndpoint();
        endpoint.setHandler(new Handler() {
            @Override
            public Processor createProcessor() {
                HttpProcessor httpProcessor = new HttpProcessor();
                httpProcessor.setAdaptor(new AdapterImpl());
                return httpProcessor;
            }
        });
        try {
            endpoint.init();
            endpoint.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
