package io.kimmking.rpcfx.client;

import com.alibaba.fastjson.JSON;
import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResponse;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author wincher
 * <p> io.kimmking.rpcfx.client <p>
 */
public class HttpClient implements RpcClient {

    public static final MediaType JSONTYPE = MediaType.get("application/json; charset=utf-8");
    // DONE: 1.可以复用client
    private static OkHttpClient client = new OkHttpClient();

    @Override
    public RpcfxResponse post(RpcfxRequest req, String url) throws IOException {
        String reqJson = encode(req);
        System.out.println("req json: "+reqJson);

        final Request request = new Request.Builder()
            .url(url)
            .post(RequestBody.create(JSONTYPE, reqJson))
            .build();
        String respJson = client.newCall(request).execute().body().string();
        System.out.println("resp json: "+respJson);
        return JSON.parseObject(respJson, RpcfxResponse.class);
    }

    private static String encode(RpcfxRequest req) {
        return JSON.toJSONString(req);
    }
}
