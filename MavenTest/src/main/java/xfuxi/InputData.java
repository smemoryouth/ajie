package xfuxi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


class Input {
    public static String s = "";
    public static void input(){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            s = bufferedReader.readLine();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public static int getInt(){
        input();
        return Integer.parseInt(s);
    }
}

class Result{
    void print(int d){
        System.out.println(d + "的平方:" + d * d);
        System.out.println(d + "的立方:" + d * d * d);
    }
}
/**
 * description：键盘录入一个数，输出平方和立方
 *
 * @author ajie
 * data 2018/9/4 15:25
 */
public class InputData{
    public static void main(String[] args){
        Result result = new Result();
        System.out.println("请输入一个整数：");
        int a = Input.getInt();
        result.print(a);
    }
}



