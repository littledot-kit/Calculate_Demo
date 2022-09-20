import com.kit.outlook.constant.Feature;
import com.kit.outlook.constant.ResolveTool;
import com.kit.outlook.constant.template.ValueOrCommand;
import org.junit.jupiter.api.Test;

import java.util.Queue;

public class finalTest {

    public static final String[] STR = new String[]{
            "(1+1-1)","(-2+2-2)",
            "-2+3+4","2+3-4",
            "-2+(-2+4)","-2-(-2-4)","-3","(-34)","+4","(+4)","+4+(+4)"

    };

    public void printInfo(Queue<ValueOrCommand> queue){
        queue.forEach(demo->{
            if(demo.isValue()){
                System.out.print("\""+demo.getValue()+"\" ");
            }else{
                System.out.print("\""+demo.getCommand()+"\" ");
            }
        });
    }

    public void printNum(int num){
        System.out.println(Integer.toBinaryString(num));
    }

    @Test
    public void test(){
        ResolveTool tool = new ResolveTool();
        for (String str: STR){
            System.out.print(str+"  ==>  ");
            printInfo(tool.getQueue(str));
            System.out.println();
        }
    }

    @Test
    public void test3(){
        int i = (1<<3)-1;
        int j = 1<<1;
        System.out.println(i&j);

    }

    @Test
    public void test4(){
//        printNum(Constant.TotalFeature);
//
//        Constant.configure(Feature.EXPRESS_ONLY_NUMBER,true);
//        Constant.configure(Feature.CALCULATE_WITH_LOGGING,true);
//        printNum(Constant.TotalFeature);
//        Constant.configure(Feature.CALCULATE_WITH_LOGGING,false);
//        printNum(Constant.TotalFeature);
//        System.out.println(Constant.isOpen(Feature.EXPRESS_ONLY_NUMBER));
//        System.out.println(Constant.isOpen(Feature.CALCULATE_WITH_LOGGING));
    }

    @Test
    public void test5(){
        System.out.println(Feature.values().length);
    }



}
