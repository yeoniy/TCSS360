package view;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import model.Author;
import view.menu.MenuBar;
import controller.Controller;

/**
 * 
 * @author Tim Loverin, Nick Ames, Yeonil
 * @version 6/2/2014
 */
public class ConferenceGui extends JFrame {
	/**
	 * Default serial ID.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Dimension of screen.
	 */
	private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	/**
	 * Title of the Window.
	 */
	private static final String FRAME_TITLE = "Conference";
	/**
	 * ArrayList of observers.
	 */
	private ArrayList<JPanel> observers;
	/**
	 * the main panel
	 */
    private static MainPanel mainPanel;
    /**
     * Login Panel.
     */
    private LoginPanel loginPanel;
    /**
     * The menu bar.
     */
    private static MenuBar menuBar;
    /**
     * Constructor
     */
    public ConferenceGui() {
    	super("Conference 1.0");
        initialize();
        setVisible(true);
	}
    /**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setTitle("Conference");
		this.setResizable(false);
		
		this.setBounds(100, 100, 450, 442);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setLocation(SCREEN_SIZE.width/2 - 300, SCREEN_SIZE.height/2 - 300);
		//mainPanel = new MainPanel();
        LoginDialog d = new LoginDialog(this);
        mainPanel = new MainPanel(null, null);
		observers = new ArrayList<JPanel>();

		// Add all the views that need updating on data changes
		Controller.observers.add(mainPanel);
		//TODO menuBar creates before User logs in.
		//menuBar = new MenuBar(new Controller(), new Author(loginPanel.getUser()));
		//Testing purpose
		menuBar = new MenuBar(new Controller(), new Author("", loginPanel.getUsername(), ""));
		setJMenuBar(menuBar);

		//TODO menuBar creates before User logs in.
		//menuBar = new MenuBar(new Controller(), new Author(loginPanel.getUser()));
		//Testing purpose
		menuBar = new MenuBar(new Controller(), new Author("", loginPanel.getUsername(), ""));
		setJMenuBar(menuBar);
		
		
		mainPanel.add(new EntryPanel(mainPanel), "entry");
		mainPanel.add(new PaperPanel(mainPanel), "paper");
		mainPanel.add(new ProChairReviewPanel(mainPanel), "pro");
		mainPanel.add(new ReviewerAssignPanel(mainPanel), "reviewerAssign");
		mainPanel.add(new ReviewerPanel(mainPanel), "reviewer");
		mainPanel.add(new StatsPanel(mainPanel), "stats");
		mainPanel.add(new SubAssignPanel(mainPanel), "subAssign");
		
		CardLayout c = (CardLayout) mainPanel.getLayout();
		c.show(mainPanel, "entry");
		
		this.getContentPane().add(mainPanel);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 444, 21);
		this.getContentPane().add(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);

	}
    /**
     * Starts the conference.
     */
	public static void startConf() {
		Component[] c = mainPanel.getComponents();
		for (Component a : c) {
			if (a instanceof EntryPanel) {
				((EntryPanel) a).updateUserInformation();
				break;
			}
		}
	}

}
