package jdbc.classtest.dao;

import java.io.InputStream;
import java.util.Properties;

/**
 * description：UserDao工厂
 *
 * @author ajie
 * data 2018/10/23 11:33
 */
class UserDaoFactory {
    private static UserDao userDao = null;
    private static UserDaoFactory daoFactory = new UserDaoFactory();

    private UserDaoFactory() {
        try {
            Properties prop = new Properties();
            InputStream inStream = UserDaoFactory.class.getClassLoader()
                    .getResourceAsStream("userdaoconfig.properties");
            prop.load(inStream);
            String userDaoClass = prop.getProperty("userDaoClass");
            Class clazz = Class.forName(userDaoClass);
            userDao = (UserDao) clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    static UserDaoFactory getDaoFactory() {
        return daoFactory;
    }

    UserDao getUserDao() {
        return userDao;
    }
}
