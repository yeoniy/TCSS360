package view;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.Conference;

/**
 * Created by Yeonil on 4/29/14.
 */
public class MainPanel extends JPanel implements Observer{
    private Conference c;
    private String user;


    @Override
    public void update (Observable o, Object arg) {
    	System.out.println("I WAS NOTIFIED");
    	
    }

    public MainPanel(Conference c, String user) {
        this.c = c;
        this.user = user;
    }
}
