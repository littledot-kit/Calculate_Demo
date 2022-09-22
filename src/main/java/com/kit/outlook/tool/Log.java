package com.kit.outlook.tool;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class Log {

    public static void out(String msg){
        System.out.println("[LOG]: "+msg);
    }

    public static void key(KeyEvent e){
        StringBuilder s = new StringBuilder();
        s.append("code[").append(e.getKeyCode()).append("] ").append("char[");
        if(e.getKeyChar()=='\n'){
            s.append("\\n");
        }else{
            s.append(e.getKeyChar());
        }
        s.append("] ");
        s.append("mod[");
        if(e.isAltDown()){
            s.append("alt ");
        }
        if(e.isControlDown()){
            s.append("ctrl ");
        }
        if(e.isShiftDown()){
            s.append("shift ");
        }
        s.append("]");
        System.out.println("[KEY]: "+ s);
    }

    public static void action(ActionEvent e){
        System.out.println("[action]: "+e.getActionCommand()+"-"+e.getID()+"-"+e.getModifiers()+"-"+e.getSource());
    }
    private static int boolToInt(boolean flag){
        return flag? 1:0;
    }
}
