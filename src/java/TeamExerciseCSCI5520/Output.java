/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TeamExerciseCSCI5520;

/**
 *
 * @author David Klecker
 */
public class Output{
    private String ErrorString;
    private StringBuffer ExampleOutputString;
    private String ExampleOutputFile;
    private String ExampleInputString;
    private String ExampleInputFile;
    private StringBuffer UserOutputString;
    private String UserOutputFile;
    private String UserInputString;
    private String UserInputFile;
    private String OutputString;
    private String InputString;
    
    private String m_SourceDirectory;
    private String m_FullPath;
    private String m_HTMLOutputString;

    public String getOutputString() {
        return OutputString;
    }

    public void setOutputString(String OutputString) {
        this.OutputString = OutputString;
    }

    public String getInputString() {
        return InputString;
    }

    public void setInputString(String InputString) {
        this.InputString = InputString;
    }

    public String getSourceDirectory()                                      {return m_SourceDirectory;}
    public void setSourceDirectory(String m_SourceDirectory)                {this.m_SourceDirectory = m_SourceDirectory;}
    public String getFullPath()                                             {return m_FullPath;}
    public void setFullPath(String m_FullPath)                              {this.m_FullPath = m_FullPath;}
    public String getHTMLOutputString()                                     {return m_HTMLOutputString;}
    public void setHTMLOutputString(String m_HTMLOutputString)              {this.m_HTMLOutputString = m_HTMLOutputString;}

    public String getErrorString() {
        return ErrorString;
    }

    public void setErrorString(String ErrorString) {
        this.ErrorString = ErrorString;
    }

    public StringBuffer getExampleOutputString() {
        return ExampleOutputString;
    }

    public void setExampleOutputString(StringBuffer ExampleOutputString) {
        this.ExampleOutputString = ExampleOutputString;
    }

    public String getExampleOutputFile() {
        return ExampleOutputFile;
    }

    public void setExampleOutputFile(String ExampleOutputFile) {
        this.ExampleOutputFile = ExampleOutputFile;
    }

    public String getExampleInputString() {
        return ExampleInputString;
    }

    public void setExampleInputString(String ExampleInputString) {
        this.ExampleInputString = ExampleInputString;
    }

    public String getExampleInputFile() {
        return ExampleInputFile;
    }

    public void setExampleInputFile(String ExampleInputFile) {
        this.ExampleInputFile = ExampleInputFile;
    }

    public StringBuffer getUserOutputString() {
        return UserOutputString;
    }

    public void setUserOutputString(StringBuffer UserOutputString) {
        this.UserOutputString = UserOutputString;
    }

    public String getUserOutputFile() {
        return UserOutputFile;
    }

    public void setUserOutputFile(String UserOutputFile) {
        this.UserOutputFile = UserOutputFile;
    }

    public String getUserInputString() {
        return UserInputString;
    }

    public void setUserInputString(String UserInputString) {
        this.UserInputString = UserInputString;
    }

    public String getUserInputFile() {
        return UserInputFile;
    }

    public void setUserInputFile(String UserInputFile) {
        this.UserInputFile = UserInputFile;
    }

    public String getM_SourceDirectory() {
        return m_SourceDirectory;
    }

    public void setM_SourceDirectory(String m_SourceDirectory) {
        this.m_SourceDirectory = m_SourceDirectory;
    }

    public String getM_FullPath() {
        return m_FullPath;
    }

    public void setM_FullPath(String m_FullPath) {
        this.m_FullPath = m_FullPath;
    }

    public String getM_HTMLOutputString() {
        return m_HTMLOutputString;
    }

    public void setM_HTMLOutputString(String m_HTMLOutputString) {
        this.m_HTMLOutputString = m_HTMLOutputString;
    }

    public Output(){
        this.ErrorString = "";
        this.ExampleInputString = "";
        this.m_SourceDirectory = "";
        this.m_FullPath = "";
        this.m_HTMLOutputString = "";
        this.ExampleOutputString = new StringBuffer();
        this.ExampleOutputFile = "";
        this.ExampleInputString = "";
        this.ExampleInputFile = "";
        this.UserOutputString = new StringBuffer();
        this.UserOutputFile = "";
        this.UserInputString = "";
        this.UserInputFile = "";
        this.OutputString = "";
        this.InputString = "";
        
    }

    public Output(String ErrorString, String OutputString, String InputString, String HTMLOutput){
        this.m_HTMLOutputString = HTMLOutput;
        this.m_SourceDirectory = "";
        this.m_FullPath = "";
        this.OutputString = OutputString;
        this.InputString = InputString;

        this.ErrorString = ErrorString;
        this.ExampleOutputString = new StringBuffer();
        this.ExampleOutputFile = "";
        this.ExampleInputString = "";
        this.ExampleInputFile = "";
        this.UserOutputString = new StringBuffer();
        this.UserOutputFile = "";
        this.UserInputString = "";
        this.UserInputFile = "";
    }
}
