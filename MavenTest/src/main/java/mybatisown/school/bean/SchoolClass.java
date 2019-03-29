package mybatisown.school.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author 阿劼
 */
public class SchoolClass implements Serializable {
    /* */
    private Integer id;

    /* */
    private String classname;

    /* */
    private Integer studentsnum;

    private List<Student> studentList;

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public Integer getStudentsnum() {
        return studentsnum;
    }

    public void setStudentsnum(Integer studentsnum) {
        this.studentsnum = studentsnum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("id=").append(id);
        sb.append(", classname=").append(classname);
        sb.append(", studentsnum=").append(studentsnum);
        sb.append("]");
        return sb.toString();
    }
}