package main;
import java.awt.EventQueue;
import java.util.ArrayList;

import model.Conference;
import util.Loader;
import view.ConferenceGui;
import view.mygui;

/**
 * Created by Yeonil on 4/29/14.
 */
public class Main {
    public static void main(String[] args) {
    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConferenceGui window = new ConferenceGui();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }
}
