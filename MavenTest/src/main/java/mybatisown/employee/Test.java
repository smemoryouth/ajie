package mybatisown.employee;

import mybatisown.employee.bean.Employee;
import mybatisown.employee.dao.EmployeeDao;
import mybatisown.employee.mybatisutil.Utils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;


import java.util.List;

/**
 * description：
 *
 * @author ajie
 * data 2018/10/29 16:19
 */
public class Test {
    private static Logger logger = Logger.getLogger(Test.class);
    public static void main(String[] args){
        System.out.println("This is println message. yeah");

        // 记录debug级别的信息
        logger.debug("This is debug message.");
        // 记录info级别的信息
        logger.info("This is info message.");
        // 记录error级别的信息
        logger.error("This is error message.");
    }
    @org.junit.Test
    public void getAllEmployeeTest(){
        SqlSession session = Utils.getSession();
        try{
            EmployeeDao userDao = session.getMapper(EmployeeDao.class);
            List<Employee> userList = userDao.getAllEmployee();
            for (Employee employee : userList) {
                System.out.println(employee);
            }
        } finally {
            session.close();
        }
    }
}
