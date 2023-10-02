package com.applic.entity.lines;

import com.applic.entity.Point;

public class ZdaLine extends Line{

    public ZdaLine() {
    }

    @Override
    public void createDrawPoints() {
        if(inputPoints.get(1).getX().equals(inputPoints.get(0).getX()) ||
                inputPoints.get(1).getY().equals(inputPoints.get(0).getY())){
            drawStraightLine();
            return;
        }
        double max = Math.max(Math.abs(inputPoints.get(1).getX() - inputPoints.get(0).getX()), Math.abs(inputPoints.get(1).getY() - inputPoints.get(0).getY()));
        double dx = (inputPoints.get(1).getX() - inputPoints.get(0).getX()) / max;
        double dy = (inputPoints.get(1).getY() - inputPoints.get(0).getY()) / max;
        double x = inputPoints.get(0).getX() + 0.5 * Math.signum(dx);
        double y = inputPoints.get(0).getY() + 0.5 * Math.signum(dy);

        drawPoints.add(new Point((int)x, (int)y));
        for(int i = 0; i < max; i++){
            x = x + dx;
            y = y + dy;
            drawPoints.add(new Point((int)x, (int)y));
        }
    }
}
