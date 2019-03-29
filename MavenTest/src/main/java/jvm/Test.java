package jvm;

import org.apache.log4j.Logger;

/**
 * description：
 *
 * @author ajie
 * data 2018/11/5 19:30
 */
public class Test {

    private static Logger logger = Logger.getLogger(Test.class);

    private Object object;

    public static void main(String[] args){

        logger.info("jvm");
        Test a = new Test();
        Test b = new Test();

        a.object = b;
        b.object = a;

        a = null;
        b = null;

        System.out.println(a);
        System.out.println(b);

        System.out.println(a.object);
        System.out.println(b.object);

    }
}

