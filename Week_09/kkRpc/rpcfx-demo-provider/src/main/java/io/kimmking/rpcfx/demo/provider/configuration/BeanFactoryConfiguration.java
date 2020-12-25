package io.kimmking.rpcfx.demo.provider.configuration;

import io.kimmking.rpcfx.api.RpcfxResolver;
import io.kimmking.rpcfx.demo.api.OrderService;
import io.kimmking.rpcfx.demo.api.UserService;
import io.kimmking.rpcfx.demo.provider.DemoResolver;
import io.kimmking.rpcfx.demo.provider.OrderServiceImpl;
import io.kimmking.rpcfx.demo.provider.UserServiceImpl;
import io.kimmking.rpcfx.server.RpcfxInvoker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wincher
 * <p> io.kimmking.rpcfx.demo.provider.configuration <p>
 */
@Configuration
public class BeanFactoryConfiguration {

    @Bean
    public RpcfxInvoker createInvoker(@Autowired RpcfxResolver resolver){
        return new RpcfxInvoker(resolver);
    }

    @Bean
    public RpcfxResolver createResolver(){
        return new DemoResolver();
    }

    // DONE: 能否去掉name
    @Bean(name = "io.kimmking.rpcfx.demo.api.UserService")
    public UserService createUserService(){
        return new UserServiceImpl();
    }

    @Bean(name = "io.kimmking.rpcfx.demo.api.OrderService")
    public OrderService createOrderService(){
        return new OrderServiceImpl();
    }
}
