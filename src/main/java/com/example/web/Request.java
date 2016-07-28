package com.example.web;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "request")
public class Request {
    @XmlElement(required = true)
    protected List<Parameter> parameters;

    public List<Parameter> getParameters() {
        if (parameters == null) {
            parameters = new ArrayList();
        }
        return parameters;
    }
}
