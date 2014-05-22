package view;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import util.Loader;
import model.Conference;
import controller.LoginController;

/**
 * An implementation of a login panel for the ConferenceGui class. This login panel will
 * allow a user to login to a specific conference with their unique id and password.
 * 
 * @version 5/18/2014
 * @author Nick Ames
 */
public class LoginPanel extends JPanel {
	
    /**
	 * Default serial ID.
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton btnLogin;
	
	private JButton btnExit;
	
	private LoginController ctrlLogin;
	
	private JTextField txtUsername;
	
	private JPasswordField txtPassword;
    
	private JComboBox<String> cmbConferences;
    
    public LoginPanel() {
        ctrlLogin = new LoginController(this);
        try {
			initialize();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	private void initialize() throws FileNotFoundException, IOException {
		JLabel lblUsername = new JLabel("Username:");
		JLabel lblPassword = new JLabel("Password:");
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(ctrlLogin);
		btnExit = new JButton("Exit");
		btnExit.addActionListener(ctrlLogin);
		txtUsername = new JTextField();
		txtUsername.setPreferredSize(new Dimension(100, 20));
		txtPassword = new JPasswordField();
		txtPassword.setPreferredSize(new Dimension(100, 20));
		
		String[] conferenceNames = Loader.loadConferenceList();
		
		cmbConferences = new JComboBox<String>(conferenceNames);
		cmbConferences.setPreferredSize(new Dimension(170, 20));
		// Add the components
		this.add(lblUsername);
		this.add(txtUsername);
		this.add(lblPassword);
		this.add(txtPassword);
		this.add(cmbConferences);
		this.add(btnLogin);
		this.add(btnExit);
	}
	
	public String getUsername() {
		return txtUsername.getText();
	}
	
	public char[] getPassword() {
		return txtPassword.getPassword();
	}
	
	public Conference getConference() {
		Conference toReturn = null;
		
		return toReturn;
	}

	public void resetPassField() {
		txtPassword.setText("");
	}
}
