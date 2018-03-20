/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TeamExerciseCSCI5520;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 *
 * @author David Klecker
 */
@Named(value = "javaExercises")
@SessionScoped
public class JavaExercises implements Serializable {

    /**
     * Creates a new instance of JavaExercises
     */
    private ArrayList<String> Chapters;
    private ArrayList<String> Exercises;
    private ArrayList<String> AllExercises;
    private String ExerciseSelected;
    private String ChapterSelected;
    private String DataFile;
    private String Comment;
    private String ErrorString;
    private String InputString;
    private String RecommendClass;
    private String OutputResultClass;
    private String CodeString;
    
    public String getRecommendClass() {
        return RecommendClass;
    }

    public void setRecommendClass(String recommendClass) {
        this.RecommendClass = recommendClass;
    }

    public String getOutputResultClass() {
        return OutputResultClass;
    }

    public void setOutputResultClass(String OutputResultClass) {
        this.OutputResultClass = OutputResultClass;
    }

    public String getErrorString() {
        return ErrorString;
    }

    public void setErrorString(String ErrorString) {
        this.ErrorString = ErrorString;
    }

    public String getInputString() {
        return InputString;
    }

    public void setInputString(String InputString) {
        this.InputString = InputString;
    }

    public ArrayList<String> getAllExercises() {
        return AllExercises;
    }

    public void setAllExercises(ArrayList<String> AllExercises) {
        this.AllExercises = AllExercises;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String Comment) {
        this.Comment = Comment;
    }

    public String getDataFile() {
        return DataFile;
    }

    public void setDataFile(String DataFile) {
        this.DataFile = DataFile;
    }
    
    public String getChapterSelected() {
        
        return ChapterSelected;
    }

    public void setChapterSelected(String ChapterSelected) {
        
        this.ChapterSelected = ChapterSelected;
        
        ResetExercises();
    }

    public ArrayList<String> getChapters(){

        return Chapters;
    }
    public void setChapters(ArrayList<String> chapters){

        this.Chapters = chapters;
    }
    
    public ArrayList<String> getExercises(){
        
        return Exercises;
    }
    
    public void setExercises(ArrayList<String> exercises){
        
        this.Exercises = exercises;
    }

    public String getExerciseSelected() {
        
        return ExerciseSelected;
    }

    public void setExerciseSelected(String ExerciseSelected) {
        
        this.ExerciseSelected = ExerciseSelected;
    }

    public String getCodeString() {
        return CodeString;
    }

    public void setCodeString(String CodeString) {
        this.CodeString = CodeString;
    }
   
    
    public JavaExercises() throws IOException {
        ServletContext ctx  = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String realPath     = ctx.getRealPath("/");
        setDataFile(realPath + "ags10e/exercisedescription");
        
        Chapters            = new ArrayList<>();
        Exercises           = new ArrayList<>();
        AllExercises        = new ArrayList<>();
        ExerciseSelected    = "Exercise01_01";
        ChapterSelected     = "Chapter 1";
        ErrorString         = "";
        InputString         = "";
        RecommendClass      ="recommend";
        OutputResultClass   ="outputresultHidden";

        CodeString          = "/*Paste your "+ExerciseSelected+" here and click Automatic Check.\n" +
                              "For all programming projects, the numbers should be double\n" +
                              "unless it is explicitly stated as integer.\n" +
                              "If you get a java.util.InputMismatchException error, check if\n" +
                              "your code used input.readInt(), but it should be input.readDouble().\n" +
                              "For integers, use int unless it is explicitly stated as long. */";
        
        Initialize();
    }
    
    public void Initialize() throws FileNotFoundException, IOException{
        
        File f                  = new File(getDataFile());
        File[] fList            = f.listFiles(); 
        
        for (File file : fList){
            String FileName = file.getName();
            String[] str    = FileName.split("_")[0].split("Exercise");
            if(str.length>0){
                int chapterNo = Integer.parseInt(str[1]);
                if(Chapters.contains("Chapter "+chapterNo) == false)
                    Chapters.add("Chapter "+chapterNo);
                
                AllExercises.add(FileName);
            }
        }
        
        ResetExercises();
    }
    
    public void ResetExercises(){
        
        Exercises = new ArrayList<>();
        
        for (int i=0; i<AllExercises.size(); i++){
            
            String FileName = AllExercises.get(i);
            String[] str    = FileName.split("_")[0].split("Exercise");
            if(str.length>0){
                int chapterNo = Integer.parseInt(str[1]);

                if(Integer.parseInt(ChapterSelected.split("Chapter")[1].trim()) == chapterNo)
                    Exercises.add(FileName);
            }
        }
    }
    
    public void compileRun() throws IOException{
                
        File temp = new File(ExerciseSelected+".java");
 
        String tempStr = temp.getAbsolutePath();
        temp.deleteOnExit();

        BufferedWriter bw = new BufferedWriter(new FileWriter(tempStr));
        String x = getCodeString();
        bw.write(x);
        bw.close();

        String command[] = {"javac",tempStr};
        ProcessBuilder pb = new ProcessBuilder(command);  //We can add arguments like a string formatter

        Process p = pb.start();  

        //if( p.exitValue() == 0 ){

        //}
        
        if( p.getErrorStream().read() != -1 ){
            ErrorString = ReturnMessage(p.getErrorStream()).toString();
        }
        if( p.getInputStream().read() != -1 ){
            InputString = ReturnMessage(p.getInputStream()).toString();
        }
        else{
            StringBuffer sb = new StringBuffer();
            sb.append("<pre>command>javac "+ExerciseSelected+".java<br>");
            sb.append("Compiled Successful");
            sb.append("<br>command></pre>");
            RecommendClass      ="recommendHidden";
            OutputResultClass   ="outputresult";
            ErrorString = sb.toString();
        }
            
        p.destroy();
                
    }
    
    public StringBuffer ReturnMessage(InputStream I) throws IOException{
        
        int count = 0;
        StringBuffer sb = new StringBuffer();
        sb.append("<pre>command>javac "+ExerciseSelected+".java<br>");
        if(I != null){
            BufferedReader in = new BufferedReader(new InputStreamReader(I));
            String line = null;
            while((line = in.readLine()) != null ){
                if(count == 0){
                    
                    String errorLine = line.split(".java")[1];
                    sb.append(ExerciseSelected+".java"+errorLine);
                }
                else
                    sb.append(line);
                
                sb.append("<br>");
                count++;
            }
            in.close();
        }
        sb.append("<br>command></pre>");

        RecommendClass      ="recommendHidden";
        OutputResultClass   ="outputresult";

        return sb;
    }
    
    public void AutomaticCheck(){
        
    }
}