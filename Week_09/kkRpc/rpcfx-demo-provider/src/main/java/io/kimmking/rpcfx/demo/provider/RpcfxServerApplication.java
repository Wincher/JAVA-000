package io.kimmking.rpcfx.demo.provider;

import io.kimmking.rpcfx.api.RpcfxResolver;
import io.kimmking.rpcfx.server.RpcfxInvoker;
import io.kimmking.rpcfx.server.RpcfxNettyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RpcfxServerApplication implements ApplicationRunner {

	@Autowired
	RpcfxResolver resolver;
	@Value("${netty_server_port}")
	private int port;

	public static void main(String[] args) {
		SpringApplication.run(RpcfxServerApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		new RpcfxNettyServer(resolver, port).run();
	}
}
