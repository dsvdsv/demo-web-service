package com.example.service;

import com.example.web.Request;
import com.example.web.Response;
import com.example.web.ResultCode;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class RegisterAccountProcessor extends BaseProcessor implements Processor {
    @Autowired
    private AccountService accountService;

    @Override
    public void process(Exchange exchange) throws Exception {
        Request request = exchange.getIn().getBody(Request.class);

        Account account = new Account(
                parameter("login", request),
                parameter("password", request)
        );

        boolean added = accountService.register(account);

        exchange.getOut().setBody(
                new Response(
                        added ? ResultCode.OK : ResultCode.UserAlReadyExist
                )
        );
    }

}
