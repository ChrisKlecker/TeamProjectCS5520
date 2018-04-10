package TeamExerciseCSCI5520;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    private String input;
    private String ShowInputWindow;
    private String inputFile;
    private String ExampleInputFile;
    private String correctProgram;
    private String correctProgramString;
    private String expectedOutputStyle;
    private String yourOutputStyle;
    private String expectedOutputString;
    private String automaticCheckAreaStyle;
    private String automaticCheckButtonStyle;
    private String inputStyle;
    private String yourOutputString;
    private String acInput;
    
    public String getAcInput()                                   {return acInput;}
    public void setAcInput(String acInput)                    {this.acInput = inputStyle;}
    public String getInputStyle()                                   {return inputStyle;}
    public void setInputStyle(String inputStyle)                    {this.inputStyle = inputStyle;}
    public String getExpectedOutputString()                                   {return expectedOutputString;}
    public void setExpectedOutputString(String expectedOutputString)                    {this.expectedOutputString = expectedOutputString;}
    public String getYourOutputString()                                   {return yourOutputString;}
    public void setYourOutputString(String yourOutputString)                    {this.yourOutputString = yourOutputString;}

    public String getInputFile() {
        return inputFile;
    }

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public String getExampleInputFile() {
        return ExampleInputFile;
    }

    public void setExampleInputFile(String ExampleInputFile) {
        this.ExampleInputFile = ExampleInputFile;
    }

    public String getCorrectProgram() {
        return correctProgram;
    }

    public void setCorrectProgram(String correctProgram) {
        this.correctProgram = correctProgram;
    }

    public String getCorrectProgramString() {
        return correctProgramString;
    }

    public void setCorrectProgramString(String correctProgramString) {
        this.correctProgramString = correctProgramString;
    }

    public String getExpectedOutputStyle() {
        return expectedOutputStyle;
    }

    public void setExpectedOutputStyle(String expectedOutputStyle) {
        this.expectedOutputStyle = expectedOutputStyle;
    }

    public String getYourOutputStyle() {
        return yourOutputStyle;
    }

    public void setYourOutputStyle(String yourOutputStyle) {
        this.yourOutputStyle = yourOutputStyle;
    }

    public String getAutomaticCheckAreaStyle() {
        return automaticCheckAreaStyle;
    }

    public void setAutomaticCheckAreaStyle(String automaticCheckAreaStyle) {
        this.automaticCheckAreaStyle = automaticCheckAreaStyle;
    }

    public String getAutomaticCheckButtonStyle() {
        return automaticCheckButtonStyle;
    }

    public void setAutomaticCheckButtonStyle(String automaticCheckButtonStyle) {
        this.automaticCheckButtonStyle = automaticCheckButtonStyle;
    }

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
        return input;
    }

    public void setInput(String input) throws FileNotFoundException {
        this.input = input;
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
        ServletContext ctx          = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String realPath             = ctx.getRealPath("/");
        setDataFile(realPath + "ags10e\\");
        
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
        
        ShowInputWindow             = "display:none;";
        inputStyle                  = "display:block;"; //
        expectedOutputStyle         = "display:block;";
        yourOutputStyle             = "display:block;";
        automaticCheckAreaStyle     = "display:none;";
        
        Initialize();
    }

    public void Initialize() throws FileNotFoundException, IOException{
        
        File f                  = new File(getDataFile()+"exercisedescription");
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
          
        CodeString=(GetCodeForExercise());
        input=(GetInputFromFiles());
        //inputStyle                  = "display:none;";
        //expectedOutputStyle         = "display:none;";
        //yourOutputStyle             = "display:none;";
    }

    public String GetCodeForExercise() throws IOException {
        
        //ServletContext ctx  = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        //String realPath     = ctx.getRealPath("/");
        //setDataFile(realPath + "ags10e\\exercisedescription");
        
        String CodeReturn = "";
        
        try{
            String route = getDataFile() + "\\exercisedescription\\"+ ExerciseSelected;
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
    
    /*public String GetInputForExercise() throws IOException {
        
        //ServletContext ctx  = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        //String realPath     = ctx.getRealPath("/");
        //setDataFile(realPath + "ags10e\\temp");

        File f                  = new File(getDataFile() + "\\gradeexercise");
        File[] fList            = f.listFiles(); 
        
        for (File file : fList){
            
            String FileName = file.getName();
            
            if(FileName.contains(ExerciseSelected) == true && FileName.contains("input") == true){
                
                //I noticed that this doesn't check if the file is "extra" even if the exercise selected wasn't an "extra" exercise. 
                if(FileName.contains("Extra") == true && ExerciseSelected.contains("Extra") == false)
                    continue;

                setInputFile(getDataFile() + "\\gradeexercise\\" + FileName);    
                Scanner scan = new Scanner(new File(getDataFile() + "\\" + FileName));
            
                setShowInputWindow("display:block;");
                
                while(scan.hasNext()){
                    
                    String value= scan.nextLine();
                    
                    if(value != null && !value.isEmpty()){
                        input = value;
                    }
                    else{
                        input = getInput();
                    }
                    
                    return input;
                }
            }
        }
        
        setShowInputWindow("display:none;");
        return "";
    }*/
    

    public String GetInputFromFiles() throws FileNotFoundException{
        
        //ServletContext ctx  = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        //String realPath     = ctx.getRealPath("/");
        //setDataFile(realPath + "ags10e\\temp");

        File f                  = new File(getDataFile() + "\\gradeexercise");
        File[] fList            = f.listFiles(); 
        
        for (File file : fList){
            
            String FileName = file.getName();
            
            if(FileName.contains(ExerciseSelected) == true && FileName.contains("input") == true){
                
                //I noticed that this doesn't check if the file is "extra" even if the exercise selected wasn't an "extra" exercise. 
                if(FileName.contains("Extra") == true && ExerciseSelected.contains("Extra") == false)
                    continue;

                ExampleInputFile = (getDataFile() + "\\gradeexercise\\" + FileName);    
                Scanner scan = new Scanner(new File(ExampleInputFile));
            
                setShowInputWindow("display:block;");
                
                while(scan.hasNext()){
                    
                    String value= scan.nextLine();
                    
                    if(value != null && !value.isEmpty()){
                        input = value;
                    }
                    else{
                        input = getInput();
                    }
                    
                    return input;
                }
            }
        }
        
        setShowInputWindow("display:none;");
        return "";
    }

    public StringBuffer GrabFileContents(String Path) throws FileNotFoundException{
        
        Scanner scan    = new Scanner(new FileReader(Path));
        StringBuffer sb = new StringBuffer();
        
        while(scan.hasNext()){
            sb.append(scan.next());
        }
        
        return sb;
    }
    
    public Output compileRun(boolean AutomaticRun) throws IOException{
        
        String NewPath = ExampleInputFile.replace("gradeexercise", "temp");
        WriteToFile(NewPath, input, false);
        
        if(!AutomaticRun)
            automaticCheckAreaStyle = "display:none";
        
        //setInput(GetInputForExercise());
        StringBuffer consoleBuffer = new StringBuffer();        
        consoleBuffer.append("<pre>command>javac "+ExerciseSelected+".java<br>");

        //We need to build up our Output object to store everything we need for compile and automatic check. 
        Output output = CompileJavaProgram(ExerciseSelected);
        
        output.setExampleInputFile(ExampleInputFile);
        output.setExampleInputString(GrabFileContents(ExampleInputFile).toString());
        output.setExampleOutputFile(ExampleInputFile.replace(".input", ".output"));
        output.setUserInputFile(ExampleInputFile.replace("gradeexercise", "temp"));
        output.setUserInputString(new StringBuffer(input).toString());
      
        if(output.getErrorString().isEmpty()){
            consoleBuffer.append("Compiled Successfully<br><br>");
                        
            if(input != null && !input.isEmpty()){
                consoleBuffer.append("command> java " +ExerciseSelected + " " + input+"<br>");
                RunJavaProgram(output);
            }
            else{
                consoleBuffer.append("command> java " +ExerciseSelected+"<br>");
                RunJavaProgram(output);
            }
            
            consoleBuffer.append( (output.getErrorString().isEmpty()) ? output.getUserOutputString() : output.getErrorString());

            if(!AutomaticRun)
                OutputResultClass   = "outputresult";
        }
        else{
            consoleBuffer.append(output.getErrorString());
            OutputResultClass   = "outputresult";
        }  
        
        consoleBuffer.append("<br>command></pre>");
        RecommendClass      = "recommendHidden";
        ErrorString         = consoleBuffer.toString();
        OutputString        = output.getUserOutputString().toString();
        
        return output;
    }
    
    public String GetSourceDirectory(String Path){
        return Path.substring(0, Path.lastIndexOf("\\"));
    }
    
    public Output CompileJavaProgram(String FileName) throws IOException{
    
        String JavaFilePath = WriteToFile(FileName.concat(".java"), getCodeString(), true);
        
        if(!JavaFilePath.isEmpty()){
    
            ArrayList<String> Command = new ArrayList<>();
            Command.add("javac");
            Command.add(JavaFilePath);
            
            ProcessBuilder pb   = new ProcessBuilder(Command);  //We can add arguments like a string formatter
            pb.directory(new File(GetSourceDirectory(JavaFilePath)));
            
            Process p           = pb.start();  

            final Output result = new Output(   ReturnMessage("javac", p.getErrorStream()).toString(), 
                                                ReturnMessage("javac", p.getInputStream()).toString(), "", "");
            
            result.setSourceDirectory(GetSourceDirectory(JavaFilePath));
            result.setFullPath(JavaFilePath);
            
            p.destroy();        
            return result;
        }
        
        return null;
    }
    
    public Output RunJavaProgram(Output output) throws IOException{
        
        //Build our java command line call to pass to processbuilder. We may input so generate a command with input if that is the case
        ProcessBuilder runpb = null;
        ArrayList<String> runCommand = new ArrayList<>();
        runCommand.add("java");
        runCommand.add(ExerciseSelected);

        /*if(InputString != null && !InputString.isEmpty()){
            String[] inputString = InputString.split(" ");
            for(int i=0; i<inputString.length; i++){
                
                runCommand.add(inputString[i]);
            }
        }*/
        
        runpb           = new ProcessBuilder(runCommand);  //We can add arguments like a string formatter
        runpb.directory(new File(output.getSourceDirectory()));
        runpb.redirectErrorStream(true);

        if(output.getExampleInputString().length() != 0) {

            runpb.redirectInput(Redirect.from(new File(output.getExampleInputFile())));
        }

        Process runp                = runpb.start();  
        String ErrorMessage         = GenerateHTMLFromText(ReturnMessage("java", runp.getErrorStream())).toString();
        StringBuffer OutputMessage  = ReturnMessage("java", runp.getInputStream());
        final Output result         = new Output(ErrorMessage, OutputMessage.toString(), "", GenerateHTMLFromText(OutputMessage).toString());
        output.setExampleOutputString(OutputMessage.toString());
        
        runp.destroy();

        if(output.getExampleInputString().length() != 0) {
            
            runpb.redirectInput(Redirect.from(new File(output.getUserInputFile())));
            runp                    = runpb.start();  
            ErrorMessage            = GenerateHTMLFromText(ReturnMessage("java", runp.getErrorStream())).toString();
            OutputMessage           = ReturnMessage("java", runp.getInputStream());
            final Output result2    = new Output(ErrorMessage, OutputMessage.toString(), "", GenerateHTMLFromText(OutputMessage).toString());
            
            output.setUserOutputString(OutputMessage.toString());
        }
        
        
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
        
    public void automaticCheck() throws IOException{
        
        correctProgram              = "display:block;";
        OutputResultClass           = "outputresultHidden";
        automaticCheckAreaStyle     = "display:block;";

        Output output = compileRun(true);
        acInput = output.getExampleInputString().toString();
        expectedOutputString = GrabFileContents(output.getExampleOutputFile()).toString();
        yourOutputString = output.getExampleOutputString().toString();
                                
        //String str = GrabFileContents(output.getExampleOutputFile()).toString();
        //String[] outputStringTokens = str.split("#");
        
        //for(int i=0; i<outputStringTokens.length; i++){
            
        //    Pattern pattern = Pattern.compile(outputStringTokens[i]);
        //    Matcher m = pattern.matcher(output.getExampleOutputString());
        //    if(!m.find()){
        //        automaticCheckAreaStyle     = "display:block";
        //        correctProgramString        = "Your Program is incorrect";
        //        return;
        //    }
        //}
        //correctProgramString        = "Your Program is Correct";
    }
    
    public String WriteToFile(String Path, String Data, boolean DestroyTextOnExit){
        
        File JavaFile = new File(Path);
        
        if(DestroyTextOnExit)
            JavaFile.deleteOnExit();

        try{
            
            BufferedWriter bw = new BufferedWriter(new FileWriter(Path, false));
            bw.write(Data);
            bw.close();
            
        } catch(IOException e){
            
            return "";
        } 
        
        return JavaFile.getAbsolutePath();
    }
}