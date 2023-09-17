package com.applic.entity;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public abstract class DrawableObject implements Drawable{
    protected List<Integer> xPoints;
    protected List<Integer> yPoints;
    @Getter
    protected int countOfPoint;

    public DrawableObject() {
    }

    public DrawableObject(int countOfPoint) {
        this.countOfPoint = countOfPoint;
        xPoints = new ArrayList<>();
        yPoints = new ArrayList<>();
    }

    public void addXPoint(Integer x){
        xPoints.add(x);
    }

    public void addYPoint(Integer y){
        yPoints.add(y);
    }

    public List<Integer> getXPoints() {
        return xPoints;
    }

    public List<Integer> getYPoints() {
        return yPoints;
    }
}
