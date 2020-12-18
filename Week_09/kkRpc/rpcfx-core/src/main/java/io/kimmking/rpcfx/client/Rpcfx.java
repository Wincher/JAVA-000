package io.kimmking.rpcfx.client;


import com.alibaba.fastjson.parser.ParserConfig;
import io.kimmking.rpcfx.client.handler.RpcfxCglibHandler;
import io.kimmking.rpcfx.client.handler.RpcfxJavassistMethodHandler;
public final class Rpcfx {

    static {
        ParserConfig.getGlobalInstance().addAccept("io.kimmking");
    }

    public <T> T create(final Class<T> serviceClass, final String url) {

        //0. 替换动态代理 -> AOP
        //Proxy.JDK.newProxy(serviceClass, new RpcfxInvocationHandler(serviceClass, url));
        Proxy.CGLIB.newProxy(serviceClass, new RpcfxCglibHandler(serviceClass, url)) ;
        return Proxy.JAVASSIST.newProxy(serviceClass, new RpcfxJavassistMethodHandler(serviceClass, url)) ;

    }
}
