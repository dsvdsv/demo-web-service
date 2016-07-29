package com.example;

import com.example.service.GetBalanceProcessor;
import com.example.service.RegisterAccountProcessor;
import com.example.web.Request;
import com.example.web.Response;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.apache.camel.component.jacksonxml.JacksonXMLDataFormat;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

;

@Component
public class CamelRouter extends SpringRouteBuilder {
    @Override
    public void configure() throws Exception {

        // @formatter:off

        JacksonXMLDataFormat requestFormat = jacksonXMLDataFormat(Request.class);
        JacksonXMLDataFormat responseFormat = jacksonXMLDataFormat(Response.class);


        onException(Exception.class)
                .handled(true)
                .transform().method(ExceptionTransformer.class)
                .marshal(responseFormat);

        from("netty4-http:http://0.0.0.0:8080/endpoint?httpMethodRestrict=POST")
                .convertBodyTo(String.class)
                .choice()
                    .when(xpath("//request-type='CREATE-AGT'"))
                        .to("direct:register-account")
                    .when(xpath("//request-type='GET-BALANCE'"))
                        .to("direct:get-balance")
                    .otherwise()
                        .throwException(new IllegalArgumentException("required correct request-type"));

        from("direct:register-account")
                .unmarshal(requestFormat)
                .process(lookup(RegisterAccountProcessor.class))
                .marshal(responseFormat);

        from("direct:get-balance")
                .unmarshal(requestFormat)
                .process(lookup(GetBalanceProcessor.class))
                .marshal(responseFormat);

        // @formatter:on
    }

    private JacksonXMLDataFormat jacksonXMLDataFormat(Class<?> unmarshalType) {
        JacksonXMLDataFormat format = new JacksonXMLDataFormat(Request.class);

        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);

        format.setXmlMapper(xmlMapper);

        return format;

    }
}
