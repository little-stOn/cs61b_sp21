package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
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
        timeGetLast();
    }

    public static void timeGetLast() {
        SLList<Integer> list = new SLList<>();
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
        for(int N = 1000 ; N <= 64000 ; N*=2){
            for(int i =0 ; i < N ; i++){
                list.addLast(i);
            }
            Stopwatch timer = new Stopwatch();
            int count = 0;
            for(int j = 0 ; j < 10000 ; j++) {
                list.getLast();
                count++;
            }
            Ns.addLast(N);
            times.addLast(timer.elapsedTime());
            opCounts.addLast(count);
        }
        printTimingTable(Ns, times, opCounts);
    }

}
