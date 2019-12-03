package xfuxi;

interface Ime{
    /**
     * 静态方法，由接口名直接调用
     */
    static void fun() {
        System.out.println("静态方法");
    }

    /**
     * default修饰的普通方法，可以在子类中覆写
     */
    default void fun1() {
        System.out.println(1);
    }

    /**
     * 接口方法，不能为protected或private
     */
    void fun2();
}

class IMeImpl implements Ime{
    @Override
    public void fun1() {
        System.out.println("fun");
    }

    @Override
    public void fun2() {
        System.out.println(10);
    }
}

/**
 *
 * @author 阿劼
 */
public class Test{
    public static void main(String[] args) {
        Ime.fun();
        Ime a = new IMeImpl();
        a.fun1();
        a.fun2();
        try{
            System.out.println("fdf");
            System.out.println(2 / 0);
        }
        catch (Exception e){
            System.out.println("error");
        }
            finally
         {
            System.out.println(132);
        }
        System.out.println("zhende ");
    }
}

