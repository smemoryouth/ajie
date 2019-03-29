package zhongfu;

import java.util.LinkedList;
import java.util.List;

class Student {
    private String name;
    private int age;
    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", age=" + age + ", sex=" + sex + "]";
    }
}

class School implements Cloneable {

    private String schoolName;
    private int stuNums;
    private Student stu;

    String getSchoolName() {
        return schoolName;
    }

    void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public int getStuNums() {
        return stuNums;
    }

    void setStuNums(int stuNums) {
        this.stuNums = stuNums;
    }

    Student getStu() {
        return stu;
    }

    void setStu(Student stu) {
        this.stu = stu;
    }

    @Override
    protected School clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return (School) super.clone();
    }

    @Override
    public String toString() {
        return "School [schoolName=" + schoolName + ", stuNums=" + stuNums + ", stu=" + stu + "]";
    }
}

/**
 * description：验证深浅拷贝
 *
 * @author ajie
 * data 2018/11/28 14:01
 */
public class LinkedListTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        List<Integer> list1 = new LinkedList<>();
        List<Integer> list2 = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            list1.add(i);
            list2.add(i);
        }

//        Iterator<Integer> it = list1.listIterator(0);
//        int count = 0;
//        while (it.hasNext()){
//            it.next();
//            if (count == 32){
//                ((ListIterator<Integer>) it).set(100);
//            }
//            if (count == 37){
//                it.remove();
//            }
//            count++;
//        }
//        System.out.println();
//        for (Integer i : list1){
//            System.out.print(i + " ");
//        }
//        System.out.println();
//        for (Integer i : list2){
//            System.out.print(i + " ");
//        }
//        System.out.println();
//        System.out.println(list1.get(50));

//        List<Integer> list = new LinkedList<>();
//        long l1 = System.currentTimeMillis();
//        for (int i = 0; i < 10000000; i++) {
//            list.add(0, i);
//        }
//        long l2 = System.currentTimeMillis();
//        System.out.println("LinkedList:" + (l2 - l1));
//
//        List<Integer> list3 = new ArrayList<>();
//        l1 = System.currentTimeMillis();
//        for (int i = 0; i < 10000000; i++) {
//            list3.add(i);
//        }
//
//        l2 = System.currentTimeMillis();
//        System.out.println("ArrayList:" + (l2 - l1));

        School s1 = new School();
        s1.setSchoolName("实验小学");
        s1.setStuNums(100);
        Student stu1 = new Student();
        stu1.setAge(20);
        stu1.setName("zhangsan");
        stu1.setSex("男");
        s1.setStu(stu1);
        System.out.println("s1: " + s1 + " s1的hashcode:" + s1.hashCode()
                + "  s1中stu1的hashcode:" + s1.getStu().hashCode() + " schoolNmae: "
                + s1.getSchoolName().hashCode());
        School s2 = s1.clone();

        System.out.println("s2: " + s2 + " s2的hashcode:" + s2.hashCode()
                + " s2中stu1的hashcode:" + s2.getStu().hashCode() + " schoolNmae: "
                + s2.getSchoolName().hashCode());
    }
}
