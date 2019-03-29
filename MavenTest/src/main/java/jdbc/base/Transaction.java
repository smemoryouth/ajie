package jdbc.base;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * description：事务实例,出错全部回滚
 *
 * @author ajie
 * data 2018/8/15 11:12
 */
class Transaction {
    static final float MAXSAL=4000.00f;
    static void transactionMain() throws SQLException {
        analogTransfer();
    }

    /**
     * 模拟转账
     *
     * @throws SQLException
     */
    private static void analogTransfer() throws SQLException {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnect();
            // 打开事务
            conn.setAutoCommit(false);
            st = conn.createStatement();
            String sql = "update employee set sal=sal-10 where ename='alan'";
            st.executeUpdate(sql);

            sql = "select sal from employee where ename='ajie'";
            rs = st.executeQuery(sql);
            float money = 0.0f;
            if (rs.next()) {
                money = rs.getFloat("sal");
            }
            if (money > MAXSAL) {
                throw new RuntimeException("beyond max sal");
            }
            sql = "update employee set sal=sal+10 where ename='ajie'";
            st.executeUpdate(sql);
            // 提交事务
            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                // 回滚
                conn.rollback();
            }
            e.printStackTrace();
        } finally {
            JdbcUtils.free(rs, st, conn);
        }
    }
}
