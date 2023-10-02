package com.applic.entity.lines;

import com.applic.entity.Point;

public class BresenhamLine extends Line{
    @Override
    public void createDrawPoints() {
        double ndx, ndy, es, el, err;

        double dx = inputPoints.get(1).getX() - inputPoints.get(0).getX();
        double dy = inputPoints.get(1).getY() - inputPoints.get(0).getY();

        int x, y;
        x = inputPoints.get(0).getX();
        y = inputPoints.get(0).getY();


        drawPoints.add(new Point(x,y));

        double dirX = Math.signum(dx);
        double dirY = Math.signum(dy);

        dx = Math.abs(dx);
        dy = Math.abs(dy);

        if (dx > dy) {
            ndx = dirX;
            ndy = 0;
            es = dy;
            el = dx;
        }
        else{
            ndx = 0;
            ndy = dirY;
            es = dx;
            el = dy;
        }
        err = 2 * es - el ;

        for (int t = 0; t < el; t++){
            if (err > 0) {
                err -= 2 * el;
                x += dirX;
                y += dirY;
            }
            else {
                x += ndx;
                y += ndy;
            }
            drawPoints.add(new Point(x,y));
            err += 2 * es;
        }
    }
}
