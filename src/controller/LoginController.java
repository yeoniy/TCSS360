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

					Controller.myActiveConference = c;
					// Set the mainPanel view for the given type of the user
					myParent.dispose();
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
	 * @param user the username from the user textfield
	 * @param pass the password form the password
	 * @param c
	 * @return
	 * @throws InvalidLoginException
	 */
	private Type validateCredentials(String user, String pass, Conference c) throws InvalidLoginException {
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
