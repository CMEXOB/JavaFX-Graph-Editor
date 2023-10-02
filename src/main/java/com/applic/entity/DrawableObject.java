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
    }

    public DrawableObject(int countOfInputPoint) {
        this.countOfInputPoint = countOfInputPoint;
        inputPoints = new ArrayList<>();
        drawPoints = new ArrayList<>();
    }

    public void addInputPoint(Point point){
        drawPoints.add(point);
    }
    public void addDrawPoint(Point point){
        drawPoints.add(point);
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
