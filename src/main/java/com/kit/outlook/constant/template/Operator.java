package com.kit.outlook.constant.template;

public enum Operator {
    CLS('C'),EQUAL('='),DEL('←'),UNSET('◇'),SET('◈'),HISTORY('◷'),SHIFT('⇵');

    private final char operator;
    Operator(char tooltip){
        this.operator = tooltip;
    }
    public char toOperator(){
        return this.operator;
    }
}
