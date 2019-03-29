package jdbc.dao;

import java.util.Date;

/**
 * description：对表Employee的操作
 *
 * @author ajie
 * data 2018/8/13 11:46
 */
public class Employee {
    private String name;
    private int deptno;
    private Date hiredate;
    private double sal;

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    int getDeptno() {
        return deptno;
    }

    void setDeptno(int deptno) {
        this.deptno = deptno;
    }

    Date getHiredate() {
        return hiredate;
    }

    void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    double getSal() {
        return sal;
    }

    void setSal(double sal) {
        this.sal = sal;
    }
}
