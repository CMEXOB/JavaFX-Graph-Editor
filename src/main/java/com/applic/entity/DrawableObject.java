package com.applic.entity;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class DrawableObject implements Drawable{
    protected List<Point> points;
    protected int countOfPoint;

    public DrawableObject() {
    }

    public DrawableObject(int countOfPoint) {
        this.countOfPoint = countOfPoint;
        points = new ArrayList<>();
    }

    public void addPoint(Point point){
        points.add(point);
    }
    public boolean isContainPoint(Point point){
        for(Point objectPoint : points){
            if(objectPoint.getX().equals(point.getX()) && objectPoint.getY().equals(point.getY())){
                return true;
            }
        }
        return false;
    }
    public boolean isContainPoint(Integer x, Integer y){
        for(Point objectPoint : points){
            if(objectPoint.getX().equals(x) && objectPoint.getY().equals(y)){
                return true;
            }
        }
        return false;
    }
}
