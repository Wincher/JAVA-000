package io.kimmking.rpcfx.demo.consumer.listener;

import io.kimmking.rpcfx.client.Rpcfx;
import io.kimmking.rpcfx.demo.api.Order;
import io.kimmking.rpcfx.demo.api.OrderService;
import io.kimmking.rpcfx.demo.api.User;
import io.kimmking.rpcfx.demo.api.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author wincher
 * <p> io.kimmking.rpcfx.demo.consumer.listener <p>
 */
@Slf4j
@Component
public class InitRequest implements ApplicationListener<ApplicationReadyEvent> {

    @Value("${server_url}")
    private String HTTP_LOCALHOST;
    private static boolean isStart = false;
    @Autowired
    private Rpcfx rpcfx;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        if (isStart) {
            log.info("already executed");
            return;
        }
        // UserService service = new xxx();
        // service.findById
        for (int i = 0; i < 100; i++) {
            try {
                UserService userService = rpcfx.create(UserService.class, HTTP_LOCALHOST);
                User user = userService.findById(1);
                System.out.println("find user id=1 from server: " + user.getName());
            } catch (Exception e) {
                log.error(e.getMessage());
            }
            try {
                //DONE 新加一个OrderService
                OrderService orderService = rpcfx.create(OrderService.class, HTTP_LOCALHOST);
                Order order = orderService.findOrderById(1992129);
                System.out.println(String.format("find order name=%s, amount=%f", order.getName(), order.getAmount()));
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }
}
