package test03;/*
 *@author:
 *@time
 */

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Optional;

public class ServerHandler03 extends SimpleChannelInboundHandler<String> {

    private static ChannelGroup channelGroup=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.stream().filter(e->e!=channel)
                .forEach(e->{
                    e.writeAndFlush("server "+channel.remoteAddress());
                });


        String value = "server: " + msg;
        Optional.of(value).ifPresent(System.out::println);
        ctx.channel().writeAndFlush(value);
        ctx.channel().closeFuture();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("client "+channel.localAddress()+"上线了");
        channelGroup.add(channel);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        channelGroup.remove(ctx.channel());
        channelGroup.writeAndFlush("client "+ctx.channel().localAddress()+"下线了");
    }
}
