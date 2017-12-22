public class EmptyDequeException extends Exception {
    public EmptyDequeException() {
        super("Pop from an empty deque.");
    }
}
