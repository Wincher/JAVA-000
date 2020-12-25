package io.kimmking.rpcfx.client;

import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResponse;
import okhttp3.MediaType;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author wincher
 * <p> io.kimmking.rpcfx.client <p>
 */
public interface RpcClient {
    public static final MediaType JSONTYPE = MediaType.get("application/json; charset=utf-8");

    RpcfxResponse post(RpcfxRequest req, String url) throws IOException, URISyntaxException, InterruptedException;
}
