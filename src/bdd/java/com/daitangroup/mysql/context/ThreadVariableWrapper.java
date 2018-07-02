package com.daitangroup.mysql.context;

public class ThreadVariableWrapper {

    private static final ThreadLocal<ContextVariables> threadVariable = new ThreadVariable();

    public static ContextVariables getContext(){
        return threadVariable.get();
    }

}
