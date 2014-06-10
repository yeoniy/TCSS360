package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import model.Paper;
import controller.Controller;
/**
 * Contains all the components to allow the user to submit/remove papers to the conference.
 * @author Nick Ames
 * @author Richard Hemingway
 * @author Tim Loverin
 * @author Yeonil Yoo
 * @version 6/3/2014
 */
public class PaperPanel extends JPanel {

	/**
	 * Labels and buttons for paper panel
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblTitle;
	private JLabel lblCurrentPapers;

	private JButton btnBack;
	private JButton btnSubmit;
	private JButton btnCheckStatus;
	private JButton btnRemove;

	private MainPanel myMainPanel;
	
	private PaperPanelListener myListener;
	
	private JScrollPane paperScrollPane;

	private JTextPane numberPane;

	private JList<String> paperList;
	/**
	 * Contructor for the PaperPanel
	 * @param m main panel
	 */
	public PaperPanel(final MainPanel m) {
		super(null);
		myMainPanel = m;
		myListener = new PaperPanelListener();
		initialize();
		setName("paper");
	}
	/**
	 * initializes the PaperPanel.
	 */
	private void initialize() {

		/*
		 * Labels
		 */
		lblTitle = new JLabel("Submit a Paper");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 11, 424, 29);

		lblCurrentPapers = new JLabel("Current Papers");
		lblCurrentPapers.setBackground(Color.WHITE);
		lblCurrentPapers.setOpaque(true);
		lblCurrentPapers.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentPapers.setBackground(new Color(255, 255, 255));

		/*
		 * Buttons
		 */

		// Back Button
		btnBack = new JButton("< Back");
		btnBack.setBounds(10, 356, 89, 23);
		btnBack.addActionListener(myListener);

		// Submit Button
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(36, 275, 89, 23);
		btnSubmit.addActionListener(myListener);

		// Remove Paper Button
		btnRemove = new JButton("Remove Paper");
		btnRemove.setBounds(278, 275, 129, 23);
		btnRemove.addActionListener(myListener);
		
		// Check Stats Button
		btnCheckStatus = new JButton("Check Status");
		btnCheckStatus.setBounds(135, 275, 133, 23);
		btnCheckStatus.addActionListener(myListener);

		/*
		 * Scroll Pane
		 */

		paperScrollPane = new JScrollPane();
		paperScrollPane.setBackground(Color.WHITE);
		paperScrollPane.setBounds(36, 76, 371, 188);

		numberPane = new JTextPane();
		numberPane.setEditable(false);
		MutableAttributeSet set = new SimpleAttributeSet();
		StyleConstants.setLineSpacing(set, (float) 0);
		numberPane.setParagraphAttributes(set, true);
		numberPane.setText("1:\r\n2:\r\n3:\r\n4:");

		paperList = new JList<String>();
		paperScrollPane.setColumnHeaderView(lblCurrentPapers);
		paperScrollPane.setRowHeaderView(numberPane);
		paperScrollPane.setViewportView(paperList);
		
		add(lblTitle);
		add(btnBack);
		add(btnSubmit);
		add(btnRemove);
		add(paperScrollPane);
	}
	/**
	 * Loads Papers into paperlist.
	 */
	public void addPapers() {
		int size = 0;
		if (Controller.getMyPapers() != null) {
			for (int i = 0; i < Controller.getMyPapers().size(); i++) {
				if (!Controller.getMyPapers().get(i).getFileName().equals("empty.txt"))
					size++;
			}
			String[] papers = new String[size];
			for (int i = 0; i < papers.length; i++) {
				if (!Controller.getMyPapers().get(i).getFileName().equals("empty.txt"))
					papers[i] = Controller.getMyPapers().get(i).getFileName();
			}
			paperList.setListData(papers);
		}
		
		
	} 
	/**
	 * Private inner class for paper panel listener
	 * @author Tim Loverin, Nick Ames.
	 * @version 6/2/2014
	 */
	private class PaperPanelListener implements ActionListener{
		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() instanceof JButton) {
				JButton btn = (JButton) e.getSource();
				//Button Action for Submit
				if (btn.getText().equals("Submit")) {
					Date d = Controller.getCurrentConference().getDate();
					Date today = new Date();
					today.setYear(today.getYear() + 1900);
					
					// DONT TOUCH
					if (today.after(d)) {
						JOptionPane.showMessageDialog(null, "Cannot submit a paper. The conference is closed.", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if (paperList.getModel().getSize() < 4) {
						JFileChooser chooser = new JFileChooser();
						int i = chooser.showOpenDialog(null);
						if (i == JFileChooser.APPROVE_OPTION) {
							// Add the paper to the users paper list
							Paper p = new Paper(chooser.getSelectedFile());
							Controller.updateStats(p.getFileName());
                            //TODO creates files to resources
							Controller.getMyPapers().add(p);
							Controller.addtoRecs(p.getFileName());
							JOptionPane.showMessageDialog(null, p.getFileName() + " was uploaded successfully!");
							addPaper(p.getFileName());
							Controller.addPaper(p.getFileName(), paperList.getModel().getSize()-1);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Cannot add more than 4 papers.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} else if (btn.getText().equals("Remove Paper")) {
					int i = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove the paper(s)?", "Remove Paper",  JOptionPane.INFORMATION_MESSAGE);
					if (i == JOptionPane.YES_OPTION)
						Controller.removefromRecs(paperList.getSelectedValue());
						Controller.updateStatsRemove(paperList.getSelectedValue());
						removePaper(paperList.getSelectedIndices());	
				} else {
					CardLayout c = (CardLayout) myMainPanel.getLayout();
					c.show(myMainPanel, "entry");
				}
			}
		}
	}
	
	/**
	 * removes a papers.
	 * @param p.
	 */
	private void removePaper(int[] p) {
		String[] s = new String[paperList.getModel().getSize() - p.length];
		for (int i = 0; i < p.length; i++) {
			for (int j = 0; j < Controller.getMyPapers().size(); j++) {
				if (paperList.getModel().getElementAt(i).equals(Controller.getMyPapers().get(j).getFileName())) {
					//System.out.print(Controller.getMyPapers().get(j) +" " + j);
					Controller.removePaper(j);
					Controller.getMyPapers().remove(j);
					Controller.getAllPapers().remove(j);
				}
			}
		}
		for (int i = 0; i < s.length; i++) {
			s[i] = paperList.getModel().getElementAt(i);
		}
		paperList.setListData(s);
	}
	/**
	 * adds a paper.
	 * @param a.
	 */
	private void addPaper(String a) {
		String[] s = new String[paperList.getModel().getSize() + 1];
		int i;
		for (i = 0; i < paperList.getModel().getSize(); i++) {
			s[i] = paperList.getModel().getElementAt(i);
		}
		s[i] = a;
		paperList.setListData(s);
		//System.out.println(Arrays.toString(Controller.getMyPapers().toArray()));
	}
}


