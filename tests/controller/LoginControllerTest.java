package controller;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import model.Conference;
import model.Type;
import model.User;

import org.junit.Before;
import org.junit.Test;

import controller.LoginController;
import exception.InvalidLoginException;

/**
 * Test case for LoginController.
 * 
 * @author Nick Ames
 * @author Richard Hemingway
 * @author Tim Loverin
 * @author Yeonil Yoo
 * @version 6/3/2014
 */
public class LoginControllerTest {

	private User user;
	private LoginController lc;
	private Conference c;
	
	@Before
	public void setUp() throws Exception {
		c = new Conference("", null, "");
		user = new User("Nick", "id", "Pass", Type.AUTHOR);
		lc = new LoginController(null, null);
		c.addUser(user);
	}

	@Test 
	public void testValidateCredentialsPass() {
		assertEquals(Type.AUTHOR, lc.testCredentials(user.getName(), user.getPassword(), c));
	}
	
	@Test 
	public void testValidateCredentialsFail() {
		try {
			lc.testCredentials("Bob", "fail", c);
			fail();
		} catch (InvalidLoginException e) {

		}
	}
}
