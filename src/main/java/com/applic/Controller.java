package com.applic;

import com.applic.entity.Drawable;
import com.applic.entity.DrawableObject;
import com.applic.entity.Point;
import com.applic.entity.lines.BresenhamLine;
import com.applic.entity.lines.WuLine;
import com.applic.entity.lines.ZdaLine;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    public TextField debugSize;
    public CheckBox debug;
    public Button next;
    public VBox sc;
    public MenuBar menu;
    @FXML
    private Canvas canvas;
    List<Drawable> drawables;
    private DrawableObject currentObject;
    private List<Integer> xPoints;
    private List<Integer> yPoints;
    int count;
    boolean isDraw = false;

    EventHandler<MouseEvent> addLineEvent = new EventHandler<MouseEvent>() {

        Point first;
        Point second;
        public void clear(){
            first = null;
            second = null;
        }
        @Override
        public void handle(MouseEvent mouseEvent) {
            currentObject.addPoint(new Point((int)mouseEvent.getX(),(int)mouseEvent.getY()));
            count++;
            if(count >= currentObject.getCountOfPoint()){
                currentObject.draw();
                if(!debug.isSelected()){
                    draw();
                }
                else {
                    isDraw = true;
                    index = 0;
                    debug.setDisable(true);
                    debugSize.setDisable(true);
                    menu.setDisable(true);
                }
                canvas.removeEventFilter(MouseEvent.MOUSE_CLICKED, addLineEvent);
                clear();
            }
        }
    };
    int dStep = 0;
    private void draw(){
        for(int i = 0; i < currentObject.getPoints().size(); i++){
            canvas.getGraphicsContext2D().fillRect(currentObject.getPoints().get(i).getX(),
                    currentObject.getPoints().get(i).getY(), 1, 1);
        }
    }
    int index;
    private void drawD(){
        for(;index < currentObject.getPoints().size() && index < Integer.parseInt(debugSize.getText()) * (dStep) ; index++){
            canvas.getGraphicsContext2D().fillRect(currentObject.getPoints().get(index).getX(),
                    currentObject.getPoints().get(index).getY(), 1, 1);
        }
        if(index == currentObject.getPoints().size()){
            isDraw = false;
            dStep = 0;
            debug.setDisable(false);
            debugSize.setDisable(false);
            menu.setDisable(false);
        }
    }

    public void initialize() {
        drawables = new ArrayList<>();
        debug.setOnAction(e->{
            next.setDisable(!debug.isSelected());
        });
        next.setOnAction(e ->{
            if(isDraw){
                dStep++;
                drawD();
            }
        });
        sc.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.W && debug.isSelected()) {
                    if(isDraw){
                        dStep++;
                        drawD();
                    }
                }
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