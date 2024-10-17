package deque;
import org.junit.Test;
import static org.junit.Assert.*;
import edu.princeton.cs.algs4.StdRandom;


public class MyTest {

    @Test
    public void randomizedTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        LinkedListDeque<Integer> lld = new LinkedListDeque<>();

        final int N = 20000000;
        for (int i = 0; i < N; i++) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                int ranVal = StdRandom.uniform(0, 434334);
                ad.addFirst(ranVal);
                lld.addFirst(ranVal);
            } else if (operationNumber == 1) {
                if (ad.size() == 0) {
                    continue;
                }
                assertEquals(ad.removeLast(), lld.removeLast());
            } else if (operationNumber == 2) {
                if (ad.size() == 0) {
                    continue;
                }
                assertEquals(ad.removeFirst(), lld.removeFirst());
            } else {
                int ranVal = StdRandom.uniform(0, 434334);
                ad.addLast(ranVal);
                lld.addLast(ranVal);
            }
        }
    }
}
