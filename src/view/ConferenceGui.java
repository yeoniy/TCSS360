package view;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Conference;

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
    private JPanel loginPanel;
    private ArrayList<Conference> conferenceList;



    public ConferenceGui(ArrayList<Conference> conferences) {
        mainPanel = new MainPanel(null);
        loginPanel = new LoginPanel(conferences);
        this.conferenceList = conferences;
        LoginDialog d = new LoginDialog(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(CENTER.width/2-this.getSize().width/2, CENTER.height/2-this.getSize().height/2);
    }


}
