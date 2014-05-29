package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import model.Conference;
import model.Type;
import model.User;
import view.LoginPanel;
import exception.InvalidLoginException;
import view.MainPanel;

/**
 * Controller for the login panel. Handles login and exit functions.
 * @version 5/18/2014
 * @author Nick Ames
 *
 */
public class LoginController extends Controller implements ActionListener {
	
	private LoginPanel myPanel;
	
	private FileController ctrlFile;
	
	public LoginController(final LoginPanel aPanel) {
		super(null);
		this.myPanel = aPanel;
	}

    /**
     * Performs Login
     * @param e
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Get the source of the action
		if (e.getSource() instanceof JButton) {
			JButton btn = (JButton) e.getSource();
            //Button Action for Login
			if (btn.getText().equals("Login")) {
				// TODO: Perform the login here
				System.out.println(myPanel.getUsername());
				System.out.println(myPanel.getPassword());
				
				// Get the conference and attempt login
				try {
					// Try grabbing the conference
					Conference c = myPanel.getConference();
					String user = myPanel.getUsername();
					String pass = String.valueOf(myPanel.getPassword());
					
					Type t = validateCredentials(user, pass, c);


				} catch (InvalidLoginException le) {
					JOptionPane.showMessageDialog(null, "Username/Password combo not found. Please ensure "
							+ "your username and password are correct and that you have selected the correct conference.", "Error", JOptionPane.ERROR_MESSAGE);
				} finally {
					myPanel.resetPassField();
				}
				// Update everything
				update();
			} else {    //Action for Exit button
				// Exit the program
				System.exit(0);
			}
		}
	}
	
	public Type testCredentials(String user, String pass, Conference c) {
		return validateCredentials(user, pass, c);
	}
	
	private Type validateCredentials(String user, String pass, Conference c) throws InvalidLoginException {
		this.ctrlFile = new FileController(c);
		c = ctrlFile.getMyConference();
		ArrayList<User> userList = c.getUserList();
		InvalidLoginException ile = new InvalidLoginException(user, pass, c);
		// TODO: Delete this
		//ArrayList<User> userList = new ArrayList<User>();
		//User u1 = new User();
		//User u2 = new User("Nick", "111", "test", Type.AUTHOR);
		//userList.add(u1);
		//userList.add(u2);
		for (User u : userList) {
			System.out.println(u.getName());
		}
		if (user.equals("")) {
			throw ile;
		}
		
		for (User u : userList) {
			if (u.getName().equals(user)) {
				if (u.getPassword().equals(pass)) {
					return u.getMyType();
				}
			}
		}
		throw ile;
	}
}
