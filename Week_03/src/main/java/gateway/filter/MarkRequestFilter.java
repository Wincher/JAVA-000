package gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public class MarkRequestFilter implements RequestFilter {

    @Override
    public void filter (FullHttpRequest req, ChannelHandlerContext ctx) {
        req.headers().add("from", "JavaCamp");
    }
}
