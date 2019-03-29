package mybatisown.employee.mybatisutil;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * description：
 *
 * @author ajie
 * data 2018/10/29 11:21
 */
public class Utils {
    private static SqlSessionFactory ssf;

    static{
        String configFile = "mybatis.xml";
        InputStream in = Utils.class.getClassLoader().getResourceAsStream(configFile);
        ssf = new SqlSessionFactoryBuilder().build(in);
    }

    /**
     * MyBatisUtils.getSession
     * @return
     */
    public static SqlSession getSession(){
        // openSession()默认是不会自动提交事务的   会自动提交事务
        SqlSession ss = ssf.openSession(false);
        return ss;
    }
}
