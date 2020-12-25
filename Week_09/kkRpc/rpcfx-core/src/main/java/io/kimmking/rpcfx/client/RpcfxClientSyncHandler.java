package io.kimmking.rpcfx.client;

import com.alibaba.fastjson.JSON;
import io.kimmking.rpcfx.api.RpcfxResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import netty.channelHandler.RpcfxProtocol;

import java.util.concurrent.CountDownLatch;

/**
 * @author wincher
 * <p> io.kimmking.rpcfx.client <p>
 */
@Slf4j
public class RpcfxClientSyncHandler extends SimpleChannelInboundHandler<RpcfxProtocol>  {

    @Setter
    private CountDownLatch latch;
    private RpcfxResponse response;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcfxProtocol msg) throws Exception {
        log.info("Netty client receive message:");
        log.info("Message length: " + msg.getLen());
        log.info("Message content: " + new String(msg.getContent(), CharsetUtil.UTF_8));

        // 将 RpcResponse字符串 反序列化成 RpcResponse对象
        RpcfxResponse rpcfxResponse = JSON.parseObject(new String(msg.getContent(), CharsetUtil.UTF_8),
            RpcfxResponse.class);
        log.info("Netty client serializer : " + rpcfxResponse.toString());
        response = rpcfxResponse;
        latch.countDown();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("occure error", cause);
        ctx.close();
    }


    public RpcfxResponse getResponse() throws InterruptedException {
        latch.await();
        return response;
    }
}
