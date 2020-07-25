package test06;/*
 *@author:
 *@time
 */

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NettyServerHandler06 extends SimpleChannelInboundHandler<DateInfo06.Student> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DateInfo06.Student msg) throws Exception {
        System.out.println(msg.getName());
        System.out.println(msg.getAge());
        System.out.println(msg.getAddress());
    }
}
