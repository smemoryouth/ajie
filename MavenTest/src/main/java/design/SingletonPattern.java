package design;

/**
 * 饿汉式单例，Runtime类就是用饿汉式设计的
 */
class Person {
    /**
     * 构造方法私有化
     */
    private Person() {
    }

    /**
     * 对象静态用于静态方法对外暴露，加private防止外部修改
     */
    private static Person person = new Person();

    /**
     * 对外暴露方法
     *
     * @return 类对象
     */
    public static Person getPerson() {
        return person;
    }
}

/**
 * 懒汉式单例
 */
class Teacher {
    /**
     * 构造方法私有化
     */
    private Teacher() {
    }

    private static Teacher teacher = null;

    /**
     * 对外暴露方法,线程安全处理
     *
     * @return 类对象
     */
    public static Teacher getTeacher() {
        if (teacher == null) {
            synchronized (Teacher.class) {
                if (teacher == null) {
                    teacher = new Teacher();
                }
            }
        }
        return teacher;
    }
}

/**
 * description：单例设计模式
 *
 * @author ajie
 * data 2018/8/4 21:28
 */
public class SingletonPattern {
    public static void main(String[] args) {
        Person person = Person.getPerson();
        Person person2 = Person.getPerson();
        System.out.println(person == person2);
        Teacher teacher = Teacher.getTeacher();
        Teacher teacher1 = Teacher.getTeacher();
        System.out.println(teacher == teacher1);
    }
}
