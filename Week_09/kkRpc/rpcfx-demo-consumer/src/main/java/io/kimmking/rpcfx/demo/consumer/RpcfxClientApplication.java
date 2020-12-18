package io.kimmking.rpcfx.demo.consumer;

import io.kimmking.rpcfx.client.Rpcfx;
import io.kimmking.rpcfx.demo.api.Order;
import io.kimmking.rpcfx.demo.api.OrderService;
import io.kimmking.rpcfx.demo.api.User;
import io.kimmking.rpcfx.demo.api.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class RpcfxClientApplication {

	// 二方库
	// 三方库 lib
	// nexus, userserivce -> userdao -> user

	public static void main(String[] args) {

		// UserService service = new xxx();
		// service.findById

		for (int i = 0; i < 100; i++) {
			try {
				UserService userService = Rpcfx.create(UserService.class, "http://localhost:8080/");
				User user = userService.findById(1);
				System.out.println("find user id=1 from server: " + user.getName());
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			try {
				//DONE 新加一个OrderService
				OrderService orderService = Rpcfx.create(OrderService.class, "http://localhost:8080/");
				Order order = orderService.findOrderById(1992129);
				System.out.println(String.format("find order name=%s, amount=%f", order.getName(), order.getAmount()));
			} catch (Exception e) {
				log.error(e.getMessage());
			}
//		SpringApplication.run(RpcfxClientApplication.class, args);
		}

	}

}
