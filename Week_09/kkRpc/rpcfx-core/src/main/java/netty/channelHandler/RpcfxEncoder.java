package netty.channelHandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wincher
 * <p> netty.channelHandler <p>
 */
@Slf4j
public class RpcfxEncoder extends MessageToByteEncoder<RpcfxProtocol> {

    @Override
    protected void encode(ChannelHandlerContext ctx, RpcfxProtocol msg, ByteBuf out) throws Exception {
        log.info("Rpcfx customize protocol encode..");
        out.writeInt(msg.getLen());
        out.writeBytes(msg.getContent());
    }
}
