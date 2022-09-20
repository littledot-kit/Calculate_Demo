package com.kit.outlook.constant;

import com.kit.outlook.component.OperatorButton;
import com.kit.outlook.component.ValueOrCommandButton;
import com.kit.outlook.constant.template.Command;
import com.kit.outlook.constant.template.Operator;
import com.kit.outlook.constant.template.ValueFriend;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;

public class MainFrame extends JFrame {
    private static  final Logger LOGGER = Logger.getLogger("MainFrame");
    public static final CardLayout CARD_LAYOUT = new CardLayout();
    public static final JPanel BOTTOM_PANEL = new JPanel();
    public static int num = 0;

    public MainFrame(){
        if(Constant.isOpen(Feature.IMMEDIATE_USE_AFTER_START)){
            Constant.OPEN_FLAG = true;
            Constant.RESULT.setText("Waiting");
        }
        setSize(500,600);

        Box box = Box.createVerticalBox();

        Constant.INPUT.setFont(new Font("Consolas",Font.BOLD,30));
        Constant.RESULT.setFont(new Font("黑体",Font.PLAIN,18));
        Constant.RESULT.setPreferredSize(new Dimension(300,50));
        Constant.INPUT.setPreferredSize(new Dimension(300,50));

        box.add(Constant.INPUT);
        box.add(new JLabel(" "));
        box.add(Constant.RESULT);
        JPanel topPanel = new JPanel();
        topPanel.add(box);


        BOTTOM_PANEL.setLayout(CARD_LAYOUT);
        BOTTOM_PANEL.add(setNormalPanel(),"normal");
        BOTTOM_PANEL.add(setMorePanel(),"more");
        BOTTOM_PANEL.add(setPlusPanel(),"plus");
        BOTTOM_PANEL.add(setSciencePanel(),"science");

        setTopPanel(-1);

        Box total = Box.createVerticalBox();
        total.add(topPanel);
        total.add(BOTTOM_PANEL);
        JPanel outerPanel = new JPanel();
        outerPanel.add(total);
        add(outerPanel);
        pack();

    }


    private  JPanel setPlusPanel(){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(6,4));
        // 1
        jPanel.add(new OperatorButton(Operator.CLS));
        jPanel.add(new OperatorButton(Operator.HISTORY));
        jPanel.add(new OperatorButton(Operator.UNSET));
        jPanel.add(new OperatorButton(Operator.DEL));
        // 2
        addValueOrCommandButtonsToPanel(jPanel,'7','8','9',Command.ADD);
        // 3
        addValueOrCommandButtonsToPanel(jPanel,'4','5','6',Command.SUB);
        // 3
        addValueOrCommandButtonsToPanel(jPanel,'1','2','3',Command.MUL);
        // 4
        addValueOrCommandButtonsToPanel(jPanel, ValueFriend.DOT,'0',ValueFriend.PER,Command.DIV);
        // 5
        jPanel.add(new OperatorButton(Operator.SHIFT));
        addValueOrCommandButtonsToPanel(jPanel,Command.LBK,Command.RBK);
        jPanel.add(new OperatorButton(Operator.EQUAL));

        return jPanel;
    }

    private JPanel setMorePanel(){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(5,4));
        // 1
        addValueOrCommandButtonsToPanel(jPanel,'7','8','9');
        jPanel.add(new OperatorButton(Operator.CLS));
        // 2
        addValueOrCommandButtonsToPanel(jPanel,'4','5','6',Command.ADD);
        // 3
        addValueOrCommandButtonsToPanel(jPanel,'1','2','3',Command.SUB);
        // 4
        addValueOrCommandButtonsToPanel(jPanel,Command.LBK,'0',Command.RBK,ValueFriend.DOT);
        // 5
        jPanel.add(new OperatorButton(Operator.SHIFT));
        addValueOrCommandButtonsToPanel(jPanel,Command.MUL,Command.DIV);
        jPanel.add(new OperatorButton(Operator.EQUAL));

        return jPanel;
    }

    private  JPanel setNormalPanel(){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(4,4));
        // 1
        addValueOrCommandButtonsToPanel(jPanel,'7','8','9');
        jPanel.add(new OperatorButton(Operator.CLS));
        // 2
        addValueOrCommandButtonsToPanel(jPanel,'4','5','6',Command.ADD);
        // 3
        addValueOrCommandButtonsToPanel(jPanel,'1','2','3',Command.SUB);
        // 4
        addValueOrCommandButtonsToPanel(jPanel,'0',Command.MUL,Command.DIV);
        jPanel.add(new OperatorButton(Operator.EQUAL));
        return jPanel;
    }

    private JPanel setSciencePanel(){
        JPanel jPanel = new JPanel();
        jPanel.add(new OperatorButton(Operator.SHIFT));
        System.out.println("计划开发中....");
        return jPanel;
    }


    public static void setTopPanel(int status){
        int temp = status;
        if(status==-1){
            if(Constant.isOpen(Feature.USE_MORE_BUTTON_SCIENCE)){
                CARD_LAYOUT.show(BOTTOM_PANEL,"science");
                temp = 3;
            }else if(Constant.isOpen(Feature.USE_MORE_BUTTON_PLUS)){
                CARD_LAYOUT.show(BOTTOM_PANEL,"plus");
                temp = 2;
            }else if(Constant.isOpen(Feature.USE_MORE_BUTTON)){
                CARD_LAYOUT.show(BOTTOM_PANEL,"more");
                temp = 1;
            }else{
                CARD_LAYOUT.show(BOTTOM_PANEL,"normal");
                temp = 0;
            }
        }else if(status==0){
            CARD_LAYOUT.show(BOTTOM_PANEL,"normal");
        }else if(status==1){
            CARD_LAYOUT.show(BOTTOM_PANEL,"more");
        }else if(status==2){
            CARD_LAYOUT.show(BOTTOM_PANEL,"plus");
        }else{
            CARD_LAYOUT.show(BOTTOM_PANEL,"science");
        }

        if(Constant.isOpen(Feature.CALCULATE_WITH_LOGGING)){
            String str;
            if(temp == 0){
                str = "Normal";
            }else if(temp == 1){
                str = "More";
            }else if(temp == 2){
                str = "Plus";
            }else{
                str = "Science";
            }
            LOGGER.info("Switch to "+str+" Panel");
        }


    }
    private void addValueOrCommandButtonsToPanel(JPanel panel,char ... valueOrCommand){
        for (char vc:valueOrCommand){
            panel.add(new ValueOrCommandButton(vc));
        }
    }

    static void setNextPanel(){
        num++;
        if(num>3){
            num = 0;
        }
        setTopPanel(num);
    }

}
