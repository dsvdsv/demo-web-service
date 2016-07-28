package com.example;

import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jacksonxml.JacksonXMLDataFormat;
import org.apache.camel.spi.DataFormat;
import org.omg.CORBA.Request;
import org.springframework.stereotype.Component;

@Component
public class CamelRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        JacksonXMLDataFormat ageViewFormat = new JacksonXMLDataFormat(Request.class);

        from("netty4-http:http://0.0.0.0:8080/endpoint?httpMethodRestrict=POST")
                .convertBodyTo(String.class)
                .choice()
                    .when(xpath("//request-type='CREATE-AGT'"))
                        .to("direct:create-agt")
                    .when(xpath("//request-type='GET-BALANCE'"))
                        .to("direct:get-balance")
                    .otherwise()
                        .transform().constant(
                                "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                                    "<response>\n" +
                                        "<result-code>2</result-code>\n" +
                                    "</response>\n");

        from("direct:create-agt")
                .unmarshal(ageViewFormat)
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        Object in = exchange.getIn();
                    }
                })
                .transform(constant("<order id=\"123\">OK</order>"));

        from("direct:get-balance").transform(constant("<order id=\"321\">OK</order>"));
    }
}
