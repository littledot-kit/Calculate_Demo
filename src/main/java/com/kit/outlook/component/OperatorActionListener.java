package com.kit.outlook.component;

import com.kit.outlook.constant.Constant;
import com.kit.outlook.constant.template.Operator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Deprecated
public class OperatorActionListener implements ActionListener {
    private final Operator operator;
    public OperatorActionListener(Operator operator){
        this.operator = operator;

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (operator){
            case CLS:
                Constant.doEmptyOrOpen();
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
                Constant.doPrevHistory();
                break;
            case UNSET:
                Constant.doSetOrUnset((OperatorButton) e.getSource());
                break;
            default:
                Constant.doNothing();

        }
    }


}
