package com.kit.outlook.component;

import com.kit.outlook.constant.Constant;
import com.kit.outlook.constant.MainFrame;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GlobalKeyProcessor implements KeyEventPostProcessor {

    @Override
    public boolean postProcessKeyEvent(KeyEvent e) {
        if(e.getID()!=402 || isSpecialModifierKey(e.getKeyCode())){
            return false;
        }
        doDispatch(e.getKeyCode(),e.getModifiers());
        return true;
    }
    private boolean isSpecialModifierKey(int keyCode){
        return (keyCode<=KeyEvent.VK_ALT && keyCode>=KeyEvent.VK_SHIFT) || keyCode==KeyEvent.VK_META;
    }

    private void doDispatch(int keyCode,int modifier){
        if(modifier==0){
            if(keyCode>=KeyEvent.VK_0 && keyCode<= KeyEvent.VK_9){
                Constant.doAddition(KeyEvent.getKeyText(keyCode).charAt(0));
            }else if(keyCode>=KeyEvent.VK_NUMPAD0 && keyCode<= KeyEvent.VK_NUMPAD9){
                char  i = (char) ((keyCode - KeyEvent.VK_NUMPAD0) + '0');
                Constant.doAddition(i);

            }else if(keyCode==KeyEvent.VK_BACK_SPACE){
                Constant.doDelete();
            }else if(keyCode== KeyEvent.VK_ENTER || keyCode==KeyEvent.VK_EQUALS){
                Constant.doFinal();
            }else if(keyCode==KeyEvent.VK_PAGE_UP){
                Constant.doPrevHistory();
            }else if(keyCode==KeyEvent.VK_PAGE_DOWN){
                Constant.doNextHistory();
            }

        }else{
            switch (keyCode){
                case KeyEvent.VK_1:
                    if(modifier==KeyEvent.CTRL_MASK){
                        MainFrame.setTopPanel(0);
                    }
                    break;
                case KeyEvent.VK_2:
                    if(modifier==KeyEvent.CTRL_MASK){
                        MainFrame.setTopPanel(1);
                    }
                    break;
                case KeyEvent.VK_3:
                    if(modifier==KeyEvent.CTRL_MASK){
                        MainFrame.setTopPanel(2);
                    }
                    break;
            }
        }
    }
}
