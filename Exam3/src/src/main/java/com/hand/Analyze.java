package com.hand;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Analyze {
    public static void main(String[] args) {
        if (args.length != 0 && args[0] != null){
            System.out.println("[INFO] 股票编码：" + args[0]);
            try {
                System.out.println("[INFO] 开始获取数据。。。。。。");
                String rawData = getRawData(args[0]);
                System.out.println("[INFO] 获取数据成功！");
                Map<String, String> map = parseToMap(rawData);
                new Thread(new Xml(map)).start();
                new Thread(new Json(map)).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("[ERROR] 没有股票编码");
        }
    }

    public static String getRawData(String stockNo) throws IOException {
        String urlString = "http://hq.sinajs.cn/list=" + stockNo;
        HttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(urlString);
        HttpResponse response = client.execute(get);
        HttpEntity entity = response.getEntity();
        return EntityUtils.toString(entity);
    }


    public static Map<String, String> parseToMap(String rawData) {
        String values = rawData.split("=")[1].replace("\"", "");
        String[] dataFields = values.split(",");
        String[] keys = {"name", "open", "close", "current", "high", "low"};
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < keys.length; i++) {
            map.put(keys[i], dataFields[i]);
        }
        return map;
    }
}
