package com.kit.outlook.component;

import com.kit.outlook.constant.Constant;
import com.kit.outlook.constant.ResolveTool;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ValueOrCommandMouseAdapter extends MouseAdapter {
    private final char value;
    public ValueOrCommandMouseAdapter(char value){
        this.value = value;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        Constant.doAddition(value);
    }


    @Override
    public void mouseEntered(MouseEvent e) {
        ValueOrCommandButton button = (ValueOrCommandButton) e.getSource();
        button.setBackground(new Color(214, 214, 214));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        ValueOrCommandButton button = (ValueOrCommandButton) e.getSource();
        if(ResolveTool.isNumber(value)){
            button.setBackground(new Color(250, 250, 250));

        }else{
            button.setBackground(new Color(240, 240, 240));
        }
    }



}
