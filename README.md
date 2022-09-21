# 项目了解
## 1.启动
本项目是一个基于Java Swing的基础计算器小Demo，如果想要使用该项目请使用`Starter`类来进行启动
```java 
public class Main{
    public static void main(String[] args) {
        Starter starter = new Starter(null);
        starter.start();
    }
}
```
## 2.接口了解
在`Starter`类的构造方法中我们可以传入自定义的`ICalculate`接口的实现类实例，下面为`ICalculate`接口的代码
```java
public interface ICalculate {
    String calculate(Queue<ValueOrCommand> queue);
}
```
上述的`ICalculate`接口中的`calculate`方法用来实现计算器计算的过程，最后输出相应的结果。其中的`Queue<ValueOrCommand>`存储用户输入的计算表达式，如下图所示，计算表达式被分解为相应的Value或Command,然后被存放至队列中 <br/>
![img](https://gitlab.com/littledot123/picture/uploads/34b8e4aa18a962e04350f9105f1a290e/202209091417867.png)



## 3.配置
默认情况下，`Starter`的构造方法在传入`null`参数的情况下将调用默认的`ICalculate`接口实现类`DefaultCalculate`。不过该实现类仅作为一个演示例子，实现功能并不完备，因此需要你自行实现`ICalculate`接口来完成其中的逻辑处理。
> 为了统一符号，在代码处理过程中请使用下面的`Command`类的静态常量
```java
public class Command{
    // 分别为加、减、乘、除
    public static final char ADD = '+';
    public static final char SUB = '-';
    public static final char MUL = '×';
    public static final char DIV = '÷';
}
```