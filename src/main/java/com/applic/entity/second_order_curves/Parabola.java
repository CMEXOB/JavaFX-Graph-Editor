package com.applic.entity.second_order_curves;

import com.applic.entity.Point;

import java.util.ArrayList;

public class Parabola extends SecondOrderCurve {
    int p;
    boolean dir;

    public Parabola(){
        super(2);
    }
    @Override
    public void createDrawPoints() {
        int dx = inputPoints.get(1).getX() - inputPoints.get(0).getX();
        int dy = inputPoints.get(1).getY() - inputPoints.get(0).getY();
        p = (int) (Math.pow(dy, 2) / (2 * dx));
        dir = (int)Math.signum(p) > 0;
        p = Math.abs(p);
        x = 0;
        y = 0;
        createQuadrantPoints();
        for(Point point : new ArrayList<>(drawPoints)){
            drawPoints.add(new Point(point.getX(), -point.getY()));
        }
        for(Point point : drawPoints){
            point.setX(point.getX() + inputPoints.get(0).getX());
            point.setY(point.getY() + inputPoints.get(0).getY());
        }
    }

    @Override
    public void addDrawPoint(int x, int y) {
        if(dir){
            drawPoints.add(new Point(x,y));
        }
        else {
            drawPoints.add(new Point(-x,y));
        }
    }

    @Override
    protected boolean isNotCompleteQuadrant() {
        return x < limitX && y < limitY;
    }

    @Override
    protected boolean isDiagonalOrHorizontal() {
        return error > 0;
    }

    @Override
    protected int calculateStartError() {
        return 1 - 2 * p;
    }

    @Override
    protected int calculateVerticalError() {
        return error -2*y + 1;
    }

    @Override
    protected int calculateDiagonalError() {
        return  error -p*2 - 2*y + 1;
    }

    @Override
    protected int calculateHorizontalError() {
        return error - p * 2;
    }
}
