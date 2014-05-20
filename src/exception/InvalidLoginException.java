package exception;

import model.Conference;

/**
 * Runtime exception that will be thrown if the user inputs an invalid username/password combo.
 * 
 * @version 5/19/2014
 * @author Nick Ames
 *
 */
public class InvalidLoginException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String username;
	
	private String password;
	
	private Conference conference;
	
	public InvalidLoginException(String user, String pass, Conference conference) {
		super();
		this.username = user;
		this.password = pass;
		this.conference = conference;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public Conference getConference() {
		return conference;
	}
}
