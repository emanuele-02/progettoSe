package ActionFolder;

import java.io.File;

public class DeleteFileAction implements Action {

    private File file;

    public DeleteFileAction(String targetDirectory, String targetFileName) {

        file = new File(targetDirectory, targetFileName);

        if (!file.exists())
            throw new IllegalArgumentException(" This path doesn't match any file");

    }

    @Override
    public void execute() {

        if (!file.delete())
            throw new RuntimeException("Impossible to delete the file, check permissions or other constraints");

    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((file == null) ? 0 : file.hashCode());
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
        DeleteFileAction other = (DeleteFileAction) obj;
        if (file == null) {
            if (other.file != null)
                return false;
        } else if (!file.equals(other.file))
            return false;
        return true;
    }

}