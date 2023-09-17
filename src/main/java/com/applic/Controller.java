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
    private List<Integer> xPoints;
    private List<Integer> yPoints;
    int count;
    boolean isDraw = false;

    EventHandler<MouseEvent> addLineEvent = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            currentObject.addXPoint((int)mouseEvent.getX());
            currentObject.addYPoint((int)mouseEvent.getY());
            count++;
            if(count >= currentObject.getCountOfPoint()){
                currentObject.draw();
                if(!debug.isSelected()){
                    draw();
                }
                else {
                    isDraw = true;
                    index = 0;
                }
                canvas.removeEventFilter(MouseEvent.MOUSE_CLICKED, addLineEvent);
            }
        }
    };
    int dStep = 0;
    private void draw(){
        for(int i = 0; i < currentObject.getXPoints().size(); i++){
            if(debug.isSelected() ){
                if(i < Integer.parseInt(debugSize.getText()) * dStep ){//
                    canvas.getGraphicsContext2D().fillRect(currentObject.getXPoints().get(i),
                            currentObject.getYPoints().get(i), 1, 1);
                }
                else{
                    break;
                }
            }
            else {
                canvas.getGraphicsContext2D().fillRect(currentObject.getXPoints().get(i),
                        currentObject.getYPoints().get(i), 1, 1);
            }
        }
    }
    int index;
    private void drawD(){
        for(;index < currentObject.getXPoints().size() && index < Integer.parseInt(debugSize.getText()) * (dStep) ; index++){
            canvas.getGraphicsContext2D().fillRect(currentObject.getXPoints().get(index),
                    currentObject.getYPoints().get(index), 1, 1);
        }
        if(index == currentObject.getXPoints().size()){
            isDraw = false;
            dStep = 0;
        }
    }

    public void initialize() {
        drawables = new ArrayList<>();
        next.setOnAction(e ->{
            if(isDraw){
                dStep++;
                drawD();
            }
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