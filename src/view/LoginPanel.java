package view;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

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

	private ArrayList<Conference> conferences;
	
	private JButton btnLogin;
	
	private JButton btnExit;
	
	private LoginController ctrlLogin;
	
	private JTextField txtUsername;
	
	private JPasswordField txtPassword;
    
    private GridLayout myLayout;
    
    public LoginPanel(ArrayList<Conference> conferences) {
        this.conferences = conferences;
        ctrlLogin = new LoginController();
        myLayout = new GridLayout(0, 1);
        initialize();
    }

	private void initialize() {
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
		this.add(lblUsername);
		this.add(txtUsername);
		this.add(lblPassword);
		this.add(txtPassword);
		this.add(btnLogin);
		this.add(btnExit);
	}

}
