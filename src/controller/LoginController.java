package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * Controller for the login panel. Handles login and exit functions.
 * @version 5/18/2014
 * @author Nick Ames
 *
 */
public class LoginController extends Controller implements ActionListener {
	
	public LoginController() {
		super();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Get the source of the action
		if (e.getSource() instanceof JButton) {
			JButton btn = (JButton) e.getSource();
			if (btn.getText().equals("Login")) {
				// TODO: Perform the login here
				
				// Update everything
				update();
			} else {
				// Exit the program
				System.exit(0);
			}
		}
	}
	
	
}
