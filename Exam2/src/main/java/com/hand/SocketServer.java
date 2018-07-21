package com.hand;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.*;
import java.math.RoundingMode;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DecimalFormat;

public class SocketServer  {
    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(1024);
            Socket socket = serverSocket.accept();
            BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
            FileInputStream fis= new FileInputStream("Exam1/tmp/SampleChapter1.pdf");
            BufferedInputStream bis = new BufferedInputStream(fis);
            System.out.println("客户端连接建立");
            byte[] buf = new byte[1024];
            int len;
            while ((len = bis.read(buf)) != -1) {
                bos.write(buf, 0, len);
            }
            bos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("已发送，关闭");
    }
}