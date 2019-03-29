package design;

import java.lang.reflect.Proxy;

interface Person0 {
    String sing(String name);
}

class Teacher0 implements Person0 {

    @Override
    public String sing(String name) {
        return "sing song";
    }
}

class TeacherProxy {

    private Person0 teacher = new Teacher0();

    Person0 getProxy() {
        return (Person0) Proxy.newProxyInstance(Teacher0.class.getClassLoader(),
                teacher.getClass().getInterfaces(),
                (proxy, method, args) -> method.invoke(teacher, args));
    }
}

/**
 * description：
 *
 * @author 阿劼
 * data 2019/1/4 16:31
 */
public class ProxyTest2 {
    public static void main(String[] args) {
        TeacherProxy proxy = new TeacherProxy();
        //获得代理对象
        Person0 p = proxy.getProxy();
        //调用代理对象的sing方法
        String retValue = p.sing("冰雨");
        System.out.println(retValue);
    }
}
