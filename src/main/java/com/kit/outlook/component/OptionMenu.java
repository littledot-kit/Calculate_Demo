package com.kit.outlook.component;

import com.kit.outlook.constant.Constant;
import com.kit.outlook.constant.MainFrame;
import com.kit.outlook.constant.template.CalType;
import com.kit.outlook.constant.template.Option;

import javax.swing.*;

import java.awt.event.*;
import java.io.IOException;

public class OptionMenu extends JMenu {

    public OptionMenu(Option option) {
        super(option.getTitle());

        if(option==Option.SHIFT){
            MouseListener MOUSE_LISTENER = new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    String text = ((JMenuItem)e.getSource()).getText();
                    if(CalType.NORMAL.getType().equals(text)){
                        MainFrame.setTopPanel(0);
                    }else if(CalType.PLUS.getType().equals(text)){
                        MainFrame.setTopPanel(1);
                    }else{
                        MainFrame.setTopPanel(2);
                    }
                }

            };
            JMenuItem normal = new JMenuItem(CalType.NORMAL.getType());
            normal.addMouseListener(MOUSE_LISTENER);
            JMenuItem plus = new JMenuItem(CalType.PLUS.getType());
            plus.addMouseListener(MOUSE_LISTENER);
            JMenuItem ultra = new JMenuItem(CalType.ULTRA.getType());
            ultra.addMouseListener(MOUSE_LISTENER);
            this.add(normal);
            this.add(plus);
            this.add(ultra);

        }else if(option == Option.HELP){
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    String path = System.getProperty("user.dir")+"\\README.html";
                    ProcessBuilder builder = new ProcessBuilder("cmd","/C","start "+path);
                    try {
                        builder.start();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

            });
        }else{
            MouseListener MOUSE_LISTENER = new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    String text = ((JMenuItem)e.getSource()).getText();
                    if("prev".equals(text)){
                        Constant.doPrevHistory();
                    }else{
                        Constant.doNextHistory();
                    }
                }

            };
            this.setToolTipText("按键盘功能区的PageUp或PageDown来浏览历史");
            JMenuItem prev = new JMenuItem("prev");
            prev.addMouseListener(MOUSE_LISTENER);
            prev.setToolTipText("浏览上一条记录PageUp");
            JMenuItem next = new JMenuItem("next");
            next.addMouseListener(MOUSE_LISTENER);
            next.setToolTipText("浏览下一条记录PageDown");
            this.add(prev);
            this.add(next);

        }
    }


}
