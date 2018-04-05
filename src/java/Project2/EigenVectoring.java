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

//Create an arraylist of matricies. 
import Jama.Matrix;
import Jama.EigenvalueDecomposition;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class EigenVectoring {
        
    private ArrayList<GroupNodeCls> groupNodeList;
    private EigenvalueDecomposition eig;
    private String[] Nodes;
    private GroupNodeCls groupNodeLocal;

    public GroupNodeCls getGroupNodeLocal() {
        return groupNodeLocal;
    }

    public void setGroupNodeLocal(GroupNodeCls groupNodeLocal) {
        this.groupNodeLocal = groupNodeLocal;
    }
    
    public EigenvalueDecomposition getEig() {
        return eig;
    }

    public void setEig(EigenvalueDecomposition eig) {
        this.eig = eig;
    }
 
    public EigenVectoring(){
        groupNodeList = new ArrayList<>();
        
    }
    
    public void ProcessRequest(HttpServletRequest request) throws FileNotFoundException, IOException{
       
        String realPath = request.getServletContext().getRealPath("/");
        realPath = realPath.concat("\\adjacencyMatrix.csv");
        
        BufferedReader br = new BufferedReader(new FileReader(realPath));
        String st;
        int count = 0;
        String Nodes[] = null;
        List<ArrayList<Double>> tempArray = new ArrayList<>();
        
        while ((st = br.readLine()) != null){
            if(count == 0){
                Nodes = st.substring(1).split(",");
            }
            else{
                String[] Values = st.split(",");
                List<Double> row = new ArrayList<>();
                
                for(int i=1; i<Values.length; i++){
                    row.add(Double.parseDouble(Values[i]));
                }
                tempArray.add((ArrayList<Double>) row);
            }
            count++;
        }
        
        double[][] d = new double[tempArray.size()][tempArray.size()];
        for(int i=0; i<tempArray.size(); i++){
            List<Double> x = tempArray.get(i);
            
            for(int j=0; j<x.size();j++){
                d[i][j]= x.get(j);
            }
        }
        
        GroupNodeCls groupNode = new GroupNodeCls(Nodes, new Matrix(d));        
        
        setGroupNodeLocal(groupNode);
        
        AddToListAndProcess(groupNode);
    }
    
    public void AddToListAndProcess(GroupNodeCls groupNode){
        
        groupNodeList.add(groupNode);
        
        boolean DoWeSplit = CalculateModularityValue(groupNode);
        
        if(DoWeSplit){
            
            int index = groupNode.ReturnHighestEigenValueIndex();
            double[] Vector = groupNode.ReturnEigenVector(index);
            
            SplitIntoGroups(Vector, groupNode);
        }
        
        //I split the group into negative and positive. 
        //We then for each group calculate Z Modulaty. 
        //If less than zero we don't need split
        //If positive, then rerun whole process above using subset adjaceny of group. 
    }
    
    public boolean CalculateModularityValue(GroupNodeCls groupNode){
        
        //TODO
        return false;
    }
    
    public void SplitIntoGroups(double[] Vector, GroupNodeCls groupNode){
        
        ArrayList<String> Group1 = new ArrayList<>();
        ArrayList<String> Group2 = new ArrayList<>();
        
        for(int i=0; i<Vector.length; i++){
            
            if(Vector[i]<0)
                Group1.add(groupNode.getNodes()[i]);
            else
                Group2.add(groupNode.getNodes()[i]);
        }
        
        
        //GroupNodeCls NewGroupNode = new GroupNodeCls(NewNode, new Matrix(newMatrix));        
        //AddToListAndProcess(NewGroupNode);
        
        //GroupNodeCls NewGroupNode2 = new GroupNodeCls(NewNode2, new Matrix(newMatrix2));        
        //AddToListAndProcess(NewGroupNode2);
    }
  
}
