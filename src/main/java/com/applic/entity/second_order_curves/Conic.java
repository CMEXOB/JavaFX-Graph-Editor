package com.applic.entity.second_order_curves;

import com.applic.entity.Point;

import java.util.ArrayList;

public class Conic extends SecondOrderCurve {
    protected int R;
    public Conic(){
        super(2);
    }
    public Conic(int countOfInputPoint){
        super(countOfInputPoint);
    }
    public void createDrawPoints() {
        R = calculateRadius();
        x = 0;
        y = R;
        limitY = 0;
        createQuadrantPoints();
        for(Point point : new ArrayList<>(drawPoints)){
            drawPoints.add(new Point(- point.getX(), point.getY()));
        }
        for(Point point : new ArrayList<>(drawPoints)){
            drawPoints.add(new Point(point.getX(), - point.getY()));
        }
        for(Point point : drawPoints){
            point.setX(point.getX() + inputPoints.get(0).getX());
            point.setY(point.getY() + inputPoints.get(0).getY());
        }
    }

    protected int calculateRadius() {
        return (int) Math.pow(Math.pow(inputPoints.get(1).getX() - inputPoints.get(0).getX(), 2) +
                        Math.pow(inputPoints.get(1).getY() - inputPoints.get(0).getY(), 2),0.5);
    }

    @Override
    protected boolean isNotCompleteQuadrant() {
        return y > limitY;
    }
    @Override
    protected int calculateLimitY() {
        return 0;
    }
    @Override
    protected int calculateStartError() {
        return 2 - 2 * R;
    }
    @Override
    protected int calculateVerticalError() {
        return error - 2*y + 1;
    }

    @Override
    protected int calculateDiagonalError() {
        return error + 2*x - 2*y + 2;
    }

    @Override
    protected int calculateHorizontalError() {
        return error + 2*x + 1;
    }
}
