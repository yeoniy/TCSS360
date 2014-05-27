package model;

import exception.InvalidInputException;

import java.io.File;
import java.util.ArrayList;

/**
 * Class for Paper, contains file, arraylist of comment(String), file name, and status of paper (accpeted, rejected, process)
 *
 * @version 5/20/2014
 * @author Yeonil
 */
public class Paper {
    private File file;
    private ArrayList<String> comment;

    private final int PROCESS_PAPER = 0;
    private final int REJECTED_PAPER = 0;
    private final int ACCEPTED_PAPER = 0;
    /**
     * int 0=process 1=rejected 2=accepted
     */
    private int accepted;
    private String fileName;

    public Paper(File file) {
        this.file  = file;
        accepted = PROCESS_PAPER;
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

    /**
     * Set status of paper
     * @param accepted int to choose status of paper. 0=process 1=rejected 2=accepted
     * @throws InvalidInputException Invalid input for param accepted.
     */
    public void setAccepted(int accepted) throws InvalidInputException{
        if(accepted >= PROCESS_PAPER && accepted <= ACCEPTED_PAPER)
            this.accepted = accepted;
        else
            throw new InvalidInputException();
    }

    public void addComment(String comment) {
        this.comment.add(comment);
    }
}
