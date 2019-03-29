package jdbc.base;

import java.sql.*;

/**
 * description：对表字段的增删查改基础操作
 *
 * @author ajie
 * data 2018/8/11 21:49
 */
class IudrDatabase {
    public static void main(String[] args) throws SQLException {
//        insert();
//        update();
//        delete();
        read();
    }

    /**
     * 增加表字段操作
     *
     * @throws SQLException
     */
    private static void insert() throws SQLException {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            // 建立连接
            conn = JdbcUtils.getConnect();
            // 创建语句
            st = conn.createStatement();

            String sql = "insert into Employee (ename, deptno, hiredate, sal) values ('df', 3, '2018-01-01', 4500.00)";
            // 执行语句
            int i = st.executeUpdate(sql);
            System.out.println("i = " + i);
        } finally {
            JdbcUtils.free(rs, st, conn);
        }
    }

    /**
     * 修改表字段操作
     *
     * @throws SQLException
     */
    private static void update() throws SQLException {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            // 建立连接
            conn = JdbcUtils.getConnect();
            // 创建语句
            st = conn.createStatement();

            String sql = "update Employee set hiredate='2018-01-02' where ename='df'";
            // 执行语句
            int i = st.executeUpdate(sql);
            System.out.println("i = " + i);
        } finally {
            JdbcUtils.free(rs, st, conn);
        }
    }

    /**
     * 删除表字段操作
     *
     * @throws SQLException
     */
    private static void delete() throws SQLException {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            // 建立连接
            conn = JdbcUtils.getConnect();
            // 创建语句
            st = conn.createStatement();
            String sql = "delete from Employee where ename='df'";
            // 执行语句
            int i = st.executeUpdate(sql);
            System.out.println("i = " + i);
        } finally {
            JdbcUtils.free(rs, st, conn);
        }
    }

    /**
     * 查询表字段操作
     *
     * @throws SQLException
     */


    private static void read() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 建立连接
            conn = JdbcUtils.getConnect();
            System.out.println(conn);
            // 创建语句
            String sql = "select id, ename, deptno, sal, hiredate from Employee";
            ps = conn.prepareStatement(sql);
            // 执行语句
            rs = ps.executeQuery();
            while (rs.next()) {
                // 输出用列名为索引
                System.out.println(rs.getObject("id")
                        + "\t" + rs.getObject("ename")
                        + "\t" + rs.getObject("deptno")
                        + "\t" + rs.getObject("hiredate")
                        + "\t" + rs.getObject("sal"));
            }
        } finally {
            JdbcUtils.free(rs, ps, conn);
        }
    }
}
