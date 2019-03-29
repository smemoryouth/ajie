package jdbc.base;

import java.io.*;
import java.sql.*;

/**
 * description：用JDBC访问大段文本数据
 *
 * @author ajie
 * data 2018/8/12 20:12
 */
public class ClobFile {
    public static void clobFileMain() throws SQLException {
//        saveFile();
        readFile();
    }

    /**
     * 存储数据到数据库
     * @throws SQLException
     */
    private static void saveFile() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 建立连接
            conn = JdbcUtils.getConnect();
            // 创建语句
            String sql = "insert into clobb(tes) values (?)";
            ps = conn.prepareStatement(sql);
            File file = new File("C:\\Users\\阿劼\\IdeaProjects\\Text\\1234.txt");
            Reader reader = new BufferedReader(new FileReader(file));
            ps.setCharacterStream(1, reader, file.length());

            // 执行语句
            int i = ps.executeUpdate();
            reader.close();
            System.out.println("i = " + i);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.free(rs, ps, conn);
        }
    }

    /**
     * 从数据库读取数据
     * @throws SQLException
     */
    private static void readFile() throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            // 建立连接
            conn = JdbcUtils.getConnect();

            // 创建语句
            String sql = "select tes from clobb";
            st = conn.prepareStatement(sql);
            // 执行语句
            rs = st.executeQuery();
            while (rs.next()) {
                Clob clob = rs.getClob(1);
                Reader reader = clob.getCharacterStream();
//                Reader reader = rs.getCharacterStream(1);
                File file = new File("c.txt");
                Writer writer = new BufferedWriter(new FileWriter(file));
                char[] buff = new char[1024];
                for (int i; (i = reader.read(buff)) > 0;) {
                    writer.write(buff, 0, i);
                }
                writer.close();
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.free(rs, st, conn);
        }
    }
}
