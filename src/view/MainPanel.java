package view;

import java.awt.CardLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.Conference;
/**
 * The main panel contains all other panels and allows for easy switching through a card layout.
 * @author Nick Ames
 * @author Richard Hemingway
 * @author Tim Loverin
 * @author Yeonil Yoo
 * @version 6/3/2014
 */
public class MainPanel extends JPanel implements Observer {
	/**
	 * Standard serial ID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The Conference.
	 */
	private Conference c;
    /**
     * The user in string form.
     */
	private String user;
	
    /**
     * Constructor.		
     * @param c the conference.
     * @param user the user.
     */
    public MainPanel(Conference c, String user) {
    	super(new CardLayout());
		setBounds(0, 5, 444, 390);
        this.c = c;
        this.user = user;
    }
	
    /**
     * Updates the main panel.
     */
    @Override
    public void update (Observable o, Object arg) {
    	System.out.println("I WAS NOTIFIED");
    	
    }
}
