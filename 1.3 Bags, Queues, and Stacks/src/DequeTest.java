/* Unit Tests for Rational.java

To compile:
javac -cp .;junit-4.12.jar DequeTest.java

To run tests:
java -cp .;junit-4.12.jar;hamcrest-core-1.3.jar org.junit.runner.JUnitCore DequeTest

jdb -sourcepath .;junit-4.12.jar;hamcrest-core-1.3.jar org.junit.runner.JUnitCore DequeTest
*/

import org.junit.Assert;
import junit.framework.TestCase;

public class DequeTest extends TestCase {
    // Provide a fresh copy of a Deque that stores the sequence
    // "suns", "out", "guns", "out"
    private Deque<String> SunsOutGunsOut() {
        Deque<String> sogo = new Deque<>();
        
        sogo.pushRight("suns");
        sogo.pushRight("out");
        sogo.pushRight("guns");
        sogo.pushRight("out");
        
        return sogo;
    }

    private ResizingArrayDeque<String> SunsOutGunsOut2() {
        ResizingArrayDeque<String> sogo = new ResizingArrayDeque<>();

        sogo.pushRight("suns");
        sogo.pushRight("out");
        sogo.pushRight("guns");
        sogo.pushRight("out");

        return sogo;
    }

    public void testPop1() throws EmptyDequeException {
        // popLeft() until all items gone

        Deque sogo = SunsOutGunsOut();

        for (int i = 0; i < sogo.size(); i++) {
            switch (i) {
                case 0: {
                    Assert.assertEquals(sogo.popLeft().toString(), "suns");
                    break;
                }
                case 1: {
                    Assert.assertEquals(sogo.popLeft().toString(), "out");
                    break;
                }
                case 2: {
                    Assert.assertEquals(sogo.popLeft().toString(), "guns");
                    break;
                }
                case 3: {
                    Assert.assertEquals(sogo.popLeft().toString(), "out");
                    break;
                }
            }
        }
    }

    public void testPop2() throws EmptyDequeException {
        // popLeft() until all items gone

        ResizingArrayDeque<String> sogo = SunsOutGunsOut2();

        for (int i = 0; i < sogo.size(); i++) {
            switch (i) {
                case 0: {
                    Assert.assertEquals(sogo.popLeft(), "suns");
                    break;
                }
                case 1: {
                    Assert.assertEquals(sogo.popLeft(), "out");
                    break;
                }
                case 2: {
                    Assert.assertEquals(sogo.popLeft(), "guns");
                    break;
                }
                case 3: {
                    Assert.assertEquals(sogo.popLeft(), "out");
                    break;
                }
            }
        }
    }

    /** Assert that popping from an empty deque triggers an exception */
    public void testPopEmpty() {
        ResizingArrayDeque empty = new ResizingArrayDeque();
        Boolean raised = false;

        try {
            empty.popLeft();
        }
        catch (EmptyDequeException e) {
            raised = true;
        }

        assertTrue(raised);
    }
}