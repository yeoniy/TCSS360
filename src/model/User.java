package model;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Yeonil on 4/22/14.
 */
public class User extends Observable {
    private String name;
    private String id;
    private String password;
    private Type myType;


    public User() {
        name = "Rich";
        id = "Tim";
        password = "Nick";
        myType = Type.AUTHOR;
    }

    public User(String name, String id, String password, Type type) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.myType = type;
    }

    public User(User user) {
        this.name = user.name;
        this.id = user.id;
        this.password = user.password;
        this.myType = user.myType;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
        this.setChanged();
        this.notifyObservers();
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
        this.setChanged();
        this.notifyObservers();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
        this.setChanged();
        this.notifyObservers();
    }

    public Type getMyType() {
    	return myType;
    }
    
    public void setMyType(Type type) {
        this.myType = type;
        this.setChanged();
        this.notifyObservers();
    }
    public String typeToString() {
    	return myType.toString();
    }
}
