package jdbc.baseoptimize;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * description：数据源，创建连接池实现连接的复用
 *
 * @author ajie
 * data 2018/8/21 10:35
 */
public class MyDataSource {
    private static String url = "jdbc:mysql://localhost:3306/test";
    private static String user = "root";
    private static String password = "wl968640";

    private static int initCount = 5;
    private static int maxCount = 10;
    private int currentCount = 0;

    LinkedList<Connection> connectionsPool = new LinkedList<>();

    public MyDataSource() {
        try {
            for (int i = 0; i < initCount; i++) {
                this.connectionsPool.addLast(this.createConnection());
                this.currentCount++;
            }
        } catch (SQLException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public Connection getConnection() throws SQLException {
        synchronized (connectionsPool) {
            if (this.connectionsPool.size() > 0) {
                return this.connectionsPool.removeFirst();
            }

            if (this.currentCount < maxCount) {
                this.currentCount++;
                return this.createConnection();
            }

            throw new SQLException("have no connection");
        }
    }

    public void free(Connection conn) {
        this.connectionsPool.addLast(conn);
    }

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
