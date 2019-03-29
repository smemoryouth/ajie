package euem;

enum ColorEnum{
    /**
     * 颜色1
     */
    RED,
    /**
     * 颜色2
     */
    GREEN,
    /**
     * 颜色3
     */
    BLUE
}
/**
 * description：验证枚举类是继承于Enum抽象类
 *
 * @author ajie
 * data 2018/6/4
 */
public class EnumTest {
    public static void main(String[] args){
        System.out.println(ColorEnum.BLUE.name());
        System.out.println(ColorEnum.BLUE.ordinal());
        System.out.println("==========");
        for(ColorEnum tmp : ColorEnum.values()){
            System.out.println(tmp);
        }
    }
}
