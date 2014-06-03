package model;
import java.util.ArrayList;

/**
 * Represents a reviewer within the conference.
 * @author Nick Ames
 * @author Richard Hemingway
 * @author Tim Loverin
 * @author Yeonil Yoo
 * @version 6/3/2014
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
