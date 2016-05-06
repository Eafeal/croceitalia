
package eu.spesesanitarie.v1.gen;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for numDocumentoFiscaleType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="numDocumentoFiscaleType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dispositivo">
 *           &lt;simpleType>
 *             &lt;restriction base="{}Int3Type">
 *               &lt;pattern value="([0-9]{1,3})"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="numDocumento" type="{}numDocType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "numDocumentoFiscaleType", propOrder = {
    "dispositivo",
    "numDocumento"
})
public class NumDocumentoFiscaleType {

    @XmlElement(required = true)
    protected String dispositivo;
    @XmlElement(required = true)
    protected String numDocumento;

    /**
     * Gets the value of the dispositivo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDispositivo() {
        return dispositivo;
    }

    /**
     * Sets the value of the dispositivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDispositivo(String value) {
        this.dispositivo = value;
    }

    /**
     * Gets the value of the numDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumDocumento() {
        return numDocumento;
    }

    /**
     * Sets the value of the numDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumDocumento(String value) {
        this.numDocumento = value;
    }

}
