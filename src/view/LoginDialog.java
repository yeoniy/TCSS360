package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class LoginDialog extends JDialog {

	/**
	 * Default serial ID.
	 */
	private static final long serialVersionUID = 1L;
	private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	
	public LoginDialog(JFrame parentFrame) {
		super(parentFrame, "Login");
		add(new LoginPanel());
		this.setPreferredSize(new Dimension(200, 150));
		setLocation(SCREEN_SIZE.width/2 - 150, SCREEN_SIZE.height/2 - 150);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		pack();
		setVisible(true);
	}
	public void pullThePlug(JFrame parentFrame) {
        WindowEvent wev = new WindowEvent(parentFrame, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
	}
}
