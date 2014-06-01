package view;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import model.Paper;
import controller.Controller;

public class ProChairReviewPanel extends JPanel {

	private JLabel lblTitle;
	private JLabel lblViewReviewsFor;
	private JLabel lblAverageRating;
	private JLabel lblRating;
	private JLabel lblAcceptanceStatus;
	private JLabel lblStatus;
	private JLabel lblRecommendation;
	private JLabel lblRec;
	private JLabel lblComments;

	private JButton btnAccept;
	private JButton btnReject;
	private JButton btnView;
	private JButton btnBack;

	private JComboBox<String> cmbProAuthorSelectBox;
	private JComboBox<String> cmbProPaperSelect;

	private JTextArea txtComments;
	
	private MainPanel myMainPanel;
	private ProChairListener myListener;

	public ProChairReviewPanel(final MainPanel m) {
		super(null);
		myMainPanel = m;
		myListener = new ProChairListener();
		initialize();
		setName("pro");
	}

	private void initialize() {

		/*
		 * Labels
		 */
		lblViewReviewsFor = new JLabel("View Reviews for:");
		lblViewReviewsFor.setBounds(26, 51, 106, 14);

		lblAverageRating = new JLabel("Average Rating:");
		lblAverageRating.setBounds(26, 107, 90, 14);

		lblRating = new JLabel("RATING");
		lblRating.setBounds(125, 107, 46, 14);

		lblComments = new JLabel("Reviewers comments:");
		lblComments.setBounds(26, 182, 132, 14);

		lblRecommendation = new JLabel("Recommendation:");
		lblRecommendation.setBounds(26, 157, 110, 14);

		lblAcceptanceStatus = new JLabel("Acceptance Status:");
		lblAcceptanceStatus.setBounds(26, 132, 120, 14);

		lblStatus = new JLabel("STATUS");
		lblStatus.setBounds(145, 132, 46, 14);

		lblTitle = new JLabel("Review Papers");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTitle.setBounds(10, 11, 424, 29);

		lblRec = new JLabel("REC");
		lblRec.setBounds(140, 157, 46, 14);

		/*
		 * Buttons
		 */
		btnBack = new JButton("< Back");
		btnBack.setBounds(10, 356, 89, 23);
		btnBack.addActionListener(myListener);

		btnView = new JButton("View");
		btnView.setBounds(317, 75, 89, 23);
		btnView.addActionListener(myListener);

		btnAccept = new JButton("ACCEPT");
		btnAccept.setBounds(212, 328, 89, 23);
		btnAccept.addActionListener(myListener);

		btnReject = new JButton("REJECT");
		btnReject.setBounds(317, 328, 89, 23);
		btnReject.addActionListener(myListener);

		/*
		 * Other
		 */

		// Combo Box
		cmbProAuthorSelectBox = new JComboBox<String>();
		cmbProAuthorSelectBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Select an Author..."}));
		cmbProAuthorSelectBox.setBounds(26, 76, 155, 20);

		cmbProPaperSelect = new JComboBox<String>();
		cmbProPaperSelect.setModel(new DefaultComboBoxModel<String>(new String[] {"Select a Paper..."}));
		cmbProPaperSelect.setBounds(191, 76, 119, 20);

		// Text Area
		txtComments = new JTextArea();
		txtComments.setBounds(26, 202, 380, 120);

		// Add

		add(lblTitle);
		add(lblViewReviewsFor);
		add(lblAverageRating);
		add(lblRating);
		add(lblAcceptanceStatus);
		add(lblStatus);
		add(lblRecommendation);
		add(lblRec);
		add(lblComments);
		add(btnAccept);
		add(btnReject);
		add(btnView);
		add(btnBack);
		add(cmbProAuthorSelectBox);
		add(cmbProPaperSelect);
		add(txtComments);
	}

	private class ProChairListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() instanceof JButton) {
				JButton btn = (JButton) e.getSource();
				//Button Action for Submit
				if (btn.getText().equals("Accept")) {
					
				
				} else if (btn.getText().equals("Reject")) {
					
				} else if (btn.getText().equals("View")) {
					
				} else {
					CardLayout c = (CardLayout) myMainPanel.getLayout();
					c.show(myMainPanel, "entry");
				}
			}

		}

	}

}
