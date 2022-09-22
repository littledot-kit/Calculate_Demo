package com.kit.outlook.component;

import com.kit.outlook.constant.template.Command;
import com.kit.outlook.constant.template.ValueFriend;

import java.util.HashMap;
import java.util.Map;

import static java.awt.event.KeyEvent.*;

public class GlobalControlMap {
    private static final Map<Integer,Character> GLOBAL_FEATURE_KEY_MAP = new HashMap<>();

    public static void setFeature(Integer i,Character c){
        GLOBAL_FEATURE_KEY_MAP.put(i,c);
    }
    public static Character getFeature(Integer i){
        return GLOBAL_FEATURE_KEY_MAP.get(i);
    }




    static{
        GlobalControlMap.setFeature(VK_MULTIPLY, Command.MUL);
        GlobalControlMap.setFeature(VK_ADD,Command.ADD);
        GlobalControlMap.setFeature(VK_SUBTRACT,Command.SUB);
        GlobalControlMap.setFeature(VK_DECIMAL, ValueFriend.DOT);
        GlobalControlMap.setFeature(VK_DIVIDE,Command.DIV);
    }
}
