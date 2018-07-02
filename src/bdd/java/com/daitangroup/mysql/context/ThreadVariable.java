package com.daitangroup.mysql.context;

public final class ThreadVariable extends ThreadLocal<ContextVariables> {
    @Override
    protected ContextVariables initialValue() {
        return new ContextVariables();
    }
}