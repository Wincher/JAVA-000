package io.kimmking.rpcfx.exception;

/**
 * @author wincher
 * <p> io.kimmking.rpcfx.exception <p>
 */
public class RpcfxException extends RuntimeException {

    public RpcfxException() {
    }

    public RpcfxException(String message) {
        super(message);
    }

    public RpcfxException(String message, Throwable cause) {
        super(message, cause);
    }

    public RpcfxException(Throwable cause) {
        super(cause);
    }
}
