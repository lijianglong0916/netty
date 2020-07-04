package test01;/*
 *@author:
 *@time
 */

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class ServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline=ch.pipeline();

        channelPipeline.addLast("HttpServerCodec",new HttpServerCodec());
        channelPipeline.addLast("HttpServerHandler",new HttpServerHandler());
        
    }
}
