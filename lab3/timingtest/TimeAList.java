package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        AList<Integer> Ns = new AList<Integer>();
        AList<Double> times = new AList<Double>();
        AList<Integer> opCounts = new AList<Integer>();
        for (int i = 1; i <= 128; i *= 2) {
            testOnce(i * 1000, Ns, times, opCounts);
        }
        printTimingTable(Ns, times, opCounts);
    }

    public static void testOnce(int n, AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        AList<Integer> testList = new AList<Integer>();
        Stopwatch sw = new Stopwatch();
        for (int i = 0; i < n; i++) {
            testList.addLast(i);
        }
        double timeInSeconds = sw.elapsedTime();
        Ns.addLast(n);
        opCounts.addLast(n);
        times.addLast(timeInSeconds);
    }
}
