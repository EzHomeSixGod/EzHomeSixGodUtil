package com.ezhomesixgod.redis;

import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author renyang
 * @description
 * @createTime 2018-12-14 15:50
 */
public class RedisClient {

    private Socket socket;

    private InputStream inputStream;

    private OutputStream outputStream;

    public RedisClient(String host,int port) throws IOException {
        socket =new Socket(host,port);
        inputStream =socket.getInputStream();
        outputStream=socket.getOutputStream();

    }

    public String set(final String key,String value)  {
        StringBuilder sb =new StringBuilder("*3")
                .append("\r\n")
                .append("$3").append("\r\n")
                .append("SET").append("\r\n")
                .append("$"+key.length()).append("\r\n")
                .append(key).append("\r\n")
                .append("$"+value.length()).append("\r\n")
                .append(value).append("\r\n");
        String result = sb.toString();
        System.out.println(result);
        byte[] bytes =new byte[1024];
        try {
            outputStream.write(result.getBytes());
            inputStream.read(bytes);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(outputStream !=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(inputStream !=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(socket !=null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new String(bytes);
    }

    public static void main(String[] args) {
//        Jedis jedis = new Jedis("127.0.0.1",6379);
//
//        jedis.set("test","test");

//        System.out.println(jedis.get("test"));

        try {
            RedisClient client =new RedisClient("47.96.94.111",6379);
            String result = client.set("test","test");
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
