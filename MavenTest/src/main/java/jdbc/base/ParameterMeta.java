package jdbc.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * description：参数的元数据信息，代码灵活，但是可读性差，可维护性差
 *
 * @author ajie
 * data 2018/8/19 10:29
 */
class ParameterMeta {
    static void parameterMetaMain() throws SQLException {
        Object[] params = new Object[] { "xiao", 100f };
        read("select * from employee where ename=? and  sal > ?", params);
    }

    static void read(String sql, Object[] params) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnect();
            ps = conn.prepareStatement(sql);
//			ParameterMetaData pmd = ps.getParameterMetaData();
//			int count = pmd.getParameterCount();
            int length = params.length;
            for (int i = 1; i <= length; i++) {
                // System.out.print(pmd.getParameterClassName(i) + "\t");
                // System.out.print(pmd.getParameterType(i) + "\t");
                // System.out.println(pmd.getParameterTypeName(i));
                ps.setObject(i, params[i - 1]);
            }

            rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getInt("id")
                        + "\t" + rs.getString("ename")
                        + "\t" + rs.getDate("hiredate")
                        + "\t" + rs.getInt("deptno")
                        + "\t" + rs.getFloat("sal"));
            }
        } finally {
            JdbcUtils.free(rs, ps, conn);
        }
    }
}
