package view;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.Controller;
import model.Author;
import model.Conference;
import view.menu.MenuBar;

/**
 * Created by Yeonil on 4/29/14.
 */
public class ConferenceGui extends JFrame {
	
	/**
	 * Default serial ID.
	 */
	private static final long serialVersionUID = 1L;

	private static final Dimension CENTER = Toolkit.getDefaultToolkit().getScreenSize();
	/**
	 * Title of the Window.
	 */
	private static final String FRAME_TITLE = "Conference Software";
	
    private JPanel mainPanel;
    private LoginPanel loginPanel;
    private MenuBar menuBar;

    public ConferenceGui() {
        //mainPanel = new MainPanel();
        loginPanel = new LoginPanel();
        LoginDialog d = new LoginDialog(this);
        mainPanel = new MainPanel(loginPanel.getConference(), loginPanel.getUsername());

        //TODO menuBar creates before User logs in.
        //menuBar = new MenuBar(new Controller(), new Author(loginPanel.getUser()));
        //Testing purpose
        menuBar = new MenuBar(new Controller(), new Author("", loginPanel.getUsername(), ""));
        setJMenuBar(menuBar);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(CENTER.width/2-this.getSize().width/2, CENTER.height/2-this.getSize().height/2);
    }


}
