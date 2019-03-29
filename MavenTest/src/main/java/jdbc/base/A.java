package jdbc.base;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * description：
 *
 * @author ajie
 * data 2018/8/21 22:11
 */
public class A {

    public static void main(String[] args) throws SQLException {

        for (int i = 0; i < 10; i++) {
            Connection connection = JdbcUtils.getConnect();
            System.out.println(connection);
            System.out.println(connection.getClass().getName());
//            System.out.println(connection.getClass().getSimpleName());
            JdbcUtils.free(null, null, connection);
        }
    }
}
