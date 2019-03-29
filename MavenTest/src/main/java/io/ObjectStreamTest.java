package io;

import java.io.*;

class Person implements Serializable {

    private static final long serialVersionUID = -4989671698370525057L;
    private transient String name;
    private int age;

    Person(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    int getAge() {
        return age;
    }
}

/**
 * description：对象序列化与反序列化
 *
 * @author ajie
 * data 2018/7/27 22:00
 */
public class ObjectStreamTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
		writeObj();
//        readObj();
    }

    /**
     * 对象的反序列化
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void readObj() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("obj.object"));
        //对象的反序列化。
        Person p = (Person) ois.readObject();
        System.out.println(p.getName() + ":" + p.getAge());
        ois.close();
    }

    /**
     * 对象序列化
     *
     * @throws IOException
     */
    public static void writeObj() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("obj.object"));
        //对象序列化，被序列化的对象必须实现Serializable接口。
        oos.writeObject(new Person("Alan", 20));
        oos.close();
    }
}
