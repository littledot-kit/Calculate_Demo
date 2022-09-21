package com.kit.outlook.constant;

import com.kit.outlook.calculate.ICalculate;
import com.kit.outlook.component.OperatorButton;
import com.kit.outlook.constant.template.Operator;

import javax.swing.*;
import java.util.*;
import java.util.logging.Logger;


public class Constant {
    static final Logger LOGGER = Logger.getLogger(Constant.class.getName());
    // 相关控制的 视图组件
    public static final JLabel RESULT = new JLabel("请按C键开启");
    public static final JLabel INPUT = new JLabel(" ");
    // 解析表达式（核心组件，可由用户自行实现，也可使用默认实现）
    private static ICalculate iCalculate;
    // 开启标志
    static boolean OPEN_FLAG = false;

    // 位运算 相关
    private static final int BITS = Feature.values().length;
    private static final boolean[] featureStatus = new boolean[BITS];
//    static int TotalFeature = 1<<(BITS-1);
    // 历史记录 相关
    private static final int HISTORY_MAX_LIMIT = 50;
    private static int CURRENT_HISTORY_POSITION = 0;
    private static int CURRENT_MAX_INDEX = -1;
    private static final LinkedList<HistoryEntry> HISTORY = new LinkedList<>();
    // 后追\覆写 模式
    private static boolean INPUT_MODE = false;
    private static boolean OPEN_AFTER_RESULT = false;
    // 默认填充字符
    private static String EMPTY_STR = " ";



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
    public static void doEmptyOrOpen(){

        OPEN_FLAG = true;
        if(isOpen(Feature.CALCULATE_WITH_LOGGING) && !INPUT.getText().equals(EMPTY_STR)){
            LOGGER.info("The expression is empty now");
        }
        INPUT.setText(EMPTY_STR);
        if(RESULT.getText().equals("请按C键开启")){
            RESULT.setText(" ");
        }


    }
    public static void doAddition(char valueOrCommand){
        if(OPEN_FLAG){
            if(INPUT_MODE && OPEN_AFTER_RESULT){
                if(ResolveTool.isNumber(valueOrCommand)){
                    INPUT.setText(String.valueOf(valueOrCommand));
                }

                OPEN_AFTER_RESULT = false;
            }else{
                String str = INPUT.getText();
                if(EMPTY_STR.equals(str)){
                    if(Constant.isOpen(Feature.INPUT_NOT_EMPTY)){
                        // "0"
                        if(ResolveTool.isNumber(valueOrCommand)){
                            INPUT.setText(String.valueOf(valueOrCommand));
                        }else{
                            INPUT.setText(str+valueOrCommand);
                        }
                    }else{
                        // " "
                        INPUT.setText(String.valueOf(valueOrCommand));
                    }
                }else{
                    if(" ".equals(str)){
                        INPUT.setText(String.valueOf(valueOrCommand));
                    }else{
                        INPUT.setText(str+valueOrCommand);
                    }
                }
            }
            CURRENT_HISTORY_POSITION = 0;
        }
    }
    public static void doDelete(){
        if(OPEN_FLAG){
            String str = INPUT.getText();
            if(str.length()==1){
                if(!str.equals(EMPTY_STR)){
                    INPUT.setText(EMPTY_STR);
                }
            }else{
                INPUT.setText(str.substring(0,str.length()-1));
            }
        }
    }
    public static void doFinal(){
        if(OPEN_FLAG){
            if(INPUT.getText().equals(" ")) return;
            String result;
            if(iCalculate.isHardLevel()){
                result = iCalculate.hardCalculate(INPUT.getText());
            }else{
                result = iCalculate.calculate(ResolveTool.obtainQueue(INPUT.getText()));
            }
            RESULT.setText(result);
            if(INPUT_MODE){
                OPEN_AFTER_RESULT = true;
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
    }
    public static void doPrevHistory(){
        if (OPEN_FLAG){
            if(CURRENT_HISTORY_POSITION>=CURRENT_MAX_INDEX){
                return;
            }
            CURRENT_HISTORY_POSITION++;
            HistoryEntry entry = HISTORY.get(CURRENT_HISTORY_POSITION);
            if(entry !=null){
                INPUT.setText(entry.expression);
                RESULT.setText(entry.result);
            }
        }
    }
    public static void doNextHistory(){
        if(OPEN_FLAG){
            if(CURRENT_HISTORY_POSITION<=0){
                return;
            }
            CURRENT_HISTORY_POSITION--;
            HistoryEntry entry = HISTORY.get(CURRENT_HISTORY_POSITION);
            if(entry!=null){
                INPUT.setText(entry.expression);
                RESULT.setText(entry.result);

            }
        }
    }
    public static void doNothing(){
        if(OPEN_FLAG){
            RESULT.setText("还没有想好做森么呢...");
        }
    }
    public static void doSetOrUnset(OperatorButton operatorButton){
        if(OPEN_FLAG){
            INPUT_MODE = !INPUT_MODE;
            if(INPUT_MODE){
                operatorButton.setText(String.valueOf(Operator.SET.toOperator()));
            }else{
                operatorButton.setText(String.valueOf(Operator.UNSET.toOperator()));
            }
            if(Constant.isOpen(Feature.CALCULATE_WITH_LOGGING)){
                LOGGER.info("Toggle Mode to [ "+(INPUT_MODE? "Cover":"Append")+" ]");
            }
        }
    }
    public static void doNextPanel(){
        MainFrame.setNextPanel();
    }

    // 配置 功能
    public static void configure(Feature feature,boolean status){
        featureStatus[feature.ordinal()] = status;
        if (feature == Feature.INPUT_NOT_EMPTY) {
            EMPTY_STR = status ? "0" : " ";
        }
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
