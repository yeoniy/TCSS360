package view;

import java.awt.CardLayout;
import java.awt.Color;
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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import view.SubAssignPanel.MyItemListener;

import controller.Controller;
/**
 * 
 * @author Tim Loverin, Nick Ames.
 * @version 6/2/2014.
 */
public class StatsPanel extends JPanel {
	
	private JLabel lbl0;
	private JLabel lbl100;
	private JLabel lblTitle;
	private JLabel lblSelectPaper;
	private JLabel lblProgress;
	private JLabel lblAverageRating;
	private JLabel lblRating;
	private JLabel lblPaperStatus;
	private JLabel lblStatus;
	private JLabel lblPaperComments;
	
	private JButton btnView;
	private JButton btnBack;
	/**
	 * boolean which prevents multiple instances of the same text file in the comboboxes.
	 */
	private boolean test;
	
	private JComboBox<String> cmbPaperBox;
	private JList<String> paperList;
	
	private JProgressBar progressBar;
	
	private JTextArea txtComments;
	
	private MainPanel myMainPanel;
	
	private StatsListener myListener;
	/**
	 * Constructor.
	 * @param m main panel.
	 */
	public StatsPanel(final MainPanel m) {
		super(null);
		test = false;
		myMainPanel = m;
		myListener = new StatsListener();
		initialize();
		setName("stats");
	}
	/**
	 * initializes the StatsPanel.
	 */
	private void initialize() {
		
		/*
		 * Labels
		 */
		lblSelectPaper = new JLabel("Select Paper:");
		lblSelectPaper.setBounds(29, 51, 100, 14);
		
		lblProgress = new JLabel("Progress...");
		lblProgress.setBounds(201, 51, 100, 14);
		
		lbl0 = new JLabel("0%");
		lbl0.setBounds(201, 98, 28, 14);
		
		lbl100 = new JLabel("100%");
		lbl100.setBounds(329, 98, 40, 14);
		
		lblPaperComments = new JLabel("Paper Comments");
		lblPaperComments.setBounds(29, 157, 100, 14);
		
		lblPaperStatus = new JLabel("Paper Status:");
		lblPaperStatus.setBounds(201, 132, 85, 14);
		
		lblStatus = new JLabel("Awaiting Review");
		lblStatus.setForeground(Color.RED);
		lblStatus.setBounds(290, 132, 136, 14);
		
		lblAverageRating = new JLabel("Average Rating:");
		lblAverageRating.setBounds(29, 132, 89, 14);
		
		lblRating = new JLabel("RATING");
		lblRating.setBounds(125, 132, 46, 14);
		
		lblTitle = new JLabel("Paper Stats");
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
		
		// Enter
		btnView = new JButton("View");
		btnView.setBounds(29, 98, 132, 23);
		btnView.addActionListener(myListener);
		
		/*
		 * Others
		 */
		paperList = new JList<String>();
		
		// Combo Box
		cmbPaperBox = new JComboBox<String>();
		cmbPaperBox.setBounds(29, 67, 132, 20);
		MyItemListener actionListener = new MyItemListener();
		cmbPaperBox.addItemListener(actionListener);
		
		//Progress Bar
		progressBar = new JProgressBar();
		progressBar.setBounds(201, 67, 157, 20);
		progressBar.setMinimum(0);
		progressBar.setMaximum(100);
		
		// Text Area
		txtComments = new JTextArea();
		txtComments.setBounds(29, 177, 380, 160);
		
		// Add
		
		add(lblTitle);
		add(lbl0);
		add(lbl100);
		add(lblSelectPaper);
		add(lblProgress);
		add(lblAverageRating);
		add(lblRating);
		add(lblPaperStatus);
		add(lblStatus);
		add(lblPaperComments);
		add(btnView);
		add(btnBack);
		add(cmbPaperBox);
		add(progressBar);
		add(txtComments);
	}
	/**
	 * adds papers to the comboBox.
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
					cmbPaperBox.addItem(Controller.getMyPapers().get(i).getFileName());
				}
		}
		test = true;
		paperList.setListData(papers);
	} 
	/**
	 * ActionListener for the statspanel.
	 * 
	 * @author Tim Loverin Nick Ames.
	 * @version 6/2/2014
	 */
	private class StatsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() instanceof JButton) {
				JButton btn = (JButton) e.getSource();
				//Button Action for Submit
				if (btn.getText().equals("View")) {
					JFrame frame = new JFrame(cmbPaperBox.getSelectedItem().toString());
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					frame.setSize(500,500);
					frame.setLayout(new FlowLayout());
					List list = new List();
					frame.add(list);
					try {
					File file = new File("Resources\\" + cmbPaperBox.getSelectedItem().toString());
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
		    int x = 0;
		    int y = 0;
		    if (evt.getStateChange() == ItemEvent.SELECTED) {
		      		x = Controller.getPCrec(cb.getSelectedItem().toString());
		      		y = Controller.getSCrec(cb.getSelectedItem().toString());
		      		if (x == 0) {
		      			lblStatus.setText("Awaiting Review");
		      			if (y == 1 || y == 2) {
		      				progressBar.setValue(66);
		      			}
		      		} else if (x == 1) {
		      			lblStatus.setText("Paper Rejected");
		      		} else if ( x == 2) {
		      			lblStatus.setText("Paper Accepted");
		      			progressBar.setValue(100);
		      		}
		 
		    } else if (evt.getStateChange() == ItemEvent.DESELECTED) {
		      // Item is no longer selected
		    }
		  }
	}

}
