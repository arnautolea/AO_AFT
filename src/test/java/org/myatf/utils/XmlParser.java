package org.myatf.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;

public class XmlParser {

    public static double parseEuroValueFromXML(String xmlContent) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(xmlContent)));

        // Navigate the XML structure to get the EUR value
        Element valCurs = (Element) document.getElementsByTagName("ValCurs").item(0);
        Element valute = (Element) valCurs.getElementsByTagName("Valute").item(0);
        String euroValueStr = valute.getElementsByTagName("Value").item(0).getTextContent();

        // Parse the EUR value to a double
        return Double.parseDouble(euroValueStr);
    }
}