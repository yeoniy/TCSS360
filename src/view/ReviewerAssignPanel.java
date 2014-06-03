package view;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import model.Paper;

import controller.Controller;
/**
 * Holds all the components for assigning a paper to a reviewer.
 * @author Nick Ames
 * @author Richard Hemingway
 * @author Tim Loverin
 * @author Yeonil Yoo
 * @version 6/3/2014
 */
public class ReviewerAssignPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel lblTitle;

	private JButton btnAssign;
	private JButton btnRemove;
	private JButton btnBack;
	/**
	 * boolean which ensures comboboxes only initialize data once
	 */
	private boolean ran;
	/**
	 * boolean which ensures comboboxes only initialize data once
	 */
	private boolean test;
	
	private JComboBox<String> cmbReviewerBox;
	private JComboBox<String> cmbPaperSelectBox;

	private JScrollPane reviewerPane;
	private JScrollPane authorSelectPane;

	private JTextArea txtReviewers;

	private JList authorSelectList;
	
	private MainPanel myMainPanel;
	private ReviewerAssignListener myListener;
	/**
	 * Constructor.
	 * @param m main panel.
	 */
	public ReviewerAssignPanel(final MainPanel m) {
		super(null);
		ran = false;
		test = false;
		myMainPanel = m;
		myListener = new ReviewerAssignListener();
		initialize();
		setName("reviewerAssign");
	}
	/**
	 * initializes the ReviewerAssignPanel.
	 */
	private void initialize() {

		/*
		 * Labels
		 */
		lblTitle = new JLabel("Assign Reviewer");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTitle.setBounds(10, 11, 424, 29);


		/*
		 * Buttons
		 */

		// Back
		btnBack = new JButton("< Back");
		btnBack.setBounds(10, 356, 89, 23);
		btnBack.addActionListener(myListener);

		// Assign
		btnAssign = new JButton("Assign");
		btnAssign.setBounds(359, 64, 80, 23);
		btnAssign.addActionListener(myListener);

		// Remove
		btnRemove = new JButton("Remove");
		btnRemove.setBounds(359, 98, 80, 23);
		btnRemove.addActionListener(myListener);


		/*
		 * Other
		 */

		// Combo Boxes
		cmbReviewerBox = new JComboBox<String>();
		cmbReviewerBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Select a Reviewer..."}));
		cmbReviewerBox.setBounds(10, 65, 172, 20);

		cmbPaperSelectBox = new JComboBox<String>();
		cmbPaperSelectBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Select a paper..."}));
		cmbPaperSelectBox.setBounds(192, 65, 157, 20);
		// Text Area
		txtReviewers = new JTextArea();
		txtReviewers.setEditable(false);

		// List
		authorSelectList = new JList();

		// Scroll Panes
		reviewerPane = new JScrollPane();
		reviewerPane.setBounds(10, 93, 172, 203);
		reviewerPane.setViewportView(txtReviewers);
		
		authorSelectPane = new JScrollPane();
		authorSelectPane.setBounds(192, 93, 157, 203);
		authorSelectPane.setViewportView(authorSelectList);



		// Add

		add(lblTitle);
		add(btnAssign);
		add(btnRemove);
		add(btnBack);
		add(cmbReviewerBox);
		add(cmbPaperSelectBox);
		add(reviewerPane);
		add(authorSelectPane);

	}
	/**
	 * adds papers to the combobox.
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
					cmbPaperSelectBox.addItem(Controller.getMyPapers().get(i).getFileName());
				}
		}
		test = true;
	}
	/**
	 * assigns reviewers to the combobox.
	 */
	public void assignReviewers() {
		int size = Controller.getAllReviewers().size();
		String[] reviewers = new String[size];
		for (int i = 0; i < Controller.getAllReviewers().size(); i++) {
			reviewers[i] = Controller.getAllReviewers().get(i).getName();
			if(!ran) {
				cmbReviewerBox.addItem(reviewers[i]);
			}
		}
		ran = true;
	}
	/**
	 * 
	 * @author Tim Loverin, Nick Ames.
	 * @version 6/2/2014
	 */
	private class ReviewerAssignListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() instanceof JButton) {
				JButton btn = (JButton) e.getSource();
				//Button Action for Submit
				if (btn.getText().equals("Assign")) {
					ArrayList<Paper> p = new ArrayList<Paper>();
					for (int i = 0; i < Controller.getUserPapers(cmbReviewerBox.getSelectedItem().toString()).size(); i++) {
						if (!Controller.getUserPapers(cmbReviewerBox.getSelectedItem().toString()).get(i).getFileName().equals("empty.txt")) {
							p.add(Controller.getUserPapers(cmbReviewerBox.getSelectedItem().toString()).get(i));
						} else {
							
						}
					}
					if (p.size() < 4) {
						Controller.assignPaper(cmbPaperSelectBox.getSelectedItem().toString(),cmbReviewerBox.getSelectedItem().toString());
					} else {
						JOptionPane.showMessageDialog(null, "Cannot assign more than 4 papers.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} else if (btn.getText().equals("Remove")) {
					Controller.deAssignPaper(cmbPaperSelectBox.getSelectedItem().toString(),cmbReviewerBox.getSelectedItem().toString());
				} else {
					CardLayout c = (CardLayout) myMainPanel.getLayout();
					c.show(myMainPanel, "entry");
				}
			}

		}

	}
}