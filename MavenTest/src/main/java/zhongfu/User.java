package zhongfu;

import java.io.*;
import java.util.Date;

/**
 * description：序列化与反序列化的验证
 *
 * @author ajie
 * data 2018/12/10 16:14
 */
class User implements Serializable, Cloneable {
    private String name;
    private int age;
    private Date birthday;
    private String gender;

    public User(String name, int age, Date birthday, String gender) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
        this.gender = gender;
    }

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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "[" + name + "---" + age + "---" + birthday + "---" + gender + "]";
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, CloneNotSupportedException, IllegalAccessException, InstantiationException {
        User user = new User("ajie", 18,  new Date(118, 12, 10),"7879");
//        writeObj(user);
//        readObj();
//
//        User user1 = (User) user.clone();
//        System.out.println(user1);

        Class clazz = User.class.getClass().newInstance();
        System.out.println(clazz);

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
        User user = (User) ois.readObject();
        System.out.println(user);
        ois.close();
    }

    /**
     * 对象序列化
     *
     * @throws IOException
     */
    public static void writeObj(User user) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("obj.object"));
        //对象序列化，被序列化的对象必须实现Serializable接口。
        oos.writeObject(user);
        oos.close();
    }
}

