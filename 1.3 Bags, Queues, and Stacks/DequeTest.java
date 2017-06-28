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
    private Deque SunsOutGunsOut() {
        Deque sogo = new Deque();
        
        sogo.pushRight("suns");
        sogo.pushRight("out");
        sogo.pushRight("guns");
        sogo.pushRight("out");
        
        return sogo;
    }
    
    public void testPop() {
        // popLeft() until all items gone

        String[] popped = new String[4];
        String[] correct = {
            "suns", "out", "guns", "out"
        };
        
        Deque test_deque = SunsOutGunsOut();
        
        popped[1] = test_deque.popLeft().toString();
        popped[2] = test_deque.popLeft().toString();
        popped[3] = test_deque.popLeft().toString();
        popped[4] = test_deque.popLeft().toString();
        
        Assert.assertEquals(test_deque, popped);        
    }  
}    