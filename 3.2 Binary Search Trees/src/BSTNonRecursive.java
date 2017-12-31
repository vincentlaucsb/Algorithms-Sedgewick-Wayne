import java.util.LinkedList;
import java.util.Queue;

/**
 * Binary Search Tree with Non-Recursive Method Implementations
 *
 * Exercise 3.2.13 Give nonrecursive implementations of get() and put()
 * Exercise 3.2.14 Give nonrecursive implementations of min(), max(), floor(), ...
 *
 * @param <Key>
 * @param <Value>
 */
public class BSTNonRecursive<Key extends Comparable<Key>, Value> {
    private Node root;      // root of BST
    private int height;     // height of BST

    private class Node
    {
        private Key key;            // key
        private Value val;          // associated value
        private Node left, right;   // links to subtrees
        private int N;              // # nodes in subtree rooted here
        private int depth;          // depth of this node in the tree

        public Node(Key key, Value val, int N)
        { this.key = key; this.val=val; this.N=N; }
    }

    public int size()
    { return size(root); }

    private int size(Node x)
    {
        if (x == null) return 0;
        else           return x.N;
    }

    public Value get(Key key)
    {   // Return value associated with key
        // return null if key not present
        Node x = root;
        while (x != null)
        {
            int cmp = key.compareTo(x.key);
            if (cmp == 0) return x.val;
            else if (cmp < 0) x = x.left;
            else x = x.right;
        }
        return null;
    }

    public Node put(Key key, Value val)
    {
        // Search for key. Update value if found; grow table if new.
        Node x = root;
        Node parent = root;       // So we can update links
        boolean new_node = false; // Flag for updating counts and
                                  // inserting new node on 2nd pass

        while (x != null) {
            if (new_node) x.N += 1; // On 2nd loop: update counts
            parent = x;

            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else {
                // Update
                x.val = val;
                break;
            }

            // Potentially restart loop
            if (x == null) {
                if (new_node) {
                    x = new Node( key,val,1);

                    // Update parent links
                    if (cmp < 0) parent.left = x;
                    else parent.right = x;
                } else {
                    // End of first loop -->
                    // Begin 2nd loop to add new node + update counts
                    new_node = true;
                    x = root;
                }
            }
        }

        // Edge Case: First node is null
        if (root == null) {
            root = new Node(key, val, 1);
        }

        return x;
    }

    public Key min()
    {   // Return the smallest key
        Node x = root;
        while (x.left != null) {
            x = x.left;
        }

        return x.key;
    }

    public Key max()
    {   // Return the largest key
        Node x = root;
        while (x.right != null) {
            x = x.right;
        }

        return x.key;
    }

    public Key floor(Key key)
    {   // Find node with largest key less than or equal to key
        Node x = root;
        Node parent = root;

        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp == 0) {
                return x.key;
            } else if (cmp < 0) {
                // Key is less than x, so floor must be in LHS
                x = x.left;
            } else {
                // Key is greater than x, so floor could be in RHS
                parent = x;
                x = x.right;
            }

        }

        // Hit a null node while searching RHS
        return parent.key;
    }

    public Key ceiling(Key key)
    {   // Find smallest key greater than or equal to key
        Node x = root;
        Node parent = root;

        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp == 0) {
                return x.key;
            } else if (cmp < 0) {
                x = x.right;
            } else {
                parent = x;
                x = x.left;
            }
        }

        return parent.key;
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi)
    {   // Return all keys between lo & hi

        Queue<Node> nodes = new LinkedList<Node>();
        Queue<Key> ret = new LinkedList<Key>();
        Node x;
        nodes.add(root);

        while (!nodes.isEmpty()) {
            x = nodes.poll();

            // Get children of x
            if (x.left != null) nodes.add(x.left);
            if (x.right != null) nodes.add(x.right);

            if (lo.compareTo(x.key) <= 0 && hi.compareTo(x.key) >= 0)
                ret.add(x.key);
        }

        return ret;
    }
}