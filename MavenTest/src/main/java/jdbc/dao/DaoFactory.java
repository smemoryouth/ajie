package jdbc.dao;

import java.io.InputStream;
import java.util.Properties;

/**
 * description：Dao工厂
 *
 * @author ajie
 * data 2018/8/13 21:13
 */
class DaoFactory {
    private static EmployeeDao employeeDao = null;
    private static DaoFactory daoFactory = new DaoFactory();

    private DaoFactory() {
        try {
            Properties prop = new Properties();
            InputStream inStream = DaoFactory.class.getClassLoader()
                    .getResourceAsStream("daoconfig.properties");
            prop.load(inStream);
            String employeeDaoClass = prop.getProperty("employeeDaoClass");
            Class clazz = Class.forName(employeeDaoClass);
            employeeDao = (EmployeeDao) clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    static DaoFactory getDaoFactory() {
        return daoFactory;
    }

    EmployeeDao getEmployeeDao() {
        return employeeDao;
    }
}
