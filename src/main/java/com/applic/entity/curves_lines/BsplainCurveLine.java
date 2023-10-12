package com.applic.entity.curves_lines;

import com.applic.entity.Point;

public class BsplainCurveLine extends CurveLine {

    public BsplainCurveLine(){
        super(10);
        matrix.set(0,0,-1);
        matrix.set(0,1,3);
        matrix.set(0,2,-3);
        matrix.set(0,3,1);

        matrix.set(1,0,3);
        matrix.set(1,1,-6);
        matrix.set(1,2,3);
        matrix.set(1,3,0);

        matrix.set(2,0,-3);
        matrix.set(2,1,0);
        matrix.set(2,2,3);
        matrix.set(2,3,0);

        matrix.set(3,0,1);
        matrix.set(3,1,4);
        matrix.set(3,2,1);
        matrix.set(3,3,0);
    }

    @Override
    public void createDrawPoints() {
        Point zero = new Point(inputPoints.get(0).getX() - 1, inputPoints.get(0).getY() - 1);
        Point zeroL = new Point(inputPoints.get(inputPoints.size() - 1).getX() + 1, inputPoints.get(inputPoints.size() - 1).getY() + 1);
        inputPoints.add(0, zero);
        inputPoints.add(zeroL);

        for(int index = 1; index < inputPoints.size() - 2; index++){
            inputPointsMatrix.set(0,0, inputPoints.get(index - 1).getX());
            inputPointsMatrix.set(0,1, inputPoints.get(index - 1).getY());

            inputPointsMatrix.set(1,0, inputPoints.get(index).getX());
            inputPointsMatrix.set(1,1, inputPoints.get(index).getY());

            inputPointsMatrix.set(2,0, inputPoints.get(index + 1).getX());
            inputPointsMatrix.set(2,1, inputPoints.get(index + 1).getY());

            inputPointsMatrix.set(3,0, inputPoints.get(index + 2).getX());
            inputPointsMatrix.set(3,1, inputPoints.get(index + 2).getY());

            super.createDrawPoints();
        }

    }

    @Override
    public void addDrawPoint(int x, int y) {
        super.addDrawPoint(x/6, y/6);
    }
}
