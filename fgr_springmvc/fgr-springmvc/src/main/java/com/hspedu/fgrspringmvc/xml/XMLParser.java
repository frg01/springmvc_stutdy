package com.hspedu.fgrspringmvc.xml;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.InputStream;

/**
 * @author: guorui fu
 * @versiion: 1.0
 * XMLParser 用于解析spring配置文件
 */
public class XMLParser {

    public static String getBasePackage(String xmlFile){

        //解析的过程
        SAXReader saxReader = new SAXReader();
        ClassLoader classLoader = XMLParser.class.getClassLoader();
//        得到类的加载路径，获得spring配置文件[对应的资源流] 类加载器提供了方法传入全类名获得.class文件的inputStream
        InputStream inputStream
                = XMLParser.class.getClassLoader().getResourceAsStream(xmlFile);

        try {
            //SAXReader.reader()传入输入流返回xmL文件
            Document document = saxReader.read(inputStream);
            Element rootElement = document.getRootElement();
            Element componentScanElement = rootElement.element("component-scan");
            Attribute attribute = componentScanElement.attribute("base-package");
            String basePackage = attribute.getText();
            return basePackage;
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return null;
    }
}
