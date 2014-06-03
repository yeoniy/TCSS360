package util;
import static org.junit.Assert.fail;
import model.Conference;

import org.junit.Before;
import org.junit.Test;

import util.Loader;

/**
 * Test case for Loader.
 * 
 * @author Nick Ames
 * @author Richard Hemingway
 * @author Tim Loverin
 * @author Yeonil Yoo
 * @version 6/3/2014
 */
public class LoaderTest {

	/**
	 * Testing loadConferenceList Pass.
	 */
	public void testloadConferenceListPass() {
		try {
			Conference[] c = Loader.loadConferenceList();
		} catch(Exception e) {
			fail("Threw exception " + e.getMessage());
		}
	}	
}
