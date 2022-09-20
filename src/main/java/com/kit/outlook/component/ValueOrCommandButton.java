package com.kit.outlook.component;

import com.kit.outlook.constant.ResolveTool;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;


public class ValueOrCommandButton extends JButton {
    public ValueOrCommandButton(char value){
        super(String.valueOf(value));
        addMouseListener(new ValueOrCommandMouseAdapter(value));
        if(ResolveTool.isNumber(value)){
            this.setBackground(new Color(250, 250, 250));
            this.setFont(new Font("黑体",Font.BOLD,16));
        }else{
            this.setBackground(new Color(240, 240, 240));
            this.setFont(new Font("Consolas",Font.PLAIN,16));
        }
        this.setBorder(new LineBorder(new Color(230, 230, 230),1));
        this.setFocusPainted(false);
        this.setPreferredSize(new Dimension(80,60));


    }

}
