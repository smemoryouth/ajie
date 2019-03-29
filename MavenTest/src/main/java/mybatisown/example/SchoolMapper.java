package mybatisown.example;

import org.apache.ibatis.annotations.Select;

/**
 * description：
 *
 * @author ajie
 * data 2018/11/5 10:08
 */
public interface SchoolMapper {
    @Select("SELECT * FROM employee")
    mybatisown.employee.bean.Employee getAllEmployee();
}
