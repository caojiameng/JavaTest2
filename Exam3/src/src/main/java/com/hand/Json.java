package com.hand;

import com.alibaba.fastjson.JSON;

import java.io.*;
import java.util.Map;

public class Json implements Runnable {

    private Map<String, String> map;

    public Json(Map<String, String> map) {
        this.map = map;
    }

    @Override
    public void run() {
        try {
            writeToJson();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeToJson() throws IOException {
        File file = new File("../Exam3/tmp/股票编码.json");
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                if (!file.getParentFile().mkdirs()) {
                    throw new IOException("can not create file parent");
                }
            }
            if (!file.createNewFile()) {
                throw new IOException("can not create file");
            }
        }


        String jsonString = JSON.toJSONString(map);

        BufferedWriter writer = null;
        try {
            FileOutputStream fos =new FileOutputStream(file);
            OutputStreamWriter osw =new OutputStreamWriter(fos);
            writer = new BufferedWriter(osw);
            writer.write(jsonString);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            if (writer != null){
                try {
                    writer.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        System.out.println("[INFO] 解析为json成功！");
    }
}