package com.applic.entity.lines;

import com.applic.entity.DrawableObject;
import com.applic.entity.Point;

public abstract class Line extends DrawableObject {
    public Line() {
        super(2);
    }

    @Override
    public void createDrawPoints() {
        if(inputPoints.get(1).getX().equals(inputPoints.get(0).getX()) ||
                inputPoints.get(1).getY().equals(inputPoints.get(0).getY())){
            drawStraightLine();
        }
    }

    protected void drawStraightLine(){
        if(inputPoints.get(1).getX().equals(inputPoints.get(0).getX())){
            for(int i = inputPoints.get(0).getY(); i != (int)  inputPoints.get(1).getY(); i += (int) Math.signum(inputPoints.get(1).getY() - inputPoints.get(0).getY())){
                drawPoints.add(new Point(inputPoints.get(0).getX(),i));
            }
        }
        else{
            for(int i = inputPoints.get(0).getX(); i != (int)  inputPoints.get(1).getX(); i += (int) Math.signum(inputPoints.get(1).getX() - inputPoints.get(0).getX())){
                drawPoints.add(new Point(i, inputPoints.get(0).getY()));
            }
        }
    }

}
