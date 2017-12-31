import junit.framework.TestCase;
import org.junit.Assert;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTNonRecursiveTest extends TestCase {
    /**
     * Search Example
     * @return BST based on search example on pg. 402
     */
    private static BSTNonRecursive<String, Integer> getBST() {
        // Generate a BST for the test cases
        BSTNonRecursive<String, Integer> search_ex = new BSTNonRecursive<>();
        String[] keys = {"S", "E", "A", "R", "C", "H",
                         "E", "X", "A", "M", "P", "L", "E"};

        for (int i = 0; i < keys.length; i++) {
            search_ex.put(keys[i], i);
        }

        return search_ex;
    }

    public static void testGet() {
        // Make sure get() & put() worked
        // Values taken from example on pg. 402

        BSTNonRecursive<String, Integer> ex = getBST();
        assertTrue(ex.get("S").equals(0));
        assertTrue(ex.get("M").equals(9));
        assertTrue(ex.get("P").equals(10));
        assertTrue(ex.get("L").equals(11));
        assertTrue(ex.get("E").equals(12));
    }

    public static void testSize() {
        // This also tests that put() updates the counts correctly
        BSTNonRecursive<String, Integer> ex = getBST();
        assertEquals(ex.size(), 10);
    }

    public static void testMax() {
        BSTNonRecursive<String, Integer> ex = getBST();
        assertEquals(ex.max(), "X");
    }

    public static void testMin() {
        BSTNonRecursive<String, Integer> ex = getBST();
        assertEquals(ex.min(), "A");
    }

    public static void testFloor() {
        // Find largest key less than or equal to key
        BSTNonRecursive<String, Integer> ex = getBST();
        assertTrue(ex.floor("C").equals("C")); // Trivial case
        assertTrue(ex.floor("G").equals("E"));
        assertTrue(ex.floor("B").equals("A"));
    }

    public static void testCeiling() {
        // Find smallest key greater than or equal to key
        BSTNonRecursive<String, Integer> ex = getBST();
        assertEquals("E", ex.ceiling("D"));
    }

    public static void testKeysSimple() {
        Iterator<String> keys = getBST().keys().iterator();
        Set<String> key_set = new HashSet<>();
        while (keys.hasNext()) key_set.add(keys.next());

        assertTrue(key_set.contains("S"));
        assertTrue(key_set.contains("X"));
        assertTrue(key_set.contains("M"));

        assertFalse(key_set.contains("B"));
    }

    public static void testKeysHarder() {
        Iterator<String> keys = getBST().keys("A", "M").iterator();
        Set<String> key_set = new HashSet<>();
        while (keys.hasNext()) key_set.add(keys.next());

        assertTrue(key_set.contains("A"));
        assertTrue(key_set.contains("M"));

        assertFalse(key_set.contains("S"));
        assertFalse(key_set.contains("X"));
        assertFalse(key_set.contains("P"));
    }
}