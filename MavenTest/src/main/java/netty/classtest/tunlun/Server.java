package netty.classtest.tunlun;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * desc:Netty服务端实现
 * @user:gongdezhe
 * @date:2018/10/26
 */

public class Server {
    public void bind(int port) {
        /**
         * NioEventLoopGroup 是用来处理I/O操作的多线程事件处理器
         * Netty 提供了许多不同的 EventLoopGroup的实现来处理不同的传输
         * 本例中实现服务端的应用，因此会有2个NioEventLoopGroup 会被使用
         * 一个是main，也叫做boss，用来接收进来的连接
         * 一个是work，用来处理已经接受的连接
         * 一旦boss接收到连接，就会把信息注册到worker上
         * 如何知道多少个线程已经被使用，如何映射到已经创建的channel上都需要依赖于EventLoopGroup的实现
         * 并且可以通过构造函数来配置他们的关系
         */
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            /**
             * ServerBootstrap 是一个启动NIO的辅助启动类
             * 可以在这个服务中直接使用channel
             */
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workGroup)
                    //这里指定使用NioServerSocketChannel类来举例说明一个新的channel如何接受进来的连接
                    .channel(NioServerSocketChannel.class)
//                    .localAddress(port)
                    /**
                     * 这里的事件处理类经常被用来处理一个最近结束的channel
                     * 匿名内部类 继承自ChannelInitializer 是一个特殊的处理类，他的目的是帮助使用者配置一个新的channel
                     */
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        //添加serverHandler到channel的channelPipeline
                        //通过ServerHandler给每一个新来的Channel初始化
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("handler", new ServerHandler());
                            pipeline.addLast("decoder", new StringDecoder());
                            pipeline.addLast("encoder", new StringEncoder());
                        }
                    })
                    //设置TCP协议的请求等待队列
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            //绑定端口，调用sync()同步阻塞方法等待完成
            ChannelFuture sync = bootstrap.bind(port).sync();
            System.out.println("服务端监听端口："+port +" 启动成功");

            //使用sync方法进行阻塞，等待服务端链路关闭之后main函数退出
            sync.channel().closeFuture().sync();
        } catch (Exception e) {

        } finally {
            //释放线程池资源
            workGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
            System.out.println("server 关闭了");
        }


    }
}
