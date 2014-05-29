package view;
import javax.swing.*;

import model.Conference;
import model.*;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Yeonil on 4/29/14.
 */
public class MainPanel extends JPanel implements Observer{
    private Conference c;
    private String user;


    @Override
    public void update (Observable o, Object arg) {

    }

    public MainPanel(Conference c, String user) {
        this.c = c;
        this.user = user;
    }
}
