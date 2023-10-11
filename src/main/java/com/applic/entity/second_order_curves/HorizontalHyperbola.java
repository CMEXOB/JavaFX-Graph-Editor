package com.applic.entity.second_order_curves;

import com.applic.entity.Point;

import java.util.ArrayList;

public class HorizontalHyperbola extends SecondOrderCurve {
    private int a;
    private int b;

    public HorizontalHyperbola(){
        super(3);
    }
    public void createDrawPoints() {
        a = Math.abs(inputPoints.get(1).getX() - inputPoints.get(0).getX());
        b = Math.abs(inputPoints.get(2).getY() - inputPoints.get(0).getY());
        x = a;
        y = 0;
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

    @Override
    protected boolean isNotCompleteQuadrant() {
        return x < limitX && Math.abs(y) < limitY;
    }

    @Override
    protected int calculateStartError() {
        return - a*a + b*b + 2 * a*a * b;
    }

    @Override
    protected int calculateVerticalError() {
        return error - a*a*(-2*y + 1);
    }

    @Override
    protected int calculateDiagonalError() {
        return error + b*b*(2*x + 1) - a*a*(-2*y + 1);
    }

    @Override
    protected int calculateHorizontalError() {
        return error + b*b*(2*x + 1);
    }
}
