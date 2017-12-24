import java.util.ArrayList;

/** Exercise 3.1.5: Implement size(), delete(), and keys()
 *
 * Implementation notes: size is cached
 */
public class SequentialSearchST<Key, Value> {
    private Node first;     // First linked list node
    private int N = 0;      // Number of items

    private class Node
    {
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next)
        {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public Value get(Key key)
    {   // Search for key and return its associated value
        for (Node x = first; x != null; x = x.next)
            if (key.equals(x.key))
                return x.val;   // search hit
        return null;            // search miss
    }

    public void put(Key key, Value val)
    {   // Search for key. Update value if found; grow table if new.
        for (Node x = first; x != null; x = x.next)
            if (key.equals(x.key)) { x.val = val; return; }

        // Key not found: add new node
        first = new Node(key, val, first);
        N++;
    }

    public void delete(Key key) {
        // Eager delete key if it exists; do nothing otherwise

        // Edge case: first node is key to be deleted
        if (key.equals(first.key)) {
            first = first.next;
            N--;
            return;
        }

        Node prev = first;
        for (Node x = first.next; x != null; x = x.next) {
            if (key.equals(x.key)) {
                prev.next = x.next; // Previously was prev.next = x
                N--;
                return;
            }

            prev = x;
        }
    }

    public int size() { return N; }

    public Iterable<Key> keys() {
        ArrayList<Key> key_list = new ArrayList<Key>();
        for (Node x = first; x != null; x = x.next) {
            key_list.add(x.key);
        }
        return key_list;
    }
}
