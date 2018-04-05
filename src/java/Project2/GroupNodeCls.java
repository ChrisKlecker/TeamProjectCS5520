package Project2;

import Jama.EigenvalueDecomposition;
import Jama.Matrix;
import java.util.ArrayList;
import java.util.List;

public class GroupNodeCls{

    private String Nodes[];
    private Matrix AdjMatrix;
    private Matrix P_Matrix;
    private Matrix B_Matrix;
    private EigenvalueDecomposition eig;
    private Matrix EigenVectors;
    private double EigenValues[];
    private double MValue;

    public String[] getNodes() {
        return Nodes;
    }

    public void setNodes(String Nodes[]) {
        this.Nodes = Nodes;
    }

    public Matrix getAdjMatrix() {
        return AdjMatrix;
    }

    public void setAdjMatrix(Matrix AdjMatrix) {
        this.AdjMatrix = AdjMatrix;
    }

    public Matrix getP_Matrix() {
        return P_Matrix;
    }

    public void setP_Matrix(Matrix P_Matrix) {
        this.P_Matrix = P_Matrix;
    }

    public Matrix getB_Matrix() {
        return B_Matrix;
    }

    public void setB_Matrix(Matrix B_Matrix) {
        this.B_Matrix = B_Matrix;
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

    public double getMValue() {
        return MValue;
    }

    public void setMValue(double MValue) {
        this.MValue = MValue;
    }

    
    public GroupNodeCls(String[] Node, Matrix AdjMatrixSubSet){
        this.Nodes = Node;
        this.AdjMatrix = AdjMatrixSubSet;
        
        CreateBMatrix();
        CreateEigenInformation();
    }
    
    public void CreateBMatrix(){
        ArrayList<Double> SumArrayX = new ArrayList<>();
        ArrayList<Double> SumArrayY = new ArrayList<>();
        P_Matrix = new Matrix(AdjMatrix.getColumnDimension(), AdjMatrix.getRowDimension());
        B_Matrix = new Matrix(AdjMatrix.getColumnDimension(), AdjMatrix.getRowDimension());
        
        for(int i=0; i<AdjMatrix.getColumnDimension(); i++){

            Double b = 0.0;
            for(int j=0; j<AdjMatrix.getRowDimension(); j++){
                
                b += AdjMatrix.get(i, j);
            }
            SumArrayX.add(b);
        }
        
        for(int i=0; i<AdjMatrix.getRowDimension(); i++){

            Double b = 0.0;
            for(int j=0; j<AdjMatrix.getColumnDimension(); j++){
                
                b += AdjMatrix.get(i, j);
            }
            SumArrayY.add(b);
        }
        
        MValue = 0.0;
        for(int i=0; i<SumArrayX.size(); i++){
            MValue += SumArrayX.get(i);
        }
        
        for(int i=0; i<SumArrayX.size(); i++){
            
            for(int j=0; j<SumArrayY.size(); j++){
                P_Matrix.set(i, j, SumArrayX.get(i)*SumArrayY.get(j));
            }
        }

        for(int i=0; i<SumArrayX.size(); i++){
            
            for(int j=0; j<SumArrayY.size(); j++){
                double x = P_Matrix.get(i, j);
                double y = 1/MValue;
                double z = AdjMatrix.get(i, j) - (y*x);
                z = Double.valueOf(String.format("%.2f", z));
                B_Matrix.set(i, j, z);
            }
        }
    }    
    public EigenvalueDecomposition CreateEigenInformation(){
        eig = new EigenvalueDecomposition(B_Matrix);
        EigenVectors = eig.getV();
        EigenValues = eig.getRealEigenvalues();
        
        for (int i=0; i<EigenValues.length; i++){
            EigenValues[i] = Double.parseDouble(String.format("%.2f ", EigenValues[i]));
        }
        
        return eig;
    }
    
    public int ReturnHighestEigenValueIndex(){
        double max = Double.MIN_EXPONENT;
        int indexReturn = -1;
        
        for (int i=0; i<EigenValues.length; i++){

            if(EigenValues[i] > max){
                max = EigenValues[i];
                indexReturn = i;
            }
        }
        
        return indexReturn;  
    }
    
    public double[] ReturnEigenVector(int Index){
        List<Double> a = new ArrayList<>();
        
        for(int i=0; i<EigenVectors.getRowDimension(); i++){
            a.add(EigenVectors.get(i, Index));
        }

        double[] target = new double[a.size()];
        for (int i = 0; i < target.length; i++) {

            target[i] = a.get(i);                // java 1.5+ style (outboxing)
        }       
        return target;
    }
};
