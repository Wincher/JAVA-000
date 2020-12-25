package netty.channelHandler;

import lombok.Data;

/**
 * @author wincher
 * <p> netty.channelHandler <p>
 */
@Data
public class RpcfxProtocol {

    private int len;

    private byte[] content;
}
