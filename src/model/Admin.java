package model;
/**
 * Created by Yeonil on 4/22/14.
 */

import java.util.ArrayList;
//test
/**
 * used for creating accounts and editting privledges,  assigning program chair.
 */
public class Admin extends User {
    private ArrayList<Conference> conferences;

    public Admin(String name, String id, String password, ArrayList<Conference> conferences) {
        super(name, id, password, Type.ADMIN);
        this.conferences = conferences;
    }

    public boolean addUser(ArrayList<User> users, User user) {
        users.add(new User(user));
        this.setChanged();
        this.notifyObservers();
        return true;
    }

    public void changePriveledge(User user, Type t) {
        // Create new whatever, author, review, etc.
    	this.setChanged();
        this.notifyObservers();
    }

    private void addConference(Conference c) {
    	
        conferences.add(c);
        this.setChanged();
        this.notifyObservers();
    }
    private void removeConference(Conference c) {
        conferences.remove(c);
        this.setChanged();
        this.notifyObservers();
    }
}
