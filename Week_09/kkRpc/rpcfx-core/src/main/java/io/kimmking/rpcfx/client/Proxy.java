package io.kimmking.rpcfx.client;

import com.google.common.base.Preconditions;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;
import lombok.SneakyThrows;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import java.lang.reflect.InvocationHandler;

/**
 * Multiple implements of proxy.
 * @author wincher
 * <p> io.kimmking.rpcfx.client <p>
 * Multiple implements of proxy.
 */
public enum Proxy {

    JDK(new ProxyDelegate() {
        @Override
        public <T> T newProxy(Class<T> clazz, Object handler) {
            Preconditions.checkArgument(InvocationHandler.class.isInstance(handler), Constants.INAPPROPRIATE_HANDLER);
            return clazz.cast(java.lang.reflect.Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, (InvocationHandler) handler));
        }
    }),
    CGLIB(new ProxyDelegate() {
        @Override
        public <T> T newProxy(Class<T> clazz, Object handler) {
            Preconditions.checkArgument(MethodInterceptor.class.isInstance(handler), Constants.INAPPROPRIATE_HANDLER);
            Enhancer enhancer =new Enhancer();
            enhancer.setSuperclass(clazz);
            enhancer.setCallback((Callback) handler);
            enhancer.setInterfaces(new Class[]{clazz});
            return clazz.cast(enhancer.create());
        }
    }),
    BYTE_BUDDY(new ProxyDelegate() {
        @Override
        public <T> T newProxy(Class<T> clazz, Object handler) {
            throw new IllegalStateException("not implement");
        }
    }),
    JAVASSIST(new ProxyDelegate() {
        @SneakyThrows
        @Override
        public <T> T newProxy(Class<T> clazz, Object handler) {
            Preconditions.checkArgument(handler instanceof MethodHandler, "handler must be a MethodHandler");
            ProxyFactory proxyFactory = new ProxyFactory();
            proxyFactory.setInterfaces(new Class[]{clazz});
            Class c = proxyFactory.createClass();
            Object result = c.newInstance();
            ((ProxyObject) result).setHandler((MethodHandler) handler);
            return clazz.cast(result);
        }
    }),
    ;


    private final ProxyDelegate delegate;

    Proxy(ProxyDelegate delegate) {
        this.delegate = delegate;
    }
    <T> T newProxy(Class<T> clazz, Object handler) {
        return delegate.newProxy(clazz, handler);
    }


    interface ProxyDelegate {
        <T> T newProxy(Class<T> clazz, Object handler);
    }

    private static class Constants {
        public static final String INAPPROPRIATE_HANDLER = "Inappropriate handler";
    }
}
