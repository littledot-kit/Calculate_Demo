package com.kit.outlook.constant;

import com.kit.outlook.constant.template.Command;
import com.kit.outlook.constant.template.ValueFriend;
import com.kit.outlook.constant.template.ValueOrCommand;
import com.kit.outlook.exception.ExpressionException;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;



import static com.kit.outlook.constant.ResolveTool.ResolveNegative.*;


public class ResolveTool {

    private static final ResolveTool RESOLVE_TOOL = new ResolveTool();



    public  Queue<ValueOrCommand> getQueue(String expression){


        Queue<ValueOrCommand> queue = new LinkedList<>();
//        Deque<ValueOrCommand> deque = new LinkedList<>();
        Deque<Integer> stack = new LinkedList<>();
        // 是否开启解析带符号数
        // 开启场景： -1+2【开始第一个数字】、34+(-2)【中括号里面第一个数字】【后续添加】
        ResolveNegative resolveNegativeNumber = (expression.charAt(0) == Command.SUB)?
                OPEN: (expression.charAt(0)== Command.ADD)? OPEN_ADD:CLOSE;


        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            switch (c){

                case Command.ADD:
                    if(!stack.isEmpty()){
                        addNumberToQueue(queue,buildNum(stack),resolveNegativeNumber);
                        resolveNegativeNumber = CLOSE;
                    }
                    if(resolveNegativeNumber==OPEN_ADD){
                        continue;
                    }else if(resolveNegativeNumber==PAUSE){
                        resolveNegativeNumber=OPEN_ADD;
                        continue;
                    }
                    queue.add(new ValueOrCommand(null,c));
                    break;
                case Command.SUB:
                    if(!stack.isEmpty()){
                        addNumberToQueue(queue,buildNum(stack),resolveNegativeNumber);
                        resolveNegativeNumber = CLOSE;
                    }
                    if(resolveNegativeNumber==OPEN){
                        continue;
                    }else if(resolveNegativeNumber==PAUSE){
                        resolveNegativeNumber= OPEN;
                        continue;
                    }
                    queue.add(new ValueOrCommand(null,c));
                    break;


                case Command.DIV:
                case Command.MUL:
                case Command.RBK:
                    if(!stack.isEmpty()){
                        addNumberToQueue(queue,buildNum(stack),resolveNegativeNumber);
                        resolveNegativeNumber = CLOSE;
                    }
                    queue.add(new ValueOrCommand(null,c));
                    break;

                case Command.LBK:
                    resolveNegativeNumber = PAUSE;
                    queue.add(new ValueOrCommand(null,c));
                    if(!stack.isEmpty()){
                        throw new ExpressionException("符号\"(\" 不能有数字");
                    }
                    break;

                default:
                    stack.offerFirst(c-'0');
            }

        }

        if(!stack.isEmpty()){
            addNumberToQueue(queue,buildNum(stack),resolveNegativeNumber);
        }

        return queue;
    }

    private static void addNumberToQueue(Queue<ValueOrCommand> queue,int num,ResolveNegative resolveNegativeNumber){
        if(resolveNegativeNumber==OPEN){
            queue.add(new ValueOrCommand(-num,null));
        }else{
            queue.add(new ValueOrCommand(num,null));
        }
    }

    public static Queue<ValueOrCommand> obtainQueue(String expression){
        return RESOLVE_TOOL.getQueue(expression);
    }
    public static boolean isCommand(char c){
        return c==Command.ADD || c==Command.SUB || c==Command.MUL || c==Command.DIV || c==Command.LBK || c==Command.RBK;
    }
    public static boolean isValueFriend(char c){
        return c== ValueFriend.DOT || c==ValueFriend.PER;
    }
    public static boolean isNumber(char c){
        return c >='0' && c<='9';
    }
    private int buildNum(Deque<Integer> stack){
        int num = 0;
        int x = 1;

        while(!stack.isEmpty()){
            num+=stack.pollFirst()*x;
            x*=10;
        }
        return num;
    }


    enum ResolveNegative{
        OPEN,CLOSE,PAUSE,OPEN_ADD
    }

}
