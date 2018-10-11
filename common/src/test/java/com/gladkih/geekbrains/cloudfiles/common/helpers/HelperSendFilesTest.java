package com.gladkih.geekbrains.cloudfiles.common.helpers;

import com.gladkih.geekbrains.cloudfiles.common.command.SendFileCommReq;
import com.gladkih.geekbrains.cloudfiles.common.command.SendFileCommRes;
import io.reactivex.Completable;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class HelperSendFilesTest {

    public static SendFileCommRes getSendFileCommRes(SendFileCommReq sendFileCommReq) {
        SendFileCommRes sendFileCommRes = new SendFileCommRes(
                false
                , null
                , sendFileCommReq.getPart()
                , sendFileCommReq.getFileName()
                , sendFileCommReq.getHasCode()
                , 2003469736
        );

        return sendFileCommRes;
    }


    @Test
    public void sendFile() throws IOException {


        System.out.println("Start!");
        HelperSendFiles helperSendFiles = HelperSendFiles.getInstance();


        helperSendFiles.sendFile(
                Paths.get("C:" + File.separator + "Test" + File.separator + "Test.txt"),
                sendFileCommReq -> {
                    System.out.println(new String(sendFileCommReq.getData()));
                    SendFileCommRes sendFileCommRes = getSendFileCommRes(sendFileCommReq);
                    helperSendFiles.recivedReply(sendFileCommRes);
                },
                50);

    }
}