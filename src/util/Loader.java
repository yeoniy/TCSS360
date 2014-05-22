package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public final class Loader {
	
	public static final String CONFERENCE_LIST_PATH = "Resources\\Conference.txt";
	
	public static final String CONFERENCE_PATH = "Resources\\";
	
	private Loader() {
		// Should not be instantiated.
	}
	
	public static String[] loadConferenceList() throws FileNotFoundException, IOException {
		File conferenceFile = new File(CONFERENCE_LIST_PATH);
		FileReader fr = new FileReader(conferenceFile);
		BufferedReader br = new BufferedReader(fr);
		String[] toReturn;
		ArrayList<String> conferenceList = new ArrayList<String>();
		
		// Parse the file gathering all the conferences into the list
		String current;
		while (br.ready()) {
			current = br.readLine();
			conferenceList.add(current);
		}
		br.close();
		fr.close();
		toReturn = new String[conferenceList.size()];
		for (int i = 0; i < toReturn.length; i++) {
			toReturn[i] = conferenceList.get(i);
		}
		return toReturn;
	}
}
