package main;
import java.util.ArrayList;

import javax.swing.JFrame;

import model.Conference;
import view.ConferenceGui;

/**
 * Created by Yeonil on 4/29/14.
 */
public class Main {
    private static ArrayList<Conference> conferences;
    public static void main(String[] args) {
        loadConference();
        ConferenceGui conferenceGui = new ConferenceGui(conferences);
        
    }

    private static void loadConference() {
        //fix later
        conferences = new ArrayList<Conference>();
    }


}
