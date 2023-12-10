package ActionFolder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class ExternalProgramAction implements Action {

    private String command;
    private String programPath;
    private String[] commandLineArgs;
    private transient ProcessBuilder processBuilder; // transient for no serialize

    public ExternalProgramAction(String command, String programPath, String... commandLineArgs) {

        this.command = command;
        this.programPath = programPath;
        this.commandLineArgs = new String[commandLineArgs.length];
        System.arraycopy(commandLineArgs, 0, this.commandLineArgs, 0, commandLineArgs.length);

        // initialization of the process that will start soon
        this.processBuilder = new ProcessBuilder(command, programPath);
        this.processBuilder.command().addAll(Arrays.asList(commandLineArgs));

        // Redirect the errorStream to the stdout
        this.processBuilder.redirectErrorStream(true);

        java.nio.file.Path path = Paths.get(programPath);
        if (!Files.exists(path)) {
            throw new IllegalArgumentException("The " + programPath + " program does not exist. ");
        }

    }

    @Override
    public void execute() {

        try {
            // Create a process to execute the external program
            Process process = this.processBuilder.start();

            // Catch the external program output
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            // catch the exit code of the external program
            int exitCode = process.waitFor();
            System.out.println("External program exited with code: " + exitCode);

        } catch (IOException | InterruptedException | NullPointerException e) {

            e.printStackTrace();

        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((command == null) ? 0 : command.hashCode());
        result = prime * result + ((programPath == null) ? 0 : programPath.hashCode());
        result = prime * result + Arrays.hashCode(commandLineArgs);
        result = prime * result + ((processBuilder == null) ? 0 : processBuilder.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ExternalProgramAction other = (ExternalProgramAction) obj;
        if (command == null) {
            if (other.command != null)
                return false;
        } else if (!command.equals(other.command))
            return false;
        if (programPath == null) {
            if (other.programPath != null)
                return false;
        } else if (!programPath.equals(other.programPath))
            return false;
        if (!Arrays.equals(commandLineArgs, other.commandLineArgs))
            return false;
        if (processBuilder == null) {
            if (other.processBuilder != null)
                return false;
        } else if (!processBuilder.equals(other.processBuilder))
            return false;
        return true;
    }

}
