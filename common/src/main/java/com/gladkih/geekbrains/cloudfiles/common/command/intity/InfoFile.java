package com.gladkih.geekbrains.cloudfiles.common.command.intity;

import java.io.Serializable;

public class InfoFile implements Serializable {
    String name;

    public InfoFile(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "InfoFile{" +
                "name='" + name + '\'' +
                '}';
    }
}
