package model;

/**
 * Represents a subchair within the conference.
 * @author Nick Ames
 * @author Richard Hemingway
 * @author Tim Loverin
 * @author Yeonil Yoo
 * @version 6/3/2014
 */
public class SubChair extends Reviewer {

	/**
	 * Constructor
	 * @param name Name  of the reviewer
	 * @param id ID for the reviewer
	 * @param password password for the reviewer
	 */
    public SubChair(String name, String id, String password) {
        super(name, id, password, Type.SUBCHAIR);
    }

    /**
     * Constructor
     * @param name Name of the reviewer
     * @param id ID for the reviewer
     * @param password Password of the reviewer
     * @param type type of the reviewer
     */
    public SubChair(String name, String id, String password, Type type) {
        super(name, id, password, type);
    }

    /**
     * Assigns a reviewer to a specific conference
     * @param user The user to be the reviewer
     * @param conference The conference to be reviewed.
     */
    public void assignReviewer(Author user, Conference conference) {
        user = new Reviewer(user.getName(), user.getId(), user.getPassword(), Type.REVIEWER);
        conference.addUser(user);
    }
}
