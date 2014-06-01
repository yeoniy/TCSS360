package view;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class ReviewerAssignPanel extends JPanel {

	private JLabel lblTitle;

	private JButton btnAssign;
	private JButton btnRemove;
	private JButton btnBack;

	private JComboBox<String> cmbReviewerBox;
	private JComboBox<String> cmbAuthorSelectBox;

	private JScrollPane reviewerPane;
	private JScrollPane authorSelectPane;

	private JTextArea txtReviewers;

	private JList authorSelectList;
	
	private MainPanel myMainPanel;
	private ReviewerAssignListener myListener;

	public ReviewerAssignPanel(final MainPanel m) {
		super(null);
		myMainPanel = m;
		myListener = new ReviewerAssignListener();
		initialize();
		setName("reviewerAssign");
	}

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

		cmbAuthorSelectBox = new JComboBox<String>();
		cmbAuthorSelectBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Select an Author..."}));
		cmbAuthorSelectBox.setBounds(192, 65, 157, 20);

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
		add(cmbAuthorSelectBox);
		add(reviewerPane);
		add(authorSelectPane);

	}
	
	private class ReviewerAssignListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() instanceof JButton) {
				JButton btn = (JButton) e.getSource();
				//Button Action for Submit
				if (btn.getText().equals("Assign")) {
					
				
				} else if (btn.getText().equals("Remove")) {
					
				} else {
					CardLayout c = (CardLayout) myMainPanel.getLayout();
					c.show(myMainPanel, "entry");
				}
			}

		}

	}
}