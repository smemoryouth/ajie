package jdbc.classtest.dao;

import java.util.Random;

/**
 * description：
 *
 * @author ajie
 * data 2018/10/23 12:22
 */
public enum SexEnum {
    /**
     * 男性
     */
    男,
    /**
     * 女性
     */
    女
}

class GetSex{
    static SexEnum randomSex(){
        int pick = new Random().nextInt(SexEnum.values().length);
        return SexEnum.values()[pick];
    }
}
