package com.kit.outlook.component;

import com.kit.outlook.constant.Constant;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Deprecated
public class ValueOrCommandActionListener implements ActionListener {
    private final char value;
    public ValueOrCommandActionListener(char value){
        super();
        this.value = value;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Constant.doAddition(value);
    }
}
