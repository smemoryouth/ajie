package jdbc.base;

import jdbc.dao.Employee;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;



/**
 * description：反射，bug
 *
 * @author ajie
 * data 2018/8/20 11:47
 */
public class ReflectJdbc {
    public static void main(String[] args) throws Exception {
        Employee employee = (Employee) getObjects(
                "select id as Id, ename as Ename, hiredate as Hiredate, sal as Sal  from employee where id=1",
                Employee.class);
        System.out.println(employee);
    }

    static Object getObjects(String sql, Class clazz) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnect();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int count = rsmd.getColumnCount();
            String[] colNames = new String[count];
            for (int i = 1; i <= count; i++) {
                colNames[i - 1] = rsmd.getColumnLabel(i);
            }

            Object obj = null;
            Method[] ms = clazz.getMethods();
            if (rs.next()) {
                obj = clazz.getDeclaredConstructor().newInstance();
                for (String colName : colNames) {
                    String methodName = "set" + colName;
                    for (Method m : ms) {
                        if (methodName.equals(m.getName())) {
                            m.invoke(obj, rs.getObject(colName));
                        }
                    }
                }
            }
            return obj;
        } finally {
            JdbcUtils.free(rs, ps, conn);
        }
    }
}
