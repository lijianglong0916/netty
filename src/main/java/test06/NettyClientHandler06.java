package test06;/*
 *@author:
 *@time
 */

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NettyClientHandler06 extends SimpleChannelInboundHandler<DateInfo06.Student> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DateInfo06.Student msg) throws Exception {
        Channel channel = ctx.channel();

        DateInfo06.Student student = DateInfo06.Student.newBuilder().setName("lijiayin").setAge(23).setAddress("shanghai").build();
            channel.writeAndFlush(student);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }
}
