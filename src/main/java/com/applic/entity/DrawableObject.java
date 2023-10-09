package com.applic.entity;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class DrawableObject implements Drawable{
    protected List<Point> inputPoints;
    protected List<Point> drawPoints;
    protected int countOfInputPoint;

    public DrawableObject() {
        inputPoints = new ArrayList<>();
        drawPoints = new ArrayList<>();
    }

    public DrawableObject(int countOfInputPoint) {
        this.countOfInputPoint = countOfInputPoint;
        inputPoints = new ArrayList<>();
        drawPoints = new ArrayList<>();
    }

    public void addInputPoint(Point point){
        inputPoints.add(point);
    }
    public void addDrawPoint(Point point){
        drawPoints.add(point);
    }
    public void addInputPoint(int x, int y){
        inputPoints.add(new Point(x,y));
    }
    public void addDrawPoint(int x, int y){
        drawPoints.add(new Point(x,y));
    }
    public boolean isContainDrawPoint(Point point){
        for(Point objectPoint : drawPoints){
            if(objectPoint.getX().equals(point.getX()) && objectPoint.getY().equals(point.getY())){
                return true;
            }
        }
        return false;
    }
    public boolean isContainDrawPoint(Integer x, Integer y){
        for(Point objectPoint : drawPoints){
            if(objectPoint.getX().equals(x) && objectPoint.getY().equals(y)){
                return true;
            }
        }
        return false;
    }
}
