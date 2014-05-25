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
/**
 * Constructor for the FileController
 * 
 * @param the_conference the conference we are dealing with.
 */
    public FileController(Conference the_conference) {
        super(null);
        myConf = the_conference.getName(); //TOCHECK: if this is the way we want to do it.
        readFile();
    }
    /**
     * Reads the file and adds users to the program.
     */
    private void readFile() { 
        File myfile = new File("\\Resources\\" + myConf + ".txt");
        try {
			Scanner file = new Scanner(new FileInputStream(myfile));
        while (file.hasNext()) {
        	String username = file.next();
        	String ID = file.next();
        	String password = file.next();
        	String t1 = file.next();
        	String p1 = file.next();
        	String p2 = file.next();
        	String p3 = file.next();
        	String p4 = file.next();
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
        }
        file.close();
        } catch (FileNotFoundException e) {
		System.out.print("unable to find file");
		e.printStackTrace();
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
        myUser.add(user);
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
}
