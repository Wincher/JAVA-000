package io.kimmking.rpcfx.demo.provider.controller;

import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResponse;
import io.kimmking.rpcfx.server.RpcfxInvoker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * notice: used for http request, make this work should remove application.yml web-application-type: none
 * @author wincher
 * <p> io.kimmking.rpcfx.demo.provider.controller <p>
 */
@RestController("/")
public class Root {

    @Autowired
    RpcfxInvoker invoker;

    @PostMapping
    public RpcfxResponse invoke(@RequestBody RpcfxRequest request) {
        return invoker.invoke(request);
    }
}
