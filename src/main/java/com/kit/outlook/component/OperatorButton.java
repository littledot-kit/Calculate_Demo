package com.kit.outlook.component;

import com.kit.outlook.constant.template.Operator;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;


public class OperatorButton extends JButton {
    public OperatorButton(Operator operator){
        super(String.valueOf(operator.toOperator()));
        addMouseListener(new OperatorMouseAdapter(operator));
        if (operator== Operator.EQUAL){
            this.setBackground(new Color(111, 213, 148));
        }else{
            this.setBackground(new Color(240, 240, 240));
        }
        this.setBorder(new LineBorder(new Color(230, 230, 230),1));
        this.setFocusPainted(false);
    }


}
