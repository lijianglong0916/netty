package test02;/*
 *@author:
 *@time
 */

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Optional;

public class ServerHandler02 extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Optional.of(ctx.channel().remoteAddress()+"  "+msg)
                .ifPresent(System.out::println);
        ctx.channel().writeAndFlush(msg+"信息返回");
    }
}
