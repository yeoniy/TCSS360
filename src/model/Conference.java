package model;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Yeonil on 4/22/14.
 */
public class Conference implements Observer {
    private String name;
    private Date date;
    private String id;
    private ArrayList<User> users;
    //a
    public Conference (String name, Date date, String id) {
        this.name = name;
        this.date = date;
        this.id = id;
        users = new ArrayList<User>();
    }

    public boolean addPaper(File file) {


        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
