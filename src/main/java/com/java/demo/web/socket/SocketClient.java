package com.java.demo.web.socket;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by xinyuan on 16/9/7.
 */
public class SocketClient {

    public static final String IP_ADDR = "localhost";

    public static final int PORT = 12345;

    public static void main(String[] args) {
        while (true) {
            Socket socket = null;
            try {
                socket = new Socket(IP_ADDR, PORT);

                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                String str = new BufferedReader(new InputStreamReader(System.in)).readLine();
                out.writeUTF(str);

                String ret = input.readUTF();
                if ("OK".equals(ret)) {
                    Thread.sleep(500);
                    break;
                }

                out.close();
                input.close();
            } catch (Exception e) {
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        socket = null;
                    }
                }
            }
        }
    }

}
