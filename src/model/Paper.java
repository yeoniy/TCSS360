package model;

import java.io.File;
import java.util.ArrayList;
import java.util.Observable;

import exception.InvalidInputException;

/**
 * Holds all the information for a paper within the conference. This includes acceptance status and comments.
 * @author Nick Ames
 * @author Richard Hemingway
 * @author Tim Loverin
 * @author Yeonil Yoo
 * @version 6/3/2014
 */
public class Paper extends Observable {
	
	/**
     * 
     */
    private final int PROCESS_PAPER = 0;
    private final int REJECTED_PAPER = 1;
    private final int ACCEPTED_PAPER = 2;
	
	/**
	 * The file for the paper.
	 */
    private File file;
    
    /**
     * The list of comments for the paper.
     */
    private ArrayList<String> comment;
    
    /**
     * int 0=process 1=rejected 2=accepted
     */
    private int accepted;
    private int screc;
    private String fileName;
    private String id;

    public Paper(File file) {
        this.file  = file;
        screc = PROCESS_PAPER;
        accepted = PROCESS_PAPER;
        comment = new ArrayList<String>();
        fileName = file.getName();
        id = "";
    }
    
    public Paper(File file, String id) {
        this.file  = file;
        screc = PROCESS_PAPER;
        accepted = PROCESS_PAPER;
        comment = new ArrayList<String>();
        fileName = file.getName();
        this.id = id;
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
    
    @Override
    public boolean equals(Object o) {
    	if (o instanceof Paper) {
    		Paper p = (Paper) o;
    		if (p.getFileName().equals(this.fileName)) {
    			return true;
    		}
    	}
    	return false;
    }

    public String toString() {
    	return fileName;
    }


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
}
