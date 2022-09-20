package com.kit.outlook.calculate;

import com.kit.outlook.constant.template.Command;
import com.kit.outlook.constant.template.ValueOrCommand;

import java.util.Queue;


public class DefaultCalculate implements ICalculate{
    @Override
    public boolean isHardLevel() {
        return false;
    }

    @Override
    public String hardCalculate(String expression) {
        return null;
    }

    @Override
    public String calculate(Queue<ValueOrCommand> queue) {
        if(queue.isEmpty()){
            return "请提供参数";
        }
        if(queue.size()!=3){
            return "参数限制为3个";
        }

        ValueOrCommand vc1 = queue.poll();
        ValueOrCommand vc2 = queue.poll();
        ValueOrCommand vc3 = queue.poll();

        if(vc1.isValue() && !vc2.isValue()  && vc3.isValue()){
            int result = 0;
            int v1 = vc1.getValue();
            int v3 = vc3.getValue();
            switch (vc2.getCommand()){
                case Command.ADD:
                    result = v1+v3;
                    break;
                case Command.SUB:
                    result = v1-v3;
                    break;
                case Command.MUL:
                    result = v1*v3;
                    break;
                case Command.DIV:
                    if(v3==0){
                        return "被除数不能为0";
                    }else{
                        return String.valueOf((double) v1/v3);
                    }
            }
            return String.valueOf(result);
        }
        return "参数错误";

    }


}
