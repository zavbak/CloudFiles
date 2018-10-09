package com.gladkih.geekbrains.cloudfiles.client;

import com.gladkih.geekbrains.cloudfiles.client.mvc.view.MainControllerView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainView extends Application {


    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/sample.fxml"));

        Parent root = loader.load();
        primaryStage.setTitle("Cloud EchoClient");
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();

        MainControllerView controller = loader.getController();

        primaryStage.setOnCloseRequest(controller.getCloseEventHandler());
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
