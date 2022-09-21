package com.kit.outlook.constant.template;

public enum CalType {
    NORMAL("Normal"),PLUS("Plus"),ULTRA("Ultra");

    private String type;
    CalType(String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }
}
