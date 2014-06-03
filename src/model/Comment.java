package model;

/**
 * Created by Yeonil on 6/3/14.
 */
public class Comment {

    private String file;
    private String id;
    private String rate;
    private String comment;
    public Comment(String file, String id, String rate, String comment) {
        this.file = file;
        this.id = id;
        this.rate = rate;
        this.comment = comment;
    }

    public String getFile() {
        return file;
    }

    public String getId() {
        return id;
    }

    public String getRate() {
        return rate;
    }

    public String getComment() {
        return comment;
    }
}
