
package eu.spesesanitarie.v1.gen;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.datatype.XMLGregorianCalendar;

public class Adapter1
    extends XmlAdapter<String, XMLGregorianCalendar>
{


    public XMLGregorianCalendar unmarshal(String value) {
        return (eu.adapter.DateFormatter.parseDate(value));
    }

    public String marshal(XMLGregorianCalendar value) {
        return (eu.adapter.DateFormatter.printDate(value));
    }

}
