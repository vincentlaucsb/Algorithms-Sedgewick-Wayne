import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Binary Search Tree with draw() method
 *
 * Description of Algorithm:
 * First, all nodes are inserted using put() as usual. Then, a recursive
 * setCoordinates() method is called by draw() which assigns each node
 * a position in the x-y plane. The root node is assigned the location
 * (0.5, 0.9). Initially, each (right/left) child node is placed dist
 * (right/left) of its parent, and dist below.
 *
 * If this placement causes the new nodes have the same x-coordinate
 * as some pre-existing node, then parts of the tree will be shifted
 * according to this rule:
 *  - If the new node's parent key is less than the conflicting node's key,
 *    then shiftRight() is called with the new node as argument
 *  - Otherwise, shiftLeft() is called with the new node as argument
 *
 *  shiftLeft() causes all nodes to the left and on top of the new node
 *  to be shifted left. The new node itself is untouched. shiftRight()
 *  does the converse operation.
 *
 *  To keep track of the position of nodes, an instance BST named node_pos is
 *  used. This BST is updated by calling updatePos() after every shift*() call.
 *
 *  TODO: Expand canvas when image overflows
 *
 * @param <Key>
 * @param <Value>
 */
public class BSTDraw<Key extends Comparable<Key>, Value> {
    private Node root;      // root of BST

    // Used for keeping track of where drawn nodes are (x-coordinate)
    BST<Double, Node> node_pos = new BST<Double, Node>();
    private final double dist = 0.05; // baseline distance between nodes

    private class Node
    {
        private Key key;            // key
        private Value val;          // associated value
        private Node left, right;   // links to subtrees
        private int N;              // # nodes in subtree rooted here

        // Add information for drawing
        private double x = 0.5;
        private double y = 0.9;

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
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val)
    {
        // Change key's value to val if key in subtree rooted at x.
        // Otherwise, add new node to subtree associating key with val.
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    private void setCoordinates() {
        setCoordinates(root, null);
    }

    /**
     * Set coordinates for drawing
     * Initially space children 0.1 from parent, but as children
     * get children of their own, go back up and give parents more room
     *
     * If adding a child causes two nodes to occupy the same x-position,
     * then trees will be shifted
     *
     * @param n
     */
    private void setCoordinates(Node n, Node parent) {
        // Make sure no two nodes share the same x-coordinate
        Node same_x = node_pos.get(n.x);
        if (same_x == null) {
            node_pos.put(n.x, n);
        } else {
            if (parent.key.compareTo(same_x.key) < 0) shiftRight(root, n);
            else shiftLeft(root, n);
            updatePos();
        }

        if (n.left != null) {
            n.left.x = n.x - dist;
            n.left.y = n.y - dist;
            setCoordinates(n.left, n);
        }

        if (n.right != null) {
            n.right.x = n.x + dist;
            n.right.y = n.y - dist;
            setCoordinates(n.right, n);
        }
    }

    private void updatePos() {
        node_pos = new BST<Double, Node>();
        updatePos(root);
    }

    private void updatePos(Node n) {
        node_pos.put(n.x, n);
        if (n.left != null) updatePos(n.left);
        if (n.right != null) updatePos(n.right);
    }

    private void shiftLeft(Node n, Node m) {
        // Shift n if at same x-pos or further left than m (unless n = m)
        if (n != m && n.x <= m.x) n.x -= dist;
        if (n.left != null) shiftLeft(n.left, m);
        if (n.right != null) shiftLeft(n.right, m);
    }

    private void shiftRight(Node n, Node m) {
        // Shift n at same x-pos or if further right than m (unless n = m)
        if (n != m && n.x >= m.x) n.x += dist;
        if (n.left != null) shiftRight(n.left, m);
        if (n.right != null) shiftRight(n.right, m);
    }

    private void draw() {
        // Draw this BST
        setCoordinates();
        draw(root);
    }

    public void draw(Node n) {
        // Draw this BST in the style of the textbook
        if (n.left != null) {
            StdDraw.setPenRadius(0.006);
            StdDraw.line(n.x, n.y, n.left.x, n.left.y);

            StdDraw.setPenRadius(0.005);
            StdDraw.setPenColor(Color.white);
            StdDraw.filledCircle(n.left.x, n.left.y, 0.025);

            StdDraw.setPenColor(Color.black);
            StdDraw.circle(n.left.x, n.left.y, 0.025);
            draw(n.left);
        } else { // Draw lines to missing node
            StdDraw.setPenRadius(0.01);
            StdDraw.line(n.x, n.y, n.x - dist/2, n.y - dist);
        }

        if (n.right != null) {
            StdDraw.setPenRadius(0.006);
            StdDraw.line(n.x, n.y, n.right.x, n.right.y);

            StdDraw.setPenRadius(0.005);
            StdDraw.setPenColor(Color.white);
            StdDraw.filledCircle(n.right.x, n.right.y, 0.025);

            StdDraw.setPenColor(Color.black);
            StdDraw.circle(n.right.x, n.right.y, 0.025);
            draw(n.right);
        } else { // Draw lines to missing node
            StdDraw.setPenRadius(0.01);
            StdDraw.line(n.x, n.y, n.x + dist/2, n.y - dist);
        }

        // Draw circle last to cover up lines
        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(Color.white);
        StdDraw.filledCircle(n.x, n.y, 0.025);

        StdDraw.setPenColor(Color.black);
        StdDraw.circle(n.x, n.y, 0.025);
        StdDraw.text(n.x, n.y, n.key.toString());
    }

    public static void main(String[] args) {
        // Draw "SEARCH EXAMPLE"
        BSTDraw<String, Integer> ex = new BSTDraw<>();
        // String[] keys = {"S", "E", "A", "R", "C", "H", "E", "X", "A", "M", "P", "L", "E"};
        String[] keys = {"T", "H", "I", "S",
                "I","S",  "A",
                "B", "I", "N", "A", "R", "Y",
                "S", "E", "A", "R", "C", "H",
                "T", "R", "E", "E",
                "E", "X", "A", "M", "P", "L", "E"
        };

        for (int i = 0; i < keys.length; i++)
            ex.put(keys[i], i);

        ex.draw();
    }
}