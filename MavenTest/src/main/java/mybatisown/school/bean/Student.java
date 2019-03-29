package mybatisown.school.bean;

import java.io.Serializable;

/**
 * @author 阿劼
 */
public class Student implements Serializable {

    private Integer id;

    private String name;

    private Byte age;

    private String sex;

    private SchoolClass schoolclass;

    private Address address;

    public SchoolClass getSchoolclass() {
        return schoolclass;
    }

    public void setSchoolclass(SchoolClass schoolclass) {
        this.schoolclass = schoolclass;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
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
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", schoolclass=" + schoolclass +
                ", address=" + address +
                '}';
    }
}