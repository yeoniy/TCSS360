package model;

import exception.InvalidInputException;

import javax.swing.*;

/**
 * Represents a program chair within the conference.
 * @author Nick Ames
 * @author Richard Hemingway
 * @author Tim Loverin
 * @author Yeonil Yoo
 * @version 6/3/2014
 */
public class ProChair extends SubChair {
    public ProChair(String name, String id, String password) {
        super(name, id, password, Type.PROCHAIR);
    }

    /**
     *
     * @param paper Paper to choose status
     * @param status int to set. 0 = process, 1=rejected 2=accepted
     * Catches InvalidInputException for status param
     */
    public void setAccepted(Paper paper, int status) {
        try {
            paper.setAccepted(status);
        } catch (InvalidInputException e) {
            JOptionPane.showMessageDialog(null, "Invalid status for Paper "
                    + "Please ensure your choice.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
