package eu.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * @author ConsDonzelliPaolo
 * 
 */
public class AdapterXMLGregorianCalendar extends XmlAdapter<String, XMLGregorianCalendar> {

    /**
     * 
     */
    private static final String FORMAT = "dd/MM/yyyy hh:mm:ss";

    /*
     * (non-Javadoc)
     * 
     * @see javax.xml.bind.annotation.adapters.XmlAdapter#unmarshal(java.lang.Object)
     */
    @Override
    public XMLGregorianCalendar unmarshal(String value) {

        return (eu.adapter.DateFormatter.parseDate(FORMAT, value));
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.xml.bind.annotation.adapters.XmlAdapter#marshal(java.lang.Object)
     */
    @Override
    public String marshal(XMLGregorianCalendar value) {

        return (eu.adapter.DateFormatter.printDate(value));
    }

}
