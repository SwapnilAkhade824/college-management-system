package com.cms;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("College Management System");
        stage.setScene(new Scene(new Label("System Boot Successful"), 400, 400));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}