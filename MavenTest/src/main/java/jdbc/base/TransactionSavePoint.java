package jdbc.base;

import java.sql.*;

import static jdbc.base.Transaction.MAXSAL;

/**
 * description：事务保存点，部分回滚
 *
 * @author ajie
 * data 2018/8/15 12:17
 */
public class TransactionSavePoint {
    public static void transactionSavePointMain() throws SQLException {
        savePointer();
    }

    private static void savePointer() throws SQLException {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        Savepoint sp = null;
        try {
            conn = JdbcUtils.getConnect();
            // 打开事务
            conn.setAutoCommit(false);
            st = conn.createStatement();
            String sql = "update employee set sal=sal-10 where ename='alan'";
            st.executeUpdate(sql);
            sp = conn.setSavepoint();

            sql = "update employee set sal=sal-10 where ename='xiao'";
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
        } catch (RuntimeException e) {
            if (conn != null && sp != null) {
                // 部分回滚
                conn.rollback(sp);
                conn.commit();
            }
            e.printStackTrace();
        }catch (SQLException e) {
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
