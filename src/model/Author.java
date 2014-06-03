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
	
	/**
	 * The list of papers for the author.
	 */
    ArrayList<Paper> paper;

    /**
     * Creates a new defualt Author.
     * <dt><b>Precondition:</b></dt><dd>
	 * None.
	 * </dd>
	 * <dt><b>Postcondition:</b></dt><dd>
	 * A new default Author is created.
	 * </dd>
     */
    public Author() {
        super();
        paper = new ArrayList<Paper>();
    }

    /**
     * Creates a new Author with a default Type of Author.
     * <dt><b>Precondition:</b></dt><dd>
	 * Params must be Strings.
	 * </dd>
	 * <dt><b>Postcondition:</b></dt><dd>
	 * A new Author is created from the given params.
	 * </dd>
     * @param name the name of the author
     * @param id the id of the author
     * @param password the password for the author
     */
    public Author(String name, String id, String password) {
        super(name, id, password, Type.AUTHOR);
        paper = new ArrayList<Paper>();
    }

    /**
     * Creates a new Author with a specific Type.
     * <dt><b>Precondition:</b></dt><dd>
	 * Params must be Strings. type != null
	 * </dd>
	 * <dt><b>Postcondition:</b></dt><dd>
	 * A new Author is created with a given Type type.
	 * </dd>
     * @param name the name of the author
     * @param id the id of the author
     * @param password the password for the author
     * @param type the Type for the author
     */
    public Author(String name, String id, String password, Type type) {
        super(name, id, password, type);
        paper = new ArrayList<Paper>();
    }
}
