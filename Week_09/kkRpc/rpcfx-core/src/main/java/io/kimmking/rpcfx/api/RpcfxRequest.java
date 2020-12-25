package io.kimmking.rpcfx.api;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class RpcfxRequest {

  private String serviceClass;

  private String method;

  private Object[] params;
}
