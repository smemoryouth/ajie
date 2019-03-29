package netty.imooc;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * description：工程的全局配置
 *
 * @author ajie
 * data 2018/10/25 19:11
 */
public class NettyConfig {
    /**
     * 存储每一个客户端接入进来的Channel对象
     */
    public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
}
