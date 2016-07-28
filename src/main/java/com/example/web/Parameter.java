package com.example.web;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "extra", propOrder = { "name" })
public class Parameter {
    @XmlAttribute(required = true)
    protected String name;

    @XmlValue()
    protected String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
