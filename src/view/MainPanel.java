package view;

import java.awt.CardLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.Conference;

public class MainPanel extends JPanel implements Observer {
	private Conference c;
    private String user;
	
	

    public MainPanel(Conference c, String user) {
    	super(new CardLayout());
		setBounds(0, 21, 444, 390);
        this.c = c;
        this.user = user;
    }
	
    @Override
    public void update (Observable o, Object arg) {
    	System.out.println("I WAS NOTIFIED");
    	
    }
}
