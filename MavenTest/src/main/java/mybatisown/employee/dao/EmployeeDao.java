package mybatisown.employee.dao;

import mybatisown.employee.bean.Employee;

import java.util.List;

/**
 *
 * @author 阿劼
 */
public interface EmployeeDao {
    /**
     * 获取所有信息
     * @return
     */
//    @Select("select * from employee")
    List<Employee> getAllEmployee();

    /**
     * 根据id查找
     * @param id
     * @return
     */
//    @Select("select * from employee where id=${id}")
    Employee getEmployeeById(int id);

    /**
     * 根据姓名查找
     * @param ename
     * @return
     */
//    @Select("select * from employee where ename=${ename}")
    Employee getEmployeeByName(String ename);

    /**
     * 根据id删除
     * @param id
     */
//    @Delete("delete from employee where id=${id}")
    void deleteEmployeeById(int id);

}
