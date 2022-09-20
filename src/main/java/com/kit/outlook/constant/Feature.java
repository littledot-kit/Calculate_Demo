package com.kit.outlook.constant;



public enum Feature {
    // 是否开启日志
    CALCULATE_WITH_LOGGING(true,0),
    // 是否开启更多按键
    USE_MORE_BUTTON(false,1),
    USE_MORE_BUTTON_PLUS(false,2),
    USE_MORE_BUTTON_SCIENCE(false,3),
    // 是否启动立即使用,而不是先按C键解锁
    IMMEDIATE_USE_AFTER_START(false,4),
    // 输入不能为空，默认填充0
    INPUT_NOT_EMPTY(false,5);



    private Feature(boolean defaultOpenStatus,int bit){}
}
