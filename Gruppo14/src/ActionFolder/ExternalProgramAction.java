package ActionFolder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ExternalProgramAction implements Action{
    
    private String command;
    private String programPath;
    private String[] commandLineArgs;
    private transient ProcessBuilder processBuilder; // transient for no serialize
   

    public ExternalProgramAction( String command, String programPath, String ... commandLineArgs ){

        this.command=command;
        this.programPath=programPath;
        this.commandLineArgs= new String[commandLineArgs.length];
        System.arraycopy(commandLineArgs, 0, this.commandLineArgs, 0, commandLineArgs.length);

        //initialization of the process that will start soon
        this.processBuilder= new ProcessBuilder(command, programPath);
        this.processBuilder.command().addAll(Arrays.asList(commandLineArgs));

        //Redirect the errorStream to the stdout
        this.processBuilder.redirectErrorStream(true);

    }

    @Override
    public void execute() {
       
        try {
            //Create a process to execute the external program
            Process process= this.processBuilder.start();

            //Catch the external program output
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            //catch the exit code of the external program
            int exitCode = process.waitFor();
            System.out.println("External program exited with code: " + exitCode);
        
        }catch(IOException | InterruptedException | NullPointerException e){

            e.printStackTrace();

        }
    }
}
