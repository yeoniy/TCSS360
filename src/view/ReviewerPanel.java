package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import controller.Controller;
/**
 * 
 * @author Tim Loverin, Nick Ames.
 * @version 6/2/2014
 */
public class ReviewerPanel extends JPanel {
	
	/**
	 * Dimension of screen.
	 */
	private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	
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
	/**
	 * Constructor.
	 * @param m main panel
	 */
	public ReviewerPanel(final MainPanel m) {
		super(null);
		test = false;
		myMainPanel = m;
		myListener = new ReviewerListener();
		initialize();
		setName("reviewer");
		
	}
	/**
	 * initializes the ReviewerPanel.
	 */
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
	/**
	 * Adds papers to the combobox
	 */
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
	/**
	 * Action Listener for the Reviewer Panel.
	 * 
	 * @author Tim Loverin, Nick Ames.
	 * @version 6/2/2014
	 */
	private class ReviewerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() instanceof JButton) {
				JButton btn = (JButton) e.getSource();
				//Button Action for Submit
				if (btn.getText().equals("Submit Review")) {
				
				}else if (btn.getText().equals("View Paper")){
					JFrame frame = new JFrame(cmbPaperSelectBox.getSelectedItem().toString());
							frame.setVisible(true);
							frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
							frame.setSize(500,500);
							frame.setLayout(new BorderLayout());
							JScrollPane pane = new JScrollPane();
							JTextArea list = new JTextArea();
							list.setEditable(false);
							list.setPreferredSize(new Dimension(450, 442));
							pane.setViewportView(list);
							frame.add(pane, BorderLayout.CENTER);
							frame.setLocation(SCREEN_SIZE.width/2 - 300, SCREEN_SIZE.height/2 - 300);
							try {
							File file = new File("Resources\\" + cmbPaperSelectBox.getSelectedItem().toString());
							FileReader fr = new FileReader(file);
							BufferedReader br = new BufferedReader(fr);
							String line = "";
							    while(br.ready())    {  
							         line += br.readLine() + "\r\n";   
							    }
							    list.setText(line);
							br.close();
							}
							catch(IOException e1) {
							    System.out.println("Error opening file");
							}
				
				} else {
					CardLayout c = (CardLayout) myMainPanel.getLayout();
					c.show(myMainPanel, "entry");
				}
			}

		}

	}
}
