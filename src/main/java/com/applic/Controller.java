package com.applic;

import com.applic.entity.DrawableObject;
import com.applic.entity.MovingPoint;
import com.applic.entity.Point;
import com.applic.entity.curves_lines.BezierCurveLine;
import com.applic.entity.curves_lines.BsplainCurveLine;
import com.applic.entity.curves_lines.CurveLine;
import com.applic.entity.curves_lines.ErmitCurveLine;
import com.applic.entity.second_order_curves.*;
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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
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
    public Canvas canvasSecondary;
    public Pane pane;
    public CheckBox isChange;
    @FXML
    private Canvas canvasPrime;
    List<DrawableObject> drawables;
    List<MovingPoint> movePoints;
    private DrawableObject currentObject;
    private MovingPoint currentMovePoints;
    int count;
    boolean isDraw = false;
    int index;
    int dStep = 0;

    EventHandler<MouseEvent> addDrawableObjectWithCountOfPointEvent = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            currentObject.addInputPoint(new Point((int)mouseEvent.getX(),(int)mouseEvent.getY()));
            count++;
            if(count >= currentObject.getCountOfInputPoint()){
                prepareForDraw();
                pane.removeEventFilter(MouseEvent.MOUSE_CLICKED, addDrawableObjectWithCountOfPointEvent);
            }
        }
    };

    EventHandler<MouseEvent> addDrawableObjectWithoutCountOfPointEvent = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            if(mouseEvent.getButton() == MouseButton.SECONDARY){
                prepareForDraw();
                pane.removeEventFilter(MouseEvent.MOUSE_CLICKED, addDrawableObjectWithoutCountOfPointEvent);
            }
            else {
                currentObject.addInputPoint(new Point((int)mouseEvent.getX(),(int)mouseEvent.getY()));
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
    EventHandler<MouseEvent> changeObjectEvent = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            for(DrawableObject drawable : new ArrayList<>(drawables)){
                if(drawable.isContainDrawPoint((int) mouseEvent.getX(), (int) mouseEvent.getY())){
                    isChange.setDisable(true);
                    currentObject = drawable;
                    MovingPoint movePoint;
                    movePoints = new ArrayList<>();
                    for(Point point : currentObject.getInputPoints()){
                        movePoint = new MovingPoint();
                        movePoint.addInputPoint(point);
                        movePoint.addInputPoint(point.getX() + 5, point.getY() + 5);
                        movePoint.createDrawPoints();
                        movePoints.add(movePoint);
                        draw(movePoint, canvasPrime);
                    }
                    pane.removeEventFilter(MouseEvent.MOUSE_CLICKED, changeObjectEvent);
                    pane.addEventFilter(MouseEvent.MOUSE_CLICKED, moveObjectPointsEvent);
                    break;
                }
            }
        }
    };

    EventHandler<MouseEvent> moveObjectPointsEvent = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            if(mouseEvent.getButton() == MouseButton.SECONDARY){
                movePoints.clear();
                redrawAllObjects();
                isChange.setDisable(false);
                pane.removeEventFilter(MouseEvent.MOUSE_CLICKED, moveObjectPointsEvent);
                pane.addEventFilter(MouseEvent.MOUSE_CLICKED, changeObjectEvent);
            }
            else {
                for (MovingPoint movingPoint : new ArrayList<>(movePoints)) {
                    if (movingPoint.isIn((int) mouseEvent.getX(), (int) mouseEvent.getY())) {
                        currentMovePoints = movingPoint;
                        movePoints.remove(currentMovePoints);
                        redrawAllObjects();
                        draw(currentMovePoints, canvasSecondary);
                        pane.removeEventFilter(MouseEvent.MOUSE_CLICKED, moveObjectPointsEvent);
                        pane.addEventFilter(MouseEvent.MOUSE_CLICKED, moveObjectPointsEvent2);
                        break;
                    }
                }
            }
        }
    };
    EventHandler<MouseEvent> moveObjectPointsEvent2 = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            if(mouseEvent.getButton() == MouseButton.SECONDARY){
                currentObject.getDrawPoints().clear();
                currentObject.createDrawPoints();
                movePoints.add(currentMovePoints);

                redrawAllObjects();
                canvasSecondary.getGraphicsContext2D().clearRect(0, 0, canvasSecondary.getHeight(), canvasSecondary.getWidth());
                pane.removeEventFilter(MouseEvent.MOUSE_CLICKED, moveObjectPointsEvent2);
                pane.addEventFilter(MouseEvent.MOUSE_CLICKED, moveObjectPointsEvent);
            }
            else {
                currentMovePoints.getDrawPoints().clear();

                currentMovePoints.getInputPoints().get(0).setX((int) mouseEvent.getX());
                currentMovePoints.getInputPoints().get(0).setY((int) mouseEvent.getY());
                currentMovePoints.getInputPoints().get(1).setX((int) mouseEvent.getX() + 5);
                currentMovePoints.getInputPoints().get(1).setY((int) mouseEvent.getY() + 5);

                currentMovePoints.createDrawPoints();
                canvasSecondary.getGraphicsContext2D().clearRect(0, 0, canvasSecondary.getHeight(), canvasSecondary.getWidth());
                draw(currentMovePoints, canvasSecondary);
            }

//            Интересный эффект
//            currentObject.getDrawPoints().clear();
//            currentObject.createDrawPoints();
//            draw(currentObject, canvasPrime);
        }
    };
    public void prepareForDraw(){
        currentObject.createDrawPoints();
        if(!isDebug.isSelected()){
            draw(currentObject,canvasPrime);
        }
        else {
            isDraw = true;
            index = 0;
            isDebug.setDisable(true);
            debugSize.setDisable(true);
            menu.setDisable(true);
        }

    }
    public void redrawAllObjects(){
        canvasPrime.getGraphicsContext2D().clearRect(0, 0, canvasPrime.getWidth(), canvasPrime.getHeight());
        for(DrawableObject drawable : drawables){
            draw(drawable,canvasPrime);
        }
        for(DrawableObject drawable : movePoints){
            draw(drawable,canvasPrime);
        }
    }
    private void draw(DrawableObject drawable, Canvas canvas){
        for(int i = 0; i < drawable.getDrawPoints().size(); i++){
            canvas.getGraphicsContext2D().setFill(drawable.getDrawPoints().get(i).getColor());
            canvas.getGraphicsContext2D().fillRect(drawable.getDrawPoints().get(i).getX(),
                    drawable.getDrawPoints().get(i).getY(), 1, 1);
        }
    }
    private void drawD(){
        for(; index < currentObject.getDrawPoints().size() && index < Integer.parseInt(debugSize.getText()) * (dStep) ; index++){
            canvasPrime.getGraphicsContext2D().setFill(currentObject.getDrawPoints().get(index).getColor());
            canvasPrime.getGraphicsContext2D().fillRect(currentObject.getDrawPoints().get(index).getX(),
                    currentObject.getDrawPoints().get(index).getY(), 1, 1);
            System.out.println("[x: "+currentObject.getDrawPoints().get(index).getX()+", y: " + currentObject.getDrawPoints().get(index).getY()+"]");
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
                pane.setScaleX(3);
                pane.setScaleY(3);
            }
            else {
                pane.setScaleX(1);
                pane.setScaleY(1);
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
                pane.addEventFilter(MouseEvent.MOUSE_CLICKED, deleteObjectEvent);
            }
            else {
                menu.setDisable(false);
                pane.removeEventFilter(MouseEvent.MOUSE_CLICKED, deleteObjectEvent);
            }
        });
        isChange.setOnAction(e->{
            if(isChange.isSelected()){
                menu.setDisable(true);
                pane.addEventFilter(MouseEvent.MOUSE_CLICKED, changeObjectEvent);
            }
            else {
                menu.setDisable(false);
                pane.removeEventFilter(MouseEvent.MOUSE_CLICKED, changeObjectEvent);
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
    public void aboutProgram(ActionEvent actionEvent) {
        AboutPopup.display();
    }
    public void prepareForInputWithCountOfPoint() {
        count = 0;
        drawables.add(currentObject);

        pane.addEventFilter(MouseEvent.MOUSE_CLICKED, addDrawableObjectWithCountOfPointEvent);
    }
    public void prepareForInputWithoutCountOfPoint() {
        count = 0;
        drawables.add(currentObject);

        pane.addEventFilter(MouseEvent.MOUSE_CLICKED, addDrawableObjectWithoutCountOfPointEvent);
    }
    public void createZdaLine(ActionEvent actionEvent) {
        currentObject = new ZdaLine();
        prepareForInputWithCountOfPoint();
    }
    public void createBresenhamLine(ActionEvent actionEvent) {
        currentObject = new BresenhamLine();
        prepareForInputWithCountOfPoint();
    }
    public void createWuLine(ActionEvent actionEvent) {
        currentObject = new WuLine();
        prepareForInputWithCountOfPoint();
    }
    public void createCircle(ActionEvent actionEvent) {
        currentObject = new Conic();
        prepareForInputWithCountOfPoint();
    }
    public void createEllipse(ActionEvent actionEvent) {
        currentObject = new Ellipse();
        prepareForInputWithCountOfPoint();
    }
    public void createHorizontalHyperbola(ActionEvent actionEvent) {
        currentObject = new HorizontalHyperbola();
        prepareForInputWithCountOfPoint();
    }
    public void createVerticalHyperbola(ActionEvent actionEvent) {
        currentObject = new VerticalHyperbola();
        prepareForInputWithCountOfPoint();
    }
    public void createParabola(ActionEvent actionEvent) {
        currentObject = new Parabola();
        prepareForInputWithCountOfPoint();
    }

    public void createErmitCurveLine(ActionEvent actionEvent) {
        currentObject = new ErmitCurveLine();
        prepareForInputWithCountOfPoint();
    }

    public void createBezierCurveLine(ActionEvent actionEvent) {
        currentObject = new BezierCurveLine();
        prepareForInputWithCountOfPoint();
    }

    public void createBsplinCurveLine(ActionEvent actionEvent) {
        currentObject = new BsplainCurveLine();
        prepareForInputWithoutCountOfPoint();
    }
}