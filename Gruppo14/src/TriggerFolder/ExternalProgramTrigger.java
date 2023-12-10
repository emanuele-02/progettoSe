package TriggerFolder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class ExternalProgramTrigger implements Trigger {

    private int targetExitValue;
    private String command;
    private String[] commandLineArgs;
    private transient ProcessBuilder processBuilder; // Used to build and start external processes
    protected boolean lastExecutionResult; // Stores the result of the last execution
    private transient Thread executionThread; // Thread responsible for executing the external program
    private boolean isRunning; // Flag to track whether the external program is currently running

    public ExternalProgramTrigger(int targetExitValue, String command, String programPath, String... commandLineArgs) {
        this.targetExitValue = targetExitValue;
        this.command = command;
        this.commandLineArgs = Arrays.copyOf(commandLineArgs, commandLineArgs.length);
        this.processBuilder = new ProcessBuilder(command, programPath);
        this.processBuilder.command().addAll(Arrays.asList(commandLineArgs));

        this.processBuilder.redirectErrorStream(true);
        this.isRunning = false;

        java.nio.file.Path path = Paths.get(programPath);
        if (!Files.exists(path)) {
            throw new IllegalArgumentException("The " + programPath + " program does not exist. ");
        }
    }

    @Override
    public boolean checkTrigger() {

        // Check if the execution thread is already active
        if (isRunning) {
            // The thread is already running, return false during execution
            return false;
        }

        try {
            // Create a new ProcessRunner object and thread only if the thread is not active
            ProcessRunner processRunner = new ProcessRunner();

            // Limit the number of threads to 5
            if (Thread.activeCount() <= 15) {
                executionThread = new Thread(processRunner);
                executionThread.start();
            } else {
                System.out.println("Thread limit reached. Cannot start a new thread.");
            }

            return lastExecutionResult;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private class ProcessRunner implements Runnable {

        @Override
        public void run() {
            try {
                // Set the running state to true
                isRunning = true;

                Process process = processBuilder.start();

                int exitCode = process.waitFor();
                lastExecutionResult = (targetExitValue == exitCode);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                // Reset the interruption flag and propagate the exception as an unchecked
                // RuntimeException
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            } finally {
                // At the end of execution, set the running state to false
                isRunning = false;
            }
        }
    }

}
