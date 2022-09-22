package com.kit.outlook.constant;

import com.kit.outlook.calculate.ICalculate;
import com.kit.outlook.component.OperatorButton;
import com.kit.outlook.constant.template.Operator;

import javax.swing.*;
import java.util.*;
import java.util.logging.Logger;


public class Constant {
    static final Logger LOGGER = Logger.getLogger(Constant.class.getName());
    // 默认填充字符
    private static final String EMPTY_STR_INPUT = " ";
    private static final String EMPTY_STR_RESULT = "0";
    // 相关控制的 视图组件
    public static final JLabel RESULT = new JLabel(EMPTY_STR_RESULT);
    public static final JLabel INPUT = new JLabel(EMPTY_STR_INPUT);
    public static final JLabel MIDDLE = new JLabel(" ");
    public static final OperatorButton OPERATOR_BUTTON = new OperatorButton(Operator.UNSET);
    // 解析表达式（核心组件，可由用户自行实现，也可使用默认实现）
    private static ICalculate iCalculate;
    // 位运算 相关
    private static final int BITS = Feature.values().length;
    private static final boolean[] featureStatus = new boolean[BITS];
    // 历史记录 相关
    private static final int HISTORY_MAX_LIMIT = 50;
    private static int CURRENT_HISTORY_POSITION = 0;
    private static int CURRENT_MAX_INDEX = -1;
    private static final LinkedList<HistoryEntry> HISTORY = new LinkedList<>();

    private static boolean ACCEPT_RESULT = true;
    private static boolean CLEAR_RESULT  = false;
    private static boolean ERROR_RESULT  = false;




    static {
        for (Feature value : Feature.values()) {
            featureStatus[value.ordinal()] = value.getDefaultOpenStatus();
        }
    }

    // 配置 核心组件
    public static void setCalculate(ICalculate calculate){
        Constant.iCalculate = calculate;
    }
    // 相关 中间操作
    public static void doEmpty(){
        MIDDLE.setText(" ");
        if(isOpen(Feature.CALCULATE_WITH_LOGGING) && !INPUT.getText().equals(EMPTY_STR_INPUT)){
            LOGGER.info("The expression is empty now");
        }
        INPUT.setText(EMPTY_STR_INPUT);
        RESULT.setText(EMPTY_STR_RESULT);
        if(!CLEAR_RESULT){
            ACCEPT_RESULT = true;
        }


    }
    public static void doAddition(char valueOrCommand){
        MIDDLE.setText(" ");
        if(ResolveTool.isCommand(valueOrCommand)){
            if(ERROR_RESULT){
                RESULT.setText(EMPTY_STR_RESULT);
                INPUT.setText(EMPTY_STR_INPUT);
                ERROR_RESULT = false;
            }else{
                INPUT.setText(RESULT.getText()+valueOrCommand);
                ACCEPT_RESULT = true;
            }
        }else if(CLEAR_RESULT){
            INPUT.setText(EMPTY_STR_INPUT);
            RESULT.setText(String.valueOf(valueOrCommand));
        }else if(ACCEPT_RESULT){
            RESULT.setText(String.valueOf(valueOrCommand));
            ACCEPT_RESULT = false;
        }else{
            RESULT.setText(RESULT.getText()+valueOrCommand);
        }
        CLEAR_RESULT = false;
        CURRENT_HISTORY_POSITION = 0;
    }
    public static void doDelete(){
        MIDDLE.setText(" ");
        String str = RESULT.getText();
        if(CLEAR_RESULT){
            INPUT.setText(EMPTY_STR_INPUT);
            return;
        }
        if(str.length()==1){
            if(!str.equals(EMPTY_STR_RESULT)){
                RESULT.setText(EMPTY_STR_RESULT);
            }
        }else{
            RESULT.setText(str.substring(0,str.length()-1));
        }
    }
    public static void doFinal(){
        // TODO:处理多次调用doFinal()的情况
        if(CLEAR_RESULT){
            return;
        }
        String input_str = INPUT.getText();
        if(EMPTY_STR_INPUT.equals(input_str)){
            INPUT.setText(RESULT.getText());
        }else{
            INPUT.setText(input_str+RESULT.getText());
        }
        MIDDLE.setText("=");

        CLEAR_RESULT = true;

        String result;
        if(iCalculate.isHardLevel()){
            result = iCalculate.hardCalculate(INPUT.getText());
        }else{
            result = iCalculate.calculate(ResolveTool.obtainQueue(INPUT.getText()));
        }
        RESULT.setText(result);
        // TODO:处理 数据处理异常的情况
        if(!ParseTool.isNumberString(result)){
            ERROR_RESULT = true;
        }
        CURRENT_HISTORY_POSITION = 0;
        if(isOpen(Feature.CALCULATE_WITH_LOGGING)){
            LOGGER.info("Calculate ["+INPUT.getText()+"]"+" To "+result);
        }
        HISTORY.addFirst(new HistoryEntry(INPUT.getText(), result));
        CURRENT_MAX_INDEX++;
        if(HISTORY.size()> HISTORY_MAX_LIMIT){
            HistoryEntry entry = HISTORY.removeLast();
            CURRENT_MAX_INDEX--;
            if(isOpen(Feature.CALCULATE_WITH_LOGGING)){
                LOGGER.info("History overflow, delete [ "+entry.expression+" => "+entry.result+" ]");
            }
        }
    }


    public static void doPrevHistory(){
        if(CURRENT_HISTORY_POSITION>=CURRENT_MAX_INDEX){
            return;
        }
        CURRENT_HISTORY_POSITION++;
        HistoryEntry entry = HISTORY.get(CURRENT_HISTORY_POSITION);
        if(entry !=null){
            INPUT.setText(entry.expression);
            RESULT.setText(entry.result);
            MIDDLE.setText("=");
        }
    }
    public static void doNextHistory(){
        if(CURRENT_HISTORY_POSITION<=0){
            return;
        }
        CURRENT_HISTORY_POSITION--;
        HistoryEntry entry = HISTORY.get(CURRENT_HISTORY_POSITION);
        if(entry!=null){
            INPUT.setText(entry.expression);
            RESULT.setText(entry.result);
            MIDDLE.setText("=");
        }
    }
    public static void doNothing(){
        RESULT.setText("还没有想好做森么呢...");
    }
    public static void doNextPanel(){
        MainFrame.setNextPanel();
    }

    // 配置 功能
    public static void configure(Feature feature,boolean status){
        featureStatus[feature.ordinal()] = status;
    }
    public static boolean isOpen(Feature feature){
        return featureStatus[feature.ordinal()];
    }

    private static class HistoryEntry{
        public String expression;
        public String result;
        public HistoryEntry(String expression,String result){
            this.expression = expression;
            this.result = result;
        }
    }



}
