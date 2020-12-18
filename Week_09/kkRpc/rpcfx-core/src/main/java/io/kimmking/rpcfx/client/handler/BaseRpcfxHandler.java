package io.kimmking.rpcfx.client.handler;

import com.alibaba.fastjson.JSON;
import io.kimmking.rpcfx.RpcfxHandler;
import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResponse;
import io.kimmking.rpcfx.client.HttpClient;
import io.kimmking.rpcfx.exception.RpcfxException;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author wincher
 * <p> io.kimmking.rpcfx.client.handler <p>
 */
public class BaseRpcfxHandler implements RpcfxHandler {

    HttpClient httpClient = new HttpClient();

    @Override
    public Object handle(Method method, Object[] params, Class serviceClass, String requestUrl) throws IOException {
        RpcfxRequest request = new RpcfxRequest();
        request.setServiceClass(serviceClass.getName());
        request.setMethod(method.getName());
        request.setParams(params);

        RpcfxResponse response = httpClient.post(request, requestUrl);
        if (!response.isStatus()) {
            throw new RpcfxException(response.getException());
        }
        // 这里判断response.status，处理异常
        // 考虑封装一个全局的RpcfxException

        return JSON.parse(response.getResult().toString());

    }
}
