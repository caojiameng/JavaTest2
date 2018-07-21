package com.hand;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exam1 {

    public static void main(String[] args) {
        try{
            downLoad("http://192.168.11.205:18080/trainning/SampleChapter1.pdf",
                    "SampleChapter1.pdf","../Exam1/tmp");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void  downLoad(String urlStr,String fileName,String savePath)  {
        try {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        InputStream inputStream = conn.getInputStream();
        BufferedInputStream bis =new BufferedInputStream(inputStream);
        byte[] getData = readInputStream(bis);
        File saveDir = new File(savePath);
        if(!saveDir.exists()){
            saveDir.mkdir();
        }
        File file = new File(saveDir+File.separator+fileName);
        FileOutputStream fos = new FileOutputStream(file);
        BufferedOutputStream bos=new BufferedOutputStream(fos);
        bos.write(getData);
            if(fos!=null){
                fos.close();
            }
            if(inputStream!=null){
                inputStream.close();
            }
            System.out.println("info:"+url+" download success");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    public static  byte[] readInputStream(BufferedInputStream bis) throws IOException {
        byte[] input = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = bis.read(input)) != -1) {
            bos.write(input, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }


}

