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

import model.Paper;
import controller.Controller;
/**
 * Holds all components to allow the assigning of subchairs to papers.
 * @author Nick Ames
 * @author Richard Hemingway
 * @author Tim Loverin
 * @author Yeonil Yoo
 * @version 6/3/2014
 */
public class SubAssignPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel lblTitle;

	private JButton btnAssign;
	private JButton btnRemove;
	private JButton btnBack;
	/**
	 * boolean which ensures combobox only initializes once.
	 */
	private boolean test;
	/**
	 * boolean which ensures combobox only initializes once.
	 */
	private boolean ran;

	private JComboBox<String> cmbSubSelectBox;
	private JLabel lblPaperSelect;

	private JScrollPane reviewerPane;
	private JScrollPane paperSelectPane;

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

		lblPaperSelect = new JLabel("Please Select a Paper...");
		lblPaperSelect.setBounds(192, 65, 157, 20);

		// Text Area
		txtSubAssignedPapers = new JTextArea();
		txtSubAssignedPapers.setEditable(false);

		// List
		subAssignPaperList = new JList<String>();
		paperList = new JList<String>();

		// Scroll Panes
		reviewerPane = new JScrollPane();
		reviewerPane.setBounds(10, 93, 172, 203);
		reviewerPane.setViewportView(txtSubAssignedPapers);

		paperSelectPane = new JScrollPane();
		paperSelectPane.setBounds(192, 93, 157, 203);
		paperSelectPane.setViewportView(paperList);


		// Add
		
		add(lblTitle);
		add(btnAssign);
		add(btnRemove);
		add(btnBack);
		add(cmbSubSelectBox);
		add(lblPaperSelect);
		add(reviewerPane);
		add(paperSelectPane);

	}
	/**
	 * Loads subchairs into combobox.
	 */
	public void SubChairs() {
		if (Controller.getAllSubChairs() != null) {
			int size = Controller.getAllSubChairs().size();
			String[] subchairs = new String[size];
			for (int i = 0; i < Controller.getAllSubChairs().size(); i++) {
				subchairs[i] = Controller.getAllSubChairs().get(i).getName();
				if(!ran) {
					cmbSubSelectBox.addItem(subchairs[i]);
				}
			}
		}
		
		ran = true;
		//subAssignPaperList.setListData(subchairs);
	}
	/**
	 * Loads papers into combobox.
	 */
	public void addPapers() {
		int size = 0;
		if (Controller.getAllPapers() != null) {
			for (int i = 0; i < Controller.getAllPapers().size(); i++) {
				if (!Controller.getAllPapers().get(i).getFileName().equals("empty.txt"))
					size++;
			}
			String[] papers = new String[size];
			for (int i = 0; i < papers.length; i++) {
				if (!Controller.getAllPapers().get(i).getFileName().equals("empty.txt"))
					papers[i] = Controller.getAllPapers().get(i).getFileName();
			}
			//if(!test) {
				paperList.setListData(papers);
			//}
		} else {
			paperList.setListData(new String[0]);
		}
			
		test = true;
	//	paperList.setListData(papers);
	} 
	private void updateText() {
		ArrayList<String> temp = new ArrayList<String>();
			temp = Controller.getUserAssignments(cmbSubSelectBox.getSelectedItem().toString());
			txtSubAssignedPapers.setText("");
			for (int i = 0; i < temp.size(); i++) {
				txtSubAssignedPapers.setText(txtSubAssignedPapers.getText() + temp.get(i).toString() + "\n");
  		}
	}
	/**
	 * 
	 * @author Tim Loverin, Nick Names.
	 * @version 6/2/2014
	 */
	private class SubAssignListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() instanceof JButton) {
				JButton btn = (JButton) e.getSource();
				//Button Action for Submit
				if (btn.getText().equals("Assign")) {
					if (cmbSubSelectBox.getSelectedIndex() > 0) {
						String uID = Controller.getUserID(cmbSubSelectBox.getSelectedItem().toString());
						String paperID = Controller.getPaperID(paperList.getSelectedValue());
						if (uID.equals(paperID)) {
							JOptionPane.showMessageDialog(null, "Cannot assign a sub-chair their own paper.", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
						ArrayList<Paper> p = new ArrayList<Paper>();
						for (int i = 0; i < Controller.getUserPapers(cmbSubSelectBox.getSelectedItem().toString()).size(); i++) {
							if (!Controller.getUserPapers(cmbSubSelectBox.getSelectedItem().toString()).get(i).getFileName().equals("empty.txt")) {
								p.add(Controller.getUserPapers(cmbSubSelectBox.getSelectedItem().toString()).get(i));
							}
						}
						if (p.size() < 4 && !Controller.getUserPapers(cmbSubSelectBox.getSelectedItem().toString()).contains(new Paper(new File(paperList.getSelectedValue())))) {
							Controller.assignPaper(paperList.getSelectedValue(),cmbSubSelectBox.getSelectedItem().toString());
							updateText();
						} else {
							JOptionPane.showMessageDialog(null, "Cannot assign more than 4 papers or paper already assigned.", "Error", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Please select a Sub-Chair before assigning.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} else if (btn.getText().equals("Remove")) {
					if (cmbSubSelectBox.getSelectedIndex() > 0) {
						Controller.deAssignPaper(paperList.getSelectedValue(),cmbSubSelectBox.getSelectedItem().toString());
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
	 * @version 6/2/2014
	 */
	class MyItemListener implements ItemListener {
		  // This method is called only if a new item has been selected.
		  public void itemStateChanged(ItemEvent evt) {
		    JComboBox cb = (JComboBox) evt.getSource();

		    Object item = evt.getItem();

		    if (evt.getStateChange() == ItemEvent.SELECTED) {
		      		if(!cb.getSelectedItem().toString().equals("Select a Sub-Chair...")) {
		      			updateText();
		      		} else {
		      			txtSubAssignedPapers.setText("");
		      		}
		      		
		 
		    } else if (evt.getStateChange() == ItemEvent.DESELECTED) {
		      // Item is no longer selected
		    }
		  }
	}

}
