package test05;/*
 *@author:
 *@time
 */

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalTime;

public class NettyServerHandler05 extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        Channel channel = ctx.channel();

        channelGroup.stream().forEach(e -> {
            if (e == channel) {
                e.writeAndFlush(new TextWebSocketFrame("myself" + msg.text() + "ServerTime: " + LocalTime.now()));
            } else {
                e.writeAndFlush(new TextWebSocketFrame("[" + e.remoteAddress() + "]" + msg.text() + "ServerTime " + LocalTime.now()));
            }
        });
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush(new TextWebSocketFrame("[" + channel.remoteAddress() + "]  online ServerTime " + LocalTime.now()));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush(new TextWebSocketFrame("[" + channel.remoteAddress() + "]lose line ServerTime " + LocalTime.now()));
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush(new TextWebSocketFrame("[" + channel.remoteAddress() + "]  add ServerTime " + LocalTime.now()));
        channelGroup.add(channel);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.remove(channel);
        channelGroup.writeAndFlush(new TextWebSocketFrame("[" + channel.remoteAddress() + "]  out ServerTime " + LocalTime.now()));
    }
}
