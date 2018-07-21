package com.hand;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SocketClient extends Socket {


    public static void main(String[] args) {
        File dir = new File("Exam2/tmp");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            Socket client = new Socket("127.0.0.1", 1024);
            BufferedInputStream bis = new BufferedInputStream(client.getInputStream());
            FileOutputStream fos =new FileOutputStream("Exam2/tmp/SampleChapter1.pdf");
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            byte[] input = new byte[1024];
            int len;
            while ((len = bis.read(input)) != -1) {
                bos.write(input, 0, len);
            }
            bos.flush();


        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("文件读取完成，关闭连接");
    }
}
