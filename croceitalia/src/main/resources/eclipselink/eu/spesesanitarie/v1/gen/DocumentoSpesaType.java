
package eu.spesesanitarie.v1.gen;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for documentoSpesaType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="documentoSpesaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idSpesa" type="{}idDocumentoFiscale"/>
 *         &lt;element name="idRimborso" type="{}idDocumentoFiscale" minOccurs="0"/>
 *         &lt;element name="dataPagamento" type="{}DataMinType"/>
 *         &lt;element name="flagPagamentoAnticipato" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *               &lt;enumeration value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="flagOperazione">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="I"/>
 *               &lt;enumeration value="V"/>
 *               &lt;enumeration value="R"/>
 *               &lt;enumeration value="C"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="cfCittadino" type="{}cfType"/>
 *         &lt;element name="voceSpesa" type="{}voceSpesaType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "documentoSpesaType", propOrder = {
    "idSpesa",
    "idRimborso",
    "dataPagamento",
    "flagPagamentoAnticipato",
    "flagOperazione",
    "cfCittadino",
    "voceSpesa"
})
public class DocumentoSpesaType {

    @XmlElement(required = true)
    protected IdDocumentoFiscale idSpesa;
    protected IdDocumentoFiscale idRimborso;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter3 .class)
    protected XMLGregorianCalendar dataPagamento;
    protected Integer flagPagamentoAnticipato;
    @XmlElement(required = true)
    protected String flagOperazione;
    @XmlElement(required = true)
    protected String cfCittadino;
    @XmlElement(required = true)
    protected List<VoceSpesaType> voceSpesa;

    /**
     * Gets the value of the idSpesa property.
     * 
     * @return
     *     possible object is
     *     {@link IdDocumentoFiscale }
     *     
     */
    public IdDocumentoFiscale getIdSpesa() {
		if (idSpesa == null) {
			idSpesa = new IdDocumentoFiscale();
		}
        return idSpesa;
    }

    /**
     * Sets the value of the idSpesa property.
     * 
     * @param value
     *     allowed object is
     *     {@link IdDocumentoFiscale }
     *     
     */
    public void setIdSpesa(IdDocumentoFiscale value) {
        this.idSpesa = value;
    }

    /**
     * Gets the value of the idRimborso property.
     * 
     * @return
     *     possible object is
     *     {@link IdDocumentoFiscale }
     *     
     */
    public IdDocumentoFiscale getIdRimborso() {
		if (idRimborso == null) {
			idRimborso = new IdDocumentoFiscale();
		}
        return idRimborso;
    }

    /**
     * Sets the value of the idRimborso property.
     * 
     * @param value
     *     allowed object is
     *     {@link IdDocumentoFiscale }
     *     
     */
    public void setIdRimborso(IdDocumentoFiscale value) {
        this.idRimborso = value;
    }

    /**
     * Gets the value of the dataPagamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public XMLGregorianCalendar getDataPagamento() {
        return dataPagamento;
    }

    /**
     * Sets the value of the dataPagamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataPagamento(XMLGregorianCalendar value) {
        this.dataPagamento = value;
    }

    /**
     * Gets the value of the flagPagamentoAnticipato property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFlagPagamentoAnticipato() {
        return flagPagamentoAnticipato;
    }

    /**
     * Sets the value of the flagPagamentoAnticipato property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFlagPagamentoAnticipato(Integer value) {
        this.flagPagamentoAnticipato = value;
    }

    /**
     * Gets the value of the flagOperazione property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlagOperazione() {
        return flagOperazione;
    }

    /**
     * Sets the value of the flagOperazione property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlagOperazione(String value) {
        this.flagOperazione = value;
    }

    /**
     * Gets the value of the cfCittadino property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCfCittadino() {
        return cfCittadino;
    }

    /**
     * Sets the value of the cfCittadino property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCfCittadino(String value) {
        this.cfCittadino = value;
    }

    /**
     * Gets the value of the voceSpesa property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the voceSpesa property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVoceSpesa().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VoceSpesaType }
     * 
     * 
     */
    public List<VoceSpesaType> getVoceSpesa() {
        if (voceSpesa == null) {
            voceSpesa = new ArrayList<VoceSpesaType>();
        }
        return this.voceSpesa;
    }

}
