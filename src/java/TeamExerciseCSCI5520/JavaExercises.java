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
    
public class Output{
        private String m_ErrorString;
        private String m_OutputString;
        private String m_InputString;
        private String m_SourceDirectory;
        private String m_FullPath;
        
        public Output(){
            this.m_ErrorString = "";
            this.m_OutputString = "";
            this.m_InputString = "";
            this.m_SourceDirectory = "";
            this.m_FullPath = "";
        }

        public Output(String ErrorString, String OutputString){
            this.m_ErrorString = ErrorString;
            this.m_OutputString = OutputString;
            this.m_InputString = "";
            this.m_SourceDirectory = "";
            this.m_FullPath = "";
        }

        public Output(String ErrorString, String OutputString, String InputString){
            this.m_ErrorString = ErrorString;
            this.m_OutputString = OutputString;
            this.m_InputString = InputString;
            this.m_SourceDirectory = "";
            this.m_FullPath = "";
        }
        
        public String getInputString() {
            return m_InputString;
        }

        public void setInputString(String m_InputString) {
            this.m_InputString = m_InputString;
        }
        
        public String getErrorString() {
            return m_ErrorString;
        }

        public void setErrorString(String ErrorString) {
            this.m_ErrorString = ErrorString;
        }

        public String getOutputString() {
            return m_OutputString;
        }

        public void setOutputString(String OutputString) {
            this.m_OutputString = OutputString;
        }

        public String getSourceDirectory() {
            return m_SourceDirectory;
        }

        public void setSourceDirectory(String m_SourceDirectory) {
            this.m_SourceDirectory = m_SourceDirectory;
        }

        public String getFullPath() {
            return m_FullPath;
        }

        public void setFullPath(String m_FullPath) {
            this.m_FullPath = m_FullPath;
        }        
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
    
    public void compileRun() throws IOException{
                
        StringBuffer consoleBuffer = new StringBuffer();        
        consoleBuffer.append("<pre>command>javac "+ExerciseSelected+".java<br>");

        Output output = CompileJavaProgram(ExerciseSelected);

        if(output.m_ErrorString.isEmpty()){
            consoleBuffer.append("Compiled Successfully<br><br>");
                        
            if(InputString != null && !InputString.isEmpty()){
                consoleBuffer.append("command> java " +ExerciseSelected + " " + Input+"<br>");
                output = RunJavaProgram(Input, ExerciseSelected, output);
            }
            else{
                consoleBuffer.append("command> java " +ExerciseSelected+"<br>");
                output = RunJavaProgram("", ExerciseSelected, output);
            }
            
            consoleBuffer.append( (output.getErrorString().isEmpty()) ? output.getOutputString() : output.getErrorString());

        }
        else{
            consoleBuffer.append(output.m_ErrorString);
        }  
        
        consoleBuffer.append("<br>command></pre>");
        RecommendClass      = "recommendHidden";
        OutputResultClass   = "outputresult";
        ErrorString         = consoleBuffer.toString();
        OutputString        = output.m_OutputString;
    }
    
    public String CreateFileWithText(String File, String Extension, String Text) throws IOException{
        
        try{
            File JavaFile = new File(ExerciseSelected+"."+Extension);
            JavaFile.deleteOnExit();

            //Get Code written by the user and save it to a .java file using absolute path
            BufferedWriter bw   = new BufferedWriter(new FileWriter(JavaFile.getAbsolutePath()));
            bw.write(getCodeString());
            bw.close();
            
            return JavaFile.getAbsolutePath();
        }
        catch(IOException e){
            return "";
        }
    }
    
    public String GetSourceDirectory(String Path){
        return Path.substring(0, Path.lastIndexOf("\\"));
    }
    
    public Output CompileJavaProgram(String FileName) throws IOException{
    
        String JavaFilePath = CreateFileWithText(FileName, "java", getCodeString());
        
        if(!JavaFilePath.isEmpty()){
    
            ArrayList<String> Command = new ArrayList<>();
            Command.add("javac");
            Command.add(JavaFilePath);
            
            ProcessBuilder pb   = new ProcessBuilder(Command);  //We can add arguments like a string formatter
            pb.directory(new File(GetSourceDirectory(JavaFilePath)));
            
            Process p           = pb.start();  

            final Output result = new Output(   ReturnMessage("javac", p.getErrorStream()).toString(), 
                                                ReturnMessage("javac", p.getInputStream()).toString());
            result.setSourceDirectory(GetSourceDirectory(JavaFilePath));
            result.setFullPath(JavaFilePath);
            
            p.destroy();        
            return result;
        }
        
        return null;
    }
    
    public Output RunJavaProgram(String InputString, String ClassName, Output output) throws IOException{
        
        //Build our java command line call to pass to processbuilder. We may input so generate a command with input if that is the case
        ProcessBuilder runpb = null;
        ArrayList<String> runCommand = new ArrayList<>();
        runCommand.add("java");
        runCommand.add(ClassName);

        if(InputString != null && !InputString.isEmpty()){
            String[] inputString = InputString.split(" ");
            for(int i=0; i<inputString.length; i++){
                
                runCommand.add(inputString[i]);
            }
        }
        
        runpb           = new ProcessBuilder(runCommand);  //We can add arguments like a string formatter
        runpb.directory(new File(output.m_SourceDirectory));
        Process runp    = runpb.start();  

        final Output result = new Output(   GenerateHTMLFromText(ReturnMessage("java", runp.getErrorStream())).toString(), 
                                            GenerateHTMLFromText(ReturnMessage("java", runp.getInputStream())).toString());

        result.setFullPath(output.getFullPath());
        result.setSourceDirectory(output.getSourceDirectory());
        
        runp.destroy();
        
        return result;
    }
        
    public StringBuffer ReturnMessage(String str, InputStream I) throws IOException{
        
        StringBuffer sb = new StringBuffer();
        if(I != null){
            BufferedReader in = new BufferedReader(new InputStreamReader(I));
            for (String line; (line = in.readLine()) != null; ){
                sb.append(line);
                sb.append(System.lineSeparator());
            }
            in.close();
        }
        return sb;
    }
    
    public StringBuffer GenerateHTMLFromText(StringBuffer Text){
        
        
        StringBuffer sb = new StringBuffer();
        
        if(Text.length() == 0)
            return sb;

        sb.append("<pre>");

        String[] lines = Text.toString().split("\\n");
        for(String s: lines){
        
            if(s == "javac"){
                sb.append(ExerciseSelected+ ".java" + s.split(".java")[1]);
            }
            else{
                sb.append(s);
            }
        }
        
        sb.append("</pre>");

        return sb;        
    }
        
    public void AutomaticCheck(){
        //This will run compileRun() and get back the output which means you need to store output into an object. 
        //We have the actual output from the file. 
        //Run a compare and highlight where the objects do not match. 
        //Print input, output and any errors. 
    }    
}
