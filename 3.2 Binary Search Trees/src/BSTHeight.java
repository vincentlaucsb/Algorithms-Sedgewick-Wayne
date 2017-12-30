/**
 * Binary Search Tree with Non-Recursive height() Method
 *
 * Exercise 3.2.6 Implement a recursive height() method by adding a field
 * to each node. Each query should take constant time.
 *
 * Note: This implementation doesn't deal with updating height
 * once nodes are deleted
 *
 * @param <Key>
 * @param <Value>
 */
public class BSTHeight<Key extends Comparable<Key>, Value> {
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
    { return get(root, key); }

    private Value get(Node x, Key key)
    {   // Return value associated with key
        // return null if key not present
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.val;
    }

    public void put(Key key, Value val)
    {
        // Search for key. Update value if found; grow table if new.
        root = put(root, key, val, 1);
    }

    public Node put(Node x, Key key, Value val, int depth)
    {
        // Change key's value to val if key in subtree rooted at x.
        // Otherwise, add new node to subtree associating key with val.

        // Also keep tabs on how tall the tree is
        if (depth > height)
            height = depth;

        if (x == null) return new Node(key, val, depth);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left = put(x.left, key, val, depth + 1);
        else if (cmp > 0) x.right = put(x.right, key, val, depth + 1);
        else x.val = val;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public int height()
    {
        // Calculate the height of this tree
        return height;
    }
}
