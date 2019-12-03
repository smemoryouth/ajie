package jdbc.base;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 工具类
 * @author 阿劼
 */
public final class JdbcUtils {
    /**
     * 连接池类对象
     */
    private static DataSource dataSource;
    private JdbcUtils() {
    }

    // 注册驱动
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Properties prop = new Properties();
            InputStream is = JdbcUtils.class.getClassLoader()
                    .getResourceAsStream("dbcpconfig.properties");
            prop.load(is);
            dataSource = BasicDataSourceFactory.createDataSource(prop);
            System.out.println(dataSource);
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * 建立连接
     *
     * @return
     * @throws SQLException
     */
    public static Connection getConnect() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * 释放资源
     *
     * @param rs
     * @param st
     * @param conn
     */
    public static void free(ResultSet rs, Statement st, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
