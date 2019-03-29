package jdbc.dao;

import jdbc.base.JdbcUtils;

import java.sql.*;

/**
 * description：标准Dao操作数据库
 *
 * @author ajie
 * data 2018/8/13 12:09
 */
public class EmployeeDaoJdbcImpl implements EmployeeDao {
    @Override
    public void addEmployee(Employee employee) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 建立连接
            conn = JdbcUtils.getConnect();
            // 创建语句
            String sql = "insert into employee values (?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, employee.getName());
            ps.setInt(2, employee.getDeptno());
            ps.setDate(3, new java.sql.Date(employee.getHiredate().getTime()));
            ps.setDouble(4, employee.getSal());
            // 执行语句
            ps.executeUpdate();
        } catch (SQLException e) {
            // 包装异常
            throw new DaoException(e.getMessage(), e);
        } finally {
            JdbcUtils.free(rs, ps, conn);
        }
    }


    @Override
    public Employee getEmployee(int deptno) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        Employee employee = null;
        try {
            // 建立连接
            conn = JdbcUtils.getConnect();
            // 创建语句
            String sql = "select ename, deptno, hiredate, sal from employee where deptno =?";
            st = conn.prepareStatement(sql);
            st.setInt(1, deptno);
            // 执行语句
            rs = st.executeQuery();
            while (rs.next()) {
                employee = mappingEmployee(rs);
            }
        } catch (SQLException e) {
            // 包装异常
            throw new DaoException(e.getMessage(), e);
        } finally {
            JdbcUtils.free(rs, st, conn);
        }
        return employee;
    }

    @Override
    public Employee findEmployee(String loginName, String password) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Employee employee = null;
        try {
            // 建立连接
            conn = JdbcUtils.getConnect();
            // 创建语句
            String sql = "select ename, deptno, hiredate, sal from employee where ename =?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, loginName);
            // 执行语句
            rs = ps.executeQuery();
            while (rs.next()) {
                employee = mappingEmployee(rs);
            }
        } catch (SQLException e) {
            // 包装异常
            throw new DaoException(e.getMessage(), e);
        } finally {
            JdbcUtils.free(rs, ps, conn);
        }
        return employee;
    }

    @Override
    public void update(Employee employee) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 建立连接
            conn = JdbcUtils.getConnect();
            // 创建语句
            String sql = "update employee set deptno = ?, sal = ? where ename = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, employee.getDeptno());
            ps.setDouble(2, employee.getSal());
            ps.setString(3, employee.getName());
            // 执行语句
            ps.executeUpdate();
        } catch (SQLException e) {
            // 包装异常
            throw new DaoException(e.getMessage(), e);
        } finally {
            JdbcUtils.free(rs, ps, conn);
        }
    }

    @Override
    public void delete(Employee employee) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 建立连接
            conn = JdbcUtils.getConnect();
            // 创建语句
            String sql = "delete from employee where ename=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, employee.getName());
            // 执行语句
            ps.executeUpdate();
        } catch (SQLException e) {
            // 包装异常
            throw new DaoException(e.getMessage(), e);
        } finally {
            JdbcUtils.free(rs, ps, conn);
        }
    }

    /**
     * 重构相同代码
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    private Employee mappingEmployee(ResultSet rs) throws SQLException {
        Employee employee = new Employee();
        employee.setName(rs.getNString("ename"));
        employee.setDeptno(rs.getInt("deptno"));
        employee.setHiredate(rs.getDate("hiredate"));
        employee.setSal(rs.getDouble("sal"));
        return employee;
    }
}
