package com.applic.entity.second_order_curves;

import com.applic.Application;
import com.applic.entity.DrawableObject;

public abstract class SecondOrderCurve extends DrawableObject {
    protected int x;
    protected int y;
    protected int error;

    protected int limitX;
    protected int limitY;
    public SecondOrderCurve(int countOfInputPoint){
        super(countOfInputPoint);
    }

    public void createQuadrantPoints(){
        addDrawPoint(x,y);
        error = calculateStartError();
        limitX = calculateLimitX();
        limitY = calculateLimitY();
        while(isNotCompleteQuadrant()){
            if(error == 0){
                moveDiagonal();
            }
            else if(isDiagonalOrHorizontal()){
                if(Math.abs(calculateHorizontalError()) - Math.abs(calculateDiagonalError()) > 0){
                    moveDiagonal();
                }
                else {
                    moveHorizontal();
                }
            }
            else {
                if(Math.abs(calculateDiagonalError()) - Math.abs(calculateVerticalError()) > 0){
                    moveVertical();
                }
                else {
                    moveDiagonal();
                }
            }
            addDrawPoint(x,y);
        }
    }

    protected int calculateLimitY() {
        return Math.max(Application.getHeight() - inputPoints.get(0).getY(),inputPoints.get(0).getY());
    }

    protected int calculateLimitX() {
        return Math.max(Application.getWidth() - inputPoints.get(0).getX(),inputPoints.get(0).getX());
    }

    protected abstract  boolean isNotCompleteQuadrant();
    protected boolean isDiagonalOrHorizontal(){
        return error < 0;
    }
    protected abstract int calculateStartError();
    protected abstract int calculateVerticalError();
    protected void moveVertical(){
        y = y - 1;
        error = calculateVerticalError();
    }
    protected abstract int calculateDiagonalError();
    protected void moveDiagonal(){
        x = x + 1;
        y = y - 1;
        error = calculateDiagonalError();
    }
    protected abstract int calculateHorizontalError();
    protected void moveHorizontal(){
        x = x + 1;
        error = calculateHorizontalError();
    }
}
