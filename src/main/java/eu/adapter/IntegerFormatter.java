
package eu.adapter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class IntegerFormatter {

    /**
     * 
     */
    private static final String PATTERN = "############";

    /**
     * @param value
     * @return
     */
    public static String printInt(BigDecimal value) {

        if (value == null) {
            return "";
        }
        String result = customFormat(PATTERN, value);
        return result;
    }

    /**
     * @param value
     * @return
     */
    public static BigDecimal parseInt(String value) {

        return BigDecimal.valueOf(Double.valueOf(value));
    }

    /**
     * @param pattern
     * @param value
     */
    static public String customFormat(String pattern, BigDecimal value) {

        // DecimalFormat myFormatter = new DecimalFormat(pattern);
        // String output = myFormatter.format(value);
        // return output;

        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator('.');
        decimalFormatSymbols.setGroupingSeparator(',');
        DecimalFormat decimalFormat = new DecimalFormat("##########0", decimalFormatSymbols);
        String format = decimalFormat.format(value);
        return format;

    }
}
