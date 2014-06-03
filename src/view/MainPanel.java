package view;

import java.awt.CardLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.Conference;
/**
 * 
 * @author Tim Loverni, Nick Ames, Yeonil
 * @version 6/2/2014
 */
public class MainPanel extends JPanel implements Observer {
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
	
    @Override
    public void update (Observable o, Object arg) {
    	System.out.println("I WAS NOTIFIED");
    	
    }
}
