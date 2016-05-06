/**
 * 
 */
package test.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author Donzelli Paolo
 * 
 */
public class TestXmlParser {

    /**
     * @param args
     */
    public static void main(String[] args) {

        try {
            TestXmlParser testXmlParser = new TestXmlParser();
            testXmlParser.parse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws IOException
     */
    public void parse() throws Exception {

        String path = "D:/workspace/documentazione/avcp/prove/out/";
        String xmlFileName = path + "dataset2015.3.xml";

        File inputFile = new File(xmlFileName);
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        UserHandler userhandler = new UserHandler();
        saxParser.parse(inputFile, userhandler);
    }

    class UserHandler extends DefaultHandler {

        /**
         * 
         */
        public UserHandler() {

            super();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

            System.out.println("Start Element :" + qName);

            if (qName.equalsIgnoreCase("indici")) {
                System.out.println("indici");
            }

            if (qName.equalsIgnoreCase("pubblicazione")) {
                System.out.println("pubblicazione");
            }

        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {

            System.out.println("End Element :" + qName);
        }

        @Override
        public void characters(char ch[], int start, int length) throws SAXException {

            System.out.println("Element characters: " + new String(ch, start, length));
        }
    }

}
