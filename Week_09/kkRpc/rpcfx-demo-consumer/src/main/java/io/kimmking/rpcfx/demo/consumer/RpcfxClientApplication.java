package io.kimmking.rpcfx.demo.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class RpcfxClientApplication {

	// 二方库
	// 三方库 lib
	// nexus, userserivce -> userdao -> user

	public static void main(String[] args) {
		SpringApplication.run(RpcfxClientApplication.class, args);
	}
}
