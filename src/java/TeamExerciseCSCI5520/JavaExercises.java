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
     * Properties of our Program
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
    private String OutputString;
    private String RecommendClass;
    private String OutputResultClass;
    private String CodeString;
    private String Input;
    private String ShowInputWindow;

    /**
     * Getters and setters for program
     */
    
    public String getOutputString() {
        return OutputString;
    }

    public void setOutputString(String OutputString) {
        this.OutputString = OutputString;
    }

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

    public void compileRun() throws IOException{
                
        StringBuffer consoleBuffer = new StringBuffer();
        File JavaFile = new File(ExerciseSelected+".java");
        JavaFile.deleteOnExit();

        //Get Code written by the user and save it to a .java file
        BufferedWriter bw   = new BufferedWriter(new FileWriter(JavaFile.getAbsolutePath()));
        bw.write(getCodeString());
        bw.close();

        //get the java file and pass a javac command to ProcessBuilder
        String command[]    = {"javac",JavaFile.getAbsolutePath()};
        ProcessBuilder pb   = new ProcessBuilder(command);  //We can add arguments like a string formatter
        Process p           = pb.start();  
        consoleBuffer.append("<pre>command>javac "+ExerciseSelected+".java<br>");

        //There may an error returned. 
        ErrorString = ReturnMessage("javac", p.getErrorStream()).toString();

        //If there is no error returned then we are good to run the java program
        if(ErrorString.isEmpty()){
            consoleBuffer.append("Compiled Successfully<br><br>");
            
            File JavaClassFile  = new File(ExerciseSelected);
            JavaClassFile.deleteOnExit();

            //Go to the directory of our java class file. 
            System.getProperty(JavaClassFile.getAbsolutePath().substring(0, JavaClassFile.getAbsolutePath().lastIndexOf("\\")));

            //Build our java command line call to pass to processbuilder. We may input so generate a command with input if that is the case
            ProcessBuilder runpb = null;
            if(Input != null && !Input.isEmpty()){
                String runCommand[] = {"java",ExerciseSelected + " " + Input}; //add input parameters
                consoleBuffer.append("command> java " +ExerciseSelected + " " + Input+"<br>");
                runpb   = new ProcessBuilder(runCommand);  //We can add arguments like a string formatter
            }
            else{
                String runCommand[] = {"java",ExerciseSelected}; //add input parameters
                consoleBuffer.append("command> java " +ExerciseSelected+"<br>");
                runpb   = new ProcessBuilder(runCommand);  //We can add arguments like a string formatter
            }
            Process runp = runpb.start();  

            //We may have an error, or we may have input from the program
            ErrorString = ReturnMessage("java", runp.getErrorStream()).toString();
            InputString = ReturnMessage("java", runp.getInputStream()).toString();
            
            if(ErrorString.isEmpty()){
                consoleBuffer.append(InputString);
                OutputString = InputString;
            }
            else
                consoleBuffer.append(ErrorString);
            
            consoleBuffer.append("<br><br>command></pre>");

            runp.destroy();
            RecommendClass      ="recommendHidden";
            OutputResultClass   ="outputresult";
            ErrorString = consoleBuffer.toString();
        }
            
        p.destroy();
    }
    
    public StringBuffer ReturnMessage(String str, InputStream I) throws IOException{
        
        int count = 0;
        StringBuffer sb = new StringBuffer();
        if(I != null){
            BufferedReader in = new BufferedReader(new InputStreamReader(I));
            for (String line; (line = in.readLine()) != null; ){
                if(count == 0){
                    sb.append("<pre>");

                    String errorLine = "";
                    if(str == "javac"){
                        errorLine = line.split(".java")[1];
                        sb.append(ExerciseSelected+ ((str=="javac")?".java" : ".class") +errorLine);
                    }
                    else{
                        errorLine = line;
                        sb.append(errorLine);
                    }
                }
                else{
                    sb.append("<br>");
                    sb.append(line);
                }
                count++;
            }
            in.close();
        }
        if(count>0)
            sb.append("</pre>");


        RecommendClass      ="recommendHidden";
        OutputResultClass   ="outputresult";

        return sb;
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
    
    public void AutomaticCheck(){
        
    }    
}
