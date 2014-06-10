package controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import org.junit.Test;
import model.Conference;
import model.Paper;
import model.Type;
import model.User;
/**
 * 
 * @author Tim Loverin
 * @version 6/5/2014
 */
public class ControllerTest {	
	private static User myUser;
	public static Conference myActiveConference;
	
	public ControllerTest() {
		myUser = new User("Tim","ID1", "PASS", Type.REVIEWER);
	}
	
	@Test
	public void TestgetMyType() {
		Date date = new Date(2015, 04, 06);
		Controller.setActiveConference(new Conference("Seattle", date, "Seattle Sympo"));
		Controller.setActiveUser(myUser);
		assertEquals(Type.REVIEWER, Controller.getUserType());	
	}
	@Test
	public void TestgetConferenceDeadline() {
		String s = "";
		try {
			s = Controller.getConferenceDeadline("Seattle");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("2015/4/6", s);
	}
	@Test
	public void TestgetTheFile() {
		String s = "";
		s = Controller.getTheFile("testpaper.txt").toString();
		assertEquals("Resources\\testpaper.txt" , s);
	}		
}