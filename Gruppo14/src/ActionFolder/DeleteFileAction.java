package ActionFolder;

import java.io.File;

public class DeleteFileAction implements Action{

    private File file;

    public DeleteFileAction(String targetDirectory, String targetFileName){

        file= new File(targetDirectory, targetFileName);

        if(!file.exists())
            throw new IllegalArgumentException(" This path doesn't match any file");
        
    }


    @Override
    public void execute() {

        if(!file.delete()) 
            throw new RuntimeException("Impossible to delete the file, check permissions or other constraints");
        
    }


    public File getFile() {
        return file;
    }


    public void setFile(File file) {
        this.file = file;
    }


}