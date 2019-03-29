package mybatisown;

import mybatisown.school.bean.Student;

import java.lang.annotation.*;
import java.lang.reflect.*;
import java.sql.*;

interface StudentMapper {
    @MySelect("select * from student where id = #{id}")
    Student selectById(Integer id);
}

class MyBatisTestUtils {
    static {
        // 加载jdbc驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static MySqlSession openSession() {
        return new MySqlSession();
    }
}

/**
 * =========================================================
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface MySelect {
    String value();
}

class MySqlSession {
    private Connection connection;

    public MySqlSession() {
        String url = "jdbc:mysql://localhost:3306/school";
        try {
            this.connection = DriverManager.getConnection(url,
                    "root", "wl968640");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // StudentMapper sm = session.getMapper(StudentMapper.class);
    // 实现Mapper方法
    public <T> T getMapper(Class<T> c) {
        if (!c.isInterface()) {
            throw new ExceptionInInitializerError("error:" + c.getName() + "is not interface");
        }
        // Proxy.newProxyInstance(加载器，  接口，   实现invocationhandler)
        // 自己添加一下
        return (T) Proxy.newProxyInstance(MySqlSession.class.getClassLoader(), new Class[]{c},
                new MyHandler());
    }

    public void close() {
        try {
            if (connection != null) {
                this.connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 给$Proxy0代理对象使用的  作为该代理对象的成员变量
     */
    class MyHandler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            // method
            // 通过反射访问method的注解 @MySelect
            MySelect s = null;
            Annotation[] anns = method.getAnnotations();
            for (Annotation ann : anns) {
                s = (MySelect) ann;
                break;
            }
            if (s == null) {
                return null;
            }
            String sql = s.value();
            // 把#{id}替换成？
            for (; ; ) {
                int begin = sql.indexOf("#{");
                if (begin == -1) {
                    break;
                }
                int end = sql.indexOf("}", begin);
                sql = sql.substring(0, begin) + "?" + sql.substring(end + 1);
            }
            System.out.println(sql);
            PreparedStatement pst = MySqlSession.this.connection.prepareStatement(sql);
            // @MySelect value => select * from student where id = #{id}
            // 解析args，把参数拿出来，填到上面的sql语句中
            for (int i = 0; i < args.length; i++) {
                pst.setObject(i + 1, args[i]);
            }
            ResultSet rs = pst.executeQuery();
            // 从method里面提取返回值信息
//            Type t = method.getGenericReturnType();
            Class<?> returnType = method.getReturnType();


            if (rs.next()){
                Field[] fields = returnType.getDeclaredFields();
                Object o = returnType.newInstance();
                for (Field field : fields) {
                    field.setAccessible(true);
                    try {
                        Object value = rs.getObject(field.getName());
                        field.set(o, value);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                return o;
            }
            // Preparestatement => server  => sql
            // 服务器返回的student记录打包成student对象，返回回去
            return null;
        }
    }
}

/**
 * 描述:
 *
 * @Author shilei
 * @Date 2018/11/10
 */
class MyBatisTest {
    public static void main(String[] args) {
        MySqlSession session = MyBatisTestUtils.openSession();

        try {
            StudentMapper sm = session.getMapper(StudentMapper.class);
            Student student = sm.selectById(1);
            System.out.println(student);
        } finally {
            session.close();
        }
    }
}