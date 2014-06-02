package view;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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

public class SubAssignPanel extends JPanel {

	private JLabel lblTitle;

	private JButton btnAssign;
	private JButton btnRemove;
	private JButton btnBack;
	/**
	 * boolean which ensures combobox only initializes once
	 */
	private boolean test;
	/**
	 * boolean which ensures combobox only initializes once
	 */
	private boolean ran;

	private JComboBox<String> cmbSubSelectBox;
	private JComboBox<String> cmbSubAuthorSelectBox;

	private JScrollPane reviewerPane;
	private JScrollPane authorSelectPane;

	private JTextArea txtSubAssignedPapers;

	private JList<String> subAssignPaperList;
	private JList<String> paperList;
	
	private MainPanel myMainPanel;
	
	private SubAssignListener myListener;

	public SubAssignPanel(final MainPanel m) {
		super(null);
		test = false;
		ran = false;
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
		MyItemListener actionListener = new MyItemListener();
		cmbSubSelectBox.addItemListener(actionListener);

		cmbSubAuthorSelectBox = new JComboBox<String>();
		cmbSubAuthorSelectBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Select a paper..."}));
		cmbSubAuthorSelectBox.setBounds(192, 65, 157, 20);

		// Text Area
		txtSubAssignedPapers = new JTextArea();
		txtSubAssignedPapers.setEditable(false);

		// List
		subAssignPaperList = new JList();
		paperList = new JList();

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
	public void SubChairs() {
		int size = Controller.getAllSubChairs().size();
		String[] subchairs = new String[size];
		for (int i = 0; i < Controller.getAllSubChairs().size(); i++) {
			subchairs[i] = Controller.getAllSubChairs().get(i).getName();
			if(!ran) {
				cmbSubSelectBox.addItem(subchairs[i]);
			}
		}
		ran = true;
		//subAssignPaperList.setListData(subchairs);
	}
	public void addPapers() {
		int size = 0;
		for (int i = 0; i < Controller.getAllPapers().size(); i++) {
			if (!Controller.getAllPapers().get(i).getFileName().equals("empty.txt"))
				size++;
		}
		String[] papers = new String[size];
		for (int i = 0; i < papers.length; i++) {
			if (!Controller.getAllPapers().get(i).getFileName().equals("empty.txt"))
				papers[i] = Controller.getAllPapers().get(i).getFileName();
				if(!test) {
					cmbSubAuthorSelectBox.addItem(papers[i]);
				}	
		}
		test = true;
	//	paperList.setListData(papers);
	} 
	
	private class SubAssignListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() instanceof JButton) {
				JButton btn = (JButton) e.getSource();
				//Button Action for Submit
				if (btn.getText().equals("Assign")) {
					ArrayList<Paper> p = new ArrayList<Paper>();
					for (int i = 0; i < Controller.getUserPapers(cmbSubSelectBox.getSelectedItem().toString()).size(); i++) {
						if (!Controller.getUserPapers(cmbSubSelectBox.getSelectedItem().toString()).get(i).getFileName().equals("empty.txt")) {
							p.add(Controller.getUserPapers(cmbSubSelectBox.getSelectedItem().toString()).get(i));
						} else {
							
						}
					}
					if (p.size() < 4) {
						Controller.assignPaper(cmbSubAuthorSelectBox.getSelectedItem().toString(),cmbSubSelectBox.getSelectedItem().toString());
					} else {
						JOptionPane.showMessageDialog(null, "Cannot assign more than 4 papers.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				
				} else if (btn.getText().equals("Remove")) {
					Controller.deAssignPaper(cmbSubAuthorSelectBox.getSelectedItem().toString(),cmbSubSelectBox.getSelectedItem().toString());
				} else {
					CardLayout c = (CardLayout) myMainPanel.getLayout();
					c.show(myMainPanel, "entry");
				}
			}

		}

	}
	class MyItemListener implements ItemListener {
		  // This method is called only if a new item has been selected.
		  public void itemStateChanged(ItemEvent evt) {
		    JComboBox cb = (JComboBox) evt.getSource();

		    Object item = evt.getItem();

		    if (evt.getStateChange() == ItemEvent.SELECTED) {
		      		
		 
		    } else if (evt.getStateChange() == ItemEvent.DESELECTED) {
		      // Item is no longer selected
		    }
		  }
	}

}
