package gateway.outbound.common;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public interface ProxyClient {
    
    void doGetRequest(String ip, int port, String url, FullHttpRequest origReq, ChannelHandlerContext ctx);
}
