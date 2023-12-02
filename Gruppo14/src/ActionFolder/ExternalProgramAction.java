package ActionFolder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ExternalProgramAction implements Action{
    
    private String command;
    private String programPath;
    private String[] commandLineArgs;
    private ProcessBuilder processBuilder;
   

    public ExternalProgramAction( String command, String programPath, String ... commandLineArgs ){

        this.command=command;
        this.programPath=programPath;
        this.commandLineArgs= new String[commandLineArgs.length];
        System.arraycopy(commandLineArgs, 0, this.commandLineArgs, 0, commandLineArgs.length);

        this.processBuilder= new ProcessBuilder(command, programPath);
        this.processBuilder.command().addAll(Arrays.asList(commandLineArgs));
        this.processBuilder.redirectErrorStream(true);

    }

    @Override
    public void execute() {
       
        try {
            Process process= this.processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("External program exited with code: " + exitCode);
        
        }catch(IOException | InterruptedException | NullPointerException e){

            e.printStackTrace();

        }
    }
}
