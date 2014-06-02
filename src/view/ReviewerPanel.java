package view;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import controller.Controller;

public class ReviewerPanel extends JPanel {
	
	private JLabel lblTitle;
	private JLabel lblSelectPaper;
	private JLabel lblPaperRating;
	private JLabel lblComments;
	
	private JButton btnSubmitReview;
	private JButton btnBack;
	private JButton btnViewPaper;
	
	/**
	 * boolean which prevents multiple instances of the same text file in the comboboxes.
	 */
	private boolean test;
	
	private JComboBox<String> cmbPaperSelectBox;
	private JList<String> paperList;
	
	private JSpinner reviewRatingSpinner;
	
	private JTextArea txtComments;
	
	private MainPanel myMainPanel;
	
	private ReviewerListener myListener;
	
	public ReviewerPanel(final MainPanel m) {
		super(null);
		test = false;
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
		
		btnViewPaper = new JButton("View Paper");
		btnViewPaper.setBounds(155, 75, 106, 23);
		btnViewPaper.addActionListener(myListener);
		
		/*
		 * Other
		 */
		paperList = new JList<String>();
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
		add(btnViewPaper);
		add(cmbPaperSelectBox);
		add(reviewRatingSpinner);
		add(txtComments);

	}
	
	public void addPapers() {
		int size = 0;
		for (int i = 0; i < Controller.getMyPapers().size(); i++) {
			if (!Controller.getMyPapers().get(i).getFileName().equals("empty.txt"))
				size++;
		}
		String[] papers = new String[size];
		for (int i = 0; i < papers.length; i++) {
			if (!Controller.getMyPapers().get(i).getFileName().equals("empty.txt"))
				papers[i] = Controller.getMyPapers().get(i).getFileName();
				if(!test) {
					cmbPaperSelectBox.addItem(papers[i]);
				}	
		}
		test = true;
		paperList.setListData(papers);
	} 
	private class ReviewerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() instanceof JButton) {
				JButton btn = (JButton) e.getSource();
				//Button Action for Submit
				if (btn.getText().equals("Submit Review")) {
				
				}else if (btn.getText().equals("View Paper")){
					
				
				} else {
					CardLayout c = (CardLayout) myMainPanel.getLayout();
					c.show(myMainPanel, "entry");
				}
			}

		}

	}
}
