package deque;
import org.junit.Test;
import static org.junit.Assert.*;
import edu.princeton.cs.algs4.StdRandom;


public class myTest {

    @Test
    public void randomizedTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        LinkedListDeque<Integer> lld = new LinkedListDeque<>();

        int N = 200000;
        for (int i = 0; i < N; i++) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0 || operationNumber == 1) {
                int ranVal = StdRandom.uniform(0, 434334);
                ad.addLast(ranVal);
                lld.addLast(ranVal);
            } else if (operationNumber == 1) {
                assertEquals(ad.size(), lld.size());
            } else if (operationNumber == 2) {
                if (ad.size() == 0) {
                    continue;
                }
                int index = StdRandom.uniform(0, ad.size());
                assertEquals(ad.get(index), lld.get(index));
            } else if (operationNumber == 3) {
                if (ad.size() == 0) {
                    continue;
                }
                assertEquals(ad.removeLast(), lld.removeLast());
            }
        }
    }

}
