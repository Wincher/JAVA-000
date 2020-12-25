package netty.channelHandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author wincher
 * <p> netty.channelHandler <p>
 */
@Slf4j
public class RpcfxDecoder extends ByteToMessageDecoder {

    private int length = 0;
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        log.info("Netty decode run..");
        if (in.readableBytes() < 4) {
            return;
        }
        if (0 == length) {
            length = in.readInt();
        }
        if (length > in.readableBytes()) {
            log.info("Readable data is not finished receiving.");
            return;
        }
        byte[] content = new byte[length];
        if (length <= in.readableBytes()) {
            in.readBytes(content);
            RpcfxProtocol rpcfxProtocol = new RpcfxProtocol();
            rpcfxProtocol.setLen(length);
            rpcfxProtocol.setContent(content);
            out.add(rpcfxProtocol);
        }
        length = 0;

    }
}
