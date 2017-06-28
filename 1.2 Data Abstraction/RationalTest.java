/* Unit Tests for Rational.java

To compile:
javac -cp .;junit-4.12.jar RationalTest.java

To run tests:
java -cp .;junit-4.12.jar;hamcrest-core-1.3.jar org.junit.runner.JUnitCore RationalTest
*/

import org.junit.Assert;
import junit.framework.TestCase;

public class RationalTest extends TestCase {
    private Rational x1 = new Rational(3, 5);
    private Rational x2 = new Rational(6, 10);
    private Rational y = new Rational(2, 5);
    private Rational z = new Rational(1, 2);
    
    public void testSimplify() {
        // 3/5 = 6/10
        Assert.assertEquals(x1, x2);
    }    
    
    public void testPlus() {
        // 3/5 + 2/5 = 1
        Assert.assertEquals(x1.plus(y), 1);
    }
    
    public void testMinus1() {
        // 6/10 - 2/5 = 1/5
        Rational correct = new Rational(1, 5);
        Assert.assertEquals(x2.minus(y), correct);
    }
    
    public void testMinus2() {
        // 6/10 - 3/5 = 0     
        Assert.assertEquals(x2.minus(x1), 0);
    }
    
    public void testTimes() {
        // 3/5 * 1/2 = 3/10
        Rational correct = new Rational(3, 10);
        Assert.assertEquals(x1.times(z), correct);
    }
    
    public void testDividedBy1() {
        // 6/10 divided by 1/2 = 12/10 = 6/5
        Rational correct = new Rational(6, 5);
        Assert.assertEquals(x2.dividedBy(z), correct);   
    }
    
    public void testDividedBy2() {
        // 6/10 divided by 2 = 6/20 = 3/10
        Rational two = new Rational(2, 1);
        Rational correct = new Rational(3, 10);
        Assert.assertEquals(x2.dividedBy(two), correct);
    }
}
