package com.applic.entity.lines;

import com.applic.entity.Point;
import javafx.scene.paint.Color;


public class WuLine extends Line{
    @Override
    public void createDrawPoints() {
        if(inputPoints.get(1).getX().equals(inputPoints.get(0).getX()) ||
                inputPoints.get(1).getY().equals(inputPoints.get(0).getY())){
            drawStraightLine();
            return;
        }
        int x1 = inputPoints.get(0).getX();
        int x2 = inputPoints.get(1).getX();
        int y1 = inputPoints.get(0).getY();
        int y2 = inputPoints.get(1).getY();

        Point added;
        int dx = x2 - x1;
        int dy = y2 - y1;

        if((Math.abs(dx) > Math.abs(dy))&&(x2 < x1)||
                (Math.abs(dx) <= Math.abs(dy))&&(y2 < y1)){
            x1 += x2;
            x2 = x1 - x2;
            x1 -= x2;
            y1 += y2;
            y2 = y1 - y2;
            y1 -= y2;

            dx = x2 - x1;
            dy = y2 - y1;
        }

        double gradient = 0;
        double inter;

        drawPoints.add(new Point(x1, y1));
        drawPoints.add(new Point(x2, y2));
        if (Math.abs(dx) > Math.abs(dy)) {
            gradient = (double) dy / dx;
            inter = y1 + gradient;

            for (int x = x1; x < x2; x++) {
                added = new Point(x, (int) inter);
                added.setColor(new Color(0, 0, 0, (1 - (inter - (int) inter))));
                drawPoints.add(added);

                added = new Point(x, (int) inter + 1);
                added.setColor(new Color(0, 0, 0,  inter - (int) inter));
                drawPoints.add(added);

                inter += gradient;
            }
        }
        else {
            gradient = (double) dx / dy;
            inter = x1 + gradient;

            for (int y = y1; y < y2; y++) {

                added = new Point((int) inter, y);
                added.setColor(new Color(0, 0, 0,   (1 - (inter - (int) inter))));
                drawPoints.add(added);

                added = new Point((int) inter + 1, y);
                added.setColor(new Color(0, 0, 0,  inter - (int) inter));
                drawPoints.add(added);

                inter += gradient;
            }

        }

    }
}
