package com.kit.outlook.constant.template;

public class ValueOrCommand {
    private final Integer value;
    private final Character command;

    public ValueOrCommand(Integer value,Character command){
        this.value = value;
        this.command = command;
    }

    public boolean isValue(){
        return value!=null;
    }

    public Integer getValue() {
        return value;
    }
    public Character getCommand(){
        return command;
    }

}
