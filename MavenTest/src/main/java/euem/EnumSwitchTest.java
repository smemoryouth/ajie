package euem;

import java.util.Random;

/**
 * 枚举类
 */
enum SexEnum{
    /**
     * 男性
     */
    MALE,
    /**
     * 女性
     */
    FEMALE

}
class S{
    public static SexEnum randomLetter() {
        int pick = new Random().nextInt(SexEnum.values().length);
        return SexEnum.values()[pick];
    }
}

/**
 * description：枚举中使用switch语句
 *
 * @author ajie
 * data 2018/6/4
 */
public class EnumSwitchTest {
    public static void main(String[] args){
//        switch (SexEnum.MALE){
//            case MALE:
//                System.out.println("男");
//                break;
//            case FEMALE:
//                System.out.println("女");
//                break;
//                default:
//                    break;
//        }

        System.out.println(S.randomLetter());
    }
}
