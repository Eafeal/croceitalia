
package eu.spesesanitarie.v1.gen;

import java.math.BigDecimal;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class Adapter2
    extends XmlAdapter<String, BigDecimal>
{


    public BigDecimal unmarshal(String value) {
        return (eu.adapter.BigDecimalFormatter.parseDouble(value));
    }

    public String marshal(BigDecimal value) {
        return (eu.adapter.BigDecimalFormatter.printDouble(value));
    }

}
