package com.gladkih.geekbrains.cloudfiles.client.mvc.model;

public class Config {

    private final int LEN_PAGE_BUTE = 3;

    public String getHost(){
       return "localhost";
    }

    public int getPort(){
        return 8189;
    }

    public String getLogin(){
        return "alex1976";
    }

    public int getPassHashCode(){
        return "gfhgvjh".hashCode();
    }

    public int getLenPage() {
        return LEN_PAGE_BUTE;
    }
}
