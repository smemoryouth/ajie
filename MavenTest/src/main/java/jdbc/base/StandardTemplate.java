package jdbc.base;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * description：JDBC模板
 * 读操作
 *
 * @author ajie
 * data 2018/8/9 21:10
 */
class StandardTemplate {
    static void standardTemplateMain() throws SQLException {
        template();
    }

    private static void template() throws SQLException {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            // 建立连接
            conn = JdbcUtils.getConnect();
            // 创建语句
            st = conn.createStatement();
            // 执行语句
            rs = st.executeQuery("select * from Employee");
            while (rs.next()) {
                System.out.println(rs.getObject(1)
                        + "\t" + rs.getObject(2)
                        + "\t" + rs.getObject(3)
                        + "\t" + rs.getObject(4));
            }
        } finally {
            JdbcUtils.free(rs, st, conn);
        }
    }
}
