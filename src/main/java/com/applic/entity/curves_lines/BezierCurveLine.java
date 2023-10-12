package com.applic.entity.curves_lines;

import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.impl.DenseDoubleMatrix2D;
import cern.colt.matrix.linalg.Algebra;
import com.applic.entity.Point;

public class BezierCurveLine extends CurveLine {

    public BezierCurveLine(){
        super(4);
        matrix.set(0,0,-1);
        matrix.set(0,1,3);
        matrix.set(0,2,-3);
        matrix.set(0,3,1);

        matrix.set(1,0,3);
        matrix.set(1,1,-6);
        matrix.set(1,2,3);
        matrix.set(1,3,0);

        matrix.set(2,0,-3);
        matrix.set(2,1,3);
        matrix.set(2,2,0);
        matrix.set(2,3,0);

        matrix.set(3,0,1);
        matrix.set(3,1,0);
        matrix.set(3,2,0);
        matrix.set(3,3,0);
    }

    @Override
    public void createDrawPoints() {
        inputPointsMatrix.set(0,0, inputPoints.get(0).getX());
        inputPointsMatrix.set(0,1, inputPoints.get(0).getY());

        inputPointsMatrix.set(1,0, inputPoints.get(1).getX());
        inputPointsMatrix.set(1,1, inputPoints.get(1).getY());

        inputPointsMatrix.set(2,0, inputPoints.get(2).getX());
        inputPointsMatrix.set(2,1, inputPoints.get(2).getY());

        inputPointsMatrix.set(3,0, inputPoints.get(3).getX());
        inputPointsMatrix.set(3,1, inputPoints.get(3).getY());

        super.createDrawPoints();

    }
}
