package view.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import view.ConferenceGui;
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
	 * Serial ID
	 */
	private static final long serialVersionUID = 1L;
	private JMenu fileMenu;
    private JMenu helpMenu;

    private ConferenceGui gui;
    private Controller ctrl; 

    //private final String ABOUT
    /**
     * Constructor
     * @param ctrl
     * @param conferenceGui
     */
    public MenuBar(Controller ctrl, ConferenceGui conferenceGui) {
        this.ctrl = ctrl;
        this.gui = conferenceGui;

        fileSetup();
        helpSetup();

       add(fileMenu);
       add(helpMenu);
    }

    /**
     * Sets up the file within the menubar.
     */
    private void fileSetup() {
        fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        final JMenuItem logout = new JMenuItem("Logout");
        final JMenuItem submitFile = new JMenuItem("Exit");
        fileMenu.add(logout);
        fileMenu.add(submitFile);
        submitFile.setMnemonic(KeyEvent.VK_X);
        logout.setMnemonic(KeyEvent.VK_L);
        logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gui.reset();
			}
        });
        submitFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	int a = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?");
            	if (a == JOptionPane.OK_OPTION) {
                    System.exit(0);
            	}
            }
        });
    }

    /**
     * sets up help within the menu bar.
     */
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
