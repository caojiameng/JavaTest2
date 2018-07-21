package com.hand;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;
import java.util.Set;

public class Xml implements Runnable {

    private Map<String, String> map;

    public Xml(Map<String, String> map) {
        this.map = map;
    }


    public void run() {
        saveXml();
    }

    private void saveXml(){
        try {
            File file = new File("../Exam3/tmp/股票编码.xml");
            if (!file.exists()) {
                file.mkdirs();
            }
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
            Element root = document.createElement("xml");

            Element stock = document.createElement("stock");

            Set<Map.Entry<String, String>> entrySet = map.entrySet();

            for (Map.Entry<String, String> entry: entrySet){
                Element element = document.createElement(entry.getKey());
                element.setTextContent(entry.getValue());
                stock.appendChild(element);
            }

            root.appendChild(stock);

            document.appendChild(root);


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty("encoding", "UTF-8");

            transformer.transform(new DOMSource(document), new StreamResult(file));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        System.out.println("[INFO] 解析为xml成功！");
    }
}
