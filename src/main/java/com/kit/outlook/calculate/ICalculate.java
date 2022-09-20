package com.kit.outlook.calculate;

import com.kit.outlook.constant.template.ValueOrCommand;

import java.util.Queue;

/**
 *  <code>ICalculate</code>接口<br/>
 *  为计算器实现计算过程的核心接口,主要有三个方法，其中<code>isHardLevel()</code>方法来选择难度级别 <br/>  <hr/>
 *  <li>Hard: <code>hardCalculate(String)</code></li>方法没有提供对原表达式（计算器界面输入的算式）解析的过程，这就意味着你需要自行来解析相应组件  <br/> <br/>
 *  <li>Normal: <code>calculate(Queue)</code></li> 方法已经为你解析好相应的表达式中的组件，你只需要实现相应的核心处理逻辑即可 <hr/>
 *  &nbsp;&nbsp;&nbsp;&nbsp;上述两个方法并不需要全部实现，这将根据你的<code>isHardLevel()</code>方法来抉择选择调用哪个，相应没有被调用的方法你只需要空实现即可  <br/>
 */


public interface ICalculate {
    boolean isHardLevel();
    String hardCalculate(String expression);
    String calculate(Queue<ValueOrCommand> queue);

}
