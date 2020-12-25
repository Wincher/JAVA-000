package io.kimmking.rpcfx.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResolver;
import io.kimmking.rpcfx.api.RpcfxResponse;
import io.kimmking.rpcfx.exception.RpcfxException;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import netty.channelHandler.RpcfxProtocol;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author wincher
 * <p> io.kimmking.rpcfx.server <p>
 */
@Slf4j
@RequiredArgsConstructor
public class RpcServerHandler extends SimpleChannelInboundHandler<RpcfxProtocol> {

    @NonNull
    private final RpcfxResolver resolver;
    private static int successCount = 0;
    private static int errorCount = 0;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcfxProtocol msg) throws Exception {
        log.info("Netty server receive message:");
        log.info("Message length: " + msg.getLen());
        String content = new String(msg.getContent(), CharsetUtil.UTF_8);
        log.info("Message content: " + content);

        // 获取 RpcProtocol中的 RpcRequest内容，反序列化成 RpcRequest 对象
        RpcfxRequest rpcfxRequest = JSON.parseObject(content, RpcfxRequest.class);
        log.info("Netty server serializer : " + rpcfxRequest.toString());

        RpcfxResponse responseContent = invoke(rpcfxRequest);
        RpcfxProtocol response = new RpcfxProtocol();
        byte[] respInBytes = JSON.toJSONString(responseContent).getBytes(CharsetUtil.UTF_8);
        response.setLen(respInBytes.length);
        response.setContent(respInBytes);
        ctx.writeAndFlush(response).sync();
        log.info("return response to client end");
    }

    private RpcfxResponse invoke(RpcfxRequest request) {
        RpcfxResponse response = new RpcfxResponse();
        String serviceClass = request.getServiceClass();

        try {
            //DONE: 作业1：改成泛型和反射
            Object service = resolver.resolve(Class.forName(serviceClass));
            Method method = resolveMethodFromClass(service.getClass(), request.getMethod());
            //TODO: dubbo, fastjson
            Object result = method.invoke(service, request.getParams());
            //TODO: 两次json序列化能否合并成一个
            response.setResult(JSON.toJSONString(result, SerializerFeature.WriteClassName));
            response.setStatus(true);
            log.info("successCount: " + successCount++);
            //just for test exception thrown
            if (3 == successCount) {
                throw new IllegalAccessException("hahaha");
            }
            return response;
        } catch (IllegalAccessException | InvocationTargetException | ClassNotFoundException e) {

            // TODO: 3.Xstream

            // DONE: 2.封装一个统一的RpcfxException
            // DONE: 客户端也需要判断异常
            log.error(e.getMessage());
            response.setException(new RpcfxException(e.getMessage()));
            response.setStatus(false);
            log.info("errorCount: " + errorCount++);
            return response;
        }
    }

    private Method resolveMethodFromClass(Class<?> klass, String methodName) {
        return Arrays.stream(klass.getMethods()).filter(m -> methodName.equals(m.getName())).findFirst().get();
    }
}
