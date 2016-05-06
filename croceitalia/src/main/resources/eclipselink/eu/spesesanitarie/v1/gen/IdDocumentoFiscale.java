
package eu.spesesanitarie.v1.gen;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for idDocumentoFiscale complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="idDocumentoFiscale">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pIva">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;pattern value="([0-9]{11})"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="dataEmissione" type="{}DataMinType"/>
 *         &lt;element name="numDocumentoFiscale" type="{}numDocumentoFiscaleType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "idDocumentoFiscale", propOrder = {
    "pIva",
    "dataEmissione",
    "numDocumentoFiscale"
})
public class IdDocumentoFiscale {

    @XmlElement(required = true)
    protected String pIva;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    protected XMLGregorianCalendar dataEmissione;
    @XmlElement(required = true)
    protected NumDocumentoFiscaleType numDocumentoFiscale;

    /**
     * Gets the value of the pIva property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPIva() {
        return pIva;
    }

    /**
     * Sets the value of the pIva property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPIva(String value) {
        this.pIva = value;
    }

    /**
     * Gets the value of the dataEmissione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public XMLGregorianCalendar getDataEmissione() {
        return dataEmissione;
    }

    /**
     * Sets the value of the dataEmissione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataEmissione(XMLGregorianCalendar value) {
        this.dataEmissione = value;
    }

    /**
     * Gets the value of the numDocumentoFiscale property.
     * 
     * @return
     *     possible object is
     *     {@link NumDocumentoFiscaleType }
     *     
     */
    public NumDocumentoFiscaleType getNumDocumentoFiscale() {
		if (numDocumentoFiscale == null) {
			numDocumentoFiscale = new NumDocumentoFiscaleType();
		}
        return numDocumentoFiscale;
    }

    /**
     * Sets the value of the numDocumentoFiscale property.
     * 
     * @param value
     *     allowed object is
     *     {@link NumDocumentoFiscaleType }
     *     
     */
    public void setNumDocumentoFiscale(NumDocumentoFiscaleType value) {
        this.numDocumentoFiscale = value;
    }

}
