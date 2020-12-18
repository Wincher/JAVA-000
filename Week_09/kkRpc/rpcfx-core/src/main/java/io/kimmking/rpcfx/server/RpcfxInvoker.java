package io.kimmking.rpcfx.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResolver;
import io.kimmking.rpcfx.api.RpcfxResponse;
import io.kimmking.rpcfx.exception.RpcfxException;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

@Slf4j
public class RpcfxInvoker {

    private RpcfxResolver resolver;
    private int successCount = 0;
    private int errorCount = 0;

    public RpcfxInvoker(RpcfxResolver resolver){
        this.resolver = resolver;
    }

    public RpcfxResponse invoke(RpcfxRequest request) {
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
