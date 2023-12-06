package TriggerFolder;

import java.io.IOException;
import java.util.Arrays;

public class ExternalProgramTrigger implements Trigger{

    private int targetExitValue;
    private String command;
    private String programPath;
    private String[] commandLineArgs;
    private ProcessBuilder processBuilder;

    public ExternalProgramTrigger(int targetExitValue, String command, String programPath, String ... commandLineArgs) {

        this.targetExitValue= targetExitValue;
        this.command=command;
        this.programPath=programPath;
        this.commandLineArgs= new String[commandLineArgs.length];
        System.arraycopy(commandLineArgs, 0, this.commandLineArgs, 0, commandLineArgs.length);

        //create the process builder and add the command line args
        this.processBuilder= new ProcessBuilder(command, programPath);
        this.processBuilder.command().addAll(Arrays.asList(commandLineArgs));

        //REdirect the error stream to the stdout
        this.processBuilder.redirectErrorStream(true);
    }

    @Override
    public boolean checkTrigger() {
        
        try {
            Process process= processBuilder.start();

            int exitCode = process.waitFor();

            if(this.targetExitValue== exitCode)
                return true;
            
        } catch (IOException  | InterruptedException e) {
            
            e.printStackTrace();
        }

        return false;
    }
}
