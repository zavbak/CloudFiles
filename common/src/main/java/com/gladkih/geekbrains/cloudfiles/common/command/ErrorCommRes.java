package com.gladkih.geekbrains.cloudfiles.common.command;

public class ErrorCommRes extends AbsCommand{
    String description;

    public ErrorCommRes(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "ErrorCommRes{" +
                "description='" + description + '\'' +
                '}';
    }
}
