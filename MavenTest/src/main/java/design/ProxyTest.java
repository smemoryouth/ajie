package design;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 描述:
 *
 * 动态代理有两种：
 * 1.JDK本身提供的动态代理机制 Proxy   =》  只能代理接口
 * 2.第三方 CGLib动态代理     =》  用来代理类
 *
 * 代理：功能增强
 * @Author shilei
 * @Date 2018/11/10
 */
interface IUser{
    void talk();
    void eat();
}

/**
 * 委托类
 */
class Boss implements IUser{

    @Override
    public void talk() {
        System.out.println("boss talk");
    }

    @Override
    public void eat() {
        System.out.println("boss eat");
    }
}

/**
 * 代理类
 */
class MyProxy implements InvocationHandler{

    /**
     * user引用的委托的对象
     */
    private IUser user;

    MyProxy(IUser user){
        this.user = user;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("权限检查开始");
        System.out.println(proxy.getClass());
        // 通过反射来调用委托对象的方法
        method.invoke(user, args);
        return null;
    }
}

/**
 *
 * @author 阿劼
 * @date 2018/11/10
 */
public class ProxyTest {
    public static void main(String[] args) {

        // System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        // 1.实现一个类，从InvocationHandler继承而来，重写invoke方法
        // 2.调用Proxy的newProxyInstance方法，产生实现了指定接口的代理对象
        // IUser user = new $Proxy0(new MyProxy(new Boss());

        // 3. IUser user => 引用的就是Proxy产生的代理对象 $Proxy0
        // 4. class $Proxy0 extends Proxy implements IUser
        // 5. public $Proxy0(InvocationHandler handler)
        // 6. user.talk()  => $Proxy0  talk   handler.invoke(this, method, args);
        IUser user = (IUser)Proxy.newProxyInstance(ProxyTest.class.getClassLoader(),
                new Class[]{IUser.class},
                new MyProxy(new Boss()));

        user.talk();  // 代理对象的talk方法
        user.eat();   // 代理对象的eat方法
    }
}
