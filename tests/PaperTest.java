import exception.InvalidInputException;
import model.Paper;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

/**
 * Created by Yeonil on 5/20/14.
 */
public class PaperTest extends junit.framework.TestCase {
    @BeforeClass
    public static void testSetup() {
    }

    @AfterClass
    public static void testCleanup() {
        // Teardown for data used by the unit tests
    }

    @Test(expected = InvalidInputException.class)
    public void testExceptionIsThrown() {
        Paper tester = new Paper(new File(" "));
        try {
            tester.setAccepted(-1);
            fail();
        } catch (InvalidInputException e) {

        }
    }

    @Test
    public void testAccepted() {
        Paper tester = new Paper(new File(" "));
        try {
            tester.setAccepted(1);
        } catch (InvalidInputException e) {
            fail();
        }
        assertEquals("WAAAA", 1, tester.isAccepted());
    }
}
