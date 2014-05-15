import java.util.ArrayList;

/**
 * Created by Yeonil on 4/29/14.
 */
public class Main {
    private static ArrayList<Conference> conferences;
    public static void main(String args) {
        loadConference();
        ConferenceGui conferenceGui = new ConferenceGui(conferences);
        conferenceGui.setVisible(true);

    }

    private static void loadConference() {
        //fix later
        conferences = new ArrayList<Conference>();
    }


}
