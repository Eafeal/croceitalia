
package eu.spesesanitarie.v1.gen;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="opzionale1" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="opzionale2" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="opzionale3" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="proprietario" type="{}proprietarioType"/>
 *         &lt;element name="documentoSpesa" type="{}documentoSpesaType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "opzionale1",
    "opzionale2",
    "opzionale3",
    "proprietario",
    "documentoSpesa"
})
@XmlRootElement(name = "precompilata")
public class Precompilata implements Comparable<Precompilata> {

    protected Object opzionale1;
    protected Object opzionale2;
    protected Object opzionale3;
    @XmlElement(required = true)
    protected ProprietarioType proprietario;
    @XmlElement(required = true)
    protected List<DocumentoSpesaType> documentoSpesa;

    /**
     * Gets the value of the opzionale1 property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getOpzionale1() {
        return opzionale1;
    }

    /**
     * Sets the value of the opzionale1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setOpzionale1(Object value) {
        this.opzionale1 = value;
    }

    /**
     * Gets the value of the opzionale2 property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getOpzionale2() {
        return opzionale2;
    }

    /**
     * Sets the value of the opzionale2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setOpzionale2(Object value) {
        this.opzionale2 = value;
    }

    /**
     * Gets the value of the opzionale3 property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getOpzionale3() {
        return opzionale3;
    }

    /**
     * Sets the value of the opzionale3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setOpzionale3(Object value) {
        this.opzionale3 = value;
    }

    /**
     * Gets the value of the proprietario property.
     * 
     * @return
     *     possible object is
     *     {@link ProprietarioType }
     *     
     */
    public ProprietarioType getProprietario() {
		if (proprietario == null) {
			proprietario = new ProprietarioType();
		}
        return proprietario;
    }

    /**
     * Sets the value of the proprietario property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProprietarioType }
     *     
     */
    public void setProprietario(ProprietarioType value) {
        this.proprietario = value;
    }

    /**
     * Gets the value of the documentoSpesa property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the documentoSpesa property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocumentoSpesa().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DocumentoSpesaType }
     * 
     * 
     */
    public List<DocumentoSpesaType> getDocumentoSpesa() {
        if (documentoSpesa == null) {
            documentoSpesa = new ArrayList<DocumentoSpesaType>();
        }
        return this.documentoSpesa;
    }

	@Override
	public int compareTo(Precompilata other) {
		String cfProprietario = getProprietario().getCfProprietario();
		int result = cfProprietario.compareTo(other.getProprietario().getCfProprietario());
		return result;
	}
}
