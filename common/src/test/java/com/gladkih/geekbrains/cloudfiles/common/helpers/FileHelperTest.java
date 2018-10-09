package com.gladkih.geekbrains.cloudfiles.common.helpers;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;

public class FileHelperTest {

    @Test
    public void getPathFolder() throws IOException {
        Path path = FileHelper.getPathFolder("alex");
        System.out.println(path);
    }

    @Test
    public void getTmpPathFolder() throws IOException {
        Path path = FileHelper.getPathTmpFolder("alex");
        System.out.println(path);
    }
}