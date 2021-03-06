/*
Copyright (c) 2010, Siemens Corporate Research a Division of Siemens Corporation 
All rights reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
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
 * <p>Java class for CalculationResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CalculationResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dataCollection" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Data" type="{gme://caCORE.caCORE/3.2/edu.northwestern.radiology.AIM}Data" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="dimensionCollection">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Dimension" type="{gme://caCORE.caCORE/3.2/edu.northwestern.radiology.AIM}Dimension" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="type" use="required" type="{gme://caCORE.caCORE/3.2/edu.northwestern.radiology.AIM}CalculationResultIdentifier" />
 *       &lt;attribute name="numberOfDimensions" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="unitOfMeasure" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CalculationResult", propOrder = {
    "dataCollection",
    "dimensionCollection"
})
public class CalculationResult {

    protected CalculationResult.DataCollection dataCollection;
    @XmlElement(required = true)
    protected CalculationResult.DimensionCollection dimensionCollection;
    @XmlAttribute(required = true)
    protected BigInteger id;
    @XmlAttribute(required = true)
    protected CalculationResultIdentifier type;
    @XmlAttribute(required = true)
    protected BigInteger numberOfDimensions;
    @XmlAttribute(required = true)
    protected String unitOfMeasure;

    /**
     * Gets the value of the dataCollection property.
     * 
     * @return
     *     possible object is
     *     {@link CalculationResult.DataCollection }
     *     
     */
    public CalculationResult.DataCollection getDataCollection() {
        return dataCollection;
    }

    /**
     * Sets the value of the dataCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link CalculationResult.DataCollection }
     *     
     */
    public void setDataCollection(CalculationResult.DataCollection value) {
        this.dataCollection = value;
    }

    /**
     * Gets the value of the dimensionCollection property.
     * 
     * @return
     *     possible object is
     *     {@link CalculationResult.DimensionCollection }
     *     
     */
    public CalculationResult.DimensionCollection getDimensionCollection() {
        return dimensionCollection;
    }

    /**
     * Sets the value of the dimensionCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link CalculationResult.DimensionCollection }
     *     
     */
    public void setDimensionCollection(CalculationResult.DimensionCollection value) {
        this.dimensionCollection = value;
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
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link CalculationResultIdentifier }
     *     
     */
    public CalculationResultIdentifier getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link CalculationResultIdentifier }
     *     
     */
    public void setType(CalculationResultIdentifier value) {
        this.type = value;
    }

    /**
     * Gets the value of the numberOfDimensions property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumberOfDimensions() {
        return numberOfDimensions;
    }

    /**
     * Sets the value of the numberOfDimensions property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumberOfDimensions(BigInteger value) {
        this.numberOfDimensions = value;
    }

    /**
     * Gets the value of the unitOfMeasure property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    /**
     * Sets the value of the unitOfMeasure property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitOfMeasure(String value) {
        this.unitOfMeasure = value;
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
     *         &lt;element name="Data" type="{gme://caCORE.caCORE/3.2/edu.northwestern.radiology.AIM}Data" maxOccurs="unbounded" minOccurs="0"/>
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
        "data"
    })
    public static class DataCollection {

        @XmlElement(name = "Data")
        protected List<Data> data;

        /**
         * Gets the value of the data property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the data property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getData().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Data }
         * 
         * 
         */
        public List<Data> getData() {
            if (data == null) {
                data = new ArrayList<Data>();
            }
            return this.data;
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
     *         &lt;element name="Dimension" type="{gme://caCORE.caCORE/3.2/edu.northwestern.radiology.AIM}Dimension" maxOccurs="unbounded"/>
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
        "dimension"
    })
    public static class DimensionCollection {

        @XmlElement(name = "Dimension", required = true)
        protected List<Dimension> dimension;

        /**
         * Gets the value of the dimension property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the dimension property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDimension().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Dimension }
         * 
         * 
         */
        public List<Dimension> getDimension() {
            if (dimension == null) {
                dimension = new ArrayList<Dimension>();
            }
            return this.dimension;
        }

    }

}
