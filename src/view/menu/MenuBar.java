package view.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import model.Author;
import controller.Controller;

/**
 * Creates a menu bar to allow the user to interact with and manipulate certain properties.
 * @author Nick Ames
 * @author Richard Hemingway
 * @author Tim Loverin
 * @author Yeonil Yoo
 * @version 6/3/2014
 */
public class MenuBar extends JMenuBar {
   
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenu fileMenu;
    private JMenu helpMenu;

    private Author author;
    private Controller ctrl;

    //private final String ABOUT
    public MenuBar(Controller ctrl, Author author) {
        this.ctrl = ctrl;
        this.author = author;

        fileSetup();
        helpSetup();

       add(fileMenu);
       add(helpMenu);
    }

    private void fileSetup() {
        fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        final JMenuItem submitFile = new JMenuItem("Submit Paper");
        fileMenu.add(submitFile);
        submitFile.setMnemonic(KeyEvent.VK_S);
        submitFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //SubmitDialog s = new SubmitDialog();
                //String path = s.getFilePath();
                //ctrl.addPaper(author, path);
            }
        });
    }

    private void helpSetup() {
        helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        final JMenuItem aboutHelp = new JMenuItem("About");
        helpMenu.add(aboutHelp);
        aboutHelp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Version 1.0 Author Team5:.", "About", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}
