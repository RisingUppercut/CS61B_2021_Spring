package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    @Test
    public void testeThreeAddThreeRemove() {
        AListNoResizing<Integer> correct = new AListNoResizing<Integer>();
        BuggyAList<Integer> buggy = new BuggyAList<Integer>();

        correct.addLast(5);
        correct.addLast(10);
        correct.addLast(15);

        buggy.addLast(5);
        buggy.addLast(10);
        buggy.addLast(15);

        assertEquals(correct.size(), buggy.size());

        assertEquals(correct.removeLast(), buggy.removeLast());
        assertEquals(correct.removeLast(), buggy.removeLast());
        assertEquals(correct.removeLast(), buggy.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> buggy = new BuggyAList<>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                buggy.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int size2 = buggy.size();
            } else if (operationNumber == 2) {
                if (L.size() == 0) {
                    continue;
                }
                assertEquals(L.getLast(), buggy.getLast());
            } else if (operationNumber == 3) {
                if (L.size() == 0) {
                    continue;
                }
                assertEquals(L.removeLast(), buggy.removeLast());
            }
        }
    }
}
