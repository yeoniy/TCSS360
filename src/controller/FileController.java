package controller;

import model.Conference;
import model.Paper;
import model.Type;
import model.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Yeonil on 5/20/14.
 * @author Tim Loverin, Yeonil, Nick, Rich
 */
public class FileController extends Controller {
    private String myConf;
    private ArrayList<User> myUser;
    private ArrayList<Paper> myPapers;
    
    private Conference myConference;
/**
 * Constructor for the FileController
 * 
 * @param the_conference the conference we are dealing with.
 */
    public FileController(Conference the_conference) {
        super(null);
        myUser = new ArrayList<User>();
        myPapers = new ArrayList<Paper>();
        myConf = the_conference.getName(); //TOCHECK: if this is the way we want to do it.
        myConference = the_conference;
        readFile();
    }
    /**
     * Reads the file and adds users to the program.
     */
    private void readFile() { 
        File myfile = new File("Resources\\" + myConf + ".txt");
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
        	addPapers(p1,p2,p3,p4);
        	addToConference(myUser);
        }
        file.close();
        } catch (FileNotFoundException e) {
		System.out.print("unable to find file");
		e.printStackTrace();
		}   
    }
    private void addToConference(ArrayList<User> myUser) {
		for (User u: myUser) {
			myConference.addUser(u);
		}
	}
	/**
     * Adds Users to the program.
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
     */
    private void addPapers(String s1, String s2, String s3, String s4) {
    	//TOFIX still need to eliminate bad file names.
    	ArrayList<String> temp = new ArrayList<String>();
    	temp.add(s1);
    	temp.add(s2);
    	temp.add(s3);
    	temp.add(s4);
    	for (int i = 0; i < temp.size(); i++) {
    		if(temp.get(i).toLowerCase().equals("empty")) {
    			System.out.println("no file found");
    		} else {
    			File tempfile = new File(temp.get(i));
    			Paper paper = new Paper(tempfile);
    		    myPapers.add(paper);
    		}
    	}

    }
	public Conference getMyConference() {
		return myConference;
	}
	public void setMyConference(Conference myConference) {
		this.myConference = myConference;
	}
}
