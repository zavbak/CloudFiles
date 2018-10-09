package com.gladkih.geekbrains.cloudfiles.server.mvc.model.prosses;

import com.gladkih.geekbrains.cloudfiles.common.command.GetInfoFilesReq;
import com.gladkih.geekbrains.cloudfiles.common.command.GetInfoFilesRes;
import com.gladkih.geekbrains.cloudfiles.common.command.intity.InfoFile;
import com.gladkih.geekbrains.cloudfiles.common.helpers.FileHelper;
import com.gladkih.geekbrains.cloudfiles.server.mvc.model.Model;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class GetInfoFilesProsses {

    public static GetInfoFilesRes response(Model model) throws IOException {

        if (model.getUser() == null){
            return new GetInfoFilesRes(true,"Не авторизованы", null);
        }

        Path pathFolder = FileHelper.getPathFolder(model.getUser().getLogin());

        List<File> fileList = Files.walk(pathFolder)
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .collect(Collectors.toList());

        InfoFile[] infoFiles = new InfoFile[fileList.size()];
        for (int i = 0; i < fileList.size(); i++) {
            infoFiles[i] = new InfoFile(fileList.get(i).getName());
        }

        return new GetInfoFilesRes(false,null, infoFiles);

    }
}
