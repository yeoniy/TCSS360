package view;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
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

import view.SubAssignPanel.MyItemListener;
import model.Paper;
import model.User;
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
	private JLabel lblPaperSelect;

	private JScrollPane reviewerPane;
	private JScrollPane authorSelectPane;

	private JTextArea txtReviewers;

	private JList<String> paperList;
	
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
		MyItemListener actionListener = new MyItemListener();
		cmbReviewerBox.addItemListener(actionListener);

		lblPaperSelect = new JLabel("Please Select a Paper...");
		lblPaperSelect.setBounds(192, 65, 157, 20);
		// Text Area
		txtReviewers = new JTextArea();
		txtReviewers.setEditable(false);

		// List
		paperList = new JList<String>();

		// Scroll Panes
		reviewerPane = new JScrollPane();
		reviewerPane.setBounds(10, 93, 172, 203);
		reviewerPane.setViewportView(txtReviewers);
		
		authorSelectPane = new JScrollPane();
		authorSelectPane.setBounds(192, 93, 157, 203);
		authorSelectPane.setViewportView(paperList);



		// Add

		add(lblTitle);
		add(btnAssign);
		add(btnRemove);
		add(btnBack);
		add(cmbReviewerBox);
		add(lblPaperSelect);
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
			if (!Controller.getAllPapers().get(i).getFileName().equals("empty.txt"))
				papers[i] = Controller.getAllPapers().get(i).getFileName();
		}
		if(!test) {
			paperList.setListData(papers);
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
                if(!Controller.getCurrentUser().getId().equals(Controller.getAllReviewers().get(i).getId())){
				    cmbReviewerBox.addItem(reviewers[i]);
                }
			}
		}
		ran = true;
	}
	private void updateText() {
		ArrayList<String> temp = new ArrayList<String>();
			temp = Controller.getUserAssignments(cmbReviewerBox.getSelectedItem().toString());
			txtReviewers.setText("");
			for (int i = 0; i < temp.size(); i++) {
				txtReviewers.setText(txtReviewers.getText() + temp.get(i).toString() + "\n");
  		}
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
					if (cmbReviewerBox.getSelectedIndex() > 0) {
						String uID = Controller.getUserID(cmbReviewerBox.getSelectedItem().toString());
						String paperID = Controller.getPaperID(paperList.getSelectedValue());
						if (uID.equals(paperID)) {
							JOptionPane.showMessageDialog(null, "Cannot assign a reviewer their own paper.", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
						ArrayList<Paper> p = new ArrayList<Paper>();
						for (int i = 0; i < Controller.getUserPapers(cmbReviewerBox.getSelectedItem().toString()).size(); i++) {
							if (!Controller.getUserPapers(cmbReviewerBox.getSelectedItem().toString()).get(i).getFileName().equals("empty.txt")) {
								p.add(Controller.getUserPapers(cmbReviewerBox.getSelectedItem().toString()).get(i));
							}
						}
						if (p.size() < 4 && !Controller.getUserPapers(cmbReviewerBox.getSelectedItem().toString()).contains(new Paper(new File(paperList.getSelectedValue())))) {
							Controller.assignPaper(paperList.getSelectedValue(),cmbReviewerBox.getSelectedItem().toString());
							updateText();
						} else {
							JOptionPane.showMessageDialog(null, "Cannot assign more than 4 papers or paper already assigned.", "Error", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Please select a Sub-Chair before assigning.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} else if (btn.getText().equals("Remove")) {
					if (cmbReviewerBox.getSelectedIndex() > 0) {
						Controller.deAssignPaper(paperList.getSelectedValue(),cmbReviewerBox.getSelectedItem().toString());
						updateText();
					}
				} else {
					CardLayout c = (CardLayout) myMainPanel.getLayout();
					c.show(myMainPanel, "entry");
				}
			}

		}

	}
	/**
	 * 
	 * @author Tim Loverin
	 * @version 6/3/2014
	 */
	class MyItemListener implements ItemListener {
		  // This method is called only if a new item has been selected.
		  public void itemStateChanged(ItemEvent evt) {
		    JComboBox cb = (JComboBox) evt.getSource();

		    Object item = evt.getItem();

		    if (evt.getStateChange() == ItemEvent.SELECTED) {
		      		if(!cb.getSelectedItem().toString().equals("Select a Reviewer...")) {
		      			updateText();
		      		} else {
		      			txtReviewers.setText("");
		      		}
		 
		    } else if (evt.getStateChange() == ItemEvent.DESELECTED) {
		      // Item is no longer selected
		    }
		  }
	}

}