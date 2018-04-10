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
    
    private String m_ErrorString;
    private String m_OutputString;
    private String m_InputString;
    private String m_SourceDirectory;
    private String m_FullPath;
    private String m_HTMLOutputString;

    private String ExampleInputFile;
    private StringBuffer ExampleInputString;
    private String UserInputFile;
    private StringBuffer UserInputString;
    private String ExampleOutputFile;
    private StringBuffer ExampleOutputString;
    private String UserOutputFile;
    private StringBuffer UserOutputString;

    public String getExampleInputFile() {
        return ExampleInputFile;
    }

    public void setExampleInputFile(String ExampleInputFile) {
        this.ExampleInputFile = ExampleInputFile;
    }

    public StringBuffer getExampleInputString() {
        return ExampleInputString;
    }

    public void setExampleInputString(StringBuffer ExampleInputString) {
        this.ExampleInputString = ExampleInputString;
    }

    public String getUserInputFile() {
        return UserInputFile;
    }

    public void setUserInputFile(String UserInputFile) {
        this.UserInputFile = UserInputFile;
    }

    public StringBuffer getUserInputString() {
        return UserInputString;
    }

    public void setUserInputString(StringBuffer UserInputString) {
        this.UserInputString = UserInputString;
    }

    public String getExampleOutputFile() {
        return ExampleOutputFile;
    }

    public void setExampleOutputFile(String ExampleOutputFile) {
        this.ExampleOutputFile = ExampleOutputFile;
    }

    public StringBuffer getExampleOutputString() {
        return ExampleOutputString;
    }

    public void setExampleOutputString(StringBuffer ExampleOutputString) {
        this.ExampleOutputString = ExampleOutputString;
    }

    public String getUserOutputFile() {
        return UserOutputFile;
    }

    public void setUserOutputFile(String UserOutputFile) {
        this.UserOutputFile = UserOutputFile;
    }

    public StringBuffer getUserOutputString() {
        return UserOutputString;
    }

    public void setUserOutputString(StringBuffer UserOutputString) {
        this.UserOutputString = UserOutputString;
    }

    public String getM_HTMLOutputString() {
        return m_HTMLOutputString;
    }

    public void setM_HTMLOutputString(String m_HTMLOutputString) {
        this.m_HTMLOutputString = m_HTMLOutputString;
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
        
    public Output(){
        this.m_ErrorString = "";
        this.m_OutputString = "";
        this.m_InputString = "";
        this.m_SourceDirectory = "";
        this.m_FullPath = "";
        this.m_HTMLOutputString = "";
        this.ExampleInputFile = "";
        this.ExampleInputString = new StringBuffer();
        this.UserInputFile = "";
        this.UserInputString = new StringBuffer();
        this.ExampleOutputFile = "";
        this.ExampleOutputString = new StringBuffer();
        this.UserOutputFile = "";
        this.UserOutputString = new StringBuffer();
    }

    public Output(String ErrorString, String OutputString, String InputString, String HTMLOutput){
        this.m_ErrorString = ErrorString;
        this.m_OutputString = OutputString;
        this.m_InputString = InputString;
        this.m_SourceDirectory = "";
        this.m_FullPath = "";
        this.m_HTMLOutputString = HTMLOutput;
        this.ExampleInputFile = "";
        this.ExampleInputString = new StringBuffer();
        this.UserInputFile = "";
        this.UserInputString = new StringBuffer();
        this.ExampleOutputFile = "";
        this.ExampleOutputString = new StringBuffer();
        this.UserOutputFile = "";
        this.UserOutputString = new StringBuffer();
    }
}
