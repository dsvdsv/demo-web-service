package com.example;

import com.example.web.Response;
import com.example.web.ResultCode;


public class ExceptionTransformer {
    public Response transform(Exception e) {
        return new Response(ResultCode.TechnicalError);
    }
}