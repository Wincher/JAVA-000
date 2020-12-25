package io.kimmking.rpcfx.client.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author wincher
 * <p> io.kimmking.rpcfx.client <p>
 */
public class RpcfxInvocationHttpHandler extends BaseRpcfxHttpHandler implements InvocationHandler {

    private final Class<?> serviceClass;
    private final String url;

    public <T> RpcfxInvocationHttpHandler(Class<T> serviceClass, String url) {
        this.serviceClass = serviceClass;
        this.url = url;
    }

    // TODO:可以尝试，自己去写对象序列化，二进制还是文本的，，，rpcfx是xml自定义序列化、反序列化，json: code.google.com/p/rpcfx
    // int byte char float double long bool
    // [], data class

    @Override
    public Object invoke(Object proxy, Method method, Object[] params) throws Throwable {
        return handle(method, params, this.serviceClass, this.url);
    }
}