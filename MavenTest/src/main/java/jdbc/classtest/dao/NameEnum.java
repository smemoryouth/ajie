package jdbc.classtest.dao;

import java.util.Random;

/**
 * description：
 *
 * @author ajie
 * data 2018/10/23 12:24
 */
public enum NameEnum {
    /**
     * 姓名1
     */
    张三,
    /**
     * 姓名2
     */
    李四,
    /**
     * 姓名3
     */
    王五,
    /**
     * 姓名4
     */
    赵六
}

class GetName{
    static NameEnum randomName(){
        int pick = new Random().nextInt(NameEnum.values().length);
        return NameEnum.values()[pick];
    }
}
