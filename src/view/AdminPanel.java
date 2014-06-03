package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Conference;
import controller.Controller;
/**
 * The main entry panel for the user. This panel contains navigation based on the user type.
 * @author Nick Ames
 * @author Richard Hemingway
 * @author Tim Loverin
 * @author Yeonil Yoo
 * @version 6/3/2014
 */
public class AdminPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblUsername;
	private JLabel lblID;
	private JLabel lblType;
	private JLabel lblConference;
	private JLabel lblDeadline;
	private JLabel lblConferences;

	private Conference c;
	
	private JButton btnCreateConference;
	private JButton btnRemoveConference;
	private JButton btnExit;
	
	private JScrollPane listPane;
	
	private JList<String> conferenceList;

	private MainPanel myMainPanel;

	private AdminListener myListener;
	/**
	 * constructor
	 * @param m main panel
	 */
	public AdminPanel(final MainPanel m) {
		super(null);
		myMainPanel = m;
		c = new Conference("Seattle", new Date(14,6,4), "sadf");
		myListener = new AdminListener();
		initialize();
		setName("admin");
	}
	/**
	 * initializes the entry panel.
	 */
	private void initialize() {

		/*
		 * Labels
		 */

		// Username Label
		lblUsername = new JLabel("User:");
		lblUsername.setBounds(60, 47, 424, 14);
		
		// Conferences
		lblConferences = new JLabel("Conferences:");
		lblConferences.setBounds(60, 180, 268, 14);

		// ID Label
		lblID = new JLabel("ID:");
		lblID.setBounds(60, 75, 424, 14);

		// Type Label
		lblType = new JLabel("Type:");
		lblType.setBounds(60, 102, 424, 14);

		// Conference Label
		lblConference = new JLabel("Conference:");
		lblConference.setBounds(60, 130, 268, 14);

		// Deadline Label
		lblDeadline = new JLabel("Current Deadline:");
		lblDeadline.setBounds(60, 156, 300, 14);

		/*
		 * Buttons
		 */

		// Create Button
		btnCreateConference = new JButton("Create Conference");
		btnCreateConference.setEnabled(true);
		btnCreateConference.setBounds(60, 320, 150, 23);
		btnCreateConference.addActionListener(myListener);

		// Remove Button
		btnRemoveConference = new JButton("Remove Conference");
		btnRemoveConference.setEnabled(true);
		btnRemoveConference.setBounds(220, 320, 150, 23);
		btnRemoveConference.addActionListener(myListener);

		// Exit Button
		btnExit = new JButton("Exit");
		btnExit.setBounds(120, 350, 170, 23);
		btnExit.addActionListener(myListener);

		listPane = new JScrollPane();
		listPane.setBounds(60, 200, 310, 110);
		
		conferenceList = new JList<String>();
		conferenceList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				String a = "Conference: " + conferenceList.getSelectedValue();
				lblConference.setText(a);
				
				// TODO: Get the conference deadline here and update the lblDeadline
			}
		});
		
		listPane.setViewportView(conferenceList);
		
		/*
		 * Add Components to Panel
		 */

		// Labels
		add(lblUsername);
		add(lblID);
		add(lblType);
		add(lblConference);
		add(lblDeadline);
		add(lblConferences);
		add(btnCreateConference);
		add(btnRemoveConference);
		add(btnExit);
		add(listPane);
	}

	/**
	 * 
	 * @author Tim Loverin, Nick Ames.
	 * @version 6/2/2014
	 */
	private class AdminListener implements ActionListener {

		// TODO
		/**
		 * How this works::::
		 * 
		 * The admin logs in, he is shown this panel. The conferenceList will contain all the conferences
		 * that are currently created.
		 * 
		 * The admin can create a conference, which opens series of dialogs gathering information then creating a file for
		 * the conference.
		 * 
		 * The admin can remove a conference which just deletes that conferences file.
		 */
		//TODO
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() instanceof JButton) {
				JButton btn = (JButton) e.getSource();
				if (btn.getText().equals("Create Conference")) {
					createAConference();
				} else if (btn.getText().equals("Remove Conference")) {
					removeAConference();
				} else {
					int i = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?");
					if (i == JOptionPane.OK_OPTION)
						System.exit(0);
				}
			}
		}

		// TODO
		private void removeAConference() {
			// TODO Auto-generated method stub
			
		}

		// TODO
		private void createAConference() {
			
		}
	}
	
	public void updateUserInformation() {
		lblUsername.setText(lblUsername.getText() + " " + Controller.getCurrentUser().getName());
		lblID.setText(lblID.getText() + " " + Controller.getCurrentUser().getId());
		lblType.setText(lblType.getText() + " " + Controller.getCurrentUser().getMyType());
	}
}
