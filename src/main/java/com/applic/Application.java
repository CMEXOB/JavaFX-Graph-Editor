package com.applic;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Getter
    private static final int height = 640;
    @Getter
    private static final int width = 840;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("App.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("MAGE");
        stage.setScene(scene);
        stage.show();
        stage.setHeight(height);
        stage.setWidth(width);
    }

    public static void main(String[] args) {
        launch();
    }
}