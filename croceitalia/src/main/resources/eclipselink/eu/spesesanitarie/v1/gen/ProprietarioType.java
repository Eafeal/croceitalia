
package eu.spesesanitarie.v1.gen;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for proprietarioType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="proprietarioType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codiceRegione" type="{}varChar3Type" minOccurs="0"/>
 *         &lt;element name="codiceAsl" type="{}varChar3Type" minOccurs="0"/>
 *         &lt;element name="codiceSSA" type="{}codSsaType" minOccurs="0"/>
 *         &lt;element name="cfProprietario" type="{}cfType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "proprietarioType", propOrder = {
    "codiceRegione",
    "codiceAsl",
    "codiceSSA",
    "cfProprietario"
})
public class ProprietarioType {

    protected String codiceRegione;
    protected String codiceAsl;
    protected String codiceSSA;
    @XmlElement(required = true)
    protected String cfProprietario;

    /**
     * Gets the value of the codiceRegione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceRegione() {
        return codiceRegione;
    }

    /**
     * Sets the value of the codiceRegione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceRegione(String value) {
        this.codiceRegione = value;
    }

    /**
     * Gets the value of the codiceAsl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceAsl() {
        return codiceAsl;
    }

    /**
     * Sets the value of the codiceAsl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceAsl(String value) {
        this.codiceAsl = value;
    }

    /**
     * Gets the value of the codiceSSA property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceSSA() {
        return codiceSSA;
    }

    /**
     * Sets the value of the codiceSSA property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceSSA(String value) {
        this.codiceSSA = value;
    }

    /**
     * Gets the value of the cfProprietario property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCfProprietario() {
        return cfProprietario;
    }

    /**
     * Sets the value of the cfProprietario property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCfProprietario(String value) {
        this.cfProprietario = value;
    }

}
