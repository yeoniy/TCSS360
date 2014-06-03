package model;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

/**
 * The conference holds the information such as Users and the deadline of the conference.
 * @author Nick Ames
 * @author Richard Hemingway
 * @author Tim Loverin
 * @author Yeonil Yoo
 * @version 6/3/2014
 */
public class Conference extends Observable {
	/**
	 * The name of the conference.
	 */
    private String name;
    
    /**
     * The deadline for the conference.
     */
    private Date date;
    
    /**
     * The id for the conference.
     */
    private String id;
    
    /**
     * The list of users for the conference.
     */
    private ArrayList<User> users;

    /**
     * Creates a new conference from the given params.
     * <dt><b>Precondition:</b></dt><dd>
	 * Params must be Strings. date != null
	 * </dd>
	 * <dt><b>Postcondition:</b></dt><dd>
	 * A new conference is created.
	 * </dd>
     * @param name the name of the conference
     * @param date the deadline for the conference 
     * @param id the id of the conference
     */
    public Conference(String name, Date date, String id) {
        this.name = name;
        this.date = date;
        this.id = id;
        users = new ArrayList<User>();
    }

    /**
     * Gets the name of the conference.
     * @return the name of the conference
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the conference.
     * @param name name to set conference to
     */
    public void setName(String name) {
        this.name = name;
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Gets the date of the conference.
     * @return the date of the conference
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date of the conference.
     * @param date date to set conference to
     */
    public void setDate(Date date) {
        this.date = date;
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Gets the id of the conference.
     * @return the id of the conference
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id of the conference.
     * @param id id to set conference to
     */
    public void setId(String id) {
        this.id = id;
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Adds the user to the conference.
     * @param user user to add to conference to
     */
    public void addUser(User user) {
        this.users.add(user);
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Gets the user list of the conference.
     * @return the user list of the conference
     */
    public ArrayList<User> getUserList() {
    	return this.users;
    }
    
    public String toString() {
    	return this.name;
    }

    /**
     * Add observers to this conference.
     * @param observers the observers for the conference.
     */
	public void addObservers(ArrayList<JPanel> observers) {
		for (JPanel j : observers) {
			addObserver((Observer) j);
		}
	}
}
