package com.applic.entity.lines;

import com.applic.entity.Point;

public class BresenhamLine extends Line{
    @Override
    public void draw() {
        double ndx, ndy, es, el, err;

        double dx = points.get(1).getX() - points.get(0).getX();
        double dy = points.get(1).getY() - points.get(0).getY();

        int x, y;
        x = points.get(0).getX();
        y = points.get(0).getY();

        points.clear();
        points.add(new Point(x,y));

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
        err = el/2;

        for (int t = 0; t < el; t++){
            err -= es;
            if (err < 0) {
                err += el;
                x += dirX;
                y += dirY;
            }
            else {
                x += ndx;
                y += ndy;
            }
            points.add(new Point(x,y));
        }
    }
}
