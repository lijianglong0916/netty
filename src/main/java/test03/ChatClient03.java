package test03;/*
 *@author:
 *@time
 */


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ChatClient03 {
    public static void main(String[] args) throws InterruptedException, IOException {
        EventLoopGroup clientGroup=new NioEventLoopGroup();

        try {
            Bootstrap clientBooStrap=new Bootstrap();
            clientBooStrap.group(clientGroup).channel(NioSocketChannel.class)
                    .handler(new ClientInitializer03());
            ChannelFuture channelFuture = clientBooStrap.connect("localhost", 8899).sync();
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            String msg = br.readLine();
           while (true){
               channelFuture.channel().writeAndFlush(msg+"\n\r").sync();
           }

        } finally {
            clientGroup.shutdownGracefully();
        }


    }
}
