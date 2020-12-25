package io.kimmking.rpcfx.client.handler;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author wincher
 * <p> io.kimmking.rpcfx.client.handler <p>
 */
public class RpcfxCglibHttpHandler extends BaseRpcfxHttpHandler implements MethodInterceptor {
    private final Class<?> serviceClass;
    private final String url;

    public RpcfxCglibHttpHandler(Class<?> serviceClass, String url) {
        this.serviceClass = serviceClass;
        this.url = url;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] params, MethodProxy methodProxy) throws Throwable {
        return handle(method, params, this.serviceClass, this.url);

    }
}
