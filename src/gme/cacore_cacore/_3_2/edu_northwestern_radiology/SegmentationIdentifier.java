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

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SegmentationIdentifier.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SegmentationIdentifier">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Binary"/>
 *     &lt;enumeration value="Fractional_Probability"/>
 *     &lt;enumeration value="Fractional_Occupancy"/>
 *     &lt;enumeration value="Surface"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SegmentationIdentifier")
@XmlEnum
public enum SegmentationIdentifier {

    @XmlEnumValue("Binary")
    BINARY("Binary"),
    @XmlEnumValue("Fractional_Probability")
    FRACTIONAL_PROBABILITY("Fractional_Probability"),
    @XmlEnumValue("Fractional_Occupancy")
    FRACTIONAL_OCCUPANCY("Fractional_Occupancy"),
    @XmlEnumValue("Surface")
    SURFACE("Surface");
    private final String value;

    SegmentationIdentifier(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SegmentationIdentifier fromValue(String v) {
        for (SegmentationIdentifier c: SegmentationIdentifier.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
