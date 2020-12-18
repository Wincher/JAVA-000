package io.kimmking.rpcfx.client.handler;

import com.alibaba.fastjson.JSON;
import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResponse;
import io.kimmking.rpcfx.client.HttpClient;
import io.kimmking.rpcfx.exception.RpcfxException;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;

/**
 * @author wincher
 * <p> io.kimmking.rpcfx.client.handler <p>
 */
public class RpcfxCglibHandler implements MethodInterceptor {
    private final Class<?> serviceClass;
    private final String url;
    @Autowired
    private HttpClient httpClient;

    public RpcfxCglibHandler(Class<?> serviceClass, String url) {
        this.serviceClass = serviceClass;
        this.url = url;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        RpcfxRequest request = new RpcfxRequest();
        request.setServiceClass(this.serviceClass.getName());
        request.setMethod(method.getName());
        request.setParams(objects);

        RpcfxResponse response = httpClient.post(request, url);
        if (!response.isStatus()) {
            throw response.getException();
        }
        // 这里判断response.status，处理异常
        // 考虑封装一个全局的RpcfxException

        return JSON.parse(response.getResult().toString());
    }
}
