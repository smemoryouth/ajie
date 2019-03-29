package jdbc.base;

import java.sql.*;

/**
 * description：返回主键
 *
 * @author ajie
 * data 2018/8/16 22:04
 */
public class MajorKeyReturn {
    public static void majorKeyReturnMain() throws SQLException {
        int i = insert();
        System.out.println(i);
    }

    private static int insert() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnect();
            String sql = "insert into Employee (ename, deptno, hiredate, sal) values ('fdd11', 3, '2018-01-01', 4500.00)";
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            int id = 0;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            return id;
        } finally {
            JdbcUtils.free(rs, ps, conn);
        }
    }
}
