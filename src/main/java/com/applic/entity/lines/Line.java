package com.applic.entity.lines;

import com.applic.entity.DrawableObject;
import com.applic.entity.Point;

public abstract class Line extends DrawableObject {
    public Line() {
        super(2);
    }
    protected void drawStraightLine(){
        if(drawPoints.get(1).getX().equals(drawPoints.get(0).getX())){
            for(int i = drawPoints.get(0).getY(); i != (int)  drawPoints.get(1).getY(); i += (int) Math.signum(drawPoints.get(1).getY() - drawPoints.get(0).getY())){
                drawPoints.add(new Point(drawPoints.get(0).getX(),i));
            }
        }
        else{
            for(int i = drawPoints.get(0).getX(); i != (int)  drawPoints.get(1).getX(); i += (int) Math.signum(drawPoints.get(1).getX() - drawPoints.get(0).getX())){
                drawPoints.add(new Point(i, drawPoints.get(0).getY()));
            }
        }
    }

}
