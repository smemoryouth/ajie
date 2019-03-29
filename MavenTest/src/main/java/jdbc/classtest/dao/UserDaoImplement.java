package jdbc.classtest.dao;

import jdbc.base.JdbcUtils;
import jdbc.c3p0.C3p0Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * description：user功能实现类
 *
 * @author ajie
 * data 2018/10/23 10:55
 */
public class UserDaoImplement implements UserDao {
    @Override
    public void addUser(User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 建立连接
            conn = JdbcUtils.getConnect();
            // 创建语句

            String sql = "insert into user(name, sex, age,score) values (?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getSex());
            ps.setInt(3, user.getAge());
            ps.setDouble(4, user.getScore());
            // 执行语句
            ps.executeUpdate();
        } catch (SQLException e) {
            // 包装异常
            throw new UserDaoException(e.getMessage(), e);
        } finally {
            JdbcUtils.free(rs, ps, conn);
        }
    }

    @Override
    public User getUser(int id) {
        Connection conn = null;
        PreparedStatement st;
        ResultSet rs;
        User user = null;
        try {
            // 建立连接
            conn = C3p0Utils.getConnection();
            // 创建语句
            String sql = "select * from user where id =?";
            st = conn.prepareStatement(sql);
            st.setInt(1, id);
            // 执行语句
            rs = st.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getObject("id")
                        + "\t" + rs.getObject("name")
                        + "\t" + rs.getObject("sex")
                        + "\t" + rs.getObject("age")
                        + "\t" + rs.getObject("score"));
            }
        } catch (SQLException e) {
            // 包装异常
            throw new UserDaoException(e.getMessage(), e);
        } finally {
            C3p0Utils.closeConn(conn);
        }
        return user;
    }

    @Override
    public int createTable() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 建立连接
            conn = JdbcUtils.getConnect();
            // 创建语句
            String sql = "create table user(id int primary key auto_increment, name varchar(20)," +
                    "sex varchar(20), age int, score double(5,2))";
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            // 包装异常
            throw new UserDaoException(e.getMessage(), e);
        } finally {
            JdbcUtils.free(rs, ps, conn);
        }
        return 0;
    }
}
