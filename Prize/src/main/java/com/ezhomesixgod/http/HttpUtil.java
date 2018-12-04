package com.ezhomesixgod.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author renyang
 * @description
 * @createTime 2018-12-04 10:39
 */
public class HttpUtil {

    public String httpPost(String url,String entity) throws IOException {
        HttpClient httpClient =new DefaultHttpClient();
        HttpPost httpPost =new HttpPost(url);
        httpPost.setEntity(new StringEntity(entity));
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        HttpResponse response = httpClient.execute(httpPost);

        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            return EntityUtils.toString(response.getEntity());
        }
        return null;
    }


    public static String httpGet(String url) {
        String responseString = null;
        try {
            @SuppressWarnings("resource")
            DefaultHttpClient client = new DefaultHttpClient();
            // 发送get请求
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
            /** 请求发送成功，并得到响应 **/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /** 读取服务器返回过来的json字符串数据 **/
                return EntityUtils.toString(response.getEntity());
                /** 把json字符串转换成json对象 **/
            } else {
                // logger.error("get请求提交失败:" + url);
            }
        } catch (IOException e) {
            // logger.error("get请求提交失败:" + url, e);
        }
        return responseString;
    }

    public static void main(String[] args) {
        String result = httpGet("http://www.baidu.com");
        System.out.println(result);
    }

}
