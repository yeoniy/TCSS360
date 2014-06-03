package view;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import model.Paper;
import model.Type;
import controller.Controller;
/**
 * 
 * @author Tim Loverin, Nick Names
 * @version 6/2/2014
 */
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
	/**
	 * boolean to assist with loading authors.
	 */
	private boolean ran;
	private boolean test;
	
	private JButton btnAccept;
	private JButton btnReject;
	private JButton btnView;
	private JButton btnBack;

	private JComboBox<String> cmbProAuthorSelectBox;
	private JComboBox<String> cmbProPaperSelect;

	private JTextArea txtComments;
	
	private MainPanel myMainPanel;
	private ProChairListener myListener;
	/**
	 * constructor.
	 * @param m the main panel
	 */
	public ProChairReviewPanel(final MainPanel m) {
		super(null);
		ran = false;
		test = false;
		myMainPanel = m;
		myListener = new ProChairListener();
		initialize();
		setName("pro");
	}
	/**
	 * initializes the panel.
	 */
	private void initialize() {

		/*
		 * Labels
		 */
		lblViewReviewsFor = new JLabel("View Reviews for:");
		lblViewReviewsFor.setBounds(26, 51, 106, 14);

		lblAverageRating = new JLabel("Average Rating:");
		lblAverageRating.setBounds(26, 107, 90, 14);

		lblRating = new JLabel("RATING");
		lblRating.setBounds(125, 107, 106, 14);

		lblComments = new JLabel("Reviewers comments:");
		lblComments.setBounds(26, 182, 132, 14);

		lblRecommendation = new JLabel("Recommendation:");
		lblRecommendation.setBounds(26, 157, 110, 14);

		lblAcceptanceStatus = new JLabel("Acceptance Status:");
		lblAcceptanceStatus.setBounds(26, 132, 120, 14);

		lblStatus = new JLabel("STATUS");
		lblStatus.setBounds(145, 132, 95, 14);

		lblTitle = new JLabel("Review Papers");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTitle.setBounds(10, 11, 424, 29);

		lblRec = new JLabel("REC");
		lblRec.setBounds(140, 157, 91, 14);

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
		MyItemListener actionListener = new MyItemListener();
		cmbProAuthorSelectBox.addItemListener(actionListener);

		cmbProPaperSelect = new JComboBox<String>();
		cmbProPaperSelect.setModel(new DefaultComboBoxModel<String>(new String[] {"Select a Paper..."}));
		cmbProPaperSelect.setBounds(191, 76, 119, 20);
		MyItemListener2 actionListener2 = new MyItemListener2();
		cmbProPaperSelect.addItemListener(actionListener2);

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
	/**
	 * loads authors into combo box.
	 */
	public void assignAuthors() {
		int size = Controller.getAllAuthors().size();
		String[] authors = new String[size];
		for (int i = 0; i < Controller.getAllAuthors().size(); i++) {
			authors[i] = Controller.getAllAuthors().get(i).getName();
			if(!ran) {
				cmbProAuthorSelectBox.addItem(authors[i]);
			}
		}
		cmbProAuthorSelectBox.setVisible(true);
		ran = true;
		if(Controller.getUserType() == Type.SUBCHAIR) {
			cmbProAuthorSelectBox.setVisible(false);
		}
	}
	public void initPapers() {
		if (Controller.getUserType() == Type.SUBCHAIR) {
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
						cmbProPaperSelect.addItem(Controller.getMyPapers().get(i).getFileName());
					}
			}
			test = true;
		}
	}
	/**
	 * helps load papers into combobox
	 * @param s the author string name to assign papers to.
	 */
	private void assignPapers(String s) {
		cmbProPaperSelect.removeAllItems();
		for (int i = 0; i < Controller.getUserPapers(s).size(); i++) {
			if(!Controller.getUserPapers(s).get(i).getFileName().equals("empty.txt")) {
				cmbProPaperSelect.addItem(Controller.getUserPapers(s).get(i).getFileName());
			}
		}
	}
	/**
	 * 
	 * @author Tim Loverin, Nick Names
	 * @version 6/2/2014
	 */
	private class ProChairListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() instanceof JButton) {
				JButton btn = (JButton) e.getSource();
				//Button Action for Submit
				if (btn.getText().equals("ACCEPT")) {
					if (Controller.getUserType() == Type.PROCHAIR || Controller.getUserType() == Type.ADMIN) {
						Controller.setPCrec(cmbProPaperSelect.getSelectedItem().toString() , 2);
						lblStatus.setText("ACCEPTED");
					} else {
						Controller.setSCrec(cmbProPaperSelect.getSelectedItem().toString() , 2);
						lblRec.setText("ACCEPT");
					}
				} else if (btn.getText().equals("REJECT")) {
					if (Controller.getUserType() == Type.PROCHAIR || Controller.getUserType() == Type.ADMIN) {
						Controller.setPCrec(cmbProPaperSelect.getSelectedItem().toString() , 1);
						lblStatus.setText("REJECTED");
					} else {
						Controller.setSCrec(cmbProPaperSelect.getSelectedItem().toString() , 1);
						lblRec.setText("REJECT");
					}
				} else if (btn.getText().equals("View")) {
					JFrame frame = new JFrame(cmbProPaperSelect.getSelectedItem().toString());
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					frame.setSize(500,500);
					frame.getContentPane().setLayout(new FlowLayout());
					List list = new List();
					frame.getContentPane().add(list);
					try {
					File file = new File("Resources\\" + cmbProPaperSelect.getSelectedItem().toString());
					FileReader fr = new FileReader(file);
					BufferedReader br = new BufferedReader(fr);
					String line = "";
					    while((line = br.readLine()) != null)    {  
					            list.add(line);
					    }
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
		    	if(!cb.getSelectedItem().toString().equals("Select an Author...")) {
		    		assignPapers(cb.getSelectedItem().toString());
		    	} else {
		    		
		    	}
		    } else if (evt.getStateChange() == ItemEvent.DESELECTED) {
		      // Item is no longer selected
		    }
		  }
	}
	class MyItemListener2 implements ItemListener {
		  // This method is called only if a new item has been selected.
		  public void itemStateChanged(ItemEvent evt) {
		    JComboBox cb = (JComboBox) evt.getSource();

		    Object item = evt.getItem();
		    int x = 0;
		    int y = 0;
		    if (evt.getStateChange() == ItemEvent.SELECTED) {
		    	//if(Controller.getUserType() == Type.PROCHAIR || Controller.getUserType() == Type.ADMIN) {
		    		if(!cb.getSelectedItem().toString().equals("Select a Paper...")) {
		    			x = Controller.getPCrec(cb.getSelectedItem().toString());
		      			y = Controller.getSCrec(cb.getSelectedItem().toString());
		      			if (x == 0) {
		      				lblStatus.setText("Pending");
		      			} else if (x == 1) {
		      				lblStatus.setText("REJECTED");
		      			} else if (x == 2) {
		      				lblStatus.setText("ACCEPTED");
		      			}
		      			if (y == 0) {
		      				lblRec.setText("Pending");
		      			}else if (y == 1) {
		      				lblRec.setText("REJECT");
		      			}else if (y == 2) {
		      				lblRec.setText("ACCEPT");
		      			}
		    		} else {
		    		
		    		}
		    	
		    } else if (evt.getStateChange() == ItemEvent.DESELECTED) {
		      // Item is no longer selected
		    }
		  }
	}
}
