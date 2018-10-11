package com.gladkih.geekbrains.cloudfiles.common.helpers;

import com.gladkih.geekbrains.cloudfiles.common.command.SendFileCommReq;
import com.gladkih.geekbrains.cloudfiles.common.command.SendFileCommRes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HelperSendFiles {

    private static HelperSendFiles instance;

    private static Path file;
    private static SenderFile senderFile;
    private static List<PartFiles> queue;
    private static int partByteSize;
    private static int sendParts;
    private static int hasCodelast;

    private static boolean stateSend;


    private HelperSendFiles() {
    }


    public static synchronized HelperSendFiles getInstance() {


        if (instance == null) {
            instance = new HelperSendFiles();
        }

        instance.partByteSize = partByteSize;
        instance.queue = new ArrayList<>();

        return instance;
    }

    public void sendFile(Path file, SenderFile senderFile, int partByteSize) throws IOException {

        if (partByteSize == 0) {
            return;
        }

        instance.file = file;
        instance.senderFile = senderFile;
        instance.partByteSize = partByteSize;

        instance.initFile();
        instance.sendNextPart();
    }


    /**
     * Создаем части для отправки
     *
     * @throws IOException
     */
    private void initFile() throws IOException {
        long sizeFull = Files.size(file);
        long seek = 0;
        long part = 0;
        queue.clear();

        while (sizeFull != seek) {
            int sizePart = (int) Math.min(partByteSize, sizeFull - seek);

            PartFiles partFiles = new PartFiles(
                    seek,
                    sizePart,
                    part);

            seek += sizePart;
            part++;

            queue.add(partFiles);
        }

        stateSend = true;
        sendParts = 0;
        hasCodelast = 0;
    }

    /**
     * Отвечаем на ответ
     *
     * @param sendFileCommRes
     * @throws IOException
     */
    public void recivedReply(SendFileCommRes sendFileCommRes) throws IOException {

        if (sendFileCommRes.getFileName().equals(file.getFileName().toString())) {
            initFile();
        }

        if (sendFileCommRes.getPart() != sendParts) {
            initFile();
        }


        if (sendFileCommRes.getHasCode() != hasCodelast) {
            initFile();
        }

        if (sendFileCommRes.getError()) {
            initFile();
        }

        if (sendFileCommRes.getPart() > queue.size()) {
            initFile();
        }


        if (sendFileCommRes.getPart() == queue.size()) {
            finish(sendFileCommRes.getHasCodeFiles());
        }

        sendNextPart();
    }

    public void sendNextPart() throws IOException {

        if (!stateSend) {
            return;
        }
        if (queue.size() == 0) {
            stateSend = false;
            return;
        }

        sendParts++;

        int i = sendParts - 1;

        byte[] data = FileHelper.getPartFile(
                file
                , queue.get(i).seek
                , partByteSize);


        SendFileCommReq sendFileCommReq = new SendFileCommReq(
                file.toString()
                , data
                , queue.get(i).seek
                , sendParts
                , Arrays.hashCode(data));

        hasCodelast = sendFileCommReq.getHasCode();
        senderFile.sendFile(sendFileCommReq);

    }


    private void finish(int hasCode) throws IOException {
        if (new File(file.toString()).hashCode() == hasCode) {
            stateSend = false;
        } else {
            initFile();
        }
    }

    public void interrupt(){
        stateSend = false;
    }


    class PartFiles {
        long seek;
        int sizePart;
        long part;

        public PartFiles(long seek, int sizePart, long part) {
            this.seek = seek;
            this.sizePart = sizePart;
            this.part = part;
        }

        public long getSeek() {
            return seek;
        }

        public int getSizePart() {
            return sizePart;
        }

        public long getPart() {
            return part;
        }
    }


}
