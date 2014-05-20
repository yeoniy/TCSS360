package controller;
import java.util.ArrayList;
import java.util.Observable;

import model.Conference;

/**
 * Main controller class for controlling actions between view and controller. Should not be
 * used for specific control of small subtasks but as a general purpose class for easier model
 * access.
 * @version 5/19/2014
 * @author Nick Ames
 */
public class Controller extends Observable {

	private ArrayList<Conference> myConferenceList;
	
	private Conference myActiveConference;
	
    public Controller(final ArrayList<Conference> aConferenceList) {
    	super();
    	this.myConferenceList = aConferenceList;
    }

    public void update() {
    	

    }

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

	public Conference getMyActiveConference() {
		return myActiveConference;
	}

	public void setMyActiveConference(Conference myActiveConference) {
		this.myActiveConference = myActiveConference;
	}
    
}
