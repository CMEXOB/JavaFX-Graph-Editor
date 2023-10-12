package com.applic.entity.curves_lines;

import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.impl.DenseDoubleMatrix2D;
import cern.colt.matrix.linalg.Algebra;
import com.applic.entity.DrawableObject;
import com.applic.entity.Point;

public abstract class CurveLine extends DrawableObject {
    protected DoubleMatrix2D matrix = new DenseDoubleMatrix2D(4,4);
    protected DoubleMatrix2D inputPointsMatrix = new DenseDoubleMatrix2D(4,2);

    public CurveLine(int count) {
        super(count);
    }

    @Override
    public void createDrawPoints() {
        DoubleMatrix2D resultMult = Algebra.DEFAULT.mult(matrix, inputPointsMatrix);
        DoubleMatrix2D tMatrix = new DenseDoubleMatrix2D(1,4);
        tMatrix.set(0,3, 1);
        DoubleMatrix2D r;

        for(double t = 0; t <= 1; t += 0.002){
            tMatrix.set(0,0, Math.pow(t, 3));
            tMatrix.set(0,1, Math.pow(t, 2));
            tMatrix.set(0,2, t);
            r = Algebra.DEFAULT.mult(tMatrix, resultMult);
            addDrawPoint((int) r.get(0,0), (int) r.get(0,1));
        }

    }
}
