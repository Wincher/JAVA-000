package io.kimmking.rpcfx.client;


import com.alibaba.fastjson.parser.ParserConfig;
import io.kimmking.rpcfx.client.handler.RpcfxCglibHttpHandler;
import io.kimmking.rpcfx.client.handler.RpcfxCglibNettyHandler;
import io.kimmking.rpcfx.client.handler.RpcfxInvocationHttpHandler;
import io.kimmking.rpcfx.client.handler.RpcfxJavassistMethodHttpHandler;
public final class Rpcfx {

    static {
        ParserConfig.getGlobalInstance().addAccept("io.kimmking");
    }

    public <T> T create(final Class<T> serviceClass, final String url) {

//        0. 替换动态代理 -> AOP
//        Proxy.JDK.newProxy(serviceClass, new RpcfxInvocationHttpHandler(serviceClass, url));
//        Proxy.CGLIB.newProxy(serviceClass, new RpcfxCglibHttpHandler(serviceClass, url)) ;
        return Proxy.CGLIB.newProxy(serviceClass, new RpcfxCglibNettyHandler(serviceClass, url)) ;
//        return Proxy.JAVASSIST.newProxy(serviceClass, new RpcfxJavassistMethodHttpHandler(serviceClass, url)) ;

    }
}
