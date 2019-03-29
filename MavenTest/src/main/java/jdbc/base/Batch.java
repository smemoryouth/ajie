package jdbc.base;

import java.sql.*;

/**
 * description：批处理
 *
 * @author ajie
 * data 2018/8/16 23:33
 */
public class Batch {
    public static void batchMain() throws SQLException {
        insertBatch();
    }

    private static void insertBatch() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnect();
            String sql = "insert into Employee (ename, deptno, hiredate, sal) values (?, ?, ?,?)";
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < 100; i++) {
                ps.setString(1, "ename" + i);
                ps.setInt(2, i);
                ps.setDate(3, new Date(System.currentTimeMillis()));
                ps.setDouble(4, 1000 + i);
                ps.addBatch();
            }
            ps.executeBatch();
        } finally {
            JdbcUtils.free(rs, ps, conn);
        }
    }
}
