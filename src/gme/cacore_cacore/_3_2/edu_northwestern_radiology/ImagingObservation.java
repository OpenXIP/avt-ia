//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.08.17 at 06:25:05 AM BST 
//


package gme.cacore_cacore._3_2.edu_northwestern_radiology;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ImagingObservation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ImagingObservation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="imagingObservationCharacteristicCollection" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ImagingObservationCharacteristic" type="{gme://caCORE.caCORE/3.2/edu.northwestern.radiology.AIM}ImagingObservationCharacteristic" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="segmentation" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Segmentation" type="{gme://caCORE.caCORE/3.2/edu.northwestern.radiology.AIM}Segmentation" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="codeValue" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="codeMeaning" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="codingSchemeDesignator" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="codingSchemeVersion" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="comment" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="confidence" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ImagingObservation", propOrder = {
    "imagingObservationCharacteristicCollection",
    "segmentation"
})
public class ImagingObservation {

    protected ImagingObservation.ImagingObservationCharacteristicCollection imagingObservationCharacteristicCollection;
    protected ImagingObservation.Segmentation segmentation;
    @XmlAttribute(required = true)
    protected BigInteger id;
    @XmlAttribute(required = true)
    protected String codeValue;
    @XmlAttribute(required = true)
    protected String codeMeaning;
    @XmlAttribute(required = true)
    protected String codingSchemeDesignator;
    @XmlAttribute
    protected String codingSchemeVersion;
    @XmlAttribute
    protected String comment;
    @XmlAttribute
    protected String confidence;

    /**
     * Gets the value of the imagingObservationCharacteristicCollection property.
     * 
     * @return
     *     possible object is
     *     {@link ImagingObservation.ImagingObservationCharacteristicCollection }
     *     
     */
    public ImagingObservation.ImagingObservationCharacteristicCollection getImagingObservationCharacteristicCollection() {
        return imagingObservationCharacteristicCollection;
    }

    /**
     * Sets the value of the imagingObservationCharacteristicCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link ImagingObservation.ImagingObservationCharacteristicCollection }
     *     
     */
    public void setImagingObservationCharacteristicCollection(ImagingObservation.ImagingObservationCharacteristicCollection value) {
        this.imagingObservationCharacteristicCollection = value;
    }

    /**
     * Gets the value of the segmentation property.
     * 
     * @return
     *     possible object is
     *     {@link ImagingObservation.Segmentation }
     *     
     */
    public ImagingObservation.Segmentation getSegmentation() {
        return segmentation;
    }

    /**
     * Sets the value of the segmentation property.
     * 
     * @param value
     *     allowed object is
     *     {@link ImagingObservation.Segmentation }
     *     
     */
    public void setSegmentation(ImagingObservation.Segmentation value) {
        this.segmentation = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setId(BigInteger value) {
        this.id = value;
    }

    /**
     * Gets the value of the codeValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodeValue() {
        return codeValue;
    }

    /**
     * Sets the value of the codeValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodeValue(String value) {
        this.codeValue = value;
    }

    /**
     * Gets the value of the codeMeaning property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodeMeaning() {
        return codeMeaning;
    }

    /**
     * Sets the value of the codeMeaning property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodeMeaning(String value) {
        this.codeMeaning = value;
    }

    /**
     * Gets the value of the codingSchemeDesignator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodingSchemeDesignator() {
        return codingSchemeDesignator;
    }

    /**
     * Sets the value of the codingSchemeDesignator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodingSchemeDesignator(String value) {
        this.codingSchemeDesignator = value;
    }

    /**
     * Gets the value of the codingSchemeVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodingSchemeVersion() {
        return codingSchemeVersion;
    }

    /**
     * Sets the value of the codingSchemeVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodingSchemeVersion(String value) {
        this.codingSchemeVersion = value;
    }

    /**
     * Gets the value of the comment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the value of the comment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComment(String value) {
        this.comment = value;
    }

    /**
     * Gets the value of the confidence property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConfidence() {
        return confidence;
    }

    /**
     * Sets the value of the confidence property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConfidence(String value) {
        this.confidence = value;
    }


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
     *         &lt;element name="ImagingObservationCharacteristic" type="{gme://caCORE.caCORE/3.2/edu.northwestern.radiology.AIM}ImagingObservationCharacteristic" maxOccurs="unbounded" minOccurs="0"/>
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
        "imagingObservationCharacteristic"
    })
    public static class ImagingObservationCharacteristicCollection {

        @XmlElement(name = "ImagingObservationCharacteristic")
        protected List<ImagingObservationCharacteristic> imagingObservationCharacteristic;

        /**
         * Gets the value of the imagingObservationCharacteristic property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the imagingObservationCharacteristic property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getImagingObservationCharacteristic().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ImagingObservationCharacteristic }
         * 
         * 
         */
        public List<ImagingObservationCharacteristic> getImagingObservationCharacteristic() {
            if (imagingObservationCharacteristic == null) {
                imagingObservationCharacteristic = new ArrayList<ImagingObservationCharacteristic>();
            }
            return this.imagingObservationCharacteristic;
        }

    }


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
     *         &lt;element name="Segmentation" type="{gme://caCORE.caCORE/3.2/edu.northwestern.radiology.AIM}Segmentation" minOccurs="0"/>
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
        "segmentation"
    })
    public static class Segmentation {

        @XmlElement(name = "Segmentation")
        protected gme.cacore_cacore._3_2.edu_northwestern_radiology.Segmentation segmentation;

        /**
         * Gets the value of the segmentation property.
         * 
         * @return
         *     possible object is
         *     {@link gme.cacore_cacore._3_2.edu_northwestern_radiology.Segmentation }
         *     
         */
        public gme.cacore_cacore._3_2.edu_northwestern_radiology.Segmentation getSegmentation() {
            return segmentation;
        }

        /**
         * Sets the value of the segmentation property.
         * 
         * @param value
         *     allowed object is
         *     {@link gme.cacore_cacore._3_2.edu_northwestern_radiology.Segmentation }
         *     
         */
        public void setSegmentation(gme.cacore_cacore._3_2.edu_northwestern_radiology.Segmentation value) {
            this.segmentation = value;
        }

    }

}