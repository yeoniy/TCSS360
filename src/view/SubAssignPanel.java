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

public class SubAssignPanel extends JPanel {

	private JLabel lblTitle;

	private JButton btnAssign;
	private JButton btnRemove;
	private JButton btnBack;

	private JComboBox<String> cmbSubSelectBox;
	private JComboBox<String> cmbSubAuthorSelectBox;

	private JScrollPane reviewerPane;
	private JScrollPane authorSelectPane;

	private JTextArea txtSubAssignedPapers;

	private JList subAssignPaperList;
	
	private MainPanel myMainPanel;
	
	private SubAssignListener myListener;

	public SubAssignPanel(final MainPanel m) {
		super(null);
		myListener = new SubAssignListener();
		myMainPanel = m;
		initialize();
		setName("subAssign");
	}

	private void initialize() {

		/*
		 * Labels
		 */
		lblTitle = new JLabel("Assign Sub-Program Chair");
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
		cmbSubSelectBox = new JComboBox<String>();
		cmbSubSelectBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Select a Sub-Chair..."}));
		cmbSubSelectBox.setBounds(10, 65, 172, 20);

		cmbSubAuthorSelectBox = new JComboBox<String>();
		cmbSubAuthorSelectBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Select an Author..."}));
		cmbSubAuthorSelectBox.setBounds(192, 65, 157, 20);

		// Text Area
		txtSubAssignedPapers = new JTextArea();
		txtSubAssignedPapers.setEditable(false);

		// List
		subAssignPaperList = new JList();

		// Scroll Panes
		reviewerPane = new JScrollPane();
		reviewerPane.setBounds(10, 93, 172, 203);
		reviewerPane.setViewportView(txtSubAssignedPapers);

		authorSelectPane = new JScrollPane();
		authorSelectPane.setBounds(192, 93, 157, 203);
		authorSelectPane.setViewportView(subAssignPaperList);


		// Add
		
		add(lblTitle);
		add(btnAssign);
		add(btnRemove);
		add(btnBack);
		add(cmbSubSelectBox);
		add(cmbSubAuthorSelectBox);
		add(reviewerPane);
		add(authorSelectPane);

	}
	
	private class SubAssignListener implements ActionListener {

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