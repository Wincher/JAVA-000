package io.kimmking.rpcfx.client;

import com.alibaba.fastjson.JSON;
import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResponse;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import netty.channelHandler.RpcfxDecoder;
import netty.channelHandler.RpcfxEncoder;
import netty.channelHandler.RpcfxProtocol;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;

/**
 * @author wincher
 * <p> io.kimmking.rpcfx.client <p>
 */
@Slf4j
public class NettyClient implements RpcClient {

    private enum NettyClientSingleton {
        INSTANCE;
        private NettyClient instance;

        NettyClientSingleton() {
            instance = new NettyClient();
        }

        public NettyClient getInstance() {
            return instance;
        }
    }

    private NettyClient() {}

    public static NettyClient instance() {
        return NettyClientSingleton.INSTANCE.getInstance();
    };

    // DONE: 2.尝试使用httpclient或者netty client
    @Override
    public RpcfxResponse post(RpcfxRequest req, String url) throws URISyntaxException, InterruptedException {
        RpcfxProtocol request = convertNettyRequest(req);
        //TODO: reuse channel
        Channel channel = createChannel(new URI(url));
        RpcfxClientSyncHandler rpcfxClientSyncHandler = new RpcfxClientSyncHandler();
        rpcfxClientSyncHandler.setLatch(new CountDownLatch(1));
        channel.pipeline().replace("clientHandler", "clientHandler", rpcfxClientSyncHandler);
        channel.writeAndFlush(request).sync();


        return rpcfxClientSyncHandler.getResponse();
    }

    private RpcfxProtocol convertNettyRequest(RpcfxRequest req) {
        RpcfxProtocol request = new RpcfxProtocol();
        String requestJson = JSON.toJSONString(req);
        request.setLen(requestJson.getBytes(CharsetUtil.UTF_8).length);
        request.setContent(requestJson.getBytes(CharsetUtil.UTF_8));
        return request;
    }

    private EventLoopGroup clientGroup = new NioEventLoopGroup();

    private Channel createChannel(URI uri) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(clientGroup)
            .channel(NioSocketChannel.class)
//            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
//            .option(ChannelOption.SO_REUSEADDR, true)
//            .option(ChannelOption.TCP_NODELAY, true)
//            .option(ChannelOption.AUTO_CLOSE, true)
//            .option(ChannelOption.SO_KEEPALIVE, true)
            .handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();

                    pipeline.addLast("Message Encoder", new RpcfxEncoder());
                    pipeline.addLast("Message Decoder", new RpcfxDecoder());
                    pipeline.addLast("clientHandler", new RpcfxClientSyncHandler());
                }
            });
        return bootstrap.connect(uri.getHost(), uri.getPort()).sync().channel();
    }

    private static String encode(RpcfxRequest req) {
        return JSON.toJSONString(req);
    }
}
