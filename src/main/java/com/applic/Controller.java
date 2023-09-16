package com.applic;

import com.applic.entity.Drawable;
import com.applic.entity.DrawableObject;
import com.applic.entity.lines.BresenhamLine;
import com.applic.entity.lines.WuLine;
import com.applic.entity.lines.ZdaLine;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    public TextField debugSize;
    public CheckBox debug;
    public Button next;
    @FXML
    private Canvas canvas;
    List<Drawable> drawables;
    private DrawableObject currentObject;
    int count;

    EventHandler<MouseEvent> addLineEvent = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent mouseEvent) {
            currentObject.addXPoint(mouseEvent.getX());
            currentObject.addYPoint(mouseEvent.getY());
            count++;
            if(count >= currentObject.getCountOfPoint()){
                draw();
                canvas.removeEventFilter(MouseEvent.MOUSE_CLICKED, addLineEvent);
            }
        }
    };
    int dStep = 0;
    private void draw(){
        double max = Math.max(Math.abs(currentObject.getXPoints().get(0) - currentObject.getXPoints().get(1)),
                Math.abs(currentObject.getYPoints().get(0) - currentObject.getYPoints().get(1)));
        double dx = (currentObject.getXPoints().get(1) - currentObject.getXPoints().get(0)) / max;
        double dy = (currentObject.getYPoints().get(1) - currentObject.getYPoints().get(0)) / max;
        double x = currentObject.getXPoints().get(0) + 0.5 * Math.signum(dx);
        double y = currentObject.getYPoints().get(0) + 0.5 * Math.signum(dy);
        for(int i = 0; i < max; i++){
            x = x + dx;
            y = y + dy;
            if(debug.isSelected() ){
                if(i < Integer.parseInt(debugSize.getText()) * dStep ){//
                    canvas.getGraphicsContext2D().fillRect((int)x, (int)y, 1, 1);
                }
                else{
                    break;
                }
            }
            else {
                canvas.getGraphicsContext2D().fillRect((int)x, (int)y, 1, 1);
            }
        }
    }

    public void initialize() {
        drawables = new ArrayList<>();
        next.setOnAction(e ->{
           dStep++;
           draw();
        });
    }

    public void createLine(int type) {
        switch (type){
            case 0:
                currentObject = new ZdaLine();
                break;
            case 1:
                currentObject = new BresenhamLine();
                break;
            case 2:
                currentObject = new WuLine();
                break;
        }
        count = 0;
        drawables.add(currentObject);

        canvas.addEventFilter(MouseEvent.MOUSE_CLICKED, addLineEvent);
    }

    public void createZdaLine(ActionEvent actionEvent) {
        createLine(0);
    }


    public void createBresenhamLine(ActionEvent actionEvent) {
        createLine(1);
    }

    public void createWuLine(ActionEvent actionEvent) {
        createLine(2);
    }
}