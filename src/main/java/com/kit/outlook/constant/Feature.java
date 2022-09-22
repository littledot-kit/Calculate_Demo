package com.kit.outlook.constant;



public enum Feature {
    // 是否开启日志
    CALCULATE_WITH_LOGGING(true,0),
    // 是否开启更多按键
    USE_NORMAL_TYPE(true,1),
    USE_PLUS_TYPE(false,2),
    USE_ULTRA_TYPE(false,3);


    private final boolean defaultOpenStatus;
    Feature(boolean defaultOpenStatus, int bit){
        this.defaultOpenStatus = defaultOpenStatus;
    }
    public boolean getDefaultOpenStatus(){
        return this.defaultOpenStatus;
    }
}
