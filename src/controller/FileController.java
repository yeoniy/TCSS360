package controller;

import model.*;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import exception.InvalidInputException;

/**
 * Controller class for opening conference files and parsing them. This class is used
 * for gathering the conference data and allowing for easy data gathering.
 * @author Nick Ames
 * @author Richard Hemingway
 * @author Tim Loverin
 * @author Yeonil Yoo
 * @version 6/3/2014
 */
public class FileController extends Controller {
	/**
	 * Windows specific file separator 
	 */
	private static final String WIN_DIR = "\\";
	
	/**
	 * Unix specific file separator
	 */
	private static final String UNIX_DIR = "/";
	
	/**
	 * all author papers
	 */
	private ArrayList<Paper> allPapers;
	/**
	 * The name of the conference to load.
	 */
	private String myConf;
	
	/**
	 * The list of users for the conference to be loaded.
	 */
	private ArrayList<User> myUser;
	/**
	 * The list of papers for each user within the conference.
	 */
	private ArrayList<Paper> myPapers;
	/**
	 * a list of all paper files empty and full within a conference.
	 */
	private ArrayList<Paper> maxPapers;
	/**
	 * The conference that is being loaded.
	 */
	private Conference myConference;

	/**
	 * Creates a new FileController for the given conference. The conference
	 * will be updated with data from the conference file.
	 * 
	 * @param aConference the conference we are dealing with.
	 */
	public FileController(Conference aConference) {
		super();
		myUser = new ArrayList<User>();
		myPapers = new ArrayList<Paper>();
		maxPapers = new ArrayList<Paper>();
		allPapers = new ArrayList<Paper>();
		
		myConf = aConference.getName(); 
		myConference = aConference;
		readFile();
		readRecommendations();
	}

	/**
	 * Reads the file and adds users to the conference.
	 * <dt><b>Precondition:</b></dt><dd>
	 *  User has an assigned paper
	 * </dd>
	 * <dt><b>Postcondition:</b></dt><dd>
	 * 	Recommendation is set for paper.		
	 * </dd>
	 */
	private void readFile() { 
		File myfile = null;
		if (System.getProperty("os.name").startsWith("Windows")) {
			myfile = new File("Resources" + WIN_DIR + myConf + ".txt");
		} else {
			myfile = new File("Resources" + UNIX_DIR + myConf + ".txt");
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
				Type type = Type.AUTHOR;
				if(t1.toLowerCase().equals("prochair")) {
					type = Type.PROCHAIR;
				}
				if(t1.toLowerCase().equals("subchair")) {
					type = Type.SUBCHAIR;
				}
				if(t1.toLowerCase().equals("reviewer")) {
					type = Type.REVIEWER;
				}
				if(t1.toLowerCase().equals("admin")) {
					type = Type.ADMIN;
				}
				addUser(username, ID, password, type);
				addPapers(p1,p2,p3,p4, type, line[1]);
				addToConference(myUser);
			}
			file.close();
		} catch (FileNotFoundException e) {
            Controller.writePaperForAll(myConf + ".txt");
			//e.printStackTrace();
		}   
	}
	/**
	 * gets papers which have recommendation or acceptances
	 
	 */
	private void readRecommendations() { 
		File myfile = null;
		if (System.getProperty("os.name").startsWith("Windows")) {
			myfile = new File("Resources" + WIN_DIR + myConf + "Recs.txt");
		} else {
			myfile = new File("Resources" + UNIX_DIR + myConf + "Recs.txt");
		}
		try {
			Scanner file = new Scanner(new FileInputStream(myfile));
			while (file.hasNext()) {
				String name = file.next();
				int screc = file.nextInt();
				int pcrec = file.nextInt();
				for (int i = 0; i < allPapers.size(); i++) {
					if (name.equals(allPapers.get(i).getFileName())) {
						try {
							allPapers.get(i).setAccepted(pcrec);
							allPapers.get(i).setSCrec(screc);
						} catch (InvalidInputException e) {
							e.printStackTrace();
						}
					}
				}
			}
			file.close();
		} catch (FileNotFoundException e) {
            Controller.writePaperForAll(myConf + "Recs.txt");
			//e.printStackTrace();
		}
	}
	/**
	 * Adds the users to the conference.
	 * @param myUser the list of users to add
	 */
	private void addToConference(ArrayList<User> myUser) {
		for (User u: myUser) {
			myConference.addUser(u);
		}
	}
	/**
	 * Adds user into a list.
	 * 
	 * @param name name of user.
	 * @param id ID of user.
	 * @param password Users password.
	 * @param type the type of user.
	 */
	private void addUser (String name, String id, String password, Type type) {
		User user = new User (name, id, password, type);
		myConference.addUser(user);
	}

	/**
	 * Adds papers for each user.
	 * 
	 * @param s1 first paper file string name.
	 * @param s2 second paper file string name.
	 * @param s3 third paper file string name.
	 * @param s4 fourth paper file string name
	 * @param id 
	 */
	private void addPapers(String s1, String s2, String s3, String s4, Type type, String id) {
		ArrayList<String> temp = new ArrayList<String>();
		temp.add(s1);
		temp.add(s2);
		temp.add(s3);
		temp.add(s4);
		for (int i = 0; i < temp.size(); i++) {
			if(temp.get(i).toLowerCase().equals("empty")) {
				Paper epaper = new Paper(new File("empty.txt"), id); //blank text document
				myPapers.add(epaper);
				maxPapers.add(epaper);
			} else {
				File tempfile = new File(temp.get(i));
				Paper paper = new Paper(tempfile, id);
				myPapers.add(paper);
				if(type == Type.AUTHOR) {
					allPapers.add(paper);
				}
				maxPapers.add(paper);
				Controller.setAllPapers(allPapers);
			}
		}
		Controller.setMaxPapers(maxPapers);
	}

	/**
	 * Returns the conference with all users already added.
	 * @return the conference
	 */
	public Conference getMyConference() {
		return myConference;
	}
	/**
	 * gets all the files for the given user.
	 * <dt><b>Precondition:</b></dt><dd>
	 *  User has an assigned paper
	 * </dd>
	 * @param u user
	 * @return array list of papers
	 */
	public ArrayList<Paper> getPapers(User u) {
		ArrayList<Paper> temp = new ArrayList<Paper>();
		for (int i = 0; i < myConference.getUserList().size(); i++) {
			if(myConference.getUserList().get(i).getName().equals(u.getName())) {
				int j=(i + 1)*4;
				// Added the if statements to remove unnecessary empty.txt
//removed if statements to reallow for file overwriting of empty.txt to refix the remove user story ~Tim
			//	if (!myPapers.get(j - 4).getFileName().equals("empty.txt"))
					temp.add(myPapers.get(j - 4));
			//	if (!myPapers.get(j - 3).getFileName().equals("empty.txt"))
					temp.add(myPapers.get(j - 3));
			//	if (!myPapers.get(j - 2).getFileName().equals("empty.txt"))
					temp.add(myPapers.get(j - 2));
			//	if (!myPapers.get(j - 1).getFileName().equals("empty.txt"))
					temp.add(myPapers.get(j - 1));
				Controller.setMyPapers(temp);
				break; // Found users paper, no need continue iterating.
			}
		}
		return temp;
	}

	/**
	 * Gets the review for a specific paper
	 * <dt><b>Precondition:</b></dt><dd>
	 *  The paper exists in the conference
	 * </dd>
	 * @param fileName the name of the paper
	 * @return the reviews associated with that paper
	 */
    public Comment[] getReviewPaper(String fileName){
        File myfile = Controller.getTheFile(myActiveConference.getName() +"REVIEW_" + fileName);
        Comment[] line = null;
        try {
            Scanner file = new Scanner(new FileInputStream(myfile));
            String fullString = "";
            while (file.hasNext()) {
                fullString += file.nextLine() + "\n";
            }
            file.close();
            //Checking if file is empty or not
            if(fullString.length() > 1) {
                String[] subline = fullString.split(Controller.STRINGSPLIT);
                line = new Comment[subline.length-1];
                String f;
                if (System.getProperty("os.name").startsWith("Windows")) {
                    f = "Resources\\" + myActiveConference.getName() +"REVIEW_" + fileName;
                } else {
                    f = "Resources/" + myActiveConference.getName() +"REVIEW_" + fileName;
                }
                for(int i = 1; i < subline.length; i++) {
                    String[] sub1 = subline[i].split(Controller.IDSPLIT);
                    String[] sub2 = sub1[1].split(Controller.RATESPLIT);
                    String id = sub1[0];
                    String rate = sub2[0];
                    String comment = sub2[1];
                    line[i-1] = new Comment(f, id, rate, comment);
                }
            }
        } catch (FileNotFoundException e) {
            Controller.writePaperForAll(myActiveConference.getName() +"REVIEW_" + fileName);
            //e.printStackTrace();
        }
        return line;
    }
}
