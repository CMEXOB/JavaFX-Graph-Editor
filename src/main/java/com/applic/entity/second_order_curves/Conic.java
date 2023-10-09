package com.applic.entity.second_order_curves;

import com.applic.entity.Point;

import java.util.ArrayList;

public class Conic extends SecondOrderCurve {

    private int R;
    public Conic(){
        super(2);
    }

    public void createDrawPoints2() {
//        int R = 100;
        int R = (int) Math.pow(Math.pow(inputPoints.get(1).getX() - inputPoints.get(0).getX(), 2) +
                Math.pow(inputPoints.get(1).getY() - inputPoints.get(0).getY(), 2)
                ,0.5);
        x = 0;
        y = R;
        int limit = 0;

        error = 2 - 2*R;
        int q;
        drawPoints.add(new Point(x, y));
        while(y > limit){
            if(error == 0){
                d();
            }
            else if(error > 0){
                q = 2*error - 2*x - 1;
//                q = (Math.abs(calculateDiagonalError()) - Math.abs(calculateVerticalError()));
                System.out.println(2*error - 2*x - 1 + " " + (Math.abs(calculateDiagonalError()) - Math.abs(calculateVerticalError())));
                if(q > 0){
                    v();
                }
                else {
                    d();
                }
            }
            else {
                q = 2*error + 2*y - 1;
                q = (Math.abs(calculateHorizontalError()) - Math.abs(calculateDiagonalError()));
                if(q > 0){
                    d();
                }
                else {
                    h();
                }
            }
            drawPoints.add(new Point(x, y));
        }
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


    public void createDrawPoints() {
        R = (int) Math.pow(Math.pow(inputPoints.get(1).getX() - inputPoints.get(0).getX(), 2) +
                Math.pow(inputPoints.get(1).getY() - inputPoints.get(0).getY(), 2)
                ,0.5);
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

    @Override
    protected boolean isNotCompleteQuadrant() {
        return y > limitY;
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
    protected void v(){
        y = y - 1;
        error += - 2*y + 1;
    }
    protected void d(){
        x = x + 1;
        y = y - 1;
        error += 2*x - 2*y + 2;
    }
    protected void h(){
        x = x + 1;
        error += 2*x  + 1;
    }
}
