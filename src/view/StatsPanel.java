package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

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
	
	private JComboBox<String> cmbPaperBox;
	
	private JProgressBar progressBar;
	
	private JTextArea txtComments;
	
	private MainPanel myMainPanel;
	
	private StatsListener myListener;
	
	public StatsPanel(final MainPanel m) {
		super(null);
		myMainPanel = m;
		myListener = new StatsListener();
		initialize();
		setName("stats");
	}

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
		
		// Combo Box
		cmbPaperBox = new JComboBox<String>();
		cmbPaperBox.setBounds(29, 67, 132, 20);
		
		//Progress Bar
		progressBar = new JProgressBar();
		progressBar.setBounds(201, 67, 157, 20);
		
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
	
	private class StatsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() instanceof JButton) {
				JButton btn = (JButton) e.getSource();
				//Button Action for Submit
				if (btn.getText().equals("View")) {
					
				
				} else {
					CardLayout c = (CardLayout) myMainPanel.getLayout();
					c.show(myMainPanel, "entry");
				}
			}

		}

	}

}
