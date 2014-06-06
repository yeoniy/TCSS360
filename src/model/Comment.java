package model;

/**
 * Created by Yeonil on 6/3/14.
 */
public class Comment {
	/**
	 * The file where the paper is located
	 */
    private String file;
    
    /**
     * The id assigned to the paper
     */
    private String id;
    
    /**
     * The rating assigned to the paper
     */
    private String rate;
    
    /**
     * Reviewer Comments
     */
    private String comment;
    
    /**
     * Constructor
     * @param file The file where the paper is located
     * @param id The id given to the paper
     * @param rate The rating given to the paper
     * @param comment Reviewer comments
     */
    public Comment(String file, String id, String rate, String comment) {
        this.file = file;
        this.id = id;
        this.rate = rate;
        this.comment = comment;
    }

    /**
     * Gets the file
     * @return the file.
     */
    public String getFile() {
        return file;
    }

    /**
     * Gets the ID
     * @return The id String
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the paper rating.
     * @return the rating string
     */
    public String getRate() {
        return rate;
    }

    /**
     * Gets the reviewer comments
     * @return the comment string.
     */
    public String getComment() {
        return comment;
    }
}
