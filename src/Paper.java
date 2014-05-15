import java.io.File;
import java.util.ArrayList;

/**
 * Created by Yeonil on 4/22/14.
 */
public class Paper {
    private File file;
    private ArrayList<String> comment;
    /**
     * int 0=process 1=rejected 2=accepted
     */
    private int accepted;
    private String fileName;

    public Paper(File file) {
        this.file  = file;
        accepted = 0;
        comment = new ArrayList<String>();
        fileName = file.getName();
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public int isAccepted() {
        return accepted;
    }

    public void setAccepted(int accepted) {
        this.accepted = accepted;
    }

    public void addComment(String comment) {
        this.comment.add(comment);
    }
}
