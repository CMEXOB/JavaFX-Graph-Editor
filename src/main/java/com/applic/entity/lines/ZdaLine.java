package com.applic.entity.lines;

import com.applic.entity.Point;
import javafx.scene.canvas.GraphicsContext;

public class ZdaLine extends Line{

    public ZdaLine() {
    }

    @Override
    public void draw() {
        double max = Math.max(Math.abs(points.get(1).getX() - points.get(0).getX()), Math.abs(points.get(1).getY() - points.get(0).getY()));
        double dx = (points.get(1).getX() - points.get(0).getX()) / max;
        double dy = (points.get(1).getY() - points.get(0).getY()) / max;
        double x = points.get(0).getX() + 0.5 * Math.signum(dx);
        double y = points.get(0).getY() + 0.5 * Math.signum(dy);
        points.clear();
        points.add(new Point((int)x, (int)y));
        for(int i = 0; i < max; i++){
            x = x + dx;
            y = y + dy;
            points.add(new Point((int)x, (int)y));
        }
    }
}
