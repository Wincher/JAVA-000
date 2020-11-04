package gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public interface RequestFilter {
    
    /**
     * filter
     * @param req req
     * @param ctx ctx
     */
    void filter(FullHttpRequest req, ChannelHandlerContext ctx);
}
