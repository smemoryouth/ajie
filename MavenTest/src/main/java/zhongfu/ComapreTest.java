package zhongfu;

import java.util.*;

class Man implements Comparable {

    private int age;

    private String name;

    public Man(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Object o) {
        return this.getAge() - ((Man) o).getAge();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Man man = (Man) o;
        return age == man.age &&
                Objects.equals(name, man.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(age, name);
    }

    @Override
    public String toString() {
        return "Man[" +
                "age=" + age +
                ", name='" + name + '\'' +
                ']';
    }
}


class WoMan {
    private int weight;
    private String name;

    public WoMan(int weight, String name) {
        this.weight = weight;
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        WoMan woMan = (WoMan) o;
        return weight == woMan.weight &&
                Objects.equals(name, woMan.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(weight, name);
    }

    @Override
    public String toString() {
        return "WoMan[" +
                "weight=" + weight +
                ", name='" + name + '\'' +
                ']';
    }
}

class WoManCompare implements Comparator<WoMan> {
    @Override
    public int compare(WoMan o1, WoMan o2) {
        return o1.getWeight() - o2.getWeight();
    }
}
/**
 * description：比较器的使用
 *
 * @author ajie
 * data 2018/12/5 14:17
 */
public class ComapreTest {

    public static void main(String[] args){
        WoManCompare compare = new WoManCompare();
        Map<Man, String> man = new TreeMap<>();
        Map<WoMan, String> woMan = new TreeMap<>(compare);
        man.put(new Man(18, "张三"), "a");
        man.put(new Man(19, "张四"), "b");
        man.put(new Man(16, "张五"), "c");
        man.put(new Man(13, "张六"), "d");
        woMan.put(new WoMan(50, "李三"), "1");
        woMan.put(new WoMan(52, "李四"), "2");
        woMan.put(new WoMan(48, "李五"), "3");
        woMan.put(new WoMan(60, "李六"), "4");
        for (Map.Entry<Man, String> entry : man.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        for (Map.Entry<WoMan, String> entry : woMan.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

}
