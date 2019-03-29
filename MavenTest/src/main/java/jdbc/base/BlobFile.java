package jdbc.base;

import java.io.*;
import java.sql.*;

/**
 * description：JDBC访问字节文件
 *
 * @author ajie
 * data 2018/8/12 21:45
 */
public class BlobFile {
    public static void blobFileMain() throws SQLException {
//        saveFile();
        readFile();
    }

    /**
     * 存储数据到数据库
     *
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
            String sql = "insert into blobb(blo) values (?)";
            ps = conn.prepareStatement(sql);
            File file = new File("C:\\Users\\阿劼\\Pictures\\Saved Pictures\\123.jpg");
            InputStream in = new BufferedInputStream(new FileInputStream(file));
            ps.setBinaryStream(1, in, file.length());

            // 执行语句
            int i = ps.executeUpdate();
            in.close();
            System.out.println("i = " + i);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.free(rs, ps, conn);
        }
    }

    /**
     * 从数据库读取数据
     *
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
            String sql = "select blo from blobb";
            st = conn.prepareStatement(sql);
            // 执行语句
            rs = st.executeQuery();
            while (rs.next()) {
                Blob blob = rs.getBlob(1);
                InputStream in = blob.getBinaryStream();
//                InputStream in = rs.getBinaryStream(1);
                File file = new File("456.jpg");
                OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
                byte[] buff = new byte[1024];
                for (int i; (i = in.read(buff)) > 0; ) {
                    out.write(buff, 0, i);
                }
                out.close();
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.free(rs, st, conn);
        }
    }
}
