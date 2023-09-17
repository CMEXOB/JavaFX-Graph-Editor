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
}
