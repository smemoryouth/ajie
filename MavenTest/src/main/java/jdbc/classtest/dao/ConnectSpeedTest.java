package jdbc.classtest.dao;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Random;

/**
 * description：连接池与单个连接的速度比较
 *
 * @author ajie
 * data 2018/10/23 10:42
 */
public class ConnectSpeedTest {
    public static final int COUNT = 10000;
    @Test
    public void connectionPool() throws Exception {
        Random random = new Random();
        UserDao userDao = UserDaoFactory.getDaoFactory().getUserDao();
        long l1 = System.currentTimeMillis();
        // 创建表成功
//        int  is = userDao.createTable();
//        assert is ==1 : "表创建失败";
//        System.out.println("表创建成功");

        // 使用连接池插入记录
        for (int i = 0; i < COUNT; i++) {
            new Thread(() -> userDao.addUser(new User(GetName.randomName().toString(), GetSex.randomSex().toString(),
                    random.nextInt(8) + 16, Math.random() * 100))).start();

        }
        long l2 = System.currentTimeMillis();
        System.out.println("使用DBCP连接池耗时：" + (l2 - l1));

        // 直接插入记录
//        l1 = System.currentTimeMillis();
//
//        Class.forName("com.mysql.jdbc.Driver");
//        String url = "jdbc:mysql:///test";
//        for (int i = 0; i < COUNT; i++) {
//            Connection connection = DriverManager.getConnection(url, "root", "wl968640");
//            String sql = "insert into user(name, sex, age,score) values (?, ?, ?, ?)";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, GetName.randomName().toString());
//            ps.setString(2, GetSex.randomSex().toString());
//            ps.setInt(3, random.nextInt(8) + 16);
//            ps.setDouble(4, Math.random() * 100);
//            // 执行语句
//            ps.executeUpdate();
//            ps.close();
//            connection.close();
//        }
//        l2 = System.currentTimeMillis();
//        System.out.println("不使用连接池耗时：" + (l2 - l1));

//        userDao.getUser(12452);
    }

    @Test
    public void getUserTest(){
        UserDao userDao = UserDaoFactory.getDaoFactory().getUserDao();
        System.out.println(userDao.getUser(2));
    }
}
