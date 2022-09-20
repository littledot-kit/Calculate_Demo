package com.kit.outlook;

import com.kit.outlook.calculate.DefaultCalculate;
import com.kit.outlook.calculate.ICalculate;
import com.kit.outlook.constant.MainFrame;
import com.kit.outlook.constant.Constant;
import com.kit.outlook.constant.Feature;

import javax.swing.*;
import java.awt.*;

public class Starter {
    public Starter(ICalculate calculate){
        if(calculate==null){
            Constant.setCalculate(new DefaultCalculate());
        }else{
            Constant.setCalculate(calculate);
        }

    }

    public void start(){
        EventQueue.invokeLater(()->{
            MainFrame mainFrame = new MainFrame();
            mainFrame.setTitle("一个计算器小demo");
            mainFrame.setLocationRelativeTo(null);
            mainFrame.setResizable(false);
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setVisible(true);
        });

    }

    public void registry(Feature feature){
        Constant.configure(feature,true);
    }
    public void disable(Feature feature){
        Constant.configure(feature,false);
    }



    public static void main(String[] args) {
    
        Starter starter = new Starter(null);
        starter.registry(Feature.USE_MORE_BUTTON_PLUS);
        starter.registry(Feature.IMMEDIATE_USE_AFTER_START);
        starter.registry(Feature.INPUT_NOT_EMPTY);
        starter.start();

    }

}
