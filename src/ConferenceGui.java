import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Yeonil on 4/29/14.
 */
public class ConferenceGui extends JFrame {
    private JPanel mainPanel;
    private JPanel loginPanel;
    private ArrayList<Conference> conferences;



    public ConferenceGui(ArrayList<Conference> conferences) {
        mainPanel = new MainPanel();
        loginPanel = new LoginPanel();
        this.conferences = conferences;
    }


}
