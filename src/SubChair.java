/**
 * Created by Yeonil on 4/22/14.
 */
public class SubChair extends Reviewer {

    public SubChair(String name, String id, String password) {
        super(name, id, password, Type.SUBCHAIR);
    }

    public SubChair(String name, String id, String password, Type type) {
        super(name, id, password, type);
    }

    public void assignReviewer(Author user, Conference conference) {
        user = new Reviewer(user.getName(), user.getId(), user.getPassword(), Type.REVIEWER);
        conference.addUser(user);
    }
}
