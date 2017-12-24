import junit.framework.TestCase;
import org.junit.Assert;

public class SequentialSearchSTTest extends TestCase {
    private static SequentialSearchST<String, Integer> getST() {
        // Generate a symbol table for the test cases
        SequentialSearchST<String, Integer> tab = new SequentialSearchST<>();
        tab.put("a", 1);
        tab.put("b", 2);
        tab.put("c", 3);

        return tab;
    }


    public static void testSize() {
        // Make sure .size() works correctly
        SequentialSearchST<String, Integer> tab = getST();
        assertEquals(tab.size(), 3);
    }

    public static void testDelete() {
        SequentialSearchST<String, Integer> tab = getST();
        tab.delete("c");
        assertEquals(tab.size(), 2);
    }


    public static void testKeys() {
        SequentialSearchST<String, Integer> tab = getST();
        tab.delete("b");
        Iterable<String> keys = tab.keys();
        int i = 0;

        // Recall that our put() methods always places new items as first
        for (String k: keys) {
            if (i == 0) {
                System.out.println(k);
                assertTrue(k.equals("c"));
            } else {
                System.out.println(k);
                assertTrue(k.equals("a"));
            }

            i++;
        }
    }
}
