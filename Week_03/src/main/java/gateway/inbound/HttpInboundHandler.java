package gateway.inbound;

import gateway.filter.RequestFilter;
import gateway.outbound.netty4.AsyncNettyHttpClientGet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;

import java.net.URI;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class HttpInboundHandler extends ChannelInboundHandlerAdapter {

    private AsyncNettyHttpClientGet nettyClient;
    List<RequestFilter> filters = new LinkedList<>();

    public HttpInboundHandler() {
        nettyClient = new AsyncNettyHttpClientGet();
    }
    
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            FullHttpRequest fullRequest = (FullHttpRequest) msg;
            for (RequestFilter filter : this.filters) {
                filter.filter(fullRequest, ctx);
            }
            System.out.println(filters);
            final String url = Arrays.asList("http://localhost:8081", "http://localhost:8082", "http://localhost:8083").get(new Random().nextInt(3)) + fullRequest.uri();
            URI uri = new URI(url);
            System.out.println("URL: " + url);
            nettyClient.doGetRequest(uri.getHost(), uri.getPort(), url, fullRequest, ctx);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }
}
