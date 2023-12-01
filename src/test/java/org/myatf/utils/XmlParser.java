package org.myatf.utils;

import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.xml.parsers.*;
import java.io.StringReader;

public class XmlParser {

    public static double getValueFromXML(String xmlContent, String name) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(xmlContent)));

        NodeList valuteList = document.getElementsByTagName("Valute");

        for (int i = 0; i < valuteList.getLength(); i++) {
            Element valute = (Element) valuteList.item(i);
            Element nameElement = (Element) valute.getElementsByTagName("Name").item(0);

            if (nameElement.getTextContent().equals(name)) {
                String value = valute.getElementsByTagName("Value").item(0).getTextContent();
                return Double.parseDouble(value);
            }
        }

        throw new Exception("Valute with Name " + name + " not found in XML");
    }
}


