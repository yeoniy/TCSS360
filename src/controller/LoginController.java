package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import model.Conference;
import model.Type;
import model.User;
import view.ConferenceGui;
import view.LoginDialog;
import view.LoginPanel;
import exception.InvalidLoginException;

/**
 * Controller for the login panel. Handles login and exit functions.
 * @author Nick Ames
 * @author Richard Hemingway
 * @author Tim Loverin
 * @author Yeonil Yoo
 * @version 6/3/2014
 */
public class LoginController extends Controller implements ActionListener {
	/**
	 * The LoginPanel to control.
	 */
	private LoginPanel myPanel;

	/**
	 * The FileController to load the conference data.
	 */
	private FileController ctrlFile;

	/**
	 * The loginDialog for the parent panel
	 */
	private LoginDialog myParent;

	/**
	 * Creates a new LoginController for the given LoginPanel.
	 * @param aPanel the LoginPanel to control
	 */
	public LoginController(final LoginPanel aPanel, final LoginDialog parent) {
		super();
		this.myParent = parent;
		this.myPanel = aPanel;
	}

	/**
	 * Performs Login
	 * <dt><b>Precondition:</b></dt><dd>
	 *  There is an actionEvent that has been performed
	 * </dd>
	 * <dt><b>Postcondition:</b></dt><dd>
	 * 	Makes specific updates depending on the action performed.	
	 * </dd>
	 * @param e the ActionEvent that triggered this method.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Get the source of the action
		if (e.getSource() instanceof JButton) {
			JButton btn = (JButton) e.getSource();
			//Button Action for Login
			if (btn.getText().equals("Login")) {
				// Get the conference and attempt login
				try {
					// Try grabbing the conference
					Conference c = myPanel.getConference();
					String user = myPanel.getUsername();
					String pass = String.valueOf(myPanel.getPassword());

					Type t = validateCredentials(user, pass, c);
					if (t == null) {
						return;
					}
					Controller.myActiveConference = c;
					// Set the mainPanel view for the given type of the user
					myParent.start();
					myParent.dispose();
				} catch (InvalidLoginException le) {
					JOptionPane.showMessageDialog(null, "Username/Password combo not found. Please ensure "
							+ "your username and password are correct and that you have selected the correct conference.", "Error", JOptionPane.ERROR_MESSAGE);
				} finally {
					myPanel.resetPassField();
				}
				// Update everything
				update();
			} else if (btn.getText().equals("Sign Up")) {
				String uName, uPass, uID;
				uName = JOptionPane.showInputDialog("Please enter a Username:");
				uPass = JOptionPane.showInputDialog("Please enter a Password:");
				uID = JOptionPane.showInputDialog(("Please enter a unique ID:"));
				if (uName != null && uPass != null && uID != null) {
					Controller.addUserToConference(new User(uName, uID, uPass, Type.AUTHOR), myPanel.getConference());
					JOptionPane.showMessageDialog(null, uName + " was added to the conference!");
				}
				
			} else {    //Action for Exit button
				// Exit the program
				System.exit(0);
			}
		}
	}

	/**
	 * For test purposes.
	 * @param user
	 * @param pass
	 * @param c
	 * @return
	 */
	public Type testCredentials(String user, String pass, Conference c) {
		return validateCredentials(user, pass, c);
	}

	/**
	 * Validates a user based on their name and password.
	 * 
	 * <dt><b>Precondition:</b></dt><dd>
	 * User name and password typed into text field
	 * </dd>

	 * @param user the username from the user textfield
	 * @param pass the password from the password
	 * @param c the associated conference
	 * @return the type of account for user
	 * @throws InvalidLoginException
	 */
	private Type validateCredentials(String user, String pass, Conference c) throws InvalidLoginException {
		if (user.equals("admin")) {
			if (pass.equals("admin")) {
				Controller.setActiveUser(new User("admin", "0", "admin", Type.ADMIN));
				Controller.setActiveConference(c);
				ConferenceGui.startConf();
				return Type.ADMIN;
			}
		}
		
		if (myPanel.getConference() == null) {
			JOptionPane.showMessageDialog(null, "No conference selected. Please select a conference.");
			return null;
		}
		this.ctrlFile = new FileController(c);
		c = ctrlFile.getMyConference();
		ArrayList<User> userList = c.getUserList();
		InvalidLoginException ile = new InvalidLoginException(user, pass, c);
		
		for (User u : userList) {
			//System.out.println(u.getName());
		}
		
		if (user.equals("")) {
			throw ile;
		}
		
		
		for (User u : userList) {
			if (u.getName().equals(user)) {
				if (u.getPassword().equals(pass)) {
					myPanel.setVisible(false);
					ctrlFile.getPapers(u);
					Controller.setActiveUser(u);
					Controller.setActiveConference(c);
					ConferenceGui.startConf();
					return u.getMyType();	
				}
			}
		}
		throw ile;
	}
}
