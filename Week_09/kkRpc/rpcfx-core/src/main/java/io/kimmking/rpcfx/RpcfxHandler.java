package io.kimmking.rpcfx;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author wincher
 * <p> io.kimmking.rpcfx.client.handler <p>
 */
public interface RpcfxHandler {

    Object handle(Method method, Object[] params, Class serviceClass, String requestUrl) throws IOException;
    }
