import java.lang.System;

/** An implementation of Deque that uses a resizing array internally
 *  Throws an EmptyDequeException for invalid pop()s
 */
public class ResizingArrayDeque<Item> {
    private Item[] arr = (Item[]) new Object[1];
    private int numItems;

    // Double-ended queue constructor: Create an empty queue
    ResizingArrayDeque() {
        numItems = 0;
    }

    public boolean isEmpty() { return numItems == 0; }
    public int size() { return numItems; }

    /** Reallocate the internal array to a larger one */
    private void resize(int new_size) {
        Item[] new_arr = (Item[]) new Object[new_size];
        for (int i = 0; i < new_size; i++) {
            new_arr[i] = arr[i];
        }
        arr = new_arr;
    }

    /** Make a new array with this item in the first slot */
    public void pushLeft(Item item) {
        int new_arr_size = arr.length;
        if (numItems == arr.length) { new_arr_size = 2*arr.length; }

        numItems++;
        Item[] new_arr = (Item[]) new Object[new_arr_size];
        new_arr[0] = item;

        /* Copy old array to new one
         * for (int i = 1; i < numItems; i++) {
         *    new_arr[i] = arr[i - 1];
         * }
         */
        System.arraycopy(arr, 0, new_arr, 1, new_arr_size);
        arr = new_arr;
    }

    /** Add new item to the end of the existing array */
    public void pushRight(Item item) {
        if (arr.length == numItems) { resize(2*arr.length); }
        arr[numItems] = item;
    }

    public Item popLeft() throws EmptyDequeException {
        if (isEmpty()) { throw new EmptyDequeException(); }
        Item ret = arr[0];

        // Reallocate array to exclude "popped element" element
        Item[] new_arr = (Item[]) new Object[arr.length - 1];
        System.arraycopy(arr, 1, new_arr, 0, arr.length - 1);
        arr = new_arr;

        return ret;
    }

    public Item popRight() throws EmptyDequeException {
        if (isEmpty()) { throw new EmptyDequeException(); }
        Item ret = arr[numItems - 1];

        // Reallocate array to exclude "popped element" element
        Item[] new_arr = (Item[]) new Object[arr.length - 1];
        System.arraycopy(arr, 0, new_arr, 0, arr.length - 1);
        arr = new_arr;

        return ret;
    }
}