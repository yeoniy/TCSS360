/**
 * Created by Yeonil on 4/22/14.
 */

import java.util.ArrayList;

/**
 * used for creating accounts and editting privledges,  assigning program chair.
 */
public class Admin extends User {
    private ArrayList<Conference> conferences;

    public Admin(String name, String id, String password, ArrayList<Conference> conferences) {
        super(name, id, password, Type.ADMIN);
        this.conferences = conferences;
    }

    public boolean addUser(ArrayList<User> users, User user) {
        users.add(new User(user));
        return true;
    }

    public void changePriveledge(User user, Type t) {
        // Create new whatever, author, review, etc.
    }

    private void addConference(Conference c) {
        conferences.add(c);
    }
    private void removeConference(Conference c) {
        conferences.remove(c);
    }
}
