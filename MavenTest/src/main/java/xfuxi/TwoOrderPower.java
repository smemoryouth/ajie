package xfuxi;

class ResultSource{
    void print(int d){
        if(((d - 1) & d) == 0 && (d != 0)){
            System.out.println("是2的阶次");
        }else {
            System.out.println("不是2的阶次");
        }
    }
}
/**
 * description：判断一个数是不是2的阶次方数
 *
 * @author ajie
 * data 2018/9/4 15:39
 */
public class TwoOrderPower {
    public static void main(String[] args){
        ResultSource resultSource = new ResultSource();
        System.out.println("请输入一个整数：");
        int a = Input.getInt();
        resultSource.print(a);

    }
}
