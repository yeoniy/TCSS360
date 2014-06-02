package model;

import java.io.File;
import java.util.ArrayList;
import java.util.Observable;

import exception.InvalidInputException;

/**
 * Class for Paper, contains file, arraylist of comment(String), file name, and status of paper (accpeted, rejected, process)
 *
 * @version 5/20/2014
 * @author Yeonil
 */
public class Paper extends Observable {
    private File file;
    private ArrayList<String> comment;

    private final int PROCESS_PAPER = 0;
    private final int REJECTED_PAPER = 1;
    private final int ACCEPTED_PAPER = 2;
    /**
     * int 0=process 1=rejected 2=accepted
     */
    private int accepted;
    private int screc;
    private String fileName;

    public Paper(File file) {
        this.file  = file;
        screc = PROCESS_PAPER;
        accepted = PROCESS_PAPER;
        comment = new ArrayList<String>();
        fileName = file.getName();
    }


    public String getFileName() {
        return fileName;
    }
    
    public String getFileHeader() {
    	String s = fileName;
    	if(fileName.equals("empty.txt")) {
    		return "empty";
    	}
        return s;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
        this.setChanged();
        this.notifyObservers();
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
        this.setChanged();
        this.notifyObservers();
    }

    public int isAccepted() {
        return accepted;
    }
    
    public int isScrec() {
        return screc;
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
        this.setChanged();
        this.notifyObservers();
    }
    public void setSCrec(int accepted) throws InvalidInputException{
        if(accepted >= PROCESS_PAPER && accepted <= ACCEPTED_PAPER)
            this.screc = accepted;
        else
            throw new InvalidInputException();
        this.setChanged();
        this.notifyObservers();
    }

    public void addComment(String comment) {
        this.comment.add(comment);
        this.setChanged();
        this.notifyObservers();
    }

    public String toString() {
    	return fileName;
    }
}
