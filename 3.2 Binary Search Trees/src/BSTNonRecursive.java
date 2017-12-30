/**
 * Binary Search Tree with Non-Recursive get() & put()
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

    private Value get(Key key)
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
            // On 2nd loop: update counts
            if (new_node) x.N = size(x.left) + size(x.right) + 1;
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
                    if (cmp == -1) parent.left = x;
                    else parent.right = x;
                } else {
                    // End of first loop -->
                    // Begin 2nd loop to add new node + update counts
                    new_node = true;
                    x = root;
                }
            }
        }

        return x;
    }
}