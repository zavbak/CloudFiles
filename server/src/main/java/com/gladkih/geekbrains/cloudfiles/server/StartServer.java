package com.gladkih.geekbrains.cloudfiles.server;

import com.gladkih.geekbrains.cloudfiles.common.net.Network;
import com.gladkih.geekbrains.cloudfiles.server.net.ServerNetty;

import java.sql.SQLException;

public class StartServer {


    public static void main(String[] args) {
        Network network = null;
        try {
            network = new ServerNetty(8189);

            network.start()
                    .subscribe(() -> {
                        System.out.println("Server is work!");
                    },throwable -> {
                        System.out.println("Error start!");
                        throwable.printStackTrace();
                    });

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
