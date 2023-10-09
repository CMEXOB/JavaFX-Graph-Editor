package com.applic.entity.second_order_curves;

import com.applic.entity.Point;

import java.util.ArrayList;

public class HorizontalHyperbola extends SecondOrderCurve {
    private int a;
    private int b;

    public HorizontalHyperbola(){
        super(3);
    }
//    @Override
//    public void createDrawPoints() {
//        a = Math.abs(inputPoints.get(1).getX() - inputPoints.get(0).getX());
//        b = Math.abs(inputPoints.get(2).getY() - inputPoints.get(0).getY());
//        x = a;
//        y = 0;
//        int limit = - 500;
//
//        error = - a*a + b*b + 2 * a*a * b;
//        int q;
//        drawPoints.add(new Point(x, y));
//        while(y > limit){
//            if(error == 0){
//                d();
//            }
//            else if(error > 0){
//                q = 2*error - 2*x*b*b - b*b;
//                if(q > 0){
//                    v();
//                }
//                else {
//                    d();
//                }
//            }
//            else {
//                q = 2*error - 2*y*a*a - a*a;
//                if(q > 0){
//                    d();
//                }
//                else {
//                    h();
//                }
//            }
//            drawPoints.add(new Point(x, y));
//        }
//        for(Point point : new ArrayList<>(drawPoints)){
//            drawPoints.add(new Point(- point.getX(), point.getY()));
//        }
//        for(Point point : new ArrayList<>(drawPoints)){
//            drawPoints.add(new Point(point.getX(), - point.getY()));
//        }
//        for(Point point : drawPoints){
//            point.setX(point.getX() + inputPoints.get(0).getX());
//            point.setY(point.getY() + inputPoints.get(0).getY());
//        }
//    }
//
//    protected void v(){
//        y = y - 1;
//        error += - a*a*(-2*y + 1);
//    }
//    protected void d(){
//        x = x + 1;
//        y = y - 1;
//        error +=  b*b*(2*x + 1) - a*a*(-2*y + 1);
//    }
//    protected void h(){
//        x = x + 1;
//        error +=  b*b*(2*x + 1);
//    }


    public void createDrawPoints() {
        a = Math.abs(inputPoints.get(1).getX() - inputPoints.get(0).getX());
        b = Math.abs(inputPoints.get(2).getY() - inputPoints.get(0).getY());
        x = a;
        y = 0;
        limitX = 1000;
        limitY = 500;
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
