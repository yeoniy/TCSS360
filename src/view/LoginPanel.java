package view;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.Conference;
import util.Loader;
import controller.LoginController;

/**
 * An implementation of a login panel for the ConferenceGui class. This login panel will
 * allow a user to login to a specific conference with their unique id and password.
 * 
 * @author Nick Ames
 * @author Richard Hemingway
 * @author Tim Loverin
 * @author Yeonil Yoo
 * @version 6/3/2014
 */
public class LoginPanel extends JPanel {	
    /**
	 * Default serial ID.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Login Button.
	 */
	private JButton btnLogin;
	
	private JButton btnSignUp;
	/**
	 * Logout Button.
	 */
	private JButton btnExit;
	/**
	 * Login controller.
	 */
	private LoginController ctrlLogin;
	/**
	 * user name text field.
	 */
	private static JTextField txtUsername;
	/**
	 * password field.
	 */
	private JPasswordField txtPassword;
    /**
     * combo box of conferences.
     */
	private JComboBox<String> cmbConferences;
	/**
	 * array of conferences.
	 */
    private Conference[] conference;
    /**
     * Constructor.
     * @param l login Dialog
     */
    public LoginPanel(final LoginDialog l) {
        ctrlLogin = new LoginController(this, l);
        try {
			initialize();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    /**
     * initializes the LoginPanel.
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
	private void initialize() throws FileNotFoundException, IOException {
		JLabel lblUsername = new JLabel("Username:");
		JLabel lblPassword = new JLabel("Password:");
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(ctrlLogin);
		btnExit = new JButton("Exit");
		btnExit.addActionListener(ctrlLogin);
		btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(ctrlLogin);
		txtUsername = new JTextField();
		txtUsername.setPreferredSize(new Dimension(100, 20));
		txtPassword = new JPasswordField();
		txtPassword.setPreferredSize(new Dimension(100, 20));

        conference = Loader.loadConferenceList();
        String[] conferenceName = new String[conference.length];
        for (int i = 0; i < conferenceName.length; i++) {
            conferenceName[i] = conference[i].getName();
        }
		cmbConferences = new JComboBox<String>(conferenceName);
		cmbConferences.setPreferredSize(new Dimension(170, 20));
		// Add the components
		this.add(lblUsername);
		this.add(txtUsername);
		this.add(lblPassword);
		this.add(txtPassword);
		this.add(cmbConferences);
		this.add(btnLogin);
		this.add(btnSignUp);
		this.add(btnExit);

		this.setFocusable(true);
		this.requestFocusInWindow();
		
		txtUsername.setFocusable(true);
		txtUsername.requestFocusInWindow();
		txtPassword.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent arg0) {
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
			}
			@Override
			public void keyTyped(KeyEvent arg0) {
				if (arg0.getKeyChar() == KeyEvent.VK_ENTER) {
					btnLogin.doClick();
				}
			}
		});
		txtUsername.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent arg0) {
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
			}
			@Override
			public void keyTyped(KeyEvent arg0) {
				if (arg0.getKeyChar() == KeyEvent.VK_ENTER) {
					btnLogin.doClick();
				}
			}
		});
	}
	/**
	 * gets the username.
	 * @return username in string form.
	 */
	public static String getUsername() {
		return txtUsername.getText();
	}
	/**
	 *  gets the password.
	 * @return the password.
	 */
	public char[] getPassword() {
		return txtPassword.getPassword();
	}
	/**
	 * gets the Conference.
	 * @return Conference.
	 */
	public Conference getConference() {
		return conference[cmbConferences.getSelectedIndex()];
	}
	/**
	 * resets the password data field.
	 */
	public void resetPassField() {
		txtPassword.setText("");
	}
}
