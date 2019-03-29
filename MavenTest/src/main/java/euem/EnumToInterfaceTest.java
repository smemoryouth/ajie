package euem;

interface Color{
    /**
     * 枚举元素的获取
     * @return
     */
    String getColor();
}
enum ColorEnum3 implements Color{
    /**
     * 颜色
     */
    RED("红色"),
    /**
     * 颜色
     */
    GREEN("绿色"),
    /**
     * 颜色
     */
    BLUE("蓝色");
    /**
     * 属性
     */
    private String title;

    /**
     * 枚举的构造方法是private的
     * @param title 属性参数
     */
    ColorEnum3(String title){
        this.title = title;
    }

    @Override
    public String toString(){
        return this.title;
    }

    @Override
    public String getColor() {
        return this.title;
    }
}

/**
 * description：枚举实现接口，每一个枚举对象都是接口对象
 *
 * @author ajie
 * data 2018/6/4
 */
public class EnumToInterfaceTest {
    public static void main(String[] args){
        Color color = ColorEnum3.BLUE;
        System.out.println(color.getColor().length());
        System.out.println(color);
    }
}
