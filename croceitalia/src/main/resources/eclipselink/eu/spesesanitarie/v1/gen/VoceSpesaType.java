
package eu.spesesanitarie.v1.gen;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for voceSpesaType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="voceSpesaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tipoSpesa">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="TK"/>
 *               &lt;enumeration value="FC"/>
 *               &lt;enumeration value="FV"/>
 *               &lt;enumeration value="AS"/>
 *               &lt;enumeration value="AD"/>
 *               &lt;enumeration value="SR"/>
 *               &lt;enumeration value="CT"/>
 *               &lt;enumeration value="PI"/>
 *               &lt;enumeration value="IC"/>
 *               &lt;enumeration value="AA"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="flagTipoSpesa" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="1"/>
 *               &lt;enumeration value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="importo" type="{}Dec7MinTipo"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "voceSpesaType", propOrder = {
    "tipoSpesa",
    "flagTipoSpesa",
    "importo"
})
public class VoceSpesaType {

    @XmlElement(required = true)
    protected String tipoSpesa;
    protected String flagTipoSpesa;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter2 .class)
    protected BigDecimal importo;

    /**
     * Gets the value of the tipoSpesa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoSpesa() {
        return tipoSpesa;
    }

    /**
     * Sets the value of the tipoSpesa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoSpesa(String value) {
        this.tipoSpesa = value;
    }

    /**
     * Gets the value of the flagTipoSpesa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlagTipoSpesa() {
        return flagTipoSpesa;
    }

    /**
     * Sets the value of the flagTipoSpesa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlagTipoSpesa(String value) {
        this.flagTipoSpesa = value;
    }

    /**
     * Gets the value of the importo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public BigDecimal getImporto() {
        return importo;
    }

    /**
     * Sets the value of the importo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImporto(BigDecimal value) {
        this.importo = value;
    }

}
