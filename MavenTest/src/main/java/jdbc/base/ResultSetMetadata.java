package jdbc.base;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description：利用结果集元数据将查询结果封装为map
 *
 * @author ajie
 * data 2018/8/19 11:11
 */
public class ResultSetMetadata {
    public static void main(String[] args) throws SQLException {
        List<Map<String, Object>> datas = read("select id, ename as n from employee where id < 5");
        System.out.println(datas);
    }

    static List<Map<String, Object>> read(String sql) throws SQLException {
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
                // System.out.print(rsmd.getColumnClassName(i) + "\t");
                // System.out.print(rsmd.getColumnName(i) + "\t");
                // System.out.println(rsmd.getColumnLabel(i));
                colNames[i - 1] = rsmd.getColumnLabel(i);
            }
            List<Map<String, Object>> datas = new ArrayList<>();

            while (rs.next()) {
                Map<String, Object> data = new HashMap<>(4);
                for (int i = 0; i < colNames.length; i++) {
                    data.put(colNames[i], rs.getObject(colNames[i]));
                }
                datas.add(data);
            }
            return datas;
        } finally {
            JdbcUtils.free(rs, ps, conn);
        }
    }
}
