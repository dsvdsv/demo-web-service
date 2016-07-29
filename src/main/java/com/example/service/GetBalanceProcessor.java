package com.example.service;

import com.example.web.Parameter;
import com.example.web.Request;
import com.example.web.Response;
import com.example.web.ResultCode;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Component
public class GetBalanceProcessor extends BaseProcessor implements Processor {
    @Autowired
    private AccountService accountService;

    @Override
    public void process(Exchange exchange) throws Exception {
        Request request = exchange.getIn().getBody(Request.class);

        String login = parameter("login", request);
        String password = parameter("password", request);

        Optional<Account> accountOpt = accountService.byLogin(login);

        if (!accountOpt.isPresent()) {
            exchange.getOut().setBody(new Response(ResultCode.UserNotExist));
        } else {
            Account account = accountOpt.get();

            if (!Objects.equals(account.getPassword(), password)) {
                exchange.getOut().setBody(new Response(ResultCode.WrongPassword));
            } else {
                exchange.getOut().setBody(
                        new Response(
                                ResultCode.OK,
                                Arrays.asList(new Parameter("balance", account.getBalance()))
                        )
                );
            }
        }
    }
}
