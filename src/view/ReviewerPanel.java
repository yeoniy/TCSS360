package view;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

public class ReviewerPanel extends JPanel {
	
	private JLabel lblTitle;
	private JLabel lblSelectPaper;
	private JLabel lblPaperRating;
	private JLabel lblComments;
	
	private JButton btnSubmitReview;
	private JButton btnBack;
	
	private JComboBox<String> cmbPaperSelectBox;
	
	private JSpinner reviewRatingSpinner;
	
	private JTextArea txtComments;
	
	private MainPanel myMainPanel;
	
	private ReviewerListener myListener;
	
	public ReviewerPanel(final MainPanel m) {
		super(null);
		myMainPanel = m;
		myListener = new ReviewerListener();
		initialize();
		setName("reviewer");
	}

	private void initialize() {
		
		/*
		 * Labels
		 */
		
		lblSelectPaper = new JLabel("Select Paper:");
		lblSelectPaper.setBounds(23, 51, 100, 14);
		
		lblComments = new JLabel("Comments:");
		lblComments.setBounds(23, 132, 64, 14);
		
		lblPaperRating = new JLabel("Paper Rating:");
		lblPaperRating.setBounds(23, 107, 85, 14);
		
		lblTitle = new JLabel("Paper Reviewer");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTitle.setBounds(10, 11, 424, 29);
		
		/*
		 * Buttons
		 */
		
		btnBack = new JButton("< Back");
		btnBack.setBounds(10, 356, 89, 23);
		btnBack.addActionListener(myListener);
		
		btnSubmitReview = new JButton("Submit Review");
		btnSubmitReview.setBounds(164, 328, 120, 23);
		btnSubmitReview.addActionListener(myListener);
		
		/*
		 * Other
		 */
		
		// Combo Box
		cmbPaperSelectBox = new JComboBox<String>();
		cmbPaperSelectBox.setBounds(23, 76, 117, 20);
		
		// Text Area
		txtComments = new JTextArea();
		txtComments.setBounds(23, 152, 389, 170);
		
		// Spinner
		reviewRatingSpinner = new JSpinner();
		reviewRatingSpinner.setModel(new SpinnerNumberModel(1, 1, 10, 1));
		reviewRatingSpinner.setBounds(110, 107, 48, 20);
		
		// Add
		
		add(lblTitle);
		add(lblSelectPaper);
		add(lblPaperRating);
		add(lblComments);
		add(btnSubmitReview);
		add(btnBack);
		add(cmbPaperSelectBox);
		add(reviewRatingSpinner);
		add(txtComments);

	}

	private class ReviewerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() instanceof JButton) {
				JButton btn = (JButton) e.getSource();
				//Button Action for Submit
				if (btn.getText().equals("Submit Review")) {
					
				
				} else {
					CardLayout c = (CardLayout) myMainPanel.getLayout();
					c.show(myMainPanel, "entry");
				}
			}

		}

	}
}
