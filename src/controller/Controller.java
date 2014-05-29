package controller;
import java.io.*;
import java.util.ArrayList;
import java.util.Observable;

import model.Author;
import model.Conference;
import model.Paper;

/**
 * Main controller class for controlling actions between view and controller. Should not be
 * used for specific control of small subtasks but as a general purpose class for easier model
 * access.
 * @version 5/19/2014
 * @author Nick Ames
 */
public class Controller {

	/**
	 * The list of all the conferences.
	 */
	private ArrayList<Conference> myConferenceList;
	
	/**
	 * The current active conference.
	 */
	private Conference myActiveConference;
	
	/**
	 * Creates a new Controller. This is a generic controller that will only
	 * supply basic control. For more specific applications use inherited controllers.
	 * @param aConferenceList The list of conferences.
	 */
    public Controller(final ArrayList<Conference> aConferenceList) {
    	super();
    	this.myConferenceList = aConferenceList;
    }

    /**
     * Adds a new paper to the current user within the current active conference.
     * @param author author of the paper
     * @param fileName name of the file
     */
    public void addPaper (Author author, String fileName) {
        File file = new File(fileName);
        author.submit(file);
    }

    public void update() {
    	

    }

    /**
     * Returns the conference based on the given ID.
     * @param conferenceId The ID of the conference to get.
     * @return the conference, otherwise null.
     */
    public Conference getConference(String conferenceId) {
    	Conference toReturn = null;
    	for (Conference c : myConferenceList) {
    		if (c.getId().equals(conferenceId)) {
    			toReturn = c;
    			break;
    		}
    	}
    	return toReturn;
    }

    /**
     * Returns the current active conference.
     * @return the currently active conference.
     */
	public Conference getMyActiveConference() {
		return myActiveConference;
	}

	/**
	 * Sets the currently active conference.
	 * @param myActiveConference the conference that the user is working with
	 */
	public void setMyActiveConference(Conference myActiveConference) {
		this.myActiveConference = myActiveConference;
	} 
}
