package org.myatf.utils;

import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.xml.parsers.*;
import java.io.StringReader;

public class XmlParser {
    // Other methods and fields in the XmlParser class

    public static double parseEuroValueFromXML(String xmlContent) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(xmlContent)));

        String targetId = "47";
        NodeList valuteList = document.getElementsByTagName("Valute");

        for (int i = 0; i < valuteList.getLength(); i++) {
            Element valute = (Element) valuteList.item(i);
            if (valute.getAttribute("ID").equals(targetId)) {
                String value = valute.getElementsByTagName("Value").item(0).getTextContent();
                return Double.parseDouble(value);
            }
        }

        throw new Exception("Valute with ID " + targetId + " not found in XML");
    }
}


