package com.gladkih.geekbrains.cloudfiles.common.helpers;

import com.gladkih.geekbrains.cloudfiles.common.command.SendFileCommReq;
import io.reactivex.Completable;

import java.io.IOException;

public interface SenderFile {
    void sendFile(SendFileCommReq sendFileCommReq) throws IOException;
}
