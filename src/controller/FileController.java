package controller;

import model.Paper;
import model.Type;
import model.User;

import java.io.File;
import java.util.ArrayList;

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
    }

    private void readFile(String filename) {
        File myfile = new File("\\Resources\\Seattle.txt");
        //
    }
    private void addUser (String name, String id, String password, Type type) {
        User user = new User (name, id, password, type);
        myUser.add(user);
    }
    private void addPapers() {

    }
}
