package model;
/**
 * Created by Yeonil on 4/22/14.
 */

import java.util.ArrayList;
//test
/**
 * The admin is a user that is allowed to do all operation and initially create the confernece and Program Chair.
 * @author Nick Ames
 * @author Richard Hemingway
 * @author Tim Loverin
 * @author Yeonil Yoo
 * @version 6/3/2014
 */
public class Admin extends User {
	/**
	 * The list of all the conferences.
	 */
    private ArrayList<Conference> conferences;

    /**
     * Creates a new Admin from the given parameters.
     * <dt><b>Precondition:</b></dt><dd>
	 * Params must be Strings; ArrayList<Conference> != null
	 * </dd>
	 * <dt><b>Postcondition:</b></dt><dd>
	 * Admin Created from Params.
	 * </dd>
     * @param name name of the admin
     * @param id id of the admin
     * @param password password for the admin
     * @param conferences the list of conferences
     */
    public Admin(String name, String id, String password, ArrayList<Conference> conferences) {
        super(name, id, password, Type.ADMIN);
        this.conferences = conferences;
    }

    /**
     * Adds a user to the conference.
     * <dt><b>Precondition:</b></dt><dd>
	 * ArrayList<User> != null; user != null
	 * </dd>
     * @param users the list of users from the conference
     * @param user the user to add
     * @return <b>true</b> if the user was added
     */
    public boolean addUser(ArrayList<User> users, User user) {
        users.add(new User(user));
        this.setChanged();
        this.notifyObservers();
        return true;
    }

    /**
     * Changes the privilege of the user.
     * <dt><b>Precondition:</b></dt><dd>
	 * user != null; t != null
	 * </dd>
	 * <dt><b>Postcondition:</b></dt><dd>
	 * The user type will now be Type t
	 * </dd>
     * @param user the user to change
     * @param t the type to change to
     */
    public void changePriveledge(User user, Type t) {
        // Create new whatever, author, review, etc.
    	this.setChanged();
        this.notifyObservers();
    }
}
