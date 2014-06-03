package model;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;

/**
 * Represents an Author within the system.
 * @author Nick Ames
 * @author Richard Hemingway
 * @author Tim Loverin
 * @author Yeonil Yoo
 * @version 6/3/2014
 */
public class Author extends User {
    ArrayList<Paper> paper;

    public Author() {
        super();
        paper = new ArrayList<Paper>();
    }

    public Author(String name, String id, String password) {
        super(name, id, password, Type.AUTHOR);
        paper = new ArrayList<Paper>();
    }

    public Author(String name, String id, String password, Type type) {
        super(name, id, password, type);
        paper = new ArrayList<Paper>();
    }

    public boolean submit(File file) {
        paper.add(new Paper(file));
        this.setChanged();
        this.notifyObservers();
        return true;
    }

    public Date getDate() {
        return null;
    }
}
