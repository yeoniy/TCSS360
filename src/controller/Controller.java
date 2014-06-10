package controller;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JPanel;

import view.StatsPanel;

import model.Conference;
import model.Paper;
import model.Type;
import model.User;
import exception.InvalidInputException;

/**
 * Main controller class for controlling actions between view and controller. Should not be
 * used for specific control of small subtasks but as a general purpose class for easier model
 * access.
 * @version 6/2/2014
 * @author Nick Ames, Tim Loverin
 */
public class Controller {
	/**
	 * String that splits comments
	 */
	public static final String STRINGSPLIT = "REVIEWCOMMENTS";
	public static final String IDSPLIT = "REVIEWID";
	public static final String RATESPLIT = "REVIEWRATE";

	private static final String WIN_DIR = "\\";

	private static final String UNIX_DIR = "/";

	/**
	 * all non empty author papers in the system.
	 */
	private static ArrayList<Paper> allPapers;
	/**
	 * Papers for current user.
	 */
	private static ArrayList<Paper> myPapers;
	/**
	 * all paper files empty or real.
	 */
	private static ArrayList<Paper> maxPapers;
	/**
	 * all authors in the conference.
	 */
	private static ArrayList<User> authorList;
	/**
	 * current user logged in.
	 */
	private static User myUser;
	/**
	 * The list of all the conferences.
	 */
	private ArrayList<Conference> myConferenceList;
	/**
	 * A list of every subchair
	 */
	private ArrayList<User> mySubChairs;
	/**
	 * a list of all reviewers
	 */
	private ArrayList<User> myReviewers;
	/**
	 * The current active conference.
	 */
	public static Conference myActiveConference;

	/**
	 * List for the observers.
	 */
	public static ArrayList<JPanel> observers = new ArrayList<JPanel>();

	/**
	 * Creates a new Controller. This is a generic controller that will only
	 * supply basic control. For more specific applications use inherited controllers.
	 * @param aConferenceList The list of conferences.
	 */
	public Controller() {
		super();
	}

	/**
	 * Grabs current users list of papers, and adds the new paper to the end of the list.
	 * 
	 * <dt><b>Precondition:</b></dt><dd>
	 * User has an account with addPaper privilege
	 * </dd>
	 * <dt><b>Postcondition:</b></dt><dd>
	 *  Paper is added to the users list of submitted papers.
	 * </dd>
	 *
	 * @param x the index to add to.
	 * @param fileName name of the file.
	 */
	public static void addPaper (String fileName, int x) {
		String old = myUser.getName() + (",") + myUser.getId() + (",") + myUser.getPassword() + (",") + myUser.typeToString()+
				(",") + getMyPapers().get(0).getFileHeader() + (",") + getMyPapers().get(1).getFileHeader() + (",") + getMyPapers().get(2).getFileHeader()
				+ (",") + getMyPapers().get(3).getFileHeader();
		Paper p = new Paper(new File(fileName));
		getMyPapers().set(x,p);
		String content = myUser.getName() + (",") + myUser.getId() + (",") + myUser.getPassword() + (",") + myUser.typeToString()+
				(",") + getMyPapers().get(0).getFileHeader() + (",") + getMyPapers().get(1).getFileHeader() + (",") + getMyPapers().get(2).getFileHeader()
				+ (",") + getMyPapers().get(3).getFileHeader();
		try{
			BufferedReader reader = new BufferedReader(new FileReader(getTheFile(myActiveConference.getName() +".txt")));
			String line = "", oldtext = "";
			while((line = reader.readLine()) != null){
				oldtext += line + "\n";
			}
			reader.close();
			String newtext = oldtext.replaceAll(old, content);

			FileWriter writer = getTheWriter(myActiveConference.getName() +".txt");
			writer.write(newtext);writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * updates the stats panel when a new file is added.
	 * @param s file name as a string
	 */
	public static void updateStats(String s) {
		StatsPanel.addNewPaper(s);
	}
	/**
	 * adds new loaded file to the recfile.
	 * 
	 * * <dt><b>Precondition:</b></dt><dd>
	 *  Have a loaded file.
	 * </dd>
	 * <dt><b>Postcondition:</b></dt><dd>
	 * file is added to the recfile.
	 * </dd>
	 * 
	 * @param s name of file
	 */
	public static void addtoRecs(String s) {
		try {
			String content = s + " " + 0 + " " + 0;
			PrintWriter out = new PrintWriter(new BufferedWriter(getTheWriterWrite(myActiveConference.getName() +"Recs.txt")));
			out.println(content);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * removes loaded file from the recfile.
	 * 
	 * * <dt><b>Precondition:</b></dt><dd>
	 * 	Know which file to remove from recfile
	 * </dd>
	 * <dt><b>Postcondition:</b></dt><dd>
	 *   File is removed from recfile
	 * </dd>
	 * 
	 * @param s name of file
	 */
	public static void removefromRecs(String s) {
		String old = "";
		String content = "";
		for(int i = 0; i < allPapers.size(); i++) {
			if (s.equals(allPapers.get(i).getFileName())) {
				old = s + " " + allPapers.get(i).isScrec() + " " + allPapers.get(i).isAccepted(); 
				content = "";
			}
		}
		try{
			File file = getTheFile(myActiveConference.getName() +"Recs.txt");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "", oldtext = "";
			while((line = reader.readLine()) != null){
				oldtext += line + "\n";
			}
			reader.close();
			String newtext = oldtext.replaceAll(old, content);

			FileWriter writer = getTheWriter(myActiveConference.getName() +"Recs.txt");
			writer.write(newtext);writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * assigns a paper to a user
	 * 
	 *  <dt><b>Precondition:</b></dt><dd>
	 *  Submitted paper and registered user.
	 * </dd>
	 * <dt><b>Postcondition:</b></dt><dd>
	 * 	Paper is assigned to one and only one user.		
	 * </dd>
	 * 
	 * 
	 * @param fileName
	 * @param user
	 */
	public static void assignPaper (String fileName, String user) {
		User u = new User();
		int x = 0;
		for (int i = 0; i < myActiveConference.getUserList().size(); i++) {
			if (myActiveConference.getUserList().get(i).getName().equals(user)) {
				u = myActiveConference.getUserList().get(i);
				x = (i + 1)*4;
			}
		}
		String old = u.getName() + (",") + u.getId() + (",") + u.getPassword() + (",") + u.typeToString() +
				(",") + getMaxPapers().get(x - 4).getFileHeader() + (",") + getMaxPapers().get(x-3).getFileHeader() + (",") + getMaxPapers().get(x - 2).getFileHeader()
				+ (",") + getMaxPapers().get(x - 1).getFileHeader();
		Paper p = new Paper(new File(fileName));
		if(getMaxPapers().get(x - 4).getFileHeader().equals("empty")) {
			getMaxPapers().set(x - 4,p);
		} else if (getMaxPapers().get(x - 3).getFileHeader().equals("empty")) {
			getMaxPapers().set(x - 3,p);
		} else if (getMaxPapers().get(x - 2).getFileHeader().equals("empty")) {
			getMaxPapers().set(x - 2,p);
		} else {
			getMaxPapers().set(x - 1,p);
		}

		String content = u.getName() + (",") + u.getId() + (",") + u.getPassword() + (",") + u.typeToString() +
				(",") + getMaxPapers().get(x - 4).getFileHeader() + (",") + getMaxPapers().get(x-3).getFileHeader() + (",") + getMaxPapers().get(x - 2).getFileHeader()
				+ (",") + getMaxPapers().get(x - 1).getFileHeader();
		try{
			File file = getTheFile(myActiveConference.getName() +".txt");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "", oldtext = "";
			while((line = reader.readLine()) != null){
				oldtext += line + "\n";
			}
			reader.close();
			String newtext = oldtext.replaceAll(old, content);

			FileWriter writer = getTheWriter(myActiveConference.getName() +".txt");
			writer.write(newtext);writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Creates a recommendation for the program chair.
	 * <dt><b>Precondition:</b></dt><dd>
	 *  Program chair has an assigned paper
	 * </dd>
	 * <dt><b>Postcondition:</b></dt><dd>
	 * 	Recommendation is set for paper.		
	 * </dd>
	 * 
	 * @param paper paper to be recommended
	 * @param x the recommendation
	 */
	public static void setPCrec(String paper, int x) {
		String old = "";
		String content = "";
		for(int i = 0; i < allPapers.size(); i++) {
			if (paper.equals(allPapers.get(i).getFileName())) {
				old = paper + " " + allPapers.get(i).isScrec() + " " + allPapers.get(i).isAccepted(); 
				content = paper + " " + allPapers.get(i).isScrec() + " " + x;
				try {
					if(x == 1) {
						allPapers.get(i).setAccepted(1);
					} else if (x == 2) {
						allPapers.get(i).setAccepted(2);
					}
				} catch (InvalidInputException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		try{
			File file = getTheFile(myActiveConference.getName() +"Recs.txt");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "", oldtext = "";
			while((line = reader.readLine()) != null){
				oldtext += line + "\n";
			}
			reader.close();
			String newtext = oldtext.replaceAll(old, content);

			FileWriter writer = getTheWriter(myActiveConference.getName() +"Recs.txt");
			writer.write(newtext);writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	 * Sets the sub-chairs paper recommendation
	 * <dt><b>Precondition:</b></dt><dd>
	 *  SubChair has an assigned paper
	 * </dd>
	 * <dt><b>Postcondition:</b></dt><dd>
	 * 	Recommendation is set for paper.		
	 * </dd>
	 * @param paper the paper assigned to the recommendation
	 * @param x the recommendation
	 */
	public static void setSCrec(String paper, int x) {
		String old = "";
		String content = "";
		for(int i = 0; i < allPapers.size(); i++) {
			if (paper.equals(allPapers.get(i).getFileName())) {
				old = paper + " " + allPapers.get(i).isScrec() + " " + allPapers.get(i).isAccepted(); 
				content = paper + " " + x + " " + allPapers.get(i).isAccepted();
				try {
					if(x == 1) {
						allPapers.get(i).setSCrec(1);
					} else if (x == 2) {
						allPapers.get(i).setSCrec(2);
					}
				} catch (InvalidInputException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		try{
			File file = getTheFile(myActiveConference.getName() +"Recs.txt");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "", oldtext = "";
			while((line = reader.readLine()) != null){
				oldtext += line + "\n";
			}
			reader.close();
			String newtext = oldtext.replaceAll(old, content);

			FileWriter writer = getTheWriter(myActiveConference.getName() +"Recs.txt");
			writer.write(newtext);writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	 * Gets the program chairs recommendations
	 * @param paper The paper assigned to the recommendation
	 * @return recommendation status
	 */
	public static int getPCrec(String paper) {
		int x = 0;
		for(int i = 0; i < allPapers.size(); i++) {
			if (paper.equals(allPapers.get(i).getFileName())) {
				x = allPapers.get(i).isAccepted();
			}
		}
		return x;
	}
	/**
	 * Gets the sub-program chairs recommendations
	 * @param paper The paper assigned to the recommendation
	 * @return recommendation status
	 */
	public static int getSCrec(String paper) {
		int x = 0;
		for(int i = 0; i < allPapers.size(); i++) {
			if (paper.equals(allPapers.get(i).getFileName())) {
				x = allPapers.get(i).isScrec();
			}
		}
		return x;
	}
	/**
	 * gets a list of papers a user has.
	 * 
	 * @param user the user
	 * @return list of papers for the user
	 */
	public static ArrayList<Paper> getUserPapers(String user) {
		ArrayList<Paper> temp = new ArrayList<Paper>();
		int x = 0;
		for (int i = 0; i < myActiveConference.getUserList().size(); i++) {
			if (myActiveConference.getUserList().get(i).getName().equals(user)) {
				x = (i + 1)*4;
				break;
			}
		}
		temp.add(getMaxPapers().get(x - 4));
		temp.add(getMaxPapers().get(x - 3));
		temp.add(getMaxPapers().get(x - 2));
		temp.add(getMaxPapers().get(x - 1));
		return temp;
	}
	/**
	 * Returns an ArrayList of user assignments.
	 * @param name - name of user
	 * @return ArrayList of user assignments.
	 */
	public static ArrayList<String> getUserAssignments(String name) {
		ArrayList<String> temp = new ArrayList<String>();
		File myfile = null;
		if (System.getProperty("os.name").startsWith("Windows")) {
			myfile = new File("Resources" + WIN_DIR + myActiveConference + ".txt");
		} else {
			myfile = new File("Resources" + UNIX_DIR + myActiveConference + ".txt");
		}

		try {
			Scanner file = new Scanner(new FileInputStream(myfile));
			while (file.hasNext()) {
				String lineString = file.nextLine();
				String[] line = lineString.split(",");
				String username = line[0];
				String ID = line[1];
				String password = line[2];
				String t1 = line[3];
				String p1 = line[4];
				String p2 = line[5];
				String p3 = line[6];
				String p4 = line[7];
				if (!p1.equals("empty") && username.equals(name)) {
					temp.add(p1);
				}
				if (!p2.equals("empty") && username.equals(name)) {
					temp.add(p2);
				}
				if (!p3.equals("empty") && username.equals(name)) {
					temp.add(p3);
				}
				if (!p4.equals("empty") && username.equals(name)) {
					temp.add(p4);
				}
			}
		} catch (FileNotFoundException e) {
			Controller.writePaperForAll(myActiveConference + ".txt");
			//e.printStackTrace();
		}   
		return temp;
	}
	/**
	 * primarily used for the removing an assignment.
	 * <dt><b>Precondition:</b></dt><dd>
	 *  User has an assigned paper
	 * </dd>
	 * <dt><b>Postcondition:</b></dt><dd>
	 * 	Recommendation is set for paper.		
	 * </dd>
	 * @param fileName
	 * @param user
	 */
	public static void deAssignPaper (String fileName, String user) {
		User u = new User();
		int x = 0;
		for (int i = 0; i < myActiveConference.getUserList().size(); i++) {
			if (myActiveConference.getUserList().get(i).getName().equals(user)) {
				u = myActiveConference.getUserList().get(i);
				x = (i + 1)*4;
			}
		}
		String old = u.getName() + (",") + u.getId() + (",") + u.getPassword() + (",") + u.typeToString() +
				(",") + getMaxPapers().get(x - 4).getFileHeader() + (",") + getMaxPapers().get(x-3).getFileHeader() + (",") + getMaxPapers().get(x - 2).getFileHeader()
				+ (",") + getMaxPapers().get(x - 1).getFileHeader();
		Paper p = new Paper(new File("empty.txt"));
		if(getMaxPapers().get(x - 4).getFileHeader().equals(fileName)) {
			getMaxPapers().set(x - 4,p);
		} else if (getMaxPapers().get(x - 3).getFileHeader().equals(fileName)) {
			getMaxPapers().set(x - 3,p);
		} else if (getMaxPapers().get(x - 2).getFileHeader().equals(fileName)) {
			getMaxPapers().set(x - 2,p);
		} else if (getMaxPapers().get(x - 1).getFileHeader().equals(fileName)){
			getMaxPapers().set(x - 1,p);
		}

		String content = u.getName() + (",") + u.getId() + (",") + u.getPassword() + (",") + u.typeToString() +
				(",") + getMaxPapers().get(x - 4).getFileHeader() + (",") + getMaxPapers().get(x-3).getFileHeader() + (",") + getMaxPapers().get(x - 2).getFileHeader()
				+ (",") + getMaxPapers().get(x - 1).getFileHeader();
		try{
			File file = getTheFile(myActiveConference.getName() +".txt");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "", oldtext = "";
			while((line = reader.readLine()) != null){
				oldtext += line + "\n";
			}
			reader.close();
			String newtext = oldtext.replaceAll(old, content);

			FileWriter writer = getTheWriter(myActiveConference.getName() +".txt");
			writer.write(newtext);writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * update
	 */
	public void update() {


	}

	/**
	 * Returns the conference based on the given ID.
	 * @param conferenceId The ID of the conference to get.
	 * @return the conference, otherwise null.
	 */
	public Conference getConference(String conferenceId) {
		Conference toReturn = null;
		for (Conference c : myConferenceList) {
			if (c.getId().equals(conferenceId)) {
				toReturn = c;
				break;
			}
		}
		return toReturn;
	}

	/**
	 * Returns the current active conference.
	 * @return the currently active conference.
	 */
	public Conference getMyActiveConference() {
		return myActiveConference;
	}

	/**
	 * Sets the currently active conference.
	 * @param myActiveConference the conference that the user is working with
	 */
	public void setMyActiveConference(Conference myActiveConference) {
		this.myActiveConference = myActiveConference;
	} 
	/**
	 * gets a list of all the authors in the conference.
	 * 
	 * @return list of all the authors
	 */
	public static ArrayList<User> getAllAuthors() {
		ArrayList<User> temp = new ArrayList<User>();
		for(int i = 0; i < myActiveConference.getUserList().size(); i++) {
			if (myActiveConference.getUserList().get(i).typeToString().equals("AUTHOR")) {
				temp.add(myActiveConference.getUserList().get(i));
			}
		}
		return temp;
	}
	/**
	 * Sets the active user
	 * 
	 * @param theUser user thats active
	 */
	public static void setActiveUser(User theUser) {
		myUser = theUser;
	}
	/**
	 * returns the active user. 
	 * @return the user thats active.
	 */
	public static Type getUserType() {
		return myUser.getMyType();	
	}
	/**
	 * gets all the subchairs in a list.
	 * @return all the subchairs in the active conference.
	 */
	public static ArrayList<User> getAllSubChairs() {
		ArrayList<User> temp = new ArrayList<User>();
		for(int i = 0; i < myActiveConference.getUserList().size(); i++) {
			if (myActiveConference.getUserList().get(i).typeToString().equals("SUBCHAIR")) {
				temp.add(myActiveConference.getUserList().get(i));
			}
		}
		return temp;
	}
	/**
	 * gets all the reviewers in a list.
	 * @return all the reviewers in the active conference.
	 */
	public static ArrayList<User> getAllReviewers() {
		ArrayList<User> temp = new ArrayList<User>();
		for(int i = 0; i < myActiveConference.getUserList().size(); i++) {
			if (myActiveConference.getUserList().get(i).typeToString().equals("REVIEWER")) {
				temp.add(myActiveConference.getUserList().get(i));
			}
		}
		return temp;
	}
	/**
	 * gets all the papers an author has submitted in a list
	 * @return all the papers in the active conference.
	 */
	public static ArrayList<Paper> getAllPapers() {
		return allPapers;
	}
	/**
	 * gets all the non empty papers.
	 * @return all the papers real or fake in the active conference.
	 */
	public static ArrayList<Paper> getMaxPapers() {
		return maxPapers;
	}
	/**
	 * removes a paper based on location and updates the text file for the active conference.
	 * 
	 * <dt><b>Precondition:</b></dt><dd>
	 *  File of papers exist
	 * </dd>
	 * <dt><b>Postcondition:</b></dt><dd>
	 * 	specificed paper is removed from the file.		
	 * </dd>
	 * @param x the location of the paper to remove.
	 */
	public static void removePaper(int x) {
		String old = myUser.getName() + (",") + myUser.getId() + (",") + myUser.getPassword() + (",") + myUser.typeToString()+
				(",") + getMyPapers().get(0).getFileHeader() + (",") + getMyPapers().get(1).getFileHeader() + (",") + getMyPapers().get(2).getFileHeader()
				+ (",") + getMyPapers().get(3).getFileHeader();
		Paper p = new Paper(new File("empty.txt"));
		getMyPapers().set(x,p);
		String content = myUser.getName() + (",") + myUser.getId() + (",") + myUser.getPassword() + (",") + myUser.typeToString()+
				(",") + getMyPapers().get(0).getFileHeader() + (",") + getMyPapers().get(1).getFileHeader() + (",") + getMyPapers().get(2).getFileHeader()
				+ (",") + getMyPapers().get(3).getFileHeader();
		try{
			File file = getTheFile(myActiveConference.getName() +".txt");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "", oldtext = "";
			while((line = reader.readLine()) != null){
				oldtext += line + "\n";
			}
			reader.close();
			String newtext = oldtext.replaceAll(old, content);

			FileWriter writer = getTheWriter(myActiveConference.getName() +".txt");
			writer.write(newtext);writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * 	Submits reviewers comments to a paper.
	 * @param fileName - The name of the file.
	 * @param id - user id
	 * @param rate - rating given to paper
	 * @param comment - comments from reviewer
	 */
	public static void writeReviewPaper (String fileName, String id, String rate, String comment) {
		try {
			String content = STRINGSPLIT+ id + IDSPLIT + rate + RATESPLIT + comment;
			PrintWriter out = new PrintWriter(new BufferedWriter(getTheWriterWrite(myActiveConference.getName() +"REVIEW_" + fileName)));
			out.println(content);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void writePaperForAll(String fileName) {
		try {
			String content = "";
			PrintWriter out = new PrintWriter(new BufferedWriter(getTheWriterWrite(fileName)));
			out.println(content);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * sets the list of all the papers.
	 * 
	 * @param p Array list of papers.
	 */
	public static void setAllPapers(ArrayList<Paper> p) {
		allPapers = p;	
	}
	/**
	 * sets the list of all the papers.
	 * 
	 * @param p Array list of papers.
	 */
	public static void setMaxPapers(ArrayList<Paper> p) {
		maxPapers = p;	
	}
	/**
	 * gets the papers for the active user.
	 * 
	 * @return myPapers.
	 */
	public static ArrayList<Paper> getMyPapers() {
		return myPapers;
	}
	/**
	 * Sets myPapers
	 * 
	 * @param myPapers users papers.
	 */
	public static void setMyPapers(ArrayList<Paper> myPapers) {
		Controller.myPapers = myPapers;
	}
	/**
	 * gets the active user.
	 * 
	 * @return the user.
	 */
	public static User getCurrentUser() {
		return myUser;
	}
	/**
	 * gets the current conference.
	 * 
	 * @return the active conference.
	 */
	public static Conference getCurrentConference() {
		return myActiveConference;
	}
	/**
	 * sets the active conference.
	 * 
	 * @param c the conference.
	 */
	public static void setActiveConference(Conference c) {
		myActiveConference = c;
	}

	/**
	 *  Gets the file using the appropriate OS directory.
	 * @param s The file to return
	 * @return the file
	 */
	public static File getTheFile(String s) {
		File file = null;
		String a = "Resources";
		if (System.getProperty("os.name").startsWith("Windows")) {
			a += WIN_DIR + s;
			file = new File(a);
		} else {
			a += UNIX_DIR + s;
			file = new File(a);
		}
		return file;
	}

	/**
	 * Gets the appropriate file writer depending on OS
	 * @param s the current file directory
	 * @return the file writer
	 * @throws IOException
	 */
	public static FileWriter getTheWriter(String s) throws IOException {
		FileWriter writer = null;
		String a = "Resources";
		if (System.getProperty("os.name").startsWith("Windows")) {
			a += WIN_DIR + s;
			writer = new FileWriter(new File(a));
		} else {
			a += UNIX_DIR + s;
			writer = new FileWriter(new File(a));
		}
		return writer;
	}

	/**
	 *  Gets the appropriate file writer depending on OS
	 * @param s the current file directory
	 * @return the file writer
	 * @throws IOException
	 */
	public static FileWriter getTheWriterWrite(String s) throws IOException {
		FileWriter writer = null;
		String a = "Resources";
		if (System.getProperty("os.name").startsWith("Windows")) {
			a += WIN_DIR + s;
			writer = new FileWriter(new File(a), true);
		} else {
			a += UNIX_DIR + s;
			writer = new FileWriter(new File(a), true);
		}
		return writer;
	}

	/**
	 *  Creates a new conference
	 * @param name the name of the conference
	 * @param date The date of the conference
	 * @param user the user creating the conference.
	 * @throws IOException if the given conference already exists
	 */
	public static void createNewConference(String name, String date, User user) throws IOException {
		File f = getTheFile(name + ".txt");
		if (f.createNewFile()) {
			FileWriter fw = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(fw);

			// Create a new file for the conference and add the PC
			bw.write(user.getName() + "," + user.getId() + "," + user.getPassword() + "," + user.getMyType());
			for (int i = 0; i < 4; i++) {
				bw.write(",empty");
			}
			bw.write("\n");
			bw.close();
			fw.close();

			// Append to conference list
			File c = getTheFile("Conference.txt");
			FileWriter cw = new FileWriter(c, true);
			cw.append(name + "," + date + "," + name + " Sympo");
			cw.close();
		} else {
			throw new IOException("The file already exists.");
		}
	}

	/**
	 * Returns the conference based on the given ID.
	 * 
	 * @param conferenceId The ID of the conference to get.
	 * @return the conference, otherwise null.
	 */
	public static String[] getConferences() throws IOException {
		File f = getTheFile("Conference.txt");
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		ArrayList<String> list = new ArrayList<String>();
		String[] s;
		while (br.ready()) {
			String t = br.readLine();
			s = t.split(",");
			list.add(s[0]);
		}
		br.close();
		fr.close();
		s = new String[list.size()];
		for (int i = 0; i < s.length; i++) {
			s[i] = list.get(i);
		}
		return s;
	}

	/**
	 * Returns the deadline for paper submissions
	 * @param name the name of the conference
	 * @return the deadline for paper submissions
	 * @throws IOException
	 */
	public static String getConferenceDeadline(String name) throws IOException {
		File f = getTheFile("Conference.txt");
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		ArrayList<String> list = new ArrayList<String>();
		String[] s;
		while (br.ready()) {
			String t = br.readLine();
			s = t.split(",");
			if (name != null && name.equals(s[0])) {
				br.close();
				fr.close();
				return s[1];
			}
		}
		br.close();
		fr.close();
		return "";
	}

	/**
	 * Removes a conference from the conference system.
	 * @param name the name of the conference to remove.
	 * @throws IOException
	 */
	public static void removeConference(String name) throws IOException {
		File f = getTheFile(name + ".txt");
		f.delete();

		File c = getTheFile("Conference.txt");
		FileReader fr = new FileReader(c);
		BufferedReader br = new BufferedReader(fr);
		ArrayList<String> list = new ArrayList<String>();
		while (br.ready()) {
			list.add(br.readLine());
		}
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).startsWith(name)) {
				list.remove(i);
				break;
			}
		}
		br.close();
		fr.close();

		FileWriter fw = new FileWriter(c);
		BufferedWriter bw = new BufferedWriter(fw);
		for (String s : list) {
			bw.write(s + "\n");
		}
		bw.close();
		fw.close();
	}

	/**
	 * Returns a user id
	 * @param user the user whose id will be returned
	 * @return the user id string.
	 */
	public static String getUserID(String user) {

		ArrayList<User> rev = getAllReviewers();
		ArrayList<User> subs = getAllSubChairs();
		String toReturn = "";
		for (User u : rev) {
			if (u.getName().equals(user)) {
				toReturn = u.getId();
				break;
			}
		}
		for (User u : subs) {
			if (u.getName().equals(user)) {
				toReturn = u.getId();
				break;
			}
		}

		return toReturn;
	}

	/**
	 * Get the paper ID of a given paper.
	 * @param paper The paper whose id will be returned.
	 * @return the paper id string
	 */
	public static String getPaperID(String paper) {
		String id = "";
		for (Paper p : getAllPapers()) {
			if (p.getFileName().equals(paper)) {
				id = p.getId();
			}
		}
		return id;
	}

	/**
	 * Adds a user to a conference.
	 * @param user The user to add to the conference
	 * @param conference the conference to add the user to.
	 */
	public static void addUserToConference(User user, Conference conference) {
		try {
			File f = getTheFile(conference.getName() + ".txt");
			FileWriter fw = new FileWriter(f, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(user.getName() + "," + user.getId() + "," + user.getPassword() + "," + user.getMyType() + ",empty,empty,empty,empty\n");
			bw.close();
			fw.close();
		} catch (Exception e) {

		}
	}


}
