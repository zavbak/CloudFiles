package com.gladkih.geekbrains.cloudfiles.common.helpers;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHelper {

    static String PATH_GENERAL_FOLDER = "data";

    /**
     * Возвращает страницу байт фйла с первой
     *
     * @param lenPage -длинна страницы
     * @param file    - путь
     * @return
     * @throws IOException
     */
    public static byte[] getPartFile(Path file,long seek, int lenPage) throws IOException {
        byte[] data;
        try (RandomAccessFile raf = new RandomAccessFile(file.toString(), "r")) {

            data = new byte[(int) lenPage];
            raf.seek(seek);
            raf.read(data);
        }
        return data;
    }

    public static long getLenthFile(String file) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
           return raf.length();
        }
    }



    public static void saveFile(String file, byte[] data, long seek) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            raf.seek(seek);
            raf.write(data);
        }
    }

    public static Path getPathFolder(String pathStr) throws IOException {
        Path path = Paths.get(PATH_GENERAL_FOLDER + File.separator + pathStr);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        return path;
    }

    public static Path getPathTmpFolder(String pathStr) throws IOException {
        Path folder = getPathFolder(pathStr);
        Path pathTmp = Paths.get(folder.toString() + File.separator + "Tmp");

        if (!Files.exists(pathTmp)) {
            Files.createDirectories(pathTmp);
        }
        return pathTmp;
    }


}
