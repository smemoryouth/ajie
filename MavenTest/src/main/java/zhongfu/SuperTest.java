package zhongfu;

import java.util.Date;

/**
 * description：牛客网编程题：super的应用
 * super.getClass().getName()的结果是包名.类名
 *
 * @author ajie
 * data 2018/12/27 15:57
 */
public class SuperTest extends Date {
    private static final long serialVersionUID = 1L;

    private void test(){
        System.out.println(super.getClass().getName());
    }

    public static void main(String[]args){
        new SuperTest().test();
    }
}
