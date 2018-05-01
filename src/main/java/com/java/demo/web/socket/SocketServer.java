package com.java.demo.web.socket;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by xinyuan on 16/9/7.
 */
public class SocketServer {

    //�����Ķ˿ں�
    public static final int PORT = 12345;

    public static void main(String[] args) {
        System.out.println("����������...\n");
        SocketServer server = new SocketServer();
        server.init();
    }

    public void init() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            while (true) {
                // һ���ж���, ���ʾ��������ͻ��˻��������
                Socket client = serverSocket.accept();
                System.out.println("�յ��ͻ�������");
                // �����������
                new HandlerThread(client);
            }
        } catch (Exception e) {
            System.out.println("�������쳣: " + e.getMessage());
        }
    }

    private class HandlerThread implements Runnable {
        private Socket socket;

        public HandlerThread(Socket client) {
            socket = client;
            new Thread(this).start();
        }

        public void run() {
            try {
                DataInputStream input = new DataInputStream(socket.getInputStream());
                String clientInputStr = input.readUTF();

                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                String s = new BufferedReader(new InputStreamReader(System.in)).readLine();
                out.writeUTF(s);

                out.close();
                input.close();
            } catch (Exception e) {
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (Exception e) {
                        socket = null;
                    }
                }
            }
        }
    }
}
