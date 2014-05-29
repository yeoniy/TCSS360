package util;

import model.Conference;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public final class Loader {
    private static final int DATE_INDEX = 1;
	public static int total_conference;
	public static final String CONFERENCE_LIST_PATH = "Resources\\Conference.txt";
	
	public static final String CONFERENCE_PATH = "Resources\\";
	
	private Loader() {
		// Should not be instantiated.
	}
	
	public static Conference[] loadConferenceList() throws FileNotFoundException, IOException {
		File conferenceFile = new File(CONFERENCE_LIST_PATH);
		FileReader fr = new FileReader(conferenceFile);
		BufferedReader br = new BufferedReader(fr);
		Conference[] toReturn;
		ArrayList<Conference> conferenceList = new ArrayList<Conference>();
		
		// Parse the file gathering all the conferences into the list
		String current;
		while (br.ready()) {
			current = br.readLine();
            String[] list = current.split(",");
            String[] tempDate = list[DATE_INDEX].split("/");
            Date date = new Date(Integer.parseInt(tempDate[0]), Integer.parseInt(tempDate[1]), Integer.parseInt(tempDate[2]));
            Conference tempConference = new Conference(list[0], date, list[2]);
			conferenceList.add(tempConference);
		}
		br.close();
		fr.close();
		toReturn = new Conference[conferenceList.size()];
        total_conference = conferenceList.size();
		for (int i = 0; i < toReturn.length; i++) {
			toReturn[i] = conferenceList.get(i);
		}
		return toReturn;
	}
}
