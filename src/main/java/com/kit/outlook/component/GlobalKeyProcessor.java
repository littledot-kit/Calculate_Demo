package com.kit.outlook.component;

import com.kit.outlook.constant.Constant;
import com.kit.outlook.constant.MainFrame;
import com.kit.outlook.tool.Log;

import java.awt.*;
import java.awt.event.KeyEvent;


import static java.awt.event.KeyEvent.*;

public class GlobalKeyProcessor implements KeyEventPostProcessor {




    @Override
    public boolean postProcessKeyEvent(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (e.getID() != 402 || isSpecialModifierKey(keyCode)) {
            return false;
        }
        Log.key(e);
        if(e.isControlDown()){
            doCtrlKey(keyCode);
        }else{
            doOnlyKey(keyCode);
        }
        return true;
    }

    private boolean isSpecialModifierKey(int keyCode) {
        return (keyCode <= KeyEvent.VK_ALT && keyCode >= KeyEvent.VK_SHIFT) || keyCode == KeyEvent.VK_META;
    }

    private void doOnlyKey(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_0:
            case KeyEvent.VK_1:
            case KeyEvent.VK_2:
            case KeyEvent.VK_3:
            case KeyEvent.VK_4:
            case KeyEvent.VK_5:
            case KeyEvent.VK_6:
            case KeyEvent.VK_7:
            case KeyEvent.VK_8:
            case KeyEvent.VK_9:
                Constant.doAddition(KeyEvent.getKeyText(keyCode).charAt(0));
                break;

            case KeyEvent.VK_NUMPAD0:
            case KeyEvent.VK_NUMPAD1:
            case KeyEvent.VK_NUMPAD2:
            case KeyEvent.VK_NUMPAD3:
            case KeyEvent.VK_NUMPAD4:
            case KeyEvent.VK_NUMPAD5:
            case KeyEvent.VK_NUMPAD6:
            case KeyEvent.VK_NUMPAD7:
            case KeyEvent.VK_NUMPAD8:
            case KeyEvent.VK_NUMPAD9:
                Constant.doAddition((char) ((keyCode - KeyEvent.VK_NUMPAD0) + '0'));
                break;

            case KeyEvent.VK_MULTIPLY:
            case KeyEvent.VK_ADD:
            case KeyEvent.VK_SUBTRACT:
            case KeyEvent.VK_DECIMAL:
            case KeyEvent.VK_DIVIDE:
                Constant.doAddition(GlobalControlMap.getFeature(keyCode));
                break;

            case KeyEvent.VK_BACK_SPACE:
                Constant.doDelete();
                break;
            case KeyEvent.VK_ENTER:
            case KeyEvent.VK_EQUALS:
                Constant.doFinal();
                break;
            case KeyEvent.VK_PAGE_UP:
                Constant.doPrevHistory();
                break;
            case KeyEvent.VK_PAGE_DOWN:
                Constant.doNextHistory();
                break;
            case KeyEvent.VK_DELETE:
                Constant.doEmpty();
                break;
            case KeyEvent.VK_TAB:
                Constant.doNothing();
                break;
            case KeyEvent.VK_F5:
            case KeyEvent.VK_F6:
            case KeyEvent.VK_F7:
                MainFrame.setTopPanel(keyCode-VK_F5);
                break;
        }
    }
    private void doCtrlKey(int keyCode){
        switch (keyCode) {
            case KeyEvent.VK_1:
                    MainFrame.setTopPanel(0);
                break;
            case KeyEvent.VK_2:
                    MainFrame.setTopPanel(1);
                break;
            case KeyEvent.VK_3:
                    MainFrame.setTopPanel(2);
                break;
        }
    }



}
