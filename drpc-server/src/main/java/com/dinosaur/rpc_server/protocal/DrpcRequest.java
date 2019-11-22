package com.dinosaur.rpc_server.protocal;

import java.io.Serializable;

public class DrpcRequest  implements Serializable {

    private static final  long serialVersionUID = 1L;


    private String serviceName;
    private String methodName;
    private Object[] methodTypes;
    private Object[] parameters;
}
