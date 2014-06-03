package exception;

import model.Conference;

/**
 * Runtime exception that will be thrown if the user inputs an invalid username/password combo.
 * 
 * @author Nick Ames
 * @author Richard Hemingway
 * @author Tim Loverin
 * @author Yeonil Yoo
 * @version 6/3/2014
 */
public class InvalidLoginException extends RuntimeException {
	
	/**
	 * For seriazable objects.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The attempted username.
	 */
	private String username;
	
	/**
	 * The attempted password.
	 */
	private String password;
	
	/**
	 * The conference that the login was attempted on.
	 */
	private Conference conference;
	
	/**
	 * Creates a new exception for an invalid login.
	 * <dt><b>Precondition:</b></dt><dd>
	 * Params must be Strings.
	 * </dd>
	 * <dt><b>Postcondition:</b></dt><dd>
	 * Exception Created from Params.
	 * </dd>
	 * @param user the username
	 * @param pass the password
	 * @param conference the conference
	 */
	public InvalidLoginException(String user, String pass, Conference conference) {
		super();
		this.username = user;
		this.password = pass;
		this.conference = conference;
	}

	/**
	 * Gets the username.
	 * @return the username.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Gets the password.
	 * @return the password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Gets the conference.
	 * @return the conference
	 */
	public Conference getConference() {
		return conference;
	}
}
