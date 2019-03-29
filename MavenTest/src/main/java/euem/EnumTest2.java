package euem;
enum ColorEnum2{
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
    ColorEnum2(String title){
        this.title = title;
    }

   @Override
    public String toString(){
        return this.title;
    }
}
/**
 * description：为枚举增加别的属性
 *
 * @author ajie
 * data 2018/6/4
 */
public class EnumTest2 {
    public static void main(String[] args){
        System.out.println(ColorEnum2.BLUE);
    }
}
