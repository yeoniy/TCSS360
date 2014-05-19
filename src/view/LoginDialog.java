package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class LoginDialog extends JDialog {

	/**
	 * Default serial ID.
	 */
	private static final long serialVersionUID = 1L;
	private static final Dimension CENTER = Toolkit.getDefaultToolkit().getScreenSize();
	
	public LoginDialog(JFrame parentFrame) {
		super(parentFrame, "Login");
		add(new LoginPanel(null));
		setLocation(CENTER.width/2-this.getSize().width/2, CENTER.height/2-this.getSize().height/2);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setPreferredSize(new Dimension(200, 150));
		pack();
		setVisible(true);
	}
	
}
