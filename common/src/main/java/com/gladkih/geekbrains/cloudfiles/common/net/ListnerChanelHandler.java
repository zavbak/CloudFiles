package com.gladkih.geekbrains.cloudfiles.common.net;

import java.io.Serializable;

public interface ListnerChanelHandler {
    Serializable onReceive(Serializable o);
    void exceptionNet(Throwable throwable);
}
