package jdbc.classtest.dao;

import java.io.Serializable;

/**
 * description：user定义类
 *
 * @author ajie
 * data 2018/10/23 10:45
 */
public class User implements Serializable {
    private String name;
    private int age;
    private String  sex;
    private double score;

   User(String name, String sex, int age, double score){
       this.name = name;
       this.age = age;
       this.sex = sex;
       this.score = score;
   }

    public String getName() {
        return name;
    }

    int getAge() {
        return age;
    }

    String getSex() {
        return sex;
    }

    double getScore() {
        return score;
    }
}
