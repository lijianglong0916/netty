package test02;/*
 *@author:
 *@time
 */

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient02 {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup clientGroup=new NioEventLoopGroup();

        try {
            Bootstrap clientBootstrap=new Bootstrap();
            clientBootstrap.group(clientGroup).channel(NioSocketChannel.class).handler(new ClientInitializer02());
            ChannelFuture channelFuture = clientBootstrap.connect("localhost",8899).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            clientGroup.shutdownGracefully();
        }
    }
}
