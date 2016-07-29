package com.example.service;

import com.example.web.Parameter;
import com.example.web.Request;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public abstract class BaseProcessor {

    protected String parameter(String name, Request request) {
        List<Parameter> parameters = request.getParameters();

        Optional<Parameter> parameterOpt = parameters.stream()
                .filter(p -> Objects.equals(p.getName(), name))
                .findFirst();

        Parameter p = Objects.requireNonNull(parameterOpt.orElseGet(null), name);

        return p.getValue().toString();
    }
}
