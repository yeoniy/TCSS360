package main;
import java.awt.EventQueue;
import java.util.ArrayList;

import model.Conference;
import util.Loader;
import view.ConferenceGui;

/**
 * Created by Yeonil on 4/29/14.
 */
public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				ConferenceGui c = new ConferenceGui();
			}
        });
    }
}
