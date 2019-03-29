package jdbc.classtest;

import com.mysql.jdbc.Connection;
import org.junit.Test;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * description：
 *
 * @author ajie
 * data 2018/10/13 10:38
 */
public class JdbcTest {
    /**
     * 基本查询操作
     * @throws Exception
     */
    @Test
    public void Test() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql:///test?useSSL=false";
        Connection connection = (Connection) DriverManager.getConnection(url, "root", "wl968640");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from user");
        while (resultSet.next()){
            System.out.println(resultSet.getObject(1) + "\t"
            + resultSet.getObject(2) + "\t"
            + resultSet.getObject(3) + "\t"
            + resultSet.getObject(4));
        }
    }

    /**
     * Sql注入模拟
     * @throws Exception
     */
    @Test
    public void Test2() throws Exception {
//        String name = "zhang huan";
//        String pwd = "111";
//        String name = "zhang huan '#";
        String name = "zhang huan 'or'1 = 1";
        String pwd = "12345";
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://192.168.31.195:3306/GY1801Java";
        Connection connection = (Connection) DriverManager.getConnection(url, "root", "111111");
        Statement statement = connection.createStatement();
        String str = "select * from login where name ='" + name + "' and pwd='" + pwd + "'";
        System.out.println(str);
        ResultSet resultSet = statement.executeQuery(str);

        if (resultSet.next()){
            System.out.println("登录成功");
        }else{
            System.out.println("登录失败");
        }
    }

}
