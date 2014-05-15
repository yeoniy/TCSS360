import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Yeonil on 4/29/14.
 */
public class SubmitDialog extends JDialog implements Observer{

    public SubmitDialog () {

    }

    @Override
    public void update(Observable o, Object arg) {

    }

    public boolean submit(Paper paper) {

        return true;
    }
}
