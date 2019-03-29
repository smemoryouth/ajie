package collection;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * description：按年龄排序
 *
 * @author ajie
 * data 2018/7/11
 */
public class Person {
    private int id;
    private int age;


    public Person(int id, int age) {
        this.id = id;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "age = " + age + "; ID = " + id;
    }

    public static void main(String[] args) {
        // (o1, o2) -> o2.age - o1.age >lamda
        // Comparator.comparingInt(Person::getAge) 》功能型函数接口实现
        PriorityQueue<Person> priorityQueue = new PriorityQueue<>(10, Comparator.comparingInt(o -> o.age));
        priorityQueue.add(new Person(1, 20));
        priorityQueue.add(new Person(0, 21));
        priorityQueue.add(new Person(4, 18));
        priorityQueue.add(new Person(2, 22));
        priorityQueue.add(new Person(6, 23));
        priorityQueue.add(new Person(8, 26));
        priorityQueue.add(new Person(7, 24));
        int n = priorityQueue.size();
        for (int i = 0; i < n; i++) {
            System.out.println(priorityQueue.remove());
        }
    }
}
