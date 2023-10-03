package com.applic;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AboutPopup {
    public static void display()
    {
        Stage popupWindow =new Stage();
        popupWindow.initModality(Modality.APPLICATION_MODAL);
        popupWindow.setTitle("О программе");

        Label info = new Label("Программа MAGE(MAth Graphic Editor) была разработана Скрипко Егором Олеговичев в качестве лабараторной работы по курсу \"Графический интерфейс интеллектуальных систем\"");
        info.setWrapText(true);
        info.setPadding(new Insets(15));
        info.setTextAlignment(TextAlignment.CENTER);
        Button closeButton = new Button("Закрыть");
        closeButton.setOnAction(e -> popupWindow.close());

        VBox layout= new VBox(10);
        layout.getChildren().addAll(info, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene1= new Scene(layout, 380, 250);

        popupWindow.setScene(scene1);

        popupWindow.showAndWait();

    }
}
