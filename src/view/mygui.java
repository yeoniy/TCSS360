package view;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.CardLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;
import java.awt.TextArea;
import java.awt.TextField;
import javax.swing.JSpinner;

public class mygui {

	private JFrame frame;
	
	private JPanel cards;
	private CardLayout c;
	private JTextField textField;
	private JTextField txtUser;
	private JTextField txtThePaper;
	private JTextField txtThePaper_2;
	private JTextField textField_3;
	
	private JPanel Statspanel;
	private JPanel Entrypanel;
	private JPanel Paperpanel;
	private JPanel ChairAssign;
	private JPanel ChairReview;
	private JPanel ReviewerView;

	/**
	 * Launch the application.
	 */
	public static void rungui() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mygui window = new mygui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mygui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		cards = new JPanel(new CardLayout());
		frame.setBounds(100, 100, 450, 442);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		frame.getContentPane().add(cards);
		Entrypanel = new JPanel();
		Entrypanel.setName("Welcome");
		Paperpanel = new JPanel();
		Paperpanel.setName("p1");
		Statspanel = new JPanel();
		Paperpanel.setName("s1");
		ChairAssign = new JPanel();
		cards.add(Entrypanel);
		Entrypanel.setLayout(null);
		c = (CardLayout) cards.getLayout();
		c.show(cards, Entrypanel.getName());
		JButton btnNewButton = new JButton("Stats");
		btnNewButton.setBounds(216, 199, 85, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AssignPane(Statspanel);
			}
		});
		Entrypanel.add(btnNewButton);
		Entrypanel.setLayout(null);
		JButton btnSubmitPaper = new JButton("Submit Paper");
		btnSubmitPaper.setBounds(75, 199, 131, 23);
		btnSubmitPaper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AssignPane(Paperpanel);
			}
		});
		Entrypanel.add(btnSubmitPaper);
		JLabel lblNewLabel = new JLabel(LoginPanel.getUsername());
		lblNewLabel.setBounds(10, 9, 71, 14);
		Entrypanel.add(lblNewLabel);
		
		JLabel lblCurrentDeadline = new JLabel("Current Deadline:");
		lblCurrentDeadline.setBounds(101, 147, 101, 14);
		Entrypanel.add(lblCurrentDeadline);
		
		JLabel lblTheDeadline = new JLabel("THE DEADLINE");
		lblTheDeadline.setBounds(206, 147, 85, 14);
		Entrypanel.add(lblTheDeadline);
		cards.add(Paperpanel, "p1");
		Paperpanel.setLayout(null);
		
		JButton button_1 = new JButton("< Back");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AssignPane(Entrypanel);
			}
		});
		button_1.setBounds(0, 381, 89, 23);
		Paperpanel.add(button_1);
		
		JLabel lblSubmitAPaper = new JLabel("Submit a Paper");
		lblSubmitAPaper.setBounds(175, 11, 96, 14);
		Paperpanel.add(lblSubmitAPaper);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(242, 192, 89, 23);
		Paperpanel.add(btnSubmit);
		
		TextField textField_1 = new TextField();
		textField_1.setBounds(72, 192, 164, 23);
		Paperpanel.add(textField_1);
		
		JLabel lblCurrentPapers = new JLabel("Current Papers");
		lblCurrentPapers.setBounds(43, 51, 89, 14);
		Paperpanel.add(lblCurrentPapers);
		
		JLabel lblPaperOne = new JLabel("Paper One");
		lblPaperOne.setBounds(43, 73, 106, 14);
		Paperpanel.add(lblPaperOne);
		
		JLabel lblPaperTwo = new JLabel("Paper Two");
		lblPaperTwo.setBounds(43, 98, 106, 14);
		Paperpanel.add(lblPaperTwo);
		
		JLabel lblPaperThree = new JLabel("Paper Three");
		lblPaperThree.setBounds(43, 123, 89, 14);
		Paperpanel.add(lblPaperThree);
		
		JLabel lblPaperFour = new JLabel("Paper Four");
		lblPaperFour.setBounds(43, 148, 89, 14);
		Paperpanel.add(lblPaperFour);
		
		TextField textField_2 = new TextField();
		textField_2.setBounds(155, 64, 164, 23);
		Paperpanel.add(textField_2);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(325, 64, 28, 26);
		Paperpanel.add(comboBox_3);
		
		JButton btnNewButton_4 = new JButton("Remove Paper");
		btnNewButton_4.setBounds(190, 94, 129, 23);
		Paperpanel.add(btnNewButton_4);
		
		JLabel lblNewLabel_7 = new JLabel("Remove Paper");
		lblNewLabel_7.setBounds(155, 51, 89, 14);
		Paperpanel.add(lblNewLabel_7);
		cards.add(Statspanel);
		Statspanel.setLayout(null);
		
		JButton button = new JButton("< Back");
		button.setBounds(0, 381, 89, 23);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AssignPane(Entrypanel);
			}
		});
		Statspanel.add(button);
		
		JLabel lblStatistics = new JLabel("Statistics");
		lblStatistics.setBounds(191, 11, 54, 14);
		Statspanel.add(lblStatistics);
		
		textField = new JTextField();
		textField.setBounds(29, 47, 117, 20);
		Statspanel.add(textField);
		textField.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(156, 47, 28, 20);
		Statspanel.add(comboBox);
		
		JLabel lblSelectPaper = new JLabel("Select Paper");
		lblSelectPaper.setBounds(29, 29, 89, 14);
		Statspanel.add(lblSelectPaper);
		
		JButton btnEnter = new JButton("Enter");
		btnEnter.setBounds(29, 71, 89, 23);
		Statspanel.add(btnEnter);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(246, 63, 147, 14);
		Statspanel.add(progressBar);
		
		JLabel lblProgress = new JLabel("Progress...");
		lblProgress.setBounds(246, 50, 89, 14);
		Statspanel.add(lblProgress);
		
		JLabel label = new JLabel("0%");
		label.setBounds(244, 75, 28, 14);
		Statspanel.add(label);
		
		JLabel label_1 = new JLabel("100%");
		label_1.setBounds(364, 75, 49, 14);
		Statspanel.add(label_1);
		
		TextArea textArea = new TextArea();
		textArea.setBounds(13, 215, 380, 160);
		Statspanel.add(textArea);
		
		JLabel lblPaperComments = new JLabel("Paper Comments");
		lblPaperComments.setBounds(13, 191, 105, 14);
		Statspanel.add(lblPaperComments);
		
		JLabel lblPaperStatus = new JLabel("Paper Status:");
		lblPaperStatus.setBounds(228, 110, 89, 14);
		Statspanel.add(lblPaperStatus);
		
		JLabel lblNewLabel_1 = new JLabel("STATUS");
		lblNewLabel_1.setBounds(306, 110, 101, 14);
		Statspanel.add(lblNewLabel_1);
		
		JLabel lblAverageRating_1 = new JLabel("Average Rating:");
		lblAverageRating_1.setBounds(29, 132, 89, 14);
		Statspanel.add(lblAverageRating_1);
		
		JLabel lblRating_1 = new JLabel("RATING");
		lblRating_1.setBounds(127, 132, 46, 14);
		Statspanel.add(lblRating_1);
		cards.add(ChairAssign);
		ChairAssign.setLayout(null);
		
		JButton button_2 = new JButton("< Back");
		button_2.setBounds(0, 381, 89, 23);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AssignPane(Entrypanel);
			}
		});
		ChairAssign.add(button_2);
		
		txtUser = new JTextField();
		txtUser.setText("REVIEWER/SUBCHAIR");
		txtUser.setBounds(37, 65, 112, 20);
		ChairAssign.add(txtUser);
		txtUser.setColumns(10);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(154, 65, 28, 20);
		ChairAssign.add(comboBox_1);
		
		JButton btnAssign = new JButton("Assign");
		btnAssign.setBounds(164, 96, 89, 23);
		ChairAssign.add(btnAssign);
		
		JLabel lblNewLabel_2 = new JLabel("assigned paper1");
		lblNewLabel_2.setBounds(37, 96, 112, 14);
		ChairAssign.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("assigned paper2");
		lblNewLabel_3.setBounds(37, 121, 112, 14);
		ChairAssign.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("assigned paper3");
		lblNewLabel_4.setBounds(37, 146, 112, 14);
		ChairAssign.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("assigned paper4");
		lblNewLabel_5.setBounds(37, 174, 112, 14);
		ChairAssign.add(lblNewLabel_5);
		
		txtThePaper = new JTextField();
		txtThePaper.setText("THE PAPER");
		txtThePaper.setBounds(251, 65, 102, 20);
		ChairAssign.add(txtThePaper);
		txtThePaper.setColumns(10);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(358, 65, 28, 20);
		ChairAssign.add(comboBox_2);
		
		ReviewerView = new JPanel();
		cards.add(ReviewerView, "name_5705518926843732");
		ReviewerView.setLayout(null);
		
		JButton button_4 = new JButton("< Back");
		button_4.setBounds(0, 381, 89, 23);
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AssignPane(Entrypanel);
			}
		});
		ReviewerView.add(button_4);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(23, 37, 117, 20);
		ReviewerView.add(textField_3);
		
		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setBounds(150, 37, 28, 20);
		ReviewerView.add(comboBox_5);
		
		JLabel label_2 = new JLabel("Select Paper");
		label_2.setBounds(23, 21, 71, 14);
		ReviewerView.add(label_2);
		
		TextArea textArea_2 = new TextArea();
		textArea_2.setBounds(32, 188, 380, 160);
		ReviewerView.add(textArea_2);
		
		JLabel lblComments = new JLabel("Comments:");
		lblComments.setBounds(30, 169, 64, 14);
		ReviewerView.add(lblComments);
		
		JButton btnSubmitReview = new JButton("Submit Review");
		btnSubmitReview.setBounds(167, 354, 103, 23);
		ReviewerView.add(btnSubmitReview);
		
		JLabel lblPaperRating = new JLabel("Paper Rating:");
		lblPaperRating.setBounds(23, 78, 71, 14);
		ReviewerView.add(lblPaperRating);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(92, 75, 48, 20);
		ReviewerView.add(spinner);
		
		ChairReview = new JPanel();
		cards.add(ChairReview, "name_20305945684518");
		ChairReview.setLayout(null);
		
		JButton button_5 = new JButton("< Back");
		button_5.setBounds(0, 381, 89, 23);
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AssignPane(Entrypanel);
			}
		});
		ChairReview.add(button_5);
		
		txtThePaper_2 = new JTextField();
		txtThePaper_2.setText("THE PAPER");
		txtThePaper_2.setBounds(31, 64, 132, 20);
		ChairReview.add(txtThePaper_2);
		txtThePaper_2.setColumns(10);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setBounds(173, 64, 28, 20);
		ChairReview.add(comboBox_4);
		
		JLabel lblViewReviewsFor = new JLabel("View Reviews for");
		lblViewReviewsFor.setBounds(31, 39, 106, 14);
		ChairReview.add(lblViewReviewsFor);
		
		JLabel lblAverageRating = new JLabel("Average Rating:");
		lblAverageRating.setBounds(28, 114, 79, 14);
		ChairReview.add(lblAverageRating);
		
		JLabel lblRating = new JLabel("RATING");
		lblRating.setBounds(117, 114, 46, 14);
		ChairReview.add(lblRating);
		
		JLabel lblReviewersComments = new JLabel("Reviewers comments:");
		lblReviewersComments.setBounds(26, 179, 132, 14);
		ChairReview.add(lblReviewersComments);
		
		JButton btnNewButton_1 = new JButton("View Paper");
		btnNewButton_1.setBounds(224, 63, 89, 23);
		ChairReview.add(btnNewButton_1);
		
		JLabel lblRecommendationsToProgram = new JLabel("RECOMMENDATIONS TO PROGRAM CHAIR");
		lblRecommendationsToProgram.setBounds(190, 114, 218, 14);
		ChairReview.add(lblRecommendationsToProgram);
		
		JButton btnNewButton_2 = new JButton("ACCEPT");
		btnNewButton_2.setBounds(189, 140, 89, 23);
		ChairReview.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("REJECT");
		btnNewButton_3.setBounds(300, 140, 89, 23);
		ChairReview.add(btnNewButton_3);
		
		TextArea textArea_1 = new TextArea();
		textArea_1.setBounds(28, 209, 380, 160);
		ChairReview.add(textArea_1);
		
		JLabel lblNewLabel_6 = new JLabel("Acceptance Status:");
		lblNewLabel_6.setBounds(26, 144, 106, 14);
		ChairReview.add(lblNewLabel_6);
		
		JLabel lblStatus = new JLabel("STATUS");
		lblStatus.setBounds(127, 144, 46, 14);
		ChairReview.add(lblStatus);
	}
	private void AssignPane(JPanel the_panel) {
		Statspanel.setVisible(false);
		Entrypanel.setVisible(false);
		Paperpanel.setVisible(false);
		ChairAssign.setVisible(false);
		ChairReview.setVisible(false);
		ReviewerView.setVisible(false);
		the_panel.setVisible(true);
	}
}



