import java.util.ArrayList;

/**
 * Created by Yeonil on 4/22/14.
 */
public class Reviewer extends Author {

    public Reviewer(String name, String id, String password) {
        super(name, id, password, Type.REVIEWER);
    }

    public Reviewer(String name, String id, String password, Type type) {
        super(name, id, password, type);
    }

    public void addcomment(Paper paper, String comment) {
        paper.addComment(comment);
    }
}
