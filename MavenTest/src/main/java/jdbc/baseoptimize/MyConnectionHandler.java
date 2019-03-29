package jdbc.baseoptimize;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

/**
 * description：使用java动态代理技术实现连接的优化
 *
 * @author ajie
 * data 2018/8/21 22:08
 */
public class MyConnectionHandler {
    private Connection realConnection;
    private Connection warpedConnection;
    private MyDataSource2 dataSource;

    private int maxUseCount = 5;
    private int currentUserCount = 0;

    MyConnectionHandler(MyDataSource2 dataSource) {
        this.dataSource = dataSource;
    }

    Connection bind(Connection realConn) {
        this.realConnection = realConn;
        this.warpedConnection = (Connection) Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[] { Connection.class }, (InvocationHandler) this);
        return warpedConnection;
    }

    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        if ("close".equals(method.getName())) {
            this.currentUserCount++;
            if (this.currentUserCount < this.maxUseCount) {
                this.dataSource.connectionsPool.addLast(this.warpedConnection);
            }
            else {
                this.realConnection.close();
                this.dataSource.currentCount--;
            }
        }
        return method.invoke(this.realConnection, args);
    }
}
