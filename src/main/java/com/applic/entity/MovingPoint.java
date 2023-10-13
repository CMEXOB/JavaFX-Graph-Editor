package com.applic.entity;

import com.applic.entity.second_order_curves.Conic;
import com.applic.entity.second_order_curves.SecondOrderCurve;

import java.util.ArrayList;

public class MovingPoint extends Conic {
    public MovingPoint(){
        super(1);
    }

    @Override
    protected int calculateRadius() {
        return 5;
    }

    public boolean isIn(int x, int y){
        return x <= inputPoints.get(0).getX() + R && x >= inputPoints.get(0).getX() - R &&
                y <= inputPoints.get(0).getY() + R && y >= inputPoints.get(0).getY() - R;
    }
}
