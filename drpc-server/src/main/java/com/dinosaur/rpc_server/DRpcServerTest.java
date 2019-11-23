package com.dinosaur.rpc_server;

import com.crown.serverice.impl.MemberServiceImpl;
import com.dinosaur.rpc_server.server.DRpcServer;

public class DRpcServerTest {
    public static void main(String[] args) {
        DRpcServer dRpcServer =  new DRpcServer();
        dRpcServer.start(new MemberServiceImpl());
    }
}
