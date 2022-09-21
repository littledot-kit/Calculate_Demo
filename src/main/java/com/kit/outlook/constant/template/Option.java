package com.kit.outlook.constant.template;

public enum Option {
    SHIFT("切换"),HISTORY("历史"),HELP("Help");

    private final String title;
    Option(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
}
