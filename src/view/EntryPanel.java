package view;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.Controller;

public class EntryPanel extends JPanel {

	private JLabel lblUsername;
	private JLabel lblID;
	private JLabel lblType;
	private JLabel lblConference;
	private JLabel lblDeadline;

	private JButton btnSubmit;
	private JButton btnStats;
	private JButton btnReview;
	private JButton btnAssignReviewers;
	private JButton btnAssignSubChair;
	private JButton btnProReview;
	private JButton btnExit;
	
	private MainPanel myMainPanel;

	private EntryListener myListener;
	
	public EntryPanel(final MainPanel m) {
		super(null);
		myMainPanel = m;
		myListener = new EntryListener();
		initialize();
		setName("entry");
	}

	private void initialize() {
		
		/*
		 * Labels
		 */
		
		// Username Label
		lblUsername = new JLabel("User:");
		lblUsername.setBounds(60, 47, 424, 14);
		
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
		
		// Submit Button
		btnSubmit = new JButton("Submit Paper");
		btnSubmit.setEnabled(false);
		btnSubmit.setBounds(60, 181, 150, 23);
		btnSubmit.addActionListener(myListener);
		
		// Stats Button
		btnStats = new JButton("Paper Stats");
		btnStats.setEnabled(false);
		btnStats.setBounds(220, 181, 150, 23);
		btnStats.addActionListener(myListener);
		
		// Review Button
		btnReview = new JButton("Review Paper");
		btnReview.setEnabled(false);
		btnReview.setBounds(60, 215, 150, 23);
		btnReview.addActionListener(myListener);
		
		// Assign Reviewer Button
		btnAssignReviewers = new JButton("Assign Reviewers");
		btnAssignReviewers.setEnabled(false);
		btnAssignReviewers.setBounds(220, 215, 150, 23);
		btnAssignReviewers.addActionListener(myListener);
		
		// Assign Sub Chair Buttons
		btnAssignSubChair = new JButton("Assign Sub-Chairs");
		btnAssignSubChair.setEnabled(false);
		btnAssignSubChair.setBounds(60, 249, 150, 23);
		btnAssignSubChair.addActionListener(myListener);
		
		// Pro Chair Review Button
		btnProReview = new JButton("Chair Review");
		btnProReview.setEnabled(false);
		btnProReview.setBounds(220, 249, 150, 23);
		btnProReview.addActionListener(myListener);
		
		// Exit Button
		btnExit = new JButton("Exit");
		btnExit.setBounds(120, 283, 170, 23);
		btnExit.addActionListener(myListener);
		
		/*
		 * Add Components to Panel
		 */
		
		// Labels
		add(lblUsername);
		add(lblID);
		add(lblType);
		add(lblConference);
		add(lblDeadline);
		add(btnSubmit);
		add(btnStats);
		add(btnReview);
		add(btnAssignReviewers);
		add(btnAssignSubChair);
		add(btnProReview);
		add(btnExit);
	}
	
	public void updateUserInformation() {
		lblUsername.setText(lblUsername.getText() + " " + Controller.getCurrentUser().getName());
		lblID.setText(lblID.getText() + " " + Controller.getCurrentUser().getId());
		lblType.setText(lblType.getText() + " " + Controller.getCurrentUser().getMyType());
		lblConference.setText(lblConference.getText() + " " + Controller.getCurrentConference().getName());
		lblDeadline.setText(lblDeadline.getText() + " " + Controller.getCurrentConference().getDate().toString());
		setButtonAuthority();
	}
	
	private void setButtonAuthority() {
		switch (Controller.getCurrentUser().getMyType()) {
		case ADMIN:
		case PROCHAIR:
			btnProReview.setEnabled(true);
			btnAssignSubChair.setEnabled(true);
		case SUBCHAIR:
			btnAssignReviewers.setEnabled(true);
		case REVIEWER:
			btnReview.setEnabled(true);
		case AUTHOR:
			btnSubmit.setEnabled(true);
			btnStats.setEnabled(true);
		default:
			break;
		}
	}

	private class EntryListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() instanceof JButton) {
				JButton btn = (JButton) e.getSource();
				//Button Action for Submit
				if (btn.getText().equals("Submit Paper")) {
					CardLayout c = (CardLayout) myMainPanel.getLayout();
					c.show(myMainPanel, "paper");
					loadPapers();
				} else if (btn.getText().equals("Paper Stats")) {
					CardLayout c = (CardLayout) myMainPanel.getLayout();
					c.show(myMainPanel, "stats");
				} else if (btn.getText().equals("Review Paper")) {
					CardLayout c = (CardLayout) myMainPanel.getLayout();
					c.show(myMainPanel, "reviewer");
				} else if (btn.getText().equals("Assign Reviewers")) {
					CardLayout c = (CardLayout) myMainPanel.getLayout();
					c.show(myMainPanel, "reviewerAssign");
				} else if (btn.getText().equals("Assign Sub-Chairs")) {
					CardLayout c = (CardLayout) myMainPanel.getLayout();
					c.show(myMainPanel, "subAssign");
				} else if (btn.getText().equals("Chair Review")) {
					CardLayout c = (CardLayout) myMainPanel.getLayout();
					c.show(myMainPanel, "pro");
				} else {
					int i = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?");
					if (i == JOptionPane.OK_OPTION)
						System.exit(0);
				}
			}
		}

		private void loadPapers() {
			Component[] c = myMainPanel.getComponents();
			for (Component a : c) {
				if (a instanceof PaperPanel) {
					((PaperPanel) a).addPapers();
					break;
				}
			}
		}
		
	}
}