package model;

import exception.InvalidInputException;

import javax.swing.*;

/**
 * Created by Yeonil on 4/22/14.
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
