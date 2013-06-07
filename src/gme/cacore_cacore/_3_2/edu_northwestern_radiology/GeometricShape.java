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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GeometricShape complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GeometricShape">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="spatialCoordinateCollection">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="SpatialCoordinate" type="{gme://caCORE.caCORE/3.2/edu.northwestern.radiology.AIM}SpatialCoordinate" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="lineColor" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="lineOpacity" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="lineStyle" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="lineThickness" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="includeFlag" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="shapeIdentifier" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GeometricShape", propOrder = {
    "spatialCoordinateCollection"
})
@XmlSeeAlso({
    Ellipse.class,
    Circle.class,
    MultiPoint.class,
    Point.class,
    Polyline.class
})
public abstract class GeometricShape {

    @XmlElement(required = true)
    protected GeometricShape.SpatialCoordinateCollection spatialCoordinateCollection;
    @XmlAttribute(required = true)
    protected BigInteger id;
    @XmlAttribute
    protected String lineColor;
    @XmlAttribute
    protected String lineOpacity;
    @XmlAttribute
    protected String lineStyle;
    @XmlAttribute
    protected String lineThickness;
    @XmlAttribute(required = true)
    protected boolean includeFlag;
    @XmlAttribute(required = true)
    protected BigInteger shapeIdentifier;

    /**
     * Gets the value of the spatialCoordinateCollection property.
     * 
     * @return
     *     possible object is
     *     {@link GeometricShape.SpatialCoordinateCollection }
     *     
     */
    public GeometricShape.SpatialCoordinateCollection getSpatialCoordinateCollection() {
        return spatialCoordinateCollection;
    }

    /**
     * Sets the value of the spatialCoordinateCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link GeometricShape.SpatialCoordinateCollection }
     *     
     */
    public void setSpatialCoordinateCollection(GeometricShape.SpatialCoordinateCollection value) {
        this.spatialCoordinateCollection = value;
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
     * Gets the value of the lineColor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLineColor() {
        return lineColor;
    }

    /**
     * Sets the value of the lineColor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLineColor(String value) {
        this.lineColor = value;
    }

    /**
     * Gets the value of the lineOpacity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLineOpacity() {
        return lineOpacity;
    }

    /**
     * Sets the value of the lineOpacity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLineOpacity(String value) {
        this.lineOpacity = value;
    }

    /**
     * Gets the value of the lineStyle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLineStyle() {
        return lineStyle;
    }

    /**
     * Sets the value of the lineStyle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLineStyle(String value) {
        this.lineStyle = value;
    }

    /**
     * Gets the value of the lineThickness property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLineThickness() {
        return lineThickness;
    }

    /**
     * Sets the value of the lineThickness property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLineThickness(String value) {
        this.lineThickness = value;
    }

    /**
     * Gets the value of the includeFlag property.
     * 
     */
    public boolean isIncludeFlag() {
        return includeFlag;
    }

    /**
     * Sets the value of the includeFlag property.
     * 
     */
    public void setIncludeFlag(boolean value) {
        this.includeFlag = value;
    }

    /**
     * Gets the value of the shapeIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getShapeIdentifier() {
        return shapeIdentifier;
    }

    /**
     * Sets the value of the shapeIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setShapeIdentifier(BigInteger value) {
        this.shapeIdentifier = value;
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
     *         &lt;element name="SpatialCoordinate" type="{gme://caCORE.caCORE/3.2/edu.northwestern.radiology.AIM}SpatialCoordinate" maxOccurs="unbounded"/>
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
        "spatialCoordinate"
    })
    public static class SpatialCoordinateCollection {

        @XmlElement(name = "SpatialCoordinate", required = true)
        protected List<SpatialCoordinate> spatialCoordinate;

        /**
         * Gets the value of the spatialCoordinate property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the spatialCoordinate property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSpatialCoordinate().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SpatialCoordinate }
         * 
         * 
         */
        public List<SpatialCoordinate> getSpatialCoordinate() {
            if (spatialCoordinate == null) {
                spatialCoordinate = new ArrayList<SpatialCoordinate>();
            }
            return this.spatialCoordinate;
        }

    }

}
