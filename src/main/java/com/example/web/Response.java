package com.example.web;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "response")
public class Response {

    public Response() {
    }

    public Response(ResultCode code) {
        this.code = code;
    }

    public Response(ResultCode code, List<Parameter> parameters) {
        this(code);
        this.parameters = parameters;
    }

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @JacksonXmlProperty(localName = "result-code")
    protected ResultCode code;

    @JacksonXmlProperty(localName = "extra")
    @JacksonXmlElementWrapper(useWrapping = false)
    protected List<Parameter> parameters;

    public ResultCode getCode() {
        return code;
    }

    public void setCode(ResultCode code) {
        this.code = code;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }
}
