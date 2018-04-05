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
import java.io.PrintWriter;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.ProcessBuilder.Redirect;
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
    private String DataPath;


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
    private String inputStyle;
    private String expectedOutputStyle;
    private String yourOutputStyle;
    private String expectedOutputString;
    private Output output;
    private String automaticCheckAreaStyle;
    private String automaticCheckButtonStyle;

    public String getAutomaticCheckButtonStyle()                    {return automaticCheckButtonStyle;}
    public void setAutomaticCheckButtonStyle(String value)          {this.automaticCheckButtonStyle = value;}
    public String getAutomaticCheckAreaStyle()                      {return automaticCheckAreaStyle;}
    public void setAutomaticCheckAreaStyle(String value)            {this.automaticCheckAreaStyle = value;}
    public String getDataPath()                                     {return DataPath;}
    public void setDataPath(String DataPath)                        {this.DataPath = DataPath;}
    public Output getOutput()                                       {return output;}
    public void setOutput(Output output)                            {this.output = output;}
    public String getExpectedOutputString()                         {return expectedOutputString;}
    public void setExpectedOutputString(String expectedOutputString){this.expectedOutputString = expectedOutputString;}
    public String getInputStyle()                                   {return inputStyle;}
    public void setInputStyle(String inputStyle)                    {this.inputStyle = inputStyle;}
    public String getExpectedOutputStyle()                          {return expectedOutputStyle;}
    public void setExpectedOutputStyle(String expectedOutputStyle)  {this.expectedOutputStyle = expectedOutputStyle;}
    public String getYourOutputStyle()                              {return yourOutputStyle;}
    public void setYourOutputStyle(String yourOutputStyle)          {this.yourOutputStyle = yourOutputStyle;}
    public String getInputFile()                                    {return inputFile;}
    public void setInputFile(String inputFile)                      {this.inputFile = inputFile;}
    public String getOutputString()                                 {return OutputString;}
    public void setOutputString(String OutputString)                {this.OutputString = OutputString;}
    public String getShowInputWindow()                              {return ShowInputWindow;}
    public void setShowInputWindow(String ShowInputWindow)          {this.ShowInputWindow = ShowInputWindow;}
    public String getInput()                                        {return input;}
    public void setInput(String input) throws FileNotFoundException {this.input = input;}
    public String getRecommendClass()                               {return RecommendClass;}
    public void setRecommendClass(String recommendClass)            {this.RecommendClass = recommendClass;}
    public String getOutputResultClass()                            {return OutputResultClass;}
    public void setOutputResultClass(String OutputResultClass)      {this.OutputResultClass = OutputResultClass;}
    public String getErrorString()                                  {return ErrorString;}
    public void setErrorString(String ErrorString)                  {this.ErrorString = ErrorString;}
    public String getInputString()                                  {return InputString;}
    public void setInputString(String InputString)                  {this.InputString = InputString;}
    public ArrayList<String> getAllExercises()                      {return AllExercises;}
    public void setAllExercises(ArrayList<String> AllExercises)     {this.AllExercises = AllExercises;}
    public String getComment()                                      {return Comment;}
    public void setComment(String Comment)                          {this.Comment = Comment;}
    public String getDataFile()                                     {return DataFile;}
    public void setDataFile(String DataFile)                        {this.DataFile = DataFile;}
    public String getChapterSelected()                              {return ChapterSelected;}
    public void setChapterSelected(String ChapterSelected)          {this.ChapterSelected = ChapterSelected;       ResetExercises();}
    public ArrayList<String> getChapters()                          {return Chapters;}
    public void setChapters(ArrayList<String> chapters)             {this.Chapters = chapters;}
    public ArrayList<String> getExercises()                         {return Exercises;}
    public void setExercises(ArrayList<String> exercises)           {this.Exercises = exercises;}
    public String getExerciseSelected()                             {return ExerciseSelected;}
    public void setExerciseSelected(String ExerciseSelected)        {this.ExerciseSelected = ExerciseSelected;}
    public String getCodeString()                                   {return CodeString;}
    public void setCodeString(String CodeString) throws IOException {this.CodeString = CodeString;}  
    
    public JavaExercises() throws IOException {
        
        ServletContext ctx  = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String realPath     = ctx.getRealPath("/");
        setDataPath(realPath + "ags10e\\");
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
        
        ShowInputWindow             = "display:none;";
        automaticCheckAreaStyle     = "display:none;";
        inputStyle                  = "display:block;";
        expectedOutputStyle         = "display:block;";
        yourOutputStyle             = "display:block;";
        automaticCheckButtonStyle   = "display:inline-block;";

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
          
        OutputResultClass           = "outputresultHidden";
        RecommendClass              = "recommend";
        ErrorString                 = "";
        InputString                 = "";
        ShowInputWindow             = "display:none;";
        automaticCheckAreaStyle     = "display:none;";
        inputStyle                  = "display:block;";
        expectedOutputStyle         = "display:block;";
        yourOutputStyle             = "display:block;";
        automaticCheckButtonStyle   = "display:inline-block;";

        StringBuffer sb             = GetInputForExercise();
        CodeString                  = GetCodeForExercise();

        if(sb != null && sb.length()>0){
            ShowInputWindow = "display:block;";
            InputString = GetInputForExercise().toString();
        }
        else
            ShowInputWindow = "display:none;";

    }

    public String GetCodeForExercise() throws IOException {
        
        setDataFile(getDataPath() + "exercisedescription");
        String CodeReturn = "";
        
        try{
            String route = getDataFile() + "\\"+ ExerciseSelected;
            Scanner scan = new Scanner(new File(route));
            
            while(scan.hasNext()){
                 
                String text = scan.nextLine();
                CodeReturn = ReturnComment(text);
            }
        } catch (FileNotFoundException e){
            //File cannot be found
        }
        
        return CodeReturn;
    }

    public String ReturnComment(String text){
        
        if(text.equals("This exercise can be compiled and submitted, but cannot be run and automatically graded.")){
            automaticCheckButtonStyle   = "display:none;";
            return "/* This exercise cannot be graded automatically becuase it may use random\n numbers, file input/output, or graphics. */";
        }
        else{
            automaticCheckButtonStyle   = "display:inline-block;";
            return "/*Paste your "+ExerciseSelected+" here and click Automatic Check.\n" +
                    "For all programming projects, the numbers should be double\n" +
                    "unless it is explicitly stated as integer.\n" +
                    "If you get a java.util.InputMismatchException error, check if\n" +
                    "your code used input.readInt(), but it should be input.readDouble().\n" +
                    "For integers, use int unless it is explicitly stated as long. */";
        }
    }

    public StringBuffer GetInputForExercise() throws IOException {
        
        setDataFile(getDataPath() + "gradeexercise");

        File f                  = new File(getDataFile());
        File[] fList            = f.listFiles(); 
        
        for (File file : fList){
            
            String FileName = file.getName();
            if(FileName.contains(ExerciseSelected) == true && FileName.contains("input") == true){
                               
                Scanner scan = new Scanner(new File(getDataFile() + "\\" + FileName));            
                StringBuffer sb = new StringBuffer();
                
                while(scan.hasNext()){
                    sb.append(scan.nextLine());
                }
                
                if(sb.length()>0){
                    return sb;
                }
            }
        }
        return null;
    }
        
    public void compileRun(boolean AutomaticRun) throws IOException{
                      
        automaticCheckAreaStyle = "display:none";

        StringBuffer consoleBuffer = new StringBuffer();        
        consoleBuffer.append("<pre>command>javac "+ExerciseSelected+".java<br>");
        
        Output Compileoutput = CompileJavaProgram(ExerciseSelected);

        if(Compileoutput.getErrorString().isEmpty()){

            consoleBuffer.append("Compiled Successfully<br><br> command> java " +ExerciseSelected+"<br>");
            
            Output RunOutput = RunJavaProgram("", ExerciseSelected, Compileoutput);
            
            consoleBuffer.append( (RunOutput.getErrorString().isEmpty()) ? RunOutput.getM_HTMLOutputString(): RunOutput.getErrorString());

            consoleBuffer.append("<br>command></pre>");
            RecommendClass      = "recommendHidden";
            
            if(!AutomaticRun)
                OutputResultClass   = "outputresult";
            
            ErrorString         = consoleBuffer.toString();
            OutputString        = RunOutput.getOutputString();
        }
        else{
            
            consoleBuffer.append(Compileoutput.getErrorString());
            consoleBuffer.append("<br>command></pre>");
            RecommendClass      = "recommendHidden";
            OutputResultClass   = "outputresult";
            ErrorString         = consoleBuffer.toString();
            OutputString        = Compileoutput.getOutputString();
        }  
    }
      
    public Output CompileJavaProgram(String FileName) throws IOException{
    
        String JavaFilePath = getDataPath() + FileName.concat(".java");
        boolean FileCreated = CreateFileWithText(JavaFilePath, getCodeString());

        StringBuffer sb = GetInputForExercise();
        if(sb != null && sb.length()>0){
            
            setInputString(sb.toString());
            WriteStringBufferToInputFile(sb);
        }
        
        if(FileCreated){
    
            ArrayList<String> Command = new ArrayList<>();
            Command.add("javac");
            Command.add(JavaFilePath);
            
            ProcessBuilder pb   = new ProcessBuilder(Command);  //We can add arguments like a string formatter
            pb.directory(new File(getDataPath()));
            
            Process p           = pb.start();  

            final Output result = new Output(   ReturnMessage("javac", p.getErrorStream()).toString(), 
                                                ReturnMessage("javac", p.getInputStream()).toString(), "", "");
            
            result.setSourceDirectory(getDataPath());
            result.setFullPath(JavaFilePath);
            
            p.destroy();        
            return result;
        }
        
        return null;
    }
    
    public Output RunJavaProgram(String InputString, String ClassName, Output output) throws IOException{
        
        ProcessBuilder runpb            = null;
        ArrayList<String> runCommand    = new ArrayList<>();
        
        runCommand.add("java");
        runCommand.add(ClassName);
       
        runpb = new ProcessBuilder(runCommand);  //We can add arguments like a string formatter
        runpb.directory(new File(getDataPath()));
        runpb.redirectErrorStream(true);

        if(getInputFile() != null)
            runpb.redirectInput(Redirect.from(new File(getInputFile())));

        Process runp = runpb.start();  

        String ErrorMessage         = GenerateHTMLFromText(ReturnMessage("java", runp.getErrorStream())).toString();
        StringBuffer OutputMessage  = ReturnMessage("java", runp.getInputStream());
        final Output result         = new Output(ErrorMessage, OutputMessage.toString(), "", GenerateHTMLFromText(OutputMessage).toString());
        
        result.setFullPath(output.getFullPath());
        result.setSourceDirectory(output.getSourceDirectory());
        
        WriteToOutputFile(result.getOutputString());
        
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
        
        if(Text.length() == 0)  return sb;

        sb.append("<pre>");

        String[] lines = Text.toString().split("\\n");
        for(String s: lines){
        
            if(s == "javac")
                sb.append(ExerciseSelected+ ".java" + s.split(".java")[1]);
            else
                sb.append(s);
        }
        
        sb.append("</pre>");

        return sb;        
    }
        
    public void automaticCheck() throws IOException{
        
        compileRun(true);
        automaticCheckAreaStyle = "display:block";
        OutputResultClass   = "outputresultHidden";
        
        //Run a compare and highlight where the objects do not match. 
        //Print input, output and any errors. 
    }  
    
    public boolean WriteToOutputFile(String file) throws FileNotFoundException, UnsupportedEncodingException{

        try{
            setDataFile(getDataPath() + "temp\\" + ExerciseSelected + ".output");

            PrintWriter writer = new PrintWriter(getDataFile(), "UTF-8");
            writer.println(file);
            writer.close();
            return true;
        }
        catch(FileNotFoundException ex){
            return false;
        }
        catch(UnsupportedEncodingException ex){
            return false;
        }
    }
    
    public String ReturnOutputFileString(String path) throws FileNotFoundException, IOException{
        
        String sCurrentLine;
        String returnStr    = "";
        String newPath      = path.substring(0, path.lastIndexOf(".")).concat(".output");
        BufferedReader br   = new BufferedReader(new FileReader(newPath));

        while ((sCurrentLine = br.readLine()) != null) {
            
            returnStr += sCurrentLine;
        }

        return returnStr;
    }
    
    public boolean WriteStringBufferToInputFile(StringBuffer inputString) throws IOException{
        
        setDataFile(getDataPath() + "temp\\" + ExerciseSelected + ".input");

        BufferedWriter bw = null;
        String pathToWrite = getDataFile();
        
        try{
            
            bw = new BufferedWriter(new FileWriter(pathToWrite));
            bw.write(inputString.toString());
        } catch(IOException e){

            return false;
        } 

        if (bw != null)
            bw.close();
        
        setInputFile(getDataFile());
        
        return true;
    }
    
    public boolean CreateFileWithText(String FullPath, String Text) throws IOException{
        
        try{
            File JavaFile = new File(FullPath);
            JavaFile.deleteOnExit();

            //Get Code written by the user and save it to a .java file using absolute path
            BufferedWriter bw   = new BufferedWriter(new FileWriter(JavaFile.getAbsolutePath()));
            bw.write(Text);
            bw.close();
            
            return true;
        }
        catch(IOException e){
            return false;
        }
    }    
}