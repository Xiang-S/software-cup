package com.ccit.springboot.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

@Slf4j
public class HttpClient {

    private static String request(HttpUriRequest request) {
        String result = null;
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build();
             CloseableHttpResponse response = httpClient.execute(request)
        ){
            log.info("响应状态：" + response.getStatusLine());
            HttpEntity httpEntity = response.getEntity();
            if(httpEntity != null){
                result = EntityUtils.toString(httpEntity, Charset.forName("utf-8"));
                log.info("响应内容：" + result);
            }
        }catch (Exception e){
            log.error("发送请求错误",e);
        }
        return result;
    }
    public static String uploadFile(String url, String paramName, InputStream inputStream, String fileName){
        try {
            HttpPost httpPost = new HttpPost(url);
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
            multipartEntityBuilder.addBinaryBody(paramName,inputStream, ContentType.DEFAULT_BINARY,fileName);
            HttpEntity httpEntity = multipartEntityBuilder.build();
            httpPost.setEntity(httpEntity);
            return request(httpPost);
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                log.error("关闭InputStream输入流异常",e);
            }
        }
    }
}
