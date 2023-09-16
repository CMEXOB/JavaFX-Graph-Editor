package com.applic.events;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class AddLineEvent {

    private int count;
    private double x;
    private double y;
    public static EventHandler<MouseEvent> event = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {

        }
    };
}
