package io.kimmking.rpcfx.client;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResponse;
import io.kimmking.rpcfx.client.handler.RpcfxCglibHandler;
import io.kimmking.rpcfx.client.handler.RpcfxInvocationHandler;
import io.kimmking.rpcfx.client.handler.RpcfxJavassistMethodHandler;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public final class Rpcfx {

    static {
        ParserConfig.getGlobalInstance().addAccept("io.kimmking");
    }

    public static <T> T create(final Class<T> serviceClass, final String url) {

        //0. 替换动态代理 -> AOP
        //Proxy.JDK.newProxy(serviceClass, new RpcfxInvocationHandler(serviceClass, url));
        Proxy.CGLIB.newProxy(serviceClass, new RpcfxCglibHandler(serviceClass, url)) ;
        return Proxy.JAVASSIST.newProxy(serviceClass, new RpcfxJavassistMethodHandler(serviceClass, url)) ;

    }
}
