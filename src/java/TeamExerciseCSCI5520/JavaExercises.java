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
import java.util.Scanner;
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
    private String Code;
    private String Input;
    private String ShowInputWindow;

    public String getShowInputWindow() {
        return ShowInputWindow;
    }

    public void setShowInputWindow(String ShowInputWindow) {
        this.ShowInputWindow = ShowInputWindow;
    }

    public String getInput() {
        return Input;
    }

    public void setInput(String Input) throws FileNotFoundException {
        this.Input = Input;
    }
    
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

    public void setCodeString(String CodeString) throws IOException {
        this.CodeString = CodeString;
    }
   
    public void SetExerciseInformation() throws IOException{
        
        setCodeString(GetCodeForExercise());
        setInput(GetInputFromFiles());
    }
            
    public String GetCodeForExercise() throws IOException {
        ServletContext ctx  = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String realPath     = ctx.getRealPath("/");
        setDataFile(realPath + "ags10e\\exercisedescription");
        String CodeReturn = "";
        
        try{
            String route = getDataFile() + "\\"+ ExerciseSelected;
   
            
            Scanner scan = new Scanner(new File(route));
            
            while(scan.hasNext()){
                 
                String text = scan.nextLine();
               
                if(text.equals("This exercise can be compiled and submitted, but cannot be run and automatically graded.")){
                    CodeReturn = "/* This exercise cannot be graded automatically becuase it may use random\n numbers, file input/output, or graphics. */";
                }
                else{
                    CodeReturn = "/*Paste your "+ExerciseSelected+" here and click Automatic Check.\n" +
                            "For all programming projects, the numbers should be double\n" +
                            "unless it is explicitly stated as integer.\n" +
                            "If you get a java.util.InputMismatchException error, check if\n" +
                            "your code used input.readInt(), but it should be input.readDouble().\n" +
                            "For integers, use int unless it is explicitly stated as long. */";
                }
            }
        } catch (FileNotFoundException e){
          //  System.out.println("The file could not be found");
        }
        
        return CodeReturn;
    }
    
    public JavaExercises() throws IOException {
        ServletContext ctx  = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String realPath     = ctx.getRealPath("/");
        setDataFile(realPath + "ags10e\\exercisedescription");
        
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
        
        ShowInputWindow = "display:none;";
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
        temp.deleteOnExit();

        String tempStr      = temp.getAbsolutePath();
        BufferedWriter bw   = new BufferedWriter(new FileWriter(tempStr));
        String x            = getCodeString();
        bw.write(x);
        bw.close();

        String command[]    = {"javac",tempStr};
        ProcessBuilder pb   = new ProcessBuilder(command);  //We can add arguments like a string formatter
        Process p           = pb.start();  

        //if( p.exitValue() == 0 ){
        //}
        if( p.getErrorStream().read() != -1 ){
            ErrorString = ReturnMessage("javac", p.getErrorStream()).toString();
        }
        else if( p.getInputStream().read() != -1 ){
            InputString = ReturnMessage("javac", p.getInputStream()).toString();
        }
        else{
            File tempClass  = new File(ExerciseSelected);
            tempClass.deleteOnExit();

            String newtempStr         = tempClass.getAbsolutePath();
            System.getProperty(newtempStr.substring(0, newtempStr.lastIndexOf("\\")));

            String runCommand[]    = {"java",ExerciseSelected}; //add input parameters
            
            ProcessBuilder runpb   = new ProcessBuilder(runCommand);  //We can add arguments like a string formatter
            Process runp           = runpb.start();  

            StringBuffer sb = new StringBuffer();
            sb.append("<pre>command>javac "+ExerciseSelected+".java<br>");
            sb.append("Compiled Successful<br><br>");
            sb.append("command> java " +ExerciseSelected+".class<br>");

            if( runp.getErrorStream().read() != -1 ){
                ErrorString = ReturnMessage("java", runp.getErrorStream()).toString();
                sb.append(ErrorString);
            }
            else if( runp.getInputStream().read() != -1 ){
                InputString = ReturnMessage("java", runp.getInputStream()).toString();
            }
            else
                sb.append("Successfully run");
            
            sb.append("<br><br>command></pre>");

            runp.destroy();
            RecommendClass      ="recommendHidden";
            OutputResultClass   ="outputresult";
           ErrorString = sb.toString();
        }
            
        p.destroy();
    }
    
    public StringBuffer ReturnMessage(String str, InputStream I) throws IOException{
        
        int count = 0;
        StringBuffer sb = new StringBuffer();
        sb.append("<pre>command>"+str+" "+ExerciseSelected+((str=="javac")?".java" : ".class")+"<br>");
        if(I != null){
            BufferedReader in = new BufferedReader(new InputStreamReader(I));
            String line = null;
            while((line = in.readLine()) != null ){
                if(count == 0){
                    
                    String errorLine = "";
                    if(str == "javac")
                        errorLine = line.split(((str=="javac")?".java" : ".class"))[1];
                    else
                        errorLine = line;
                    
                    sb.append(ExerciseSelected+ ((str=="javac")?".java" : ".class") +errorLine);
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
    
    public String GetInputFromFiles() throws FileNotFoundException{
      
        
        ServletContext ctx  = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String realPath     = ctx.getRealPath("/");
        setDataFile(realPath + "ags10e\\gradeexercise");

        File f                  = new File(getDataFile());
        File[] fList            = f.listFiles(); 
        
        for (File file : fList){
            String FileName = file.getName();
            if(FileName.contains(ExerciseSelected) == true && FileName.contains("input") == true){
                Scanner scan = new Scanner(new File(getDataFile() + "\\" + FileName));
            
                setShowInputWindow("display:block;");
                
                while(scan.hasNext()){
                    return scan.nextLine();
                }
            }
        }
        setShowInputWindow("display:none;");
        return "";
    }
}
