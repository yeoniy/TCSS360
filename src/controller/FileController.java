package controller;

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

    public FileController() {
        super(null);
        readFile();
    }
    /**
     * Reads the file and adds users to the program.
     */
    private void readFile() {
        File myfile = new File("\\Resources\\Seattle.txt");
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
        //
    }
    private void addUser (String name, String id, String password, Type type) {
        User user = new User (name, id, password, type);
        myUser.add(user);
    }
    private void addPapers(String p1, String s2, String s3, String s4) {

    }
}
