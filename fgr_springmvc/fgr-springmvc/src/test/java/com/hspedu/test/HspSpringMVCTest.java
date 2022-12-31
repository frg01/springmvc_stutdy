package com.hspedu.test;

import com.hspedu.fgrspringmvc.xml.XMLParser;
import org.junit.Test;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
public class HspSpringMVCTest {

    @Test
    public void readXML(){
        String basePackage = XMLParser.getBasePackage("fgrspringmvc.xml");
        System.out.println("basePackage=" + basePackage);
    }
}
