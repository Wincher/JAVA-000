package io.kimmking.rpcfx.demo.consumer.configuration;

import io.kimmking.rpcfx.client.Rpcfx;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wincher
 * <p> io.kimmking.rpcfx.demo.consumer.configuration <p>
 */
@Configuration
public class BeanFactoryConfiguration {

    @Bean
    public Rpcfx rpcfx() {
        return new Rpcfx();
    }
}
