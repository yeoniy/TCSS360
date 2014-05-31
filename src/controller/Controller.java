package controller;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.Paper;
import model.Type;
import model.Author;
import model.Conference;
import model.User;

/**
 * Main controller class for controlling actions between view and controller. Should not be
 * used for specific control of small subtasks but as a general purpose class for easier model
 * access.
 * @version 5/19/2014
 * @author Nick Ames
 */
public class Controller {
	private static ArrayList<Paper> myPapers;
	/**
	 * current user logged in.
	 */
	private static User myUser;
	/**
	 * The list of all the conferences.
	 */
	private ArrayList<Conference> myConferenceList;
	
	/**
	 * The current active conference.
	 */
	public static Conference myActiveConference;
	
	/**
	 * List for the observers.
	 */
	public static ArrayList<JPanel> observers = new ArrayList<JPanel>();
	
	/**
	 * Creates a new Controller. This is a generic controller that will only
	 * supply basic control. For more specific applications use inherited controllers.
	 * @param aConferenceList The list of conferences.
	 */
    public Controller() {
    	super();
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
	/**
	 * Sets the active user
	 * 
	 * @param theUser user thats active
	 */
	public static void setActiveUser(User theUser) {
		myUser = theUser;
	}
	/**
	 * returns the active user. 
	 * @return the user thats active.
	 */
	public static Type getUserType() {
		return myUser.getMyType();	
	}
	public static ArrayList<Paper> getUserPapers() {
		return myPapers;
	}

	public static void setUserPapers(ArrayList<Paper> temp) {
		myPapers = temp;
	}
}
