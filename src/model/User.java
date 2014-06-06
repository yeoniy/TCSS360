package model;
import java.util.Observable;
import java.util.Observer;

/**
 * The user is any user within the system. This is more of an outline for more specific users within the system.
 * @author Nick Ames
 * @author Richard Hemingway
 * @author Tim Loverin
 * @author Yeonil Yoo
 * @version 6/3/2014
 */
public class User extends Observable {
	/**
	 * Name of the user.
	 */
    private String name;
    
    /**
     * ID for the user.
     */
    private String id;
    
    /**
     * password for the user.
     */
    private String password;
    
    /**
     * Type of the user.
     */
    private Type myType;

    /**
     * Constructor
     */
    public User() {
        name = "Rich";
        id = "Tim";
        password = "Nick";
        myType = Type.AUTHOR;
    }

    /**
     * Constructor
     * @param name
     * @param id
     * @param password
     * @param type
     */
    public User(String name, String id, String password, Type type) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.myType = type;
    }

    /**
     * Constructor
     * @param user
     */
    public User(User user) {
        this.name = user.name;
        this.id = user.id;
        this.password = user.password;
        this.myType = user.myType;
    }

    /**
     * Get the ID for the user.
     * @return The ID string
     */
    public String getId() {
        return id;
    }
    
    /**
     * Sets the ID for the user.
     * @param id The id
     */
    public void setId(String id) {
        this.id = id;
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * Gets the password for the user.
     * @return the password string.
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * Sets the password for the user.
     * @param password The password
     */
    public void setPassword(String password) {
        this.password = password;
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * Get the name of the user.
     * @return The name string.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Set the name for the user.
     * @param name the name string
     */
    public void setName(String name) {
        this.name = name;
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Get the users type
     * @return The type of the user
     */
    public Type getMyType() {
    	return myType;
    }
    
    /**
     * Sets the type of the user.
     * @param type The type
     */
    public void setMyType(Type type) {
        this.myType = type;
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * return string value of the type.
     * @return Type String.
     */
    public String typeToString() {
    	return myType.toString();
    }
}
