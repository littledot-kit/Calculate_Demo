package com.kit.outlook.constant;



public enum Feature {
    // 是否开启日志
    CALCULATE_WITH_LOGGING(true,0),
    // 是否开启更多按键
    USE_NORMAL_TYPE(true,1),
    USE_PLUS_TYPE(false,2),
    USE_ULTRA_TYPE(false,3),
    // 是否启动立即使用,而不是先按C键解锁
    IMMEDIATE_USE_AFTER_START(false,4),
    // 输入不能为空，默认填充0
    INPUT_NOT_EMPTY(false,5);


    private final boolean defaultOpenStatus;
    Feature(boolean defaultOpenStatus, int bit){
        this.defaultOpenStatus = defaultOpenStatus;
    }
    public boolean getDefaultOpenStatus(){
        return this.defaultOpenStatus;
    }
}
