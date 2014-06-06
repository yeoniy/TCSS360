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
     * Constant representing an unprocessed paper
     */
    private final int PROCESS_PAPER = 0;
    
    /**
     * Constant representing a rejected paper
     */
    private final int REJECTED_PAPER = 1;
    
    /**
     * Constant representing an accepted paper
     */
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
    
    /**
     * Sub-chair recommendation
     */
    private int screc;
    
    /**
     * The file name of the paper
     */
    private String fileName;
    
    /**
     * The ID given to the paper
     */
    private String id;
    
    /**
     *  Constructor
     * @param file The file where the paper is located
     */
    public Paper(File file) {
        this.file  = file;
        screc = PROCESS_PAPER;
        accepted = PROCESS_PAPER;
        comment = new ArrayList<String>();
        fileName = file.getName();
        id = "";
    }
    
    /**
     * Constructor
     * @param file The file where the paper is located
     * @param id The ID given to the paper
     */
    public Paper(File file, String id) {
        this.file  = file;
        screc = PROCESS_PAPER;
        accepted = PROCESS_PAPER;
        comment = new ArrayList<String>();
        fileName = file.getName();
        this.id = id;
    }

    /**
     * Gets the file name
     * @return the file name string
     */
    public String getFileName() {
        return fileName;
    }
    
    /**
     * get the file header
     * @return the file header string
     */
    public String getFileHeader() {
    	String s = fileName;
    	if(fileName.equals("empty.txt")) {
    		return "empty";
    	}
        return s;
    }

    /**
     * set the file name
     * @param fileName - The name for the paper
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Get the file where the paper is located.
     * @return the file for the paper.
     */
    public File getFile() {
        return file;
    }

    /**
     * Set the file where the paper is located.
     * @param file The file where the paper is located.
     */
    public void setFile(File file) {
        this.file = file;
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Return value based on status of paper
     * @return value based on status of paper
     */
    public int isAccepted() {
        return accepted;
    }
    /**
     * Boolean value based on sub-chair recommendation status.
     * @return  value based on sub-chair status.
     */
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
    
    /**
     * Set the Sub-chair recommendation status for a paper
     * <dt><b>Precondition:</b></dt><dd>
	 *  Recommendation is within range 0-2
	 * </dd>
	 * <dt><b>Postcondition:</b></dt><dd>
	 * 	Recommendation is set for paper.		
	 * </dd>
     * @param accepted int describing status of recommendation
     * @throws InvalidInputException 
     */
    public void setSCrec(int accepted) throws InvalidInputException{
        if(accepted >= PROCESS_PAPER && accepted <= ACCEPTED_PAPER)
            this.screc = accepted;
        else
            throw new InvalidInputException();
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Add a comment to the paper
     * @param comment the comment string
     */
    public void addComment(String comment) {
        this.comment.add(comment);
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * Test if two papers are equal
     * @return boolean value indicating equality of papers
     */
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

    /**
     * Return string representation of the file name.
     * @return the file name string.
     */
    public String toString() {
    	return fileName;
    }


    /**
     * Return the ID associated with the paper
     * @return The ID string.
     */
	public String getId() {
		return id;
	}

	/**
	 * Sets the ID for the paper
	 * @param id The ID string.
	 */
	public void setId(String id) {
		this.id = id;
	}
}
