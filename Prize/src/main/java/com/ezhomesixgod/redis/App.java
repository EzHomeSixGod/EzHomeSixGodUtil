package com.ezhomesixgod.redis;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author renyang
 * @description
 * @createTime 2018-12-14 15:49
 */
public class App {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket =new ServerSocket(6379);

        Socket socket = serverSocket.accept();

        byte[] chars =new byte[1024];

        socket.getInputStream().read(chars);
        System.out.println(new String(chars));
    }
}
