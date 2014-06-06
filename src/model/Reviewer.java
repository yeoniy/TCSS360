package model;
import java.util.ArrayList;

/**
 * Represents a reviewer within the conference.
 * @author Nick Ames
 * @author Richard Hemingway
 * @author Tim Loverin
 * @author Yeonil Yoo
 * @version 6/3/2014
 */
public class Reviewer extends Author {

	/**
	 * Constructor
	 * @param name Name  of the reviewer
	 * @param id ID for the reviewer
	 * @param password password for the reviewer
	 */
    public Reviewer(String name, String id, String password) {
        super(name, id, password, Type.REVIEWER);
    }
    
    /**
     * Constructor
     * @param name Name of the reviewer
     * @param id ID for the reviewer
     * @param password Password of the reviewer
     * @param type type of the reviewer
     */
    public Reviewer(String name, String id, String password, Type type) {
        super(name, id, password, type);
    }

    /**
     * Adds a reviewer comment to a specific paper.
     * @param paper The paper to add the comment to
     * @param comment The comment to be added.
     */
    public void addcomment(Paper paper, String comment) {
        paper.addComment(comment);
    }
}
