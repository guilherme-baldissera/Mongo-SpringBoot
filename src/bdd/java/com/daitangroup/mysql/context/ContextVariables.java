package com.daitangroup.mysql.context;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

public class ContextVariables {

    @Getter
    @Setter
    Map<String,Object> variables;

    public ContextVariables() {
        this.variables = new HashMap<>();
    }
}