/*
Problem:
 - TUFCompiler takes multiple responsiblities viloating SRP

class TUFCompiler{
    private void generateDriverCode(String language){
        System.out.println("Driver code generated for the language:"+language);
    }

    private void checkLanguageSyntax(String language, String code){
        System.out.println("Code syntax check successful");
    }

    private void runTests(String language, String code, String problemID){
        // fetch testcases from db based on problemID using Database Manager
        // and run individual tests and generate the results
        System.out.println("Running tests successful");
    }

    private void connectToDB(){
        System.out.println("Connection successful");
    }

    private void generateStdOutput(String submissionID){
        System.out.println("user output generated for the submission:"+submissionID);
    }

    // coordinaor service that combines classes that follow SRP
    public void runCompiler(String language, String code, String problemID, String submissionID){
        generateDriverCode(language);
        checkLanguageSyntax(language, code);
        runTests(language, code, problemID);
        connectToDB();
        generateStdOutput(submissionID);
    }
}

*/

class DriverCodeGenerator{
    // manages the driver code generator
    public void generateDriverCode(String language){
        System.out.println("Driver code generated for the language:"+language);
    }
}

class SyntaxChecker{
    public void checkLanguageSyntax(String language, String code){
        System.out.println("Code syntax check successful");
    }
}

class TestRunner{
    public void runTests(String language, String code, String problemID){
        // fetch testcases from db based on problemID using Database Manager
        // and run individual tests and generate the results
        System.out.println("Running tests successful");
    }
}

class DatabaseManager{
    public void connectToDB(){
        System.out.println("Connection successful");
    }

    public void executeQuery(String query){
        System.out.println("executed given query");
    }
}

class UserOutputHandler{
    public void generateStdOutput(String submissionID){
        System.out.println("user output generated for the submission:"+submissionID);
    }
}

class TUFCompiler{
    // has-a relationship
    DriverCodeGenerator driverCodeGenerator;
    SyntaxChecker syntaxChecker;
    TestRunner testRunner;
    DatabaseManager databaseManager;
    UserOutputHandler userOutputHandler;

    public TUFCompiler(){
        // can be injected using dependency injection instead of object creation
        this.driverCodeGenerator = new DriverCodeGenerator();
        this.syntaxChecker = new SyntaxChecker();
        this.testRunner = new TestRunner();
        this.databaseManager = new DatabaseManager();
        this.userOutputHandler = new UserOutputHandler();
    }

    // coordinaor service that combines classes that follow SRP
    public void runCompiler(String language, String code, String problemID, String submissionID){
        this.driverCodeGenerator.generateDriverCode(language);
        this.syntaxChecker.checkLanguageSyntax(language, code);
        this.testRunner.runTests(language, code, problemID);
        this.databaseManager.connectToDB();
        this.userOutputHandler.generateStdOutput(submissionID);
    }
}

public class SRP {
    public static void main(String[] args) {
        TUFCompiler tufCompiler = new TUFCompiler();

        tufCompiler.runCompiler("cpp", "code snippet", "P_001", 
        "S_123");
    }
}
