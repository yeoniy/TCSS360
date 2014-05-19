package view;
import javax.swing.*;

import model.Conference;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Yeonil on 4/29/14.
 */
public class MainPanel extends JPanel implements Observer{
    private Conference c;

    @Override
    public void update (Observable o, Object arg) {

    }

    public MainPanel(Conference c) {
        this.c = c;
    }
}
