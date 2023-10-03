package com.applic;

import com.applic.entity.DrawableObject;
import com.applic.entity.Point;
import com.applic.entity.lines.BresenhamLine;
import com.applic.entity.lines.WuLine;
import com.applic.entity.lines.ZdaLine;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    public TextField debugSize;
    public CheckBox isDebug;
    public Button next;
    public VBox sc;
    public MenuBar menu;
    public CheckBox isDelete;
    public CheckBox isScale;
    @FXML
    private Canvas canvas;
    List<DrawableObject> drawables;
    private DrawableObject currentObject;
    int count;
    boolean isDraw = false;
    int index;
    int dStep = 0;

    EventHandler<MouseEvent> addLineEvent = new EventHandler<MouseEvent>() {

        Point first;
        Point second;
        public void clear(){
            first = null;
            second = null;
        }
        @Override
        public void handle(MouseEvent mouseEvent) {
            currentObject.addInputPoint(new Point((int)mouseEvent.getX(),(int)mouseEvent.getY()));
            count++;
            if(count >= currentObject.getCountOfInputPoint()){
                currentObject.createDrawPoints();
                if(!isDebug.isSelected()){
                    draw(currentObject);
                }
                else {
                    isDraw = true;
                    index = 0;
                    isDebug.setDisable(true);
                    debugSize.setDisable(true);
                    menu.setDisable(true);
                }
                canvas.removeEventFilter(MouseEvent.MOUSE_CLICKED, addLineEvent);
                clear();
            }
        }
    };

    EventHandler<MouseEvent> deleteObjectEvent = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            for(DrawableObject drawable : new ArrayList<>(drawables)){
                if(drawable.isContainDrawPoint((int) mouseEvent.getX(), (int) mouseEvent.getY())){
                    drawables.remove(drawable);
                    redrawAllObjects();
                    break;
                }
            }
        }
    };

    public void redrawAllObjects(){
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for(DrawableObject drawable : drawables){
            draw(drawable);
        }
    }
    private void draw(DrawableObject drawable){
        for(int i = 0; i < drawable.getDrawPoints().size(); i++){
            canvas.getGraphicsContext2D().setFill(drawable.getDrawPoints().get(i).getColor());
            canvas.getGraphicsContext2D().fillRect(drawable.getDrawPoints().get(i).getX(),
                    drawable.getDrawPoints().get(i).getY(), 1, 1);
        }
    }
    private void drawD(){
        for(; index < currentObject.getDrawPoints().size() && index < Integer.parseInt(debugSize.getText()) * (dStep) ; index++){
            canvas.getGraphicsContext2D().setFill(currentObject.getDrawPoints().get(index).getColor());
            canvas.getGraphicsContext2D().fillRect(currentObject.getDrawPoints().get(index).getX(),
                    currentObject.getDrawPoints().get(index).getY(), 1, 1);
        }
        if(index == currentObject.getDrawPoints().size()){
            isDraw = false;
            dStep = 0;
            isDebug.setDisable(false);
            debugSize.setDisable(false);
            menu.setDisable(false);
        }
    }

    public void initialize() {
        drawables = new ArrayList<>();
        isDebug.setOnAction(e->{
            next.setDisable(!isDebug.isSelected());
        });
        isScale.setOnAction(e->{
            if(isScale.isSelected()){
                canvas.setScaleX(3);
                canvas.setScaleY(3);
            }
            else {
                canvas.setScaleX(1);
                canvas.setScaleY(1);
            }
        });
        next.setOnAction(e ->{
            if(isDraw){
                dStep++;
                drawD();
            }
        });
        isDelete.setOnAction(e->{
            if(isDelete.isSelected()){
                menu.setDisable(true);
                canvas.addEventFilter(MouseEvent.MOUSE_CLICKED, deleteObjectEvent);
            }
            else {
                menu.setDisable(false);
                canvas.removeEventFilter(MouseEvent.MOUSE_CLICKED, deleteObjectEvent);
            }
        });
        sc.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.W && isDebug.isSelected()) {
                    if(isDraw){
                        dStep++;
                        drawD();
                    }
                }
            }
        });
    }

    public void createLine(int type) {
        switch (type) {
            case 0 -> currentObject = new ZdaLine();
            case 1 -> currentObject = new BresenhamLine();
            case 2 -> currentObject = new WuLine();
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

    public void aboutProgram(ActionEvent actionEvent) {
        AboutPopup.display();
    }
}