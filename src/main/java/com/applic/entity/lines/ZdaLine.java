package com.applic.entity.lines;

import javafx.scene.canvas.GraphicsContext;

public class ZdaLine extends Line{

    public ZdaLine() {
    }

    @Override
    public void draw() {
        double max = Math.max(Math.abs(xPoints.get(0) - xPoints.get(1)), Math.abs(yPoints.get(0) - yPoints.get(1)));
        double dx = (xPoints.get(1) - xPoints.get(0)) / max;
        double dy = (yPoints.get(1) - yPoints.get(0)) / max;
        double x = xPoints.get(0) + 0.5 * Math.signum(dx);
        double y = yPoints.get(0) + 0.5 * Math.signum(dy);
        xPoints.clear();
        yPoints.clear();
        xPoints.add((int)x);
        yPoints.add((int)y);
        for(int i = 0; i < max; i++){
            x = x + dx;
            y = y + dy;
            xPoints.add((int)x);
            yPoints.add((int)y);
        }
    }
}
