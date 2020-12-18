package io.kimmking.rpcfx.client.handler;

import javassist.util.proxy.MethodHandler;
import lombok.Setter;

import java.lang.reflect.Method;

/**
 * @author wincher
 * <p> io.kimmking.rpcfx.client.handler <p>
 */
@Setter
public class RpcfxJavassistMethodHandler extends BaseRpcfxHandler implements MethodHandler {

    private final Class<?> serviceClass;
    private final String url;

    public RpcfxJavassistMethodHandler(Class<?> serviceClass, String url) {
        this.serviceClass = serviceClass;
        this.url = url;
    }

    @Override
    public Object invoke(Object o, Method method, Method method1, Object[] params) throws Throwable {
        return handle(method, params, this.serviceClass, this.url);
    }
}
