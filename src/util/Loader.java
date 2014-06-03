package util;

import model.Conference;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Tim, Yeonil
 * @version 1.0
 */
public final class Loader {
	
    /**
     * The index of array that indecates conference name String.
     */
    private static final int NAME_INDEX = 0;
    /**
     * The index of array that indecates date String.
     */
    private static final int DATE_INDEX = 1;
    /**
     * The index of array that indecates conference ID String.
     */
    private static final int ID_INDEX = 2;
    /**
     * The index of array that indecates int Year.
     */
    private static final int YEAR_INDEX = 0;
    /**
     * The index of array that indecates int Month.
     */
    private static final int MONTH_INDEX = 1;
    /**
     * The index of array that indecates int Day.
     */
    private static final int DAY_INDEX = 2;
    /**
     * The file (including path) that contains list of conference
     */
	public static final String CONFERENCE_LIST_PATH = "Resources\\Conference.txt";
	
	public static final String CONFERENCE_PATH = "Resources\\";

	private static final String CONFERENCE_LIST_PATH_UNIX = "Resources/Conference.txt";

    /**
     * Private constructor that we expect to not used..
     */
	private Loader() {
		// Should not be instantiated.
	}

    /**
     * Loads all Conference from CONFERENCE_LIST_PATH and creates array of Conference class.
     * This method is being called by LoginPanel to let user choose conference.
     * If file is not found, let it throws exception and it's not being catched here.
     * @return Array of conference
     * @throws FileNotFoundException
     * @throws IOException
     */
	public static Conference[] loadConferenceList() throws FileNotFoundException, IOException {
		File conferenceFile = null;
		if (System.getProperty("os.name").startsWith("Windows")) {
			conferenceFile = new File(CONFERENCE_LIST_PATH);
		} else {
			conferenceFile = new File(CONFERENCE_LIST_PATH_UNIX);
		}
		
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
            Date date = new Date(Integer.parseInt(tempDate[YEAR_INDEX]), Integer.parseInt(tempDate[MONTH_INDEX]), Integer.parseInt(tempDate[DAY_INDEX]));
            Conference tempConference = new Conference(list[NAME_INDEX], date, list[ID_INDEX]);
			conferenceList.add(tempConference);
		}
		br.close();
		fr.close();
		toReturn = new Conference[conferenceList.size()];
		for (int i = 0; i < toReturn.length; i++) {
			toReturn[i] = conferenceList.get(i);
		}
		return toReturn;
	}
}
