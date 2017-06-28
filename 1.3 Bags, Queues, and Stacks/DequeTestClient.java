/* Tests for DequeTestClient.java

To compile:
javac DequeTestClient.java

To run tests:
java DequeTestClient
*/

public class DequeTestClient {
    // Provide a fresh copy of a Deque that stores the sequence
    // "suns", "out", "guns", "out"
    private static Deque SunsOutGunsOut() {
        Deque sogo = new Deque();
        
        sogo.pushRight("suns");
        System.out.println(sogo.size());
        
        sogo.pushRight("out");
        System.out.println(sogo.size());
        
        sogo.pushRight("guns");
        System.out.println(sogo.size());
        
        sogo.pushRight("out");
        System.out.println(sogo.size());
        
        return sogo;
    }
    
    public static void main(String args[]) {
        // popLeft() until all items gone
        Deque test_deque = SunsOutGunsOut();
        Object pop_value = null;
        
        while (true) {
            pop_value = test_deque.popLeft();
            
            if (pop_value == null) { break; }
            
            System.out.println(pop_value);
        }
    }  
}