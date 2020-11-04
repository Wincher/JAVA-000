package gateway;

import gateway.inbound.HttpInboundServer;

public class NettyApplication {
    
    public static void main(String[] args) {

        System.out.println("Gateway starting...");

        HttpInboundServer server = new HttpInboundServer(8801);
        try {
            server.run();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
