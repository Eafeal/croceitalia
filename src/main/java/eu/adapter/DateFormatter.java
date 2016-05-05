/**
 * 
 */

package eu.adapter;

import it.asso.util.AssoException;
import it.asso.util.Util;

import java.text.ParseException;

import javax.xml.datatype.XMLGregorianCalendar;

/**
 * @author Paolo
 * 
 */
public class DateFormatter {

    /**
     * 
     */
    private static final String FORMAT = "yyyy-MM-dd";

    /**
     * @param value
     * @return
     */
    public static String printDate(javax.xml.datatype.XMLGregorianCalendar value) {

        if (value == null) {
            return null;
        }

        String answer = Util.fromXMLGregorianCalendar(FORMAT, value);
        return answer;

    }

    /**
     * @param value
     * @return
     * @throws ParseException
     * @throws ParseException
     */
    public static javax.xml.datatype.XMLGregorianCalendar parseDate(String value) {

        return parseDate(FORMAT, value);
    }

    /**
     * @param format
     * @param value
     * @return
     */
    public static XMLGregorianCalendar parseDate(String format, String value) {

        XMLGregorianCalendar xmlGregorianCalendar = null;
        try {
            xmlGregorianCalendar = Util.toXMLGregorianCalendar(format, value);
        } catch (ParseException e) {
            new AssoException(e);
            xmlGregorianCalendar = null;
        }
        return xmlGregorianCalendar;
    }
}
