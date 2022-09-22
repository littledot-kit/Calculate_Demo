package com.kit.outlook.component;

import com.kit.outlook.constant.Constant;
import com.kit.outlook.constant.template.Operator;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class OperatorMouseAdapter extends MouseAdapter {

    private final Operator operator;
    public OperatorMouseAdapter(Operator operator){
        this.operator = operator;

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (operator){
            case CLS:
                Constant.doEmpty();
                break;
            case EQUAL:
                Constant.doFinal();
                break;
            case DEL:
                Constant.doDelete();
                break;
            case SHIFT:
                Constant.doNextPanel();
                break;
            case HISTORY:
//                Constant.doPrevHistory();
                break;
            case UNSET:
            default:
                Constant.doNothing();

        }
    }


    @Override
    public void mouseEntered(MouseEvent e) {
        OperatorButton button = (OperatorButton) e.getSource();
        if(operator == Operator.EQUAL){
            button.setBackground(new Color(59, 213, 115));
        }else{
            button.setBackground(new Color(214, 214, 214));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        OperatorButton button = (OperatorButton) e.getSource();
        if(operator == Operator.EQUAL){
            button.setBackground(new Color(111, 213, 148));
        }else{
            button.setBackground(new Color(240, 240, 240));
        }
    }

}
