package model;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

/**
 * Created by Yeonil on 4/22/14.
 */
public class Conference extends Observable {
    private String name;
    private Date date;
    private String id;
    private ArrayList<User> users;

    public Conference (String name, Date date, String id) {
        this.name = name;
        this.date = date;
        this.id = id;
        users = new ArrayList<User>();
    }

    public boolean addPaper(File file) {


    	this.setChanged();
        this.notifyObservers();
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.setChanged();
        this.notifyObservers();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
        this.setChanged();
        this.notifyObservers();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        this.setChanged();
        this.notifyObservers();
    }

    public void addUser(User user) {
        this.users.add(user);
        this.setChanged();
        this.notifyObservers();
    }

    public ArrayList<User> getUserList() {
    	return this.users;
    }
    
    public String toString() {
    	return this.name;
    }

	public void addObservers(ArrayList<JPanel> observers) {
		for (JPanel j : observers) {
			addObserver((Observer) j);
		}
	}
}
