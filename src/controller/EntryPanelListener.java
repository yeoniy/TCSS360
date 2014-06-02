package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import model.Conference;
import model.Type;
import exception.InvalidLoginException;
import view.MainPanel;
/**
 * 
 * @author Tim Loverin, Yeonil, Nick Ames.
 * @version 6/2/2014
 */
public class EntryPanelListener implements ActionListener {
	/**
	 * Main Panel.
	 */
	private MainPanel myMainPanel;
	/**
	 * constuctor
	 * @param m main panel.
	 */
	public EntryPanelListener(MainPanel m) {
		myMainPanel = m;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Get the source of the action
		if (e.getSource() instanceof JButton) {
			JButton btn = (JButton) e.getSource();
			//Button Action for Login
			if (btn.getText().equals("Login")) {
				
			}
		}
	}

}
