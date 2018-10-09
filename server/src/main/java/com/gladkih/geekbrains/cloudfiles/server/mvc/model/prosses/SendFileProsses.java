package com.gladkih.geekbrains.cloudfiles.server.mvc.model.prosses;

import com.gladkih.geekbrains.cloudfiles.common.command.GetInfoFilesRes;
import com.gladkih.geekbrains.cloudfiles.common.command.SendFileCommReq;
import com.gladkih.geekbrains.cloudfiles.common.helpers.FileHelper;
import com.gladkih.geekbrains.cloudfiles.server.mvc.model.Model;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URLDecoder;
import java.nio.file.Path;

public class SendFileProsses {
    public static Serializable response(Model model, SendFileCommReq sendFileCommReq) throws IOException {
        if (model.getUser() == null){
            return new GetInfoFilesRes(true,"Не авторизованы", null);
        }

        Path pathFolder = FileHelper.getPathFolder(model.getUser().getLogin());
        FileHelper.saveFile(pathFolder.toString() + File.separator + sendFileCommReq.getFileName(),
                sendFileCommReq.getData(),sendFileCommReq.getSeek());

        return "Записали";


    }
}
