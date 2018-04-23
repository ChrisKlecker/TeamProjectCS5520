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
import java.util.concurrent.ThreadLocalRandom;

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
    final static int EXECUTION_TIME_ALLOWED = 10000;
    final static int EXECUTION_TIME_INTERVAL = 100;

    private final String DEFAULTPATH = "C:\\ags10e\\";
    public String getDefaultPath(){ return DEFAULTPATH;}
    
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
    private String correctProgramStyle;
    private String correctProgramString;
    private String expectedOutputStyle;
    private String yourOutputStyle;
    private String expectedOutputString;
    private String automaticCheckAreaStyle;
    private String automaticCheckButtonStyle;
    private String inputStyle;
    private String yourOutputString;
    private String acInput;
    private String inputOutputBoxes;

    /**
     * Getters and Setters
     * @return 
     */
    public String getInputOutputBoxes()                                 {return inputOutputBoxes;}
    public void setInputOutputBoxes(String inputOutputBoxes)            {this.inputOutputBoxes = inputOutputBoxes;}
    public String getAcInput()                                          {return acInput;}
    public void setAcInput(String acInput)                              {this.acInput = inputStyle;}
    public String getInputStyle()                                       {return inputStyle;}
    public void setInputStyle(String inputStyle)                        {this.inputStyle = inputStyle;}
    public String getExpectedOutputString()                             {return expectedOutputString;}
    public void setExpectedOutputString(String expectedOutputString)    {this.expectedOutputString = expectedOutputString;}
    public String getYourOutputString()                                 {return yourOutputString;}
    public void setYourOutputString(String yourOutputString)            {this.yourOutputString = yourOutputString;}
    public String getInputFile()                                        { return inputFile;}
    public void setInputFile(String inputFile)                          {this.inputFile = inputFile;}
    public String getExampleInputFile()                                 { return ExampleInputFile;}
    public void setExampleInputFile(String ExampleInputFile)            { this.ExampleInputFile = ExampleInputFile;}
    public String getCorrectProgramStyle()                              { return correctProgramStyle;}
    public void setCorrectProgramStyle(String correctProgramStyle)      { this.correctProgramStyle = correctProgramStyle;}
    public String getCorrectProgramString()                             { return correctProgramString; }
    public void setCorrectProgramString(String correctProgramString)    { this.correctProgramString = correctProgramString; }
    public String getExpectedOutputStyle()                              {return expectedOutputStyle;}
    public void setExpectedOutputStyle(String expectedOutputStyle)      {this.expectedOutputStyle = expectedOutputStyle;}
    public String getYourOutputStyle()                                  {return yourOutputStyle;}
    public void setYourOutputStyle(String yourOutputStyle)              {this.yourOutputStyle = yourOutputStyle;}
    public String getAutomaticCheckAreaStyle()                                  { return automaticCheckAreaStyle;}
    public void setAutomaticCheckAreaStyle(String automaticCheckAreaStyle)      {this.automaticCheckAreaStyle = automaticCheckAreaStyle;}
    public String getAutomaticCheckButtonStyle()                                {return automaticCheckButtonStyle;}
    public void setAutomaticCheckButtonStyle(String automaticCheckButtonStyle)  {this.automaticCheckButtonStyle = automaticCheckButtonStyle;}
    public String getOutputString()                                     {return OutputString;}
    public void setOutputString(String OutputString)                    {this.OutputString = OutputString;}
    public String getShowInputWindow()                                  {return ShowInputWindow;}
    public void setShowInputWindow(String ShowInputWindow)              {this.ShowInputWindow = ShowInputWindow;}
    public String getInput()                                            {return input;}
    public void setInput(String input) throws FileNotFoundException     {this.input = input;}
    public String getRecommendClass()                                   {return RecommendClass;}
    public void setRecommendClass(String recommendClass)                {this.RecommendClass = recommendClass;}
    public String getOutputResultClass()                                {return OutputResultClass;}
    public void setOutputResultClass(String OutputResultClass)          {this.OutputResultClass = OutputResultClass;}
    public String getErrorString()                                      {return ErrorString;}
    public void setErrorString(String ErrorString)                      {this.ErrorString = ErrorString;}
    public String getInputString()                                      { return InputString;}
    public void setInputString(String InputString)                      {this.InputString = InputString;}
    public ArrayList<String> getAllExercises()                          {return AllExercises;}
    public void setAllExercises(ArrayList<String> AllExercises)         {this.AllExercises = AllExercises;}
    public String getComment()                                          {return Comment;}
    public void setComment(String Comment)                              {this.Comment = Comment;}
    public String getDataFile()                                         {return DataFile;}
    public void setDataFile(String DataFile)                            {this.DataFile = DataFile;}
    public String getChapterSelected()                                  {return ChapterSelected;}
    public void setChapterSelected(String ChapterSelected) throws IOException {this.ChapterSelected = ChapterSelected; ResetExercises();}
    public ArrayList<String> getChapters()                              {return Chapters;}
    public void setChapters(ArrayList<String> chapters)                 {this.Chapters = chapters;}
    public ArrayList<String> getExercises()                             {return Exercises; }
    public void setExercises(ArrayList<String> exercises)               {this.Exercises = exercises;}
    public String getExerciseSelected()                                 {return ExerciseSelected;}
    public void setExerciseSelected(String ExerciseSelected)            {this.ExerciseSelected = ExerciseSelected;}
    public String getCodeString()                                       {return CodeString;}
    public void setCodeString(String CodeString) throws IOException     {this.CodeString = CodeString;}
    
    /**
     * Faces Constructor
     * @throws IOException 
     */
    public JavaExercises() throws IOException {
        //ServletContext ctx          = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        //String realPath             = ctx.getRealPath("/");
        //setDataFile(realPath);

        //Because we have a hard coded path we can just use a final variable here.
        setDataFile(getDefaultPath());
        
        //Initialize all variables. 
        Chapters            = new ArrayList<>();    //Array of all chapters
        Exercises           = new ArrayList<>();    //Array of all exercises for selected chapter
        AllExercises        = new ArrayList<>();    //Array of all exercises
        ExerciseSelected    = "Exercise01_01";      //Which exercise has been selected. 
        ChapterSelected     = "Chapter 1";          //Which chapter is selected
        CodeString          = "/*Paste your "+ExerciseSelected+" here and click Automatic Check.\n" +
                              "For all programming projects, the numbers should be double\n" +
                              "unless it is explicitly stated as integer.\n" +
                              "If you get a java.util.InputMismatchException error, check if\n" +
                              "your code used input.readInt(), but it should be input.readDouble().\n" +
                              "For integers, use int unless it is explicitly stated as long. */";
        
        ErrorString         = "";                   //What error string do we need to report?
        InputString         = "";                   //What is our input string. If the user changes the input this variable gets changed
        RecommendClass      = "recommend";          //This is the text shown on the bottom when the program starts
        ExampleInputFile    = "";                   //This is the input file for the example, not the user input
        OutputResultClass   = "outputresultHidden"; //Style sheet for the output display
        ShowInputWindow             = "display:none;";  //Style sheet for the input display
        inputStyle                  = "display:block;"; //style sheet for the input box for automatic check displays
        expectedOutputStyle         = "display:block;"; //Style sheet for the expected output for automatic check displays
        yourOutputStyle             = "display:block;"; //Style sheet for the user output for automatic check displays
        automaticCheckAreaStyle     = "display:none;";  //Style sheet for the whole automatic check display area
        correctProgramString        = "";               //this is the text if the program is correct or incorrect
        correctProgramStyle         = "display:none;";  //style sheet for the correct program text
        inputOutputBoxes            = "display:none;";  //the style sheet for the inputoutput boxes
        
        Initialize();                                   //Let's get this program started. 
    }

    /**
     * Initializes the Chapter selection box and the exercises selection box based on the initial starting chapter. 
     * 
     * @throws FileNotFoundException
     * @throws IOException 
     */
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
    
    /**
     * This is called when the user selects a new chapter. This iterates through all exercises and picks out the ones related to the selected chapter. 
     * 
     * @throws IOException 
     */
    public void ResetExercises() throws IOException{
        
        Exercises = new ArrayList<>();
        for (int i=0; i<AllExercises.size(); i++){
            
            String FileName = AllExercises.get(i);
            String[] str    = FileName.split("_")[0].split("Exercise");
            if(str.length>0){
                int chapterNo = Integer.parseInt(str[1]);
                if(Integer.parseInt(ChapterSelected.split("Chapter")[1].trim()) == chapterNo){
                    Exercises.add(FileName);
                }
            }
        }
    }
    
    /**
     * This is called when the user selects "Set Exercise". It resets many of our variables like in initialize. 
     * However it will now initialize the codeString and display a small help for the user and it will also get the
     * input information from the default input file selected for the exercise. 
     * 
     * @throws IOException 
     */
    public void SetExerciseInformation() throws IOException{
          
        ExampleInputFile    = "";
        ErrorString         = "";
        InputString         = "";
        RecommendClass      = "recommend";
        OutputResultClass   = "outputresultHidden";
        inputStyle                  = "display:block;"; //
        expectedOutputStyle         = "display:block;";
        yourOutputStyle             = "display:block;";
        automaticCheckAreaStyle     = "display:none;";
        correctProgramString        = "";
        correctProgramStyle         = "display:none;";
        inputOutputBoxes            = "display:none;";
        CodeString=(GetCodeForExercise());
        input=(GetInputFromFiles()).replace("\r\n", " ");
   }

    /**
     * Returns a string to display in the code block. If the exercise cannot be automatically checked then it simply hides that button. 
     * 
     * @return
     * @throws IOException 
     */
    public String GetCodeForExercise() throws IOException {
        
        String CodeReturn = "";
        
        try{
            String route = getDataFile() + "\\exercisedescription\\"+ ExerciseSelected;
            Scanner scan = new Scanner(new File(route));
            
            while(scan.hasNext()){
                 
                String text = scan.nextLine();
               
                if(text.equals("This exercise can be compiled and submitted, but cannot be run and automatically graded.")){
                    CodeReturn = "/* This exercise cannot be graded automatically becuase it may use random\n numbers, file input/output, or graphics. */";
                    automaticCheckButtonStyle = "display:none;";
                }
                else{
                    CodeReturn = "/*Paste your "+ExerciseSelected+" here and click Automatic Check.\n" +
                            "For all programming projects, the numbers should be double\n" +
                            "unless it is explicitly stated as integer.\n" +
                            "If you get a java.util.InputMismatchException error, check if\n" +
                            "your code used input.readInt(), but it should be input.readDouble().\n" +
                            "For integers, use int unless it is explicitly stated as long. */";
                    automaticCheckButtonStyle = "display:inline-block;";
                }
            }
        } catch (FileNotFoundException e){
          //  System.out.println("The file could not be found");
        }
        
        return CodeReturn;
    }    

    /**
     * This will look inside our ags10e folder and start grabbing all input files related to the exercise selected. If there is more than one we
     * will randomly select an input file. If input file is found he hide the input window. 
     * 
     * @return
     * @throws FileNotFoundException 
     */
    public String GetInputFromFiles() throws FileNotFoundException{
        
        File f                  = new File(getDataFile() + "\\gradeexercise");
        File[] fList            = f.listFiles(); 
        ArrayList<String> FoundFiles = new ArrayList<>();
        
        for (File file : fList){
            
            String FileName = file.getName();
            
            if(FileName.contains(ExerciseSelected) == true && FileName.contains("input") == true){
                
                //I noticed that this doesn't check if the file is "extra" even if the exercise selected wasn't an "extra" exercise. 
                if(FileName.contains("Extra") == true && ExerciseSelected.contains("Extra") == false)
                    continue;

                FoundFiles.add((getDataFile() + "\\gradeexercise\\" + FileName));
            }
        }
        
        if(FoundFiles.size()>0){
    
            //Get Random Input File
            setShowInputWindow("display:block; border-radius: 3px;");

            int randomNum       = ThreadLocalRandom.current().nextInt(0, FoundFiles.size());
            ExampleInputFile    = FoundFiles.get(randomNum);    
            Scanner scan        = new Scanner(new File(ExampleInputFile));
            StringBuffer sb     = new StringBuffer();

            while(scan.hasNext()){

                sb.append(scan.nextLine());
                sb.append(System.lineSeparator());
            }
            return sb.toString();
        }
        else{
            ExampleInputFile    = "";
            setShowInputWindow("display:none;border-radius: 3px;");
            return "";
        }    
    }

    /**
     * Universal method to grab the contents of a file from the path passed as a parameter. 
     * 
     * @param Path
     * @return
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public StringBuffer GrabFileContents(String Path) throws FileNotFoundException, IOException{
        
        StringBuffer sb = new StringBuffer();
        try{
            BufferedReader in = new BufferedReader(new FileReader(Path));
        
            for (String line; (line = in.readLine()) != null; )
                sb.append(line);
        
        }
        catch(FileNotFoundException e){
        }
        return sb;
    }
    
    //This is called when the user clicks the compile/run button
    public void CallCompileRun() throws IOException{
        
        Output output = compileRun(false);
        correctProgramString        = "";
        correctProgramStyle         = "display:none;";
        inputOutputBoxes            = "display:none;";
    }
    
    /** 
     * Will be called if the user even clicks on automatic check as we need to compile the program and return possible errors. 
     * 
     * @param AutomaticRun
     * @return
     * @throws IOException 
     */
    public Output compileRun(boolean AutomaticRun) throws IOException{
        
        //If this is not an automatic run then we can hide the automatic run stuff and display the compile/run stuff. 
        if(!AutomaticRun){
            automaticCheckAreaStyle = "display:none";
            OutputResultClass   = "outputresult";
        }
       
        StringBuffer consoleBuffer = new StringBuffer();        
        consoleBuffer.append("<pre>command>javac "+ExerciseSelected+".java<br>");

        //We need to build up our Output object to store everything we need for compile and automatic check. 
        Output output = CompileJavaProgram(ExerciseSelected);

        if(!ExampleInputFile.isEmpty()){
            
            String NewPath = ExampleInputFile.replace("gradeexercise", "temp");
            WriteToFile(NewPath, input, false);
            
            output.setExampleInputFile(ExampleInputFile);
            StringBuffer sb = GrabFileContents(ExampleInputFile);
        
            if(sb != null && sb.length()>0)
                output.setExampleInputString(GenerateHTMLFromStringBuffer(sb));
                    
            output.setUserInputFile(ExampleInputFile.replace("gradeexercise", "temp"));
            output.setUserInputString(new StringBuffer(input).toString());
            output.setExampleOutputFile(ExampleInputFile.replace(".input", ".output"));
            output.setUserOutputFile(output.getExampleOutputFile().replace("gradeexercise", "temp"));
        }
        else{
            String OutputFile = this.getDataFile() + "\\gradeexercise\\" + ExerciseSelected + ".output";
            output.setExampleOutputFile(OutputFile);
            output.setUserOutputFile(output.getExampleOutputFile().replace("gradeexercise", "temp"));            
        }    
      
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
        }
        else{
            consoleBuffer.append(output.getErrorString());
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
    
        String newCodeString    = getCodeString().replace("System.out.print(", "System.out.println(");
        String JavaFilePath     = WriteToFile(FileName.concat(".java"), newCodeString, true);
        
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
    
    public Process RunProcessInThread(ProcessBuilder runpb, Output result){
        
        long startTime = System.currentTimeMillis();
        Process runp = null;

        try {

          runp = runpb.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        final Process proc1 = runp;
        new Thread() {
            public void run() {

                int sleepTime       = 0;
                boolean isFinished  = false;

                while (sleepTime <= EXECUTION_TIME_ALLOWED && !isFinished) {
                    
                    try {
                        try {
                            Thread.sleep(EXECUTION_TIME_INTERVAL);
                        } catch (Exception ex) {
                        }

                        sleepTime += EXECUTION_TIME_INTERVAL;
                        
                        int exitValue = proc1.exitValue();
                        isFinished = true;
                        
                    } catch (IllegalThreadStateException ex) {
                        ex.printStackTrace();
                    }
                }
                if (!isFinished) {
                    proc1.destroy();
                    result.setIsInfiniteLoop(true);
                }
            }
        }.start();
        
        try {
            int exitCode = runp.waitFor();
        } catch (Exception ex) {
        }  
        
        return runp;
    }

    public void RunJavaProgram(Output output) throws IOException{
        
        //Build our java command line call to pass to processbuilder. We may input so generate a command with input if that is the case
        ProcessBuilder runpb = null;
        ArrayList<String> runCommand = new ArrayList<>();
        runCommand.add("java");
        runCommand.add(ExerciseSelected);
        
        runpb           = new ProcessBuilder(runCommand);  //We can add arguments like a string formatter
        runpb.directory(new File(output.getSourceDirectory()));
        runpb.redirectErrorStream(true);

        if(output.getExampleInputString().length() > 0) {

            runpb.redirectInput(Redirect.from(new File(output.getExampleInputFile())));
        }
        //runpb.redirectOutput(Redirect.to(new File(output.getUserOutputFile())));
        
        Process runp = RunProcessInThread(runpb, output); 

        String ErrorMessage         = GenerateHTMLFromText(ReturnMessage("java", runp.getErrorStream())).toString();
        StringBuffer OutputMessage  = ReturnMessage("java", runp.getInputStream());

        WriteToFile(output.getUserOutputFile(), OutputMessage.toString(), false);
        
        if(ErrorMessage.isEmpty() && output.isIsInfiniteLoop()){
            ErrorMessage = "Your program takes too long. It runs out of the allowed CPU time 10000ms. It may have an infinite loop or the expected input for the program is not provided or provided incorrectly.";
            ErrorMessage = (GenerateHTMLFromText(new StringBuffer(ErrorMessage))).toString();
        }
        

        output.setErrorString(ErrorMessage);
        output.setOutputString(OutputMessage.toString());
        output.setHTMLOutputString(GenerateHTMLFromText(OutputMessage).toString());
        output.setExampleOutputString(OutputMessage);   
        output.setUserOutputString(OutputMessage);
                //output.setExampleOutputString(new StringBuffer(EchoPrint(output.getExampleOutputString().toString(), GrabFileContents(output.getExampleInputFile()).toString().split(" "), CodeString)));
        
        runp.destroy();

        if(output.getExampleInputString().length() > 0) {
            
            runpb.redirectInput(Redirect.from(new File(output.getUserInputFile())));
            runp = RunProcessInThread(runpb, output); 
            ErrorMessage            = GenerateHTMLFromText(ReturnMessage("java", runp.getErrorStream(), null)).toString();

            if(ErrorMessage.isEmpty() && output.isIsInfiniteLoop()){
                ErrorMessage = "Your program takes too long. It runs out of the allowed CPU time 10000ms. It may have an infinite loop or the expected input for the program is not provided or provided incorrectly.";
                ErrorMessage = (GenerateHTMLFromText(new StringBuffer(ErrorMessage))).toString();
            }

            OutputMessage           = ReturnMessage("java", runp.getInputStream(), GrabFileContents(output.getUserInputFile()).toString().split(" "));
            output.setErrorString(ErrorMessage);
            output.setOutputString(OutputMessage.toString());
            output.setHTMLOutputString(GenerateHTMLFromText(OutputMessage).toString());
            output.setUserOutputString(OutputMessage);
            //output.setUserOutputString(new StringBuffer(EchoPrint(output.getUserOutputString().toString(), GrabFileContents(output.getUserInputFile()).toString().split(" "), CodeString)));
        }
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

    public StringBuffer ReturnMessage(String str, InputStream I, String[] InputValues) throws IOException{
        
        StringBuffer sb = new StringBuffer();
        if(I != null){
            int SystemOutIndex = 0;
            int InputValueIndex = 0;
            BufferedReader in = new BufferedReader(new InputStreamReader(I));
            for (String line; (line = in.readLine()) != null; ){

                if(InputValues != null && InputValueIndex < InputValues.length){
                    
                    while(true){
                        
                        int flag = DoWeNeedToAddInputValueHere(SystemOutIndex, InputValueIndex);
                        if(flag == 0){
                            sb.append(InputValues[InputValueIndex++]);
                            sb.append(System.lineSeparator());
                        }
                        else if(flag == 1){
                            sb.append(line);
                            sb.append(System.lineSeparator());
                            break;
                        }
                        else{
                            InputValues = null;
                            sb.append(line);
                            sb.append(System.lineSeparator());
                            break;
                        }
                    }
                    
                    SystemOutIndex++;
                }
                else{

                    sb.append(line);
                    sb.append(System.lineSeparator());
                }
            }
            in.close();
        }
        return sb;
    }
    
    public int DoWeNeedToAddInputValueHere(int SystemOutIndex, int InputValueIndex){
        
        /**
         * We know we need to return true if we find a scanner input after the system.out.print we have just printed and 
         * either we come to the end of file or find another system.out.print. 
         */
        
        int i = 0;
        ArrayList<Integer> SystemOutIndecies = new ArrayList<>();
        ArrayList<Integer> ScannerIndecies = new ArrayList<>();
        ArrayList<String> ScannerVariable = new ArrayList<>();
        
        //Find all system.out.print lines in the code
        Pattern pattern = Pattern.compile("System.out.print[ln]*");
        Matcher m = pattern.matcher(CodeString);
        while(m.find()){
            SystemOutIndecies.add(m.end());
        }
        
        Pattern patternScanner = Pattern.compile("Scanner ([a-zA-Z_][a-zA-Z0-9_]*)[\\s]*.*Scanner\\(System.in\\);");
        Matcher mscanner = patternScanner.matcher(CodeString);
        if(mscanner.find()){
            for(int j=0; j<mscanner.groupCount(); j++){
                ScannerVariable.add(mscanner.group(j+1));
            }
        }

        //If there are no scanner variables then we are done. 
        if(ScannerVariable.isEmpty())
            return -1;

        //We need to create a pattern to find all of our scanner input calls. These get stored into an array created
        //from pattern matching. This array is stored in m.group();

        String PatternForScannerVariables = "";
        for(int j=0; j<ScannerVariable.size(); j++){
            if(j>0)
                PatternForScannerVariables += "|";

            PatternForScannerVariables += "(" + ScannerVariable.get(j) + ")\\.";
        }        

        //Get all input matches and start index locations
        Pattern patternForScanner = Pattern.compile(PatternForScannerVariables);
        Matcher ScannersMatch = patternForScanner.matcher(CodeString);
                
        while(ScannersMatch.find())
            ScannerIndecies.add(ScannersMatch.start());
            
        int o = SystemOutIndecies.get(SystemOutIndex);
        
        if(ScannerIndecies.size() > InputValueIndex){
        
            int p = ScannerIndecies.get(InputValueIndex);
        
            if(p < o) //Input Scanner is first
                return 0;
            else if(p > 0) //System print is first
                return 1;
            else
                return -1;
        }
        else        
            return -1;
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
        
    public String GenerateHTMLFromStringBuffer(StringBuffer strB){
        
        String htmlStr = "<span>";
        String[] lines = strB.toString().split("\\n");
        for(String s: lines){
            htmlStr += s + "<br />";
        }
        htmlStr += "</span>";
        
        return htmlStr;
        
    }

    public String GenerateHTMLFromStringBuffer(String[] strB){
        
        String htmlStr = "<span><pre>";
        for(String s: strB){
            htmlStr += s + "<br />";
        }
        htmlStr += "</pre></span>";
        
        return htmlStr;
        
    }
    
    public void automaticCheck() throws IOException{
        
        correctProgramStyle         = "display:block;";
        OutputResultClass           = "outputresultHidden";
        automaticCheckAreaStyle     = "display:block;";

        Output output               = compileRun(true);
        
        if(output.getExampleInputFile().isEmpty())
            inputStyle = "display:none;";
        else
            inputStyle = "display:block;";

        if(!output.getErrorString().isEmpty()){
            automaticCheckAreaStyle = "display:none";
            OutputResultClass   = "outputresult";
        }
        else{
                    
            acInput                     = output.getExampleInputString();

            String str = GrabFileContents(output.getExampleOutputFile()).toString();

            //Take output strings from example output file and split them into separate lines based on the # character.
            //Take output strings from our example and split them into separate lines based on the \r\n characters. 
            String[] RealOutputStringTokens = str.split("#");
            String[] MyOutputStringTokens  = output.getExampleOutputString().toString().split("\r\n");
            
            //We now have two arrays with output which we need to match. We match from the example output file to the 
            //user output line by line. 
        
            int index = 0;
            boolean IntegerOnly = false;
            
            for(int i=0; i<RealOutputStringTokens.length; i++, index++){

                boolean Found = false;
                if(IsNumber(RealOutputStringTokens[i], false)){
                    
                    IntegerOnly = true;
                    Pattern pattern = Pattern.compile(RealOutputStringTokens[i]);
                    for(int j=0; j<MyOutputStringTokens.length; j++){

                        Matcher m = pattern.matcher(MyOutputStringTokens[j]);
                        if(m.find()){
                            Found = true;
                            break;
                        }
                    }
                }
                else{
                    
                    IntegerOnly = false;
                    for(int j=i; j<MyOutputStringTokens.length; j++){

                        Found = false;
                        if(MyOutputStringTokens[j].equals(RealOutputStringTokens[i])){
                            Found = true;
                            break;
                        }
                        else{
                            DrillIntoProblemWithMatch(RealOutputStringTokens[i], MyOutputStringTokens[j], output);
                            RealOutputStringTokens[i] = output.getLocalExampleOutputString();
                            MyOutputStringTokens[j] = output.getLocalUserOutputString();
                            break;
                        }                        
                    }                    
                }

                if(i >= MyOutputStringTokens.length && !IntegerOnly){
                    if(MyOutputStringTokens.length < RealOutputStringTokens.length){
                        DrillIntoProblemWithMatch(RealOutputStringTokens[i], "", output);
                        RealOutputStringTokens[i] = output.getLocalExampleOutputString();
                        Found = false;
                    }
                }

                if(!Found){
                    
                    correctProgramString    = "Your program is incorrect";
                    automaticCheckAreaStyle = "display:block;";
                    inputOutputBoxes        = "display:block;";
                    expectedOutputString    = GenerateHTMLFromStringBuffer(RealOutputStringTokens);
                    yourOutputString        = GenerateHTMLFromStringBuffer(MyOutputStringTokens);
                    return;
                }
            }

            if(index >= RealOutputStringTokens.length && !IntegerOnly){
                if(MyOutputStringTokens.length > RealOutputStringTokens.length){
                    DrillIntoProblemWithMatch("", MyOutputStringTokens[index], output);
                    MyOutputStringTokens[index] = output.getLocalUserOutputString();
                    correctProgramString    = "Your program is incorrect";
                    automaticCheckAreaStyle = "display:block;";
                    inputOutputBoxes        = "display:block;";
                    expectedOutputString    = GenerateHTMLFromStringBuffer(RealOutputStringTokens);
                    yourOutputString        = GenerateHTMLFromStringBuffer(MyOutputStringTokens);
                    return;
                }
            }
            
            correctProgramString        = "Your program is correct";
            automaticCheckAreaStyle     = "display:block;";
            inputOutputBoxes            = "display:none;";
            expectedOutputString    = GenerateHTMLFromStringBuffer(RealOutputStringTokens);
            yourOutputString        = GenerateHTMLFromStringBuffer(MyOutputStringTokens);            
        }
    }
    
    public void DrillIntoProblemWithMatch(String ExpectedOutput, String UserOutput, Output output) throws IOException{
        
        String NewExampleOutputString = "";
        String NewUserOutputString = "";
        
        if(IsNumber(ExpectedOutput, false)){
            
            if(UserOutput.length()>0){
                NewExampleOutputString = NewExampleOutputString.concat("<span style=\"background-color:red;\">"+ExpectedOutput.substring(0, 1)+"</span>");
                NewExampleOutputString = NewExampleOutputString.concat(ExpectedOutput.substring(1, ExpectedOutput.length()));
                
                NewUserOutputString = NewUserOutputString.concat("<span style=\"background-color:red;\">"+UserOutput.substring(0, 1)+"</span>");
                NewUserOutputString = NewUserOutputString.concat(UserOutput.substring(1, UserOutput.length()));
            }
            else{
                NewExampleOutputString = NewExampleOutputString.concat("<span style=\"background-color:red;\">"+ExpectedOutput.substring(0, 1)+"</span>");
                NewExampleOutputString = NewExampleOutputString.concat(ExpectedOutput.substring(1, ExpectedOutput.length()));                
            }
        }
        else{
            
            if(UserOutput.isEmpty()){
                //this means that we are comparing an empty string to a expected string. Therefore highlight the first character of the expected 
                //string in this case. 
                
                NewExampleOutputString = NewExampleOutputString.concat("<span style=\"background-color:red;\">"+ExpectedOutput.substring(0, 1)+"</span>");
                NewExampleOutputString = NewExampleOutputString.concat(ExpectedOutput.substring(1, ExpectedOutput.length()));                                
            }
            else if(ExpectedOutput.isEmpty()){
                //This means that we are comparing an empty string from expected to a string from user. Threfore highlight the first character of
                //the user string in this case.
                
                NewUserOutputString = NewUserOutputString.concat("<span style=\"background-color:red;\">"+UserOutput.substring(0, 1)+"</span>");
                NewUserOutputString = NewUserOutputString.concat(UserOutput.substring(1, UserOutput.length()));                                
            }
            else{
                //Both cases have a string to compare, so we need to drill into each string and find where the error lies. 
                
                int index = -1;
                for(int i=0; i<ExpectedOutput.length() && i < UserOutput.length(); i++){

                    if(ExpectedOutput.charAt(i) != UserOutput.charAt(i)){
                        index = i;
                        break;
                    }
                }
            
                //We have reached the end of a string without error however if there is more or less to the useroutput result then we need
                //to express an error. This can be done by highlighting the length of the smallest string of either the user or expected output

                if(index == -1){

                    index = (ExpectedOutput.length() > UserOutput.length()) ? UserOutput.length() : ExpectedOutput.length();

                    //Now highlight this character. Get the string before the character
                    NewExampleOutputString = NewExampleOutputString.concat(ExpectedOutput.substring(0, index));
                    NewUserOutputString = NewUserOutputString.concat(UserOutput.substring(0, index));

                    //Get the Character in question from the index.
                    if(UserOutput.length() < ExpectedOutput.length()){
                        NewUserOutputString = NewUserOutputString.concat("<span style=\"background-color:red;\"> </span>");
                        NewExampleOutputString = NewExampleOutputString.concat("<span style=\"background-color:red;\">"+ExpectedOutput.charAt(index)+"</span>");

                        if(index+1 < ExpectedOutput.length())
                            NewExampleOutputString = NewExampleOutputString.concat(ExpectedOutput.substring(index+1, ExpectedOutput.length()));
                    }
                    else if(UserOutput.length() > ExpectedOutput.length()){
                        NewUserOutputString = NewUserOutputString.concat("<span style=\"background-color:red;\">"+UserOutput.charAt(index)+"</span>");
                        NewExampleOutputString = NewExampleOutputString.concat("<span style=\"background-color:red;\"> </span>");

                        if(index+1 < UserOutput.length())
                            NewUserOutputString = NewUserOutputString.concat(UserOutput.substring(index+1, UserOutput.length()));
                    }
                }
                else if(index == 0){
                    //This means the very first character is in error. So we highlight the very first character in the string. 
                    NewUserOutputString = NewUserOutputString.concat("<span style=\"background-color:red;\">"+UserOutput.substring(index, index+1)+"</span>");
                    NewExampleOutputString = NewExampleOutputString.concat("<span style=\"background-color:red;\">"+ExpectedOutput.substring(index, index+1)+"</span>");

                    //Then we concat the rest of the string. 
                    NewUserOutputString = NewUserOutputString.concat(UserOutput.substring(index+1, UserOutput.length()));
                    NewExampleOutputString = NewExampleOutputString.concat(ExpectedOutput.substring(index+1, ExpectedOutput.length()));
                }
                else{
                    //This means the index is not the first character nor the last, therefore we can highlight normally. 
                    NewExampleOutputString = NewExampleOutputString.concat(ExpectedOutput.substring(0, index));
                    NewUserOutputString = NewUserOutputString.concat(UserOutput.substring(0, index));

                    NewUserOutputString = NewUserOutputString.concat("<span style=\"background-color:red;\">"+UserOutput.substring(index, index+1)+"</span>");
                    NewUserOutputString = NewUserOutputString.concat(UserOutput.substring(index+1, UserOutput.length()));

                    NewExampleOutputString = NewExampleOutputString.concat("<span style=\"background-color:red;\">"+ExpectedOutput.substring(index, index+1)+"</span>");
                    NewExampleOutputString = NewExampleOutputString.concat(ExpectedOutput.substring(index+1, ExpectedOutput.length()));
                }
            } 
        }
        
        output.setLocalExampleOutputString(NewExampleOutputString);
        output.setLocalUserOutputString(NewUserOutputString);
    }
    
    public boolean IsNumber(String value, boolean IntegerOnly){
        
        try {
            int num = Integer.parseInt(value);
            return true;
        } catch(NumberFormatException ex) {
            
            if(IntegerOnly)
                return false;
            else
                return IsFloat(value);
        }        
    }

    public boolean IsFloat(String value){
        
        try {
            float num = Float.parseFloat(value);
            return true;
        } catch(NumberFormatException ex) {
            return false;
        }        
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