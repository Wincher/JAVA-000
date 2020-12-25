package io.kimmking.rpcfx.server;

import io.kimmking.rpcfx.api.RpcfxResolver;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import netty.channelHandler.RpcfxDecoder;
import netty.channelHandler.RpcfxEncoder;
import org.springframework.stereotype.Component;

/**
 * @author wincher
 * <p> io.kimmking.rpcfx.server <p>
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RpcfxNettyServer {

    @NonNull
    private final RpcfxResolver resolver;
    @NonNull
    private final int port;

    private EventLoopGroup boss;
    private EventLoopGroup worker;


    public void run() throws Exception {
        boss = new NioEventLoopGroup(1);
        worker = new NioEventLoopGroup(1);

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boss, worker)
            .channel(NioServerSocketChannel.class)
            .childHandler(new ChannelInitializer() {
                @Override
                protected void initChannel(Channel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    pipeline.addLast("encode", new RpcfxEncoder());
                    pipeline.addLast("decode", new RpcfxDecoder());
                    pipeline.addLast("handler", new RpcServerHandler(resolver));
                }
            });
        Channel channel = bootstrap.bind(port).sync().channel();
        log.info("Netty server start, listening port: [{}]", port);
        channel.closeFuture().sync();
    }

    public void destory() {
        boss.shutdownGracefully();
        worker.shutdownGracefully();
    }

}
