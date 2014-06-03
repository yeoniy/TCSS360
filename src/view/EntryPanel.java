package view;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Type;

import controller.Controller;
/**
 * 
 * @author Tim Loverin, Nick Ames.
 * @version 1.0
 */
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
	/**
	 * constructor
	 * @param m main panel
	 */
	public EntryPanel(final MainPanel m) {
		super(null);
		myMainPanel = m;
		myListener = new EntryListener();
		initialize();
		setName("entry");
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
	/**
	 * updates the user information.
	 */
	public void updateUserInformation() {
		lblUsername.setText(lblUsername.getText() + " " + Controller.getCurrentUser().getName());
		lblID.setText(lblID.getText() + " " + Controller.getCurrentUser().getId());
		lblType.setText(lblType.getText() + " " + Controller.getCurrentUser().getMyType());
		lblConference.setText(lblConference.getText() + " " + Controller.getCurrentConference().getName());
        Date date = Controller.getCurrentConference().getDate();
		lblDeadline.setText(lblDeadline.getText() + " " + date.getYear() + "-" + date.getMonth() + "-" + date.getDate());
		setButtonAuthority();
	}
	/**
	 * sets visibility of buttons based on user type.
	 */
	private void setButtonAuthority() {
		Type T = Controller.getCurrentUser().getMyType();
	
		if (T == Type.AUTHOR) {
			btnSubmit.setEnabled(true);
			btnStats.setEnabled(true);
		} else if (T == Type.REVIEWER) {
			btnStats.setEnabled(true);
			btnReview.setEnabled(true);
		} else if (T == Type.SUBCHAIR) {
			btnStats.setEnabled(true);
			btnAssignReviewers.setEnabled(true);
			btnProReview.setEnabled(true);
		} else if(T == Type.PROCHAIR) {
			btnReview.setEnabled(false);
			btnAssignReviewers.setEnabled(false);
			btnProReview.setEnabled(true);
			btnAssignSubChair.setEnabled(true);
		} else if(T == Type.ADMIN) {
			btnSubmit.setEnabled(true);
			btnStats.setEnabled(true);
			btnReview.setEnabled(true);
			btnProReview.setEnabled(true);
			btnAssignSubChair.setEnabled(true);
			btnAssignReviewers.setEnabled(true);
		}
	}
	/**
	 * 
	 * @author Tim Loverin, Nick Ames.
	 * @version 6/2/2014
	 */
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
					loadStats();
				} else if (btn.getText().equals("Review Paper")) {
					CardLayout c = (CardLayout) myMainPanel.getLayout();
					c.show(myMainPanel, "reviewer");
					loadReviews();
				} else if (btn.getText().equals("Assign Reviewers")) {
					CardLayout c = (CardLayout) myMainPanel.getLayout();
					c.show(myMainPanel, "reviewerAssign");
					loadrevassignpan();
				} else if (btn.getText().equals("Assign Sub-Chairs")) {
					CardLayout c = (CardLayout) myMainPanel.getLayout();
					c.show(myMainPanel, "subAssign");
					loadsubpan();
				} else if (btn.getText().equals("Chair Review")) {
					CardLayout c = (CardLayout) myMainPanel.getLayout();
					c.show(myMainPanel, "pro");
					prochairrevpanel();
				} else {
					int i = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?");
					if (i == JOptionPane.OK_OPTION)
						System.exit(0);
				}
			}
		}
		/**
		 * loads data into the PaperPanel
		 */
		private void loadPapers() {
			Component[] c = myMainPanel.getComponents();
			for (Component a : c) {
				if (a instanceof PaperPanel) {
					((PaperPanel) a).addPapers();
					break;
				}
			}
		}
		/**
		 * loads review data into ReviewerPanel.
		 */
		private void loadReviews() {
			Component[] c = myMainPanel.getComponents();
			for (Component a : c) {
				if (a instanceof ReviewerPanel) {
					((ReviewerPanel) a).addPapers();
					break;
				}
			}
		}
		/**
		 * loads data into the StatsPanel.
		 */
		private void loadStats() {
			Component[] c = myMainPanel.getComponents();
			for (Component a : c) {
				if (a instanceof StatsPanel) {
					((StatsPanel) a).addPapers();
					break;
				}
			}
		}
		/**
		 * loads data into the SubAssignPanel.
		 */
		private void loadsubpan() {
			Component[] c = myMainPanel.getComponents();
			for (Component a : c) {
				if (a instanceof SubAssignPanel) {
					((SubAssignPanel) a).addPapers();
					((SubAssignPanel) a).SubChairs();
					break;	
				}
			}
		}	
		/**
		 * loads data into the ReviewerAssignPanel.
		 */
		private void loadrevassignpan() {
			Component[] c = myMainPanel.getComponents();
			for (Component a : c) {
				if (a instanceof ReviewerAssignPanel) {
					((ReviewerAssignPanel) a).assignReviewers();
					((ReviewerAssignPanel) a).addPapers();
					break;	
				}
			}
		}	
		/**
		 * loads data into the ProChairReviewPanel.
		 */
		private void prochairrevpanel() {
			Component[] c = myMainPanel.getComponents();
			for (Component a : c) {
				if (a instanceof ProChairReviewPanel) {
					((ProChairReviewPanel) a).assignAuthors();
					((ProChairReviewPanel) a).initPapers();
					break;	
				}
			}
		}	
	}
}
