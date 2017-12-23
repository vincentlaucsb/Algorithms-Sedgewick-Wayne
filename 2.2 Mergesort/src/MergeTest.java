import junit.framework.TestCase;
import org.junit.Assert;

public class MergeTest extends TestCase {

    private Integer[] _testData() {
        return new Integer[] {100, 1, 0, 99, 50, 30, 40, 30, 25, 20};
    }

    /** Test to see if our sorting algorithm works */
    public void testSort() {
        Integer[] test_data = _testData();
        Merge.sort(test_data);

        Integer[] sorted = { 0, 1, 20, 25, 30, 30, 40, 50, 99, 100 };

        for (int i = 0; i < test_data.length; i++) {
            assertEquals(sorted[i], test_data[i]);
        }
    }

    public void testSort2() {
        Integer[] test_data = _testData();
        Merge2.sort(test_data);

        Integer[] sorted = { 0, 1, 20, 25, 30, 30, 40, 50, 99, 100 };

        for (int i = 0; i < test_data.length; i++) {
            assertEquals(sorted[i], test_data[i]);
        }
    }

    public void testISort() {
        Integer[] test_data = _testData();
        ImprovedMerge.sort(test_data);

        Integer[] sorted = { 0, 1, 20, 25, 30, 30, 40, 50, 99, 100 };

        for (int i = 0; i < test_data.length; i++) {
            assertEquals(sorted[i], test_data[i]);
        }
    }
}