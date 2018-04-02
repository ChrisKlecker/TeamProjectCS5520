package Project2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author David Klecker
 */

import Jama.Matrix;
import Jama.EigenvalueDecomposition;

public class EigenVectoring {
        
    private Matrix AdjMatrix;
    private Matrix B_Matrix;
    private EigenvalueDecomposition eig;
    private Matrix EigenVectors;
    private double EigenValues[];
    
    public Matrix getB_Matrix() {
        return B_Matrix;
    }

    public void setB_Matrix(Matrix B_Matrix) {
        this.B_Matrix = B_Matrix;
    }

    public Matrix getAdjMatrix() {
        return AdjMatrix;
    }

    public void setAdjMatrix(Matrix AdjMatrix) {
        this.AdjMatrix = AdjMatrix;
    }

    public EigenvalueDecomposition getEig() {
        return eig;
    }

    public void setEig(EigenvalueDecomposition eig) {
        this.eig = eig;
    }

    public Matrix getEigenVectors() {
        return EigenVectors;
    }

    public void setEigenVectors(Matrix EigenVectors) {
        this.EigenVectors = EigenVectors;
    }

    public double[] getEigenValues() {
        return EigenValues;
    }

    public void setEigenValues(double[] EigenValues) {
        this.EigenValues = EigenValues;
    }

    public EigenVectoring(){
                
        double[][] array = {
            {1,1,1,1,1,0,0,0,0,0,0,0,0},
            {1,1,1,1,0,1,0,0,0,0,0,0,1},
            {1,1,1,1,0,1,0,0,0,0,0,0,1},
            {1,1,1,1,1,0,0,1,0,0,0,0,0},
            {1,0,0,1,1,0,0,0,0,1,1,1,1},
            {0,1,1,0,0,1,1,1,1,0,0,0,0},
            {0,0,0,0,0,1,1,1,1,0,0,0,0},
            {0,0,0,1,0,1,1,1,1,1,0,0,0},
            {0,0,0,0,0,1,1,1,1,1,0,0,0},
            {0,0,0,0,1,0,0,1,1,1,1,1,1},
            {0,0,0,0,1,0,0,0,0,1,1,1,1},
            {0,0,0,0,1,0,0,0,0,1,1,1,1},
            {0,1,1,0,1,0,0,0,0,1,1,1,1}
        };
        
        AdjMatrix = new Matrix(array);

    }

    
    public void ProcessRequest(){

        B_Matrix = CreatePMatrix(AdjMatrix);
        
        eig = new EigenvalueDecomposition(B_Matrix);
        EigenVectors = eig.getV();
        EigenValues = eig.getRealEigenvalues();
        
        for (int i=0; i<EigenValues.length; i++){
            EigenValues[i] = Double.parseDouble(String.format("%.2f ", Math.abs(EigenValues[i])));
        }
    }
   
    public Matrix CreatePMatrix(Matrix M){
        ArrayList<Integer> SumArrayX = new ArrayList<>();
        ArrayList<Integer> SumArrayY = new ArrayList<>();
        Matrix PMatrix = new Matrix(M.getColumnDimension(), M.getRowDimension());
        Matrix BMatrix = new Matrix(M.getColumnDimension(), M.getRowDimension());
        
        for(int i=0; i<M.getColumnDimension(); i++){

            Double b = 0.0;
            for(int j=0; j<M.getRowDimension(); j++){
                
                b += M.get(i, j);
            }
            SumArrayX.add(b.intValue());
        }
        
        for(int i=0; i<M.getRowDimension(); i++){

            Double b = 0.0;
            for(int j=0; j<M.getColumnDimension(); j++){
                
                b += M.get(i, j);
            }
            SumArrayY.add(b.intValue());
        }
        
        Integer m = 0;
        for(int i=0; i<SumArrayX.size(); i++){
            m += SumArrayX.get(i);
        }
        
        for(int i=0; i<SumArrayX.size(); i++){
            
            for(int j=0; j<SumArrayY.size(); j++){
                PMatrix.set(i, j, SumArrayX.get(i)*SumArrayY.get(j));
            }
        }
        
        for(int i=0; i<PMatrix.getColumnDimension(); i++){
            for(int j=0; j<PMatrix.getRowDimension(); j++){
                
                BMatrix.set(i, j, (M.get(i, j) - (1/m)*(PMatrix.get(i, j))));
            }
        }
        return BMatrix;
    }
}
