public class Deque<Item> {
    private DoubleNode first;
    private DoubleNode last;
    private int numItems;
    
    private class DoubleNode {
        Item item;
        DoubleNode next;
        DoubleNode before;
    }
    
    // Double-ended queue constructor: Create an empty queue
    Deque() {
        first = null;
        last = null;
        numItems = 0;
    }
    
    public boolean isEmpty() { return first == null; }    
    public int size() { return numItems; }
    
    // Populate an empty list
    private void addFirst(Item item) {
        first = new DoubleNode();
        first.item = item;
        first.next = null;
        first.before = null;
        
        last = first;
    }
    
    public void pushLeft(Item item) {
        if (isEmpty()) { addFirst(item); }
        else {
            // Store reference to new node in a temporary variable
            DoubleNode new_node = new DoubleNode();
            new_node.item = item;
            new_node.next = first;
            new_node.before = null;
            
            // Make the necessary modifications to the current first node
            first.before = new_node;
            
            // Reassign deque's first attribute       
            first = new_node;
        }
    
        numItems += 1;
    }
    
    public void pushRight(Item item) {
        if (isEmpty()) { addFirst(item); }
        else { 
            // Store reference to new node in a temporary variable
            DoubleNode new_node = new DoubleNode();
            new_node.item = item;
            new_node.next = null;
            new_node.before = last;
            
            // Make the necessary modifications to the current last node
            last.next = new_node;

            // Reassign deque's last attribute
            last = new_node;
        }
        
        numItems += 1;
    }
    
    public Item popLeft() {
        if (isEmpty()) { return null; }
        
        // Store value of node to be popped in a temporary variable
        Item pop_node = first.item;
        
        // Make 2nd node first new node
        first = first.next;
        if (numItems > 1) { first.before = null; }
        
        numItems -= 1;
        
        return pop_node;
    }
    
    public Item popRight() {
        if (isEmpty()) { return null; }
        
        // Store value of node to be popped in a temporary variable
        Item pop_node = last.item;
        
        // Make penultimate node new last node
        last = last.before;
        if (numItems > 1) { last.next = null; }
        
        numItems -= 1;
        
        return pop_node;
    }
}