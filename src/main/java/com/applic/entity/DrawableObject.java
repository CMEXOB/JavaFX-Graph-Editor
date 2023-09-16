package com.applic.entity;

import java.util.ArrayList;
import java.util.List;

public abstract class DrawableObject implements Drawable{
    protected List<Double> xPoints;
    protected List<Double> yPoints;
    protected int countOfPoint;

    public DrawableObject() {
    }

    public DrawableObject(int countOfPoint) {
        this.countOfPoint = countOfPoint;
        xPoints = new ArrayList<>(countOfPoint);
        yPoints = new ArrayList<>(countOfPoint);
    }

    public int getCountOfPoint() {
        return countOfPoint;
    }

    public void addXPoint(Double x){
        xPoints.add(x);
    }

    public void addYPoint(Double y){
        yPoints.add(y);
    }

    public List<Double> getXPoints() {
        return xPoints;
    }

    public List<Double> getYPoints() {
        return yPoints;
    }
}
